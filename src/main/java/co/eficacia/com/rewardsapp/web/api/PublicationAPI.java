package co.eficacia.com.rewardsapp.web.api;

import co.eficacia.com.rewardsapp.web.dto.PublicationDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/publications")
public interface PublicationAPI {

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-publication")
    PublicationDTO createPublication(@RequestBody PublicationDTO publicationDTO);

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/get-publication")
    PublicationDTO getPublication(@RequestParam UUID id);

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/get-all-publications")
    List<PublicationDTO> getPublications();

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-publication")
    PublicationDTO updatePublication(@RequestParam UUID id, @RequestBody PublicationDTO publicationDTO);

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-publication")
    boolean deletePublication(@RequestParam UUID id);
}
