package pojos.assetsPojo.factoryRequestPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FactoryCommand {

    private String company;
    private String codeName;
    private String street;
    private int cityId;
    private String postcode;
    private String streetNumber;
    private String houseNumber;


    public FactoryCommand(FactoryPojo pojo){
        this.company = pojo.getCompany();
        this.codeName = pojo.getCodeName();
        this.street = pojo.getStreet();
        this.cityId = pojo.getCity().getId();
        this.postcode = pojo.getPostcode();
        this.streetNumber = pojo.getStreetNumber();
        this.houseNumber = pojo.getHouseNumber();
    }

    @Builder
    public static FactoryCommand factoryBuilder(String company, String codeName, String street, int cityId, String postcode, String streetNumber, String houseNumber){
        return new FactoryCommand(company, codeName, street, cityId, postcode, streetNumber, houseNumber);
    }
}
