package co.eficacia.com.rewardsapp.web.dto;

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

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate birthDate;

    private boolean subscribed;

    private String phoneNumber;
}