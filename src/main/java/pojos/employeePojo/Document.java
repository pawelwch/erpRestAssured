package pojos.employeePojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor

@ToString
public class Document {

    private int id;
    private String documentType;
    private String documentName;
    private String link;
}
