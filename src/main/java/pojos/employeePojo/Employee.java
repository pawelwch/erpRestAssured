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
public class Employee {

    private int id;
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
    private FactoryPojo factory;
    private int roomId;
    private String status;
    private CorrespondenceAddress correspondenceAddress;
    private ResidenceAddress residenceAddress;
    private String locker;

public static CreateEmployeeCommand fromCommand(Employee employee) {
    return CreateEmployeeCommand.builder()
            .draftId(employee.getDraftId())
            .fullName(employee.getFullName())
            //.draftId((employee.getDraftId()))
            .factoryId(employee.getFactory().getId())
            .roomId(employee.getRoomId())
            .status(employee.getStatus())
            .emailAddress(employee.getEmailAddress())
            .localPhone(employee.getLocalPhone())
            .avatarUrl(employee.getAvatarUrl())
            .nationality(employee.getNationality())
            .foreignPhone(employee.getForeignPhone())
            .build();
}

}


