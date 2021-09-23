package pojos.assetsPojo.factoryRequestPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FactoryPojo {

        private int id;
        private String company;
        private String codeName;
        private String street;
        private City city;
        private String postcode;
        private String streetNumber;
        private String houseNumber;
        private Date createdAt;
        private String addressId;

}
