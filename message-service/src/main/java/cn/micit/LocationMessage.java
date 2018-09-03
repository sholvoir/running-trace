package cn.micit;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationMessage {
    private String userName;
    private String location;
    private String zip;
}
