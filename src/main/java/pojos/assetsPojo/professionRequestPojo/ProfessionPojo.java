package pojos.assetsPojo.professionRequestPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProfessionPojo {

    private Integer id;
    private String name;
    private String status;
    private String createdAt;
    private Boolean deleted;
    private String updatedAt;

}
