package pojos.employeePojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EmployeePojo {

    private int draftId;
    private OcrResponse ocrResponse;
    private String nationality;
    private String avatarUrl;
    private String fullName;
    private String gender;
    private String dateOfBirth;
    private String documentNumber;
    private String identityNumber;
    private String localPhone;
    private String foreignPhone;
    private String emailAddress;
    private Document document;
    private Flat flat;
    private Room room;
    private String street;
    private String city;
    private String country;
    private String postcode;
    private String streetNumber;
    private String houseNumber;
    private Integer factoryId;
    private Integer roomId;
    private Integer correspondenceAddressId;
}
