package pojos.assetsPojo.assetsResponsePojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Content {

    private int id;
    private String name;
    private String status;
    private Date createdAt;
    private boolean deleted;
    private Date updatedAt;
}
