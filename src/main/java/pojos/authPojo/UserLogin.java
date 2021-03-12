package pojos.authPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserLogin {

    private String email;
    private String password;
    private String accessToken;
}
