package pojos.employeePojo;

import pojos.assetsPojo.factoryRequestPojo.City;

import java.util.Date;

public class EmployeeBuilder {

    private int documentId;
    private String documentType;
    private String documentName;
    private String link;

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

    private int id;
    private String flatStreet;
    private City flatCity;
    private String flatPostcode;
    private String flatStreetNumber;
    private String flatHouseNumber;
    private Date createdAt;
    private int roomCount;
    private int availablePlaces;
    private Date updatedAt;

    private String ocrNationality;
    private String ocrAvatarUrl;
    private String ocrFullName;
    private String ocrGender;
    private String ocrDateOfBirth;
    private String ocrDocumentNumber;
    private String ocrIdentityNumber;
    private String ocrCodeName;
    private String ocrIssuingCountry;
    private String ocrDateOfExpire;

    private int roomPlaces;
    private int roomTakenPlaces;
    private String roomType;
    private Date roomUpdatedAt;

}
