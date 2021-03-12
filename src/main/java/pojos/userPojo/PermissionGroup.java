package pojos.userPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PermissionGroup {

    private Integer id;
    private String name;
    private String description;
    private String subject;
    private Boolean readOnly;
}
