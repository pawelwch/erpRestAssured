package pojos.employeePojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import pojos.assetsPojo.factoryRequestPojo.FactoryPojo;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateEmployeeCommand {

    private int draftId;
    private String fullName;
    private String gender;
    private String dateOfBirth;
    private String documentNumber;
    private Document document;
    private String identityNumber;
    private String nationality;
    private int roomId;
    private String status;
    private String foreignPhone;
    private String localPhone;
    private String emailAddress;
    private String avatarUrl;
    private String rfid;
    private String fullAccessToLaborMarket;
    private String stayEndDate;
    private int factoryId;
    //private int lockerId;
    private String residenceAddressId;
    private String residenceHouseNumber;
    private String correspondenceAddressId;
    private String correspondenceHouseNumber;

}

