package co.eficacia.com.rewardsapp.web.api;

import co.eficacia.com.rewardsapp.web.dto.PublicationDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/publications")
public interface PublicationAPI {

    @PostMapping("/create-publication")
    PublicationDTO createPublication(@RequestBody PublicationDTO publicationDTO);

    @GetMapping("/get-publication/{id}")
    PublicationDTO getPublication(@PathVariable UUID id);

    @GetMapping("/get-all-publications")
    List<PublicationDTO> getPublications();

    @PutMapping("/update-publication/{id}")
    PublicationDTO updatePublication(@PathVariable UUID id, @RequestBody PublicationDTO publicationDTO);

    @DeleteMapping("/delete-publication/{id}")
    boolean deletePublication(@PathVariable UUID id);
}
