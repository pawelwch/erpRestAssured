package pojos.factoryPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class City {

    private int id;
    private String name;
    private int latitude;
    private int longitude;
    private String country;
}
