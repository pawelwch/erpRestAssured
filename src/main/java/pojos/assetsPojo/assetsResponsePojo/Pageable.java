package pojos.assetsPojo.assetsResponsePojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Pageable {

    private int page;
    private int size;
    private Sort sort;
    private int offset;
    private int pageSize;
    private int pageNumber;
    private boolean paged;
    private boolean unpaged;
}
