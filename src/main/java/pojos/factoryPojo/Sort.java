package pojos.factoryPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Sort {

    private boolean sorted;
    private boolean unsorted;
    private boolean empty;
}
