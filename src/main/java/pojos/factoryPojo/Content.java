package pojos.factoryPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Content {

    private int id;
    private String company;
    private String codeName;
    private String street;
    private City city;
    private String postcode;
    private String streetNumber;
    private String houseNumber;
    private Date createdAt;
}
