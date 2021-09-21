package pojos.userPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.management.relation.Role;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EntireUserProfile {

    private Integer id;
    private String email;
    private String status;
    private String userType;
    //private Factory factory;
    private UserProfile userProfile;
    private String createdAt;
    private String lastPasswordChange;
    private Integer passwordExpirationInDays;
    private List<Role> roles = null;
    private Boolean shouldResetPassword;
    private String mfaStatus;

}
