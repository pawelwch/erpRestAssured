package pojos.employeePojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import pojos.assetsPojo.factoryRequestPojo.City;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ResidenceAddress {

    private String id;
    private City city;
    private String streetNumber;
    private String houseNumber;
    private String postCode;
    private String street;
    private String addressString;
}
