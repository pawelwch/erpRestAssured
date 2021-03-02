package pojos.factoryPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Factory {

    private int totalPages;
    private int totalElements;
    private int size;
    private List<Content> content;
    private int number;
    private Sort sort;
    private int numberOfElements;
    private boolean last;
    private boolean first;
    private Pageable pageable;
    private boolean empty;
}
