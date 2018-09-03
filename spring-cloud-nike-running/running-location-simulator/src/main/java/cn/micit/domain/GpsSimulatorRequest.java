package cn.micit.domain;

import lombok.Data;

@Data
public class GpsSimulatorRequest {
    private int reportInterval = 500;
    private String runningId;
    private double speed;
    private boolean move = true;
    private boolean exportPositionsToMessaging = true;
    private RunnerStatus runnerStatus = RunnerStatus.NONE;
    private String polyline;
    private MedicalInfo medicalInfo;
}
