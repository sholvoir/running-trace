package cn.micit.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "RUNNING_ANALYSIS")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RunningInformation {
    enum HealthWarningLevel {
        LOW, NORMAL, HIGH;
    }

    @Id
    @GeneratedValue
    private long id;
    private String runningId;
    private double latitude;
    private double longitude;
    private double runningDistance;
    private int totalRunningTime;
    private int heartRate = 0;
    private Date timestamp;
    private String userName;
    private String address;

    public HealthWarningLevel getHealthWarningLevel() {
        if (heartRate <= 75) return HealthWarningLevel.LOW;
        if (heartRate <= 120) return HealthWarningLevel.NORMAL;
        return HealthWarningLevel.HIGH;
    };
}
