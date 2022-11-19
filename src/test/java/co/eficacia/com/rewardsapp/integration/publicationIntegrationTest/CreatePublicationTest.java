package co.eficacia.com.rewardsapp.integration.publicationIntegrationTest;

import co.eficacia.com.rewardsapp.persistance.model.Publication;
import co.eficacia.com.rewardsapp.web.dto.PublicationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.WebApplicationContext;

import java.io.InputStreamReader;
import java.io.Reader;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreatePublicationTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @SneakyThrows
    @Test
    public void testCreatePublications() {
        PublicationDTO publicationDTO = getBasePublicationDTO();
        String body = objectMapper.writeValueAsString(publicationDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/publications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)).andExpect(status().isOk())
                .andReturn();
        PublicationDTO actualPublicationDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), PublicationDTO.class);
        assertEquals(publicationDTO.getLink(),actualPublicationDTO.getLink());
    }

    @SneakyThrows
    private PublicationDTO getBasePublicationDTO() {
        String body = parseResourceToString();
        return objectMapper.readValue(body, PublicationDTO.class);
    }

    @SneakyThrows
    private String parseResourceToString() {
        Resource resource = new ClassPathResource("publication.json");
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        }
    }

}
