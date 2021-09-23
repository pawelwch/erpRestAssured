package pojos.assetsPojo.factoryRequestPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FactoryCommand {

    private String company;
    private String codeName;
    private String addressId;
    private String houseNumber;


//    public FactoryCommand(FactoryPojo pojo){
//        this.company = pojo.getCompany();
//        this.codeName = pojo.getCodeName();
//        this.addressId = pojo.getAddressId();
//        this.houseNumber = pojo.getHouseNumber();
//    }

    // builder with hardcoded address
//    @Builder
//    public static FactoryCommand factoryBuilder(String company, String codeName, String houseNumber){
//        return new FactoryCommand(company, codeName, "16.27278/50.730396", houseNumber);
//    }
}
