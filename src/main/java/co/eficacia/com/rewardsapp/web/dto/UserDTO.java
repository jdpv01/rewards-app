package co.eficacia.com.rewardsapp.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firstName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String gender;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate birthDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean subscribed;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phoneNumber;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int currentPoints;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int redeemedPoints;
}