package pojos.userManagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserActivateCommand {

    private String email;
    private String token;
    private String password;
    private String retypedPassword;

}
