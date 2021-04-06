package pojos.assetsPojo.assetsResponsePojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Sort {

    private List<Object> orders;
    private boolean sorted;
    private boolean unsorted;
    private boolean empty;
}
