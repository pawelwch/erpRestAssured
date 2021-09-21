package pojos.employeePojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor

@ToString
public class OcrResponse {

    private String nationality;
    private String avatarUrl;
    private String fullName;
    private String gender;
    private String dateOfBirth;
    private String documentNumber;
    private String identityNumber;
    private String codeName;
    private String issuingCountry;
    private String dateOfExpire;
}
