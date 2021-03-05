package pojos.userPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Factory {

    private Integer id;
    private String company;
    private String codeName;
    private String street;
    private City city;
    private String postcode;
    private String streetNumber;
    private String houseNumber;
    private String createdAt;
}
