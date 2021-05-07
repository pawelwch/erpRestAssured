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
}
