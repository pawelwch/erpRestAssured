package pojos.userPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Role {

    private Integer id;
    private String title;
    private String slug;
    private Integer range;
    private List<PermissionGroup> permissionGroups = null;

}
