package co.eficacia.com.rewardsapp.service.impl;

import co.eficacia.com.rewardsapp.constant.InvoiceErrorCode;
import co.eficacia.com.rewardsapp.persistance.model.Invoice;
import co.eficacia.com.rewardsapp.persistance.model.Promotion;
import co.eficacia.com.rewardsapp.persistance.model.Store;
import co.eficacia.com.rewardsapp.persistance.model.User;
import co.eficacia.com.rewardsapp.persistance.repository.InvoiceRepository;
import co.eficacia.com.rewardsapp.service.*;
import co.eficacia.com.rewardsapp.utils.FileHandler;
import co.eficacia.com.rewardsapp.utils.Timestamp;
import co.eficacia.com.rewardsapp.web.error.ObjectError;
import co.eficacia.com.rewardsapp.web.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    public final static String INVOICES_FOLDER = "invoices/";

    private final InvoiceRepository invoiceRepository;

    private final UserService userService;

    private final StoreService storeService;

    private final PromotionService promotionService;

    @Override
    public Invoice createInvoice(MultipartFile invoiceImage, UUID storeId, List<UUID> promotionIdList) {
        Invoice invoice = new Invoice();

        //One-To-Many User-Invoice relation
        User user = userService.getSignedInUser();
        user.getInvoiceList().add(invoice);
        invoice.setUser(user);

        //One-To-Many Store-Invoice relation
        Store store = storeService.getStore(storeId);
        store.getInvoiceList().add(invoice);
        invoice.setStore(store);

        //Many-To-Many promotion-invoice relation
        List<Promotion> promotionList = new ArrayList<>();
        promotionIdList.forEach(id -> promotionList.add(promotionService.getPromotion(id)));
        promotionList.forEach(promotion -> promotion.getInvoiceList().add(invoice));
        invoice.setPromotionList(promotionList);

        invoice.setState(Invoice.PENDING);
        int totalOfferedPoints = getTotalOfferedPoints(promotionList);
        user.setPendingPoints(user.getPendingPoints()+totalOfferedPoints);
        System.out.println(user.getPendingPoints());
        invoice.setPendingPoints(totalOfferedPoints);
        invoice.setTimestamp(ZonedDateTime.now(Timestamp.ZONE_ID));
        invoice.setImage(FileHandler.upload(invoiceImage, INVOICES_FOLDER).toString());
        return invoiceRepository.save(invoice);
    }

    private Integer getTotalOfferedPoints(List<Promotion> promotionList) {
        int totalOfferedPoints = 0;
        for (Promotion promotion : promotionList) {
            totalOfferedPoints += promotion.getOfferedPoints();
        }
        return totalOfferedPoints;
    }

    public Invoice processInvoice(UUID invoiceId, boolean decision) {
        Invoice invoice = getInvoice(invoiceId);
        User user = invoice.getUser();
        if(decision){
            invoice.setState(Invoice.APPROVED);
            userService.accumulatePoints(user, invoice);
            invoice.getPromotionList().forEach(promotionService::reducePromotionQuantity);
            user.setPendingPoints(user.getPendingPoints()-getTotalOfferedPoints(invoice.getPromotionList()));
        }else{
            invoice.setState(Invoice.REJECTED);
            user.setPendingPoints(user.getPendingPoints()-getTotalOfferedPoints(invoice.getPromotionList()));
        }
        return invoice;
    }

    @Override
    public Invoice getInvoice(UUID id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        if (optionalInvoice.isPresent())
            return optionalInvoice.get();
        throw new CustomException(HttpStatus.NOT_FOUND, new ObjectError(InvoiceErrorCode.CODE_01, InvoiceErrorCode.CODE_01.getMessage()));
    }

    @Override
    public List<Invoice> getInvoices() {
        return StreamSupport.stream(invoiceRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public List<Invoice> getUserInvoices(UUID userId) {
        return userService.getUser(userId).getInvoiceList();
    }

    @Override
    public Invoice updateInvoice(Invoice Invoice) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(Invoice.getId());
        if (optionalInvoice.isPresent())
            return invoiceRepository.save(Invoice);
        throw new CustomException(HttpStatus.NOT_FOUND, new ObjectError(InvoiceErrorCode.CODE_01, InvoiceErrorCode.CODE_01.getMessage()));
    }

    @Override
    public boolean deleteInvoice(UUID id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        if (optionalInvoice.isPresent()) {
            invoiceRepository.delete(optionalInvoice.get());
            return true;
        }
        throw new CustomException(HttpStatus.NOT_FOUND, new ObjectError(InvoiceErrorCode.CODE_01, InvoiceErrorCode.CODE_01.getMessage()));
    }
}
