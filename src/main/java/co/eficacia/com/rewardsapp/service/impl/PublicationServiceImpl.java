package co.eficacia.com.rewardsapp.service.impl;

import co.eficacia.com.rewardsapp.constant.PublicationErrorCode;
import co.eficacia.com.rewardsapp.web.error.ObjectError;
import co.eficacia.com.rewardsapp.web.error.exception.CustomException;
import co.eficacia.com.rewardsapp.persistance.model.Publication;
import co.eficacia.com.rewardsapp.persistance.repository.PublicationRepository;
import co.eficacia.com.rewardsapp.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {

    public final static String PUBLICATIONS_FOLDER = "publications/";
    private final PublicationRepository publicationRepository;

    @Override
    public Publication createPublication(Publication publication) {
        return publicationRepository.save(publication);
    }

    @Override
    public Publication getPublication(UUID id) {
        Optional<Publication> optionalPublication = publicationRepository.findById(id);
        if(optionalPublication.isPresent())
            return optionalPublication.get();
        throw new CustomException(HttpStatus.NOT_FOUND, new ObjectError(PublicationErrorCode.CODE_01, PublicationErrorCode.CODE_01.getMessage()));
    }

    @Override
    public List<Publication> getPublications() {
        return StreamSupport.stream(publicationRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Publication updatePublication(Publication publication) {
        Optional<Publication> optionalPublication = publicationRepository.findById(publication.getId());
        if(optionalPublication.isPresent())
            return publicationRepository.save(publication);
        throw new CustomException(HttpStatus.NOT_FOUND, new ObjectError(PublicationErrorCode.CODE_01, PublicationErrorCode.CODE_01.getMessage()));
    }

    @Override
    public boolean deletePublication(UUID id) {
        Optional<Publication> optionalPublication = publicationRepository.findById(id);
        if(optionalPublication.isPresent()) {
            publicationRepository.delete(optionalPublication.get());
            return true;
        }
        throw new CustomException(HttpStatus.NOT_FOUND, new ObjectError(PublicationErrorCode.CODE_01, PublicationErrorCode.CODE_01.getMessage()));
    }
}