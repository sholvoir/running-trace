package cn.micit.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Embeddable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Embeddable
@Data
public class UnitInfo {
    private final String runningId;
    private String bandMake;
    private String customerName;
    private String unitNumber;

    public UnitInfo() {
        runningId = "";
    }

    public UnitInfo(String runningId) {
        this.runningId = runningId;
    }

    public UnitInfo(String runningId,
                    String bandMake,
                    String customerName,
                    String unitNumber) {
        this.runningId = runningId;
        this.bandMake = bandMake;
        this.customerName = customerName;
        this.unitNumber = unitNumber;
    }
}
