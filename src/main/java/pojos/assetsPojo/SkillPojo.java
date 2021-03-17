package pojos.assetsPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SkillPojo {

    private Integer id;
    private String name;
    private String status;
    private Date createdAt;
    private Boolean deleted;
    private Date updatedAt;
    private SkillGroup group;
}
