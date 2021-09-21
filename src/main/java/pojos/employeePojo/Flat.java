package pojos.employeePojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import pojos.assetsPojo.factoryRequestPojo.City;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor

@ToString
public class Flat {

    private int id;
    private String street;
    private City city;
    private String postcode;
    private String streetNumber;
    private String houseNumber;
    private Date createdAt;
    private int roomCount;
    private int availablePlaces;
    private Date updatedAt;
}
