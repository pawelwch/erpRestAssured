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
public class User {
    private List<Integer> roleIds;
    private String phone;
    private String factoryId;
    private String fullName;
    private String avatar;
    private String type;
    private String email;
    private UserTypes userTypes;
}
