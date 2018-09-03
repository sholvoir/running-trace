package cn.micit.task;

import cn.micit.domain.*;
import cn.micit.service.PositionService;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class LocationSimulator implements Runnable {
    private long id;
    private PositionService positionService;
    private AtomicBoolean cancel = new AtomicBoolean();
    private double speedImMps;
    private boolean shouldMove;
    private int reportInterval = 500;
    private boolean exportPositionToMessaging = true;
    private PositionInfo currentPosition;
    private List<Leg> legs;
    private RunnerStatus runnerStatus = RunnerStatus.NONE;
    private String runningId;
    private Point startPoint;
    private Date executionStartTime;
    private MedicalInfo medicalInfo;

    public LocationSimulator(GpsSimulatorRequest gpsSimulatorRequest) {
        this.shouldMove = gpsSimulatorRequest.isMove();
        this.exportPositionToMessaging = gpsSimulatorRequest.isExportPositionsToMessaging();
        this.setSpeed(gpsSimulatorRequest.getSpeed());
        this.reportInterval = gpsSimulatorRequest.getReportInterval();
        this.runningId = gpsSimulatorRequest.getRunningId();
        this.runnerStatus = gpsSimulatorRequest.getRunnerStatus();
        this.medicalInfo = gpsSimulatorRequest.getMedicalInfo();
    }

    public void setSpeed(double speed) {
        this.speedImMps = speed;
    }

    @Override
    public void run() {
        try {
            executionStartTime = new Date();
            while (!Thread.interrupted()) {
                if (cancel.get()) break;
                long startTime = new Date().getTime();
                if (currentPosition != null) {
                    if (shouldMove) {
                        moveRunningLocation();
                        currentPosition.setSpeed(speedImMps);
                    } else {
                        currentPosition.setSpeed(0);
                    }
                    currentPosition.setRunnerStatus(this.runnerStatus);
                    final MedicalInfo medicalInfoToUse;
                    switch (this.runnerStatus) {
                        case SUPPLY_NOW:
                        case SUPPLY_SOON:
                        case STOP_NOW:
                            medicalInfoToUse = this.medicalInfo;
                            break;
                        default:
                            medicalInfoToUse = null;
                            break;
                    }
                    final Point p = this.currentPosition.getPosition();
                    final CurrentPosition currentPosition = new CurrentPosition(
                            this.currentPosition.getRunningId(),
                            new Point(p.getLatitude(), p.getLongitude()),
                            this.currentPosition.getRunnerStatus(),
                            this.currentPosition.getSpeed(),
                            this.currentPosition.getLeg().getHeading(),
                            medicalInfoToUse);
                }
                sleep(startTime);
            }
        } catch (InterruptedException e) {}
        destroy();
    }

    private void destroy() {
        currentPosition = null;
    }

    private void sleep(long startTime) throws InterruptedException {
        long endTime = new Date().getTime();
        long elapsedTime = endTime - startTime;
        long t = reportInterval - elapsedTime;
        long sleepTime = t > 0 ? t : 0;
        Thread.sleep(sleepTime);
    }

    private void moveRunningLocation() {
        double distance = speedImMps * reportInterval / 1000.0;
        double distanceFromStart = currentPosition.getDistanceFromStart() + distance;
        double excess;
        for (int i = currentPosition.getLeg().getId(); i < legs.size(); i++) {
            Leg currentLeg = legs.get(i);
            double p = distanceFromStart - currentLeg.getLength();
            excess = p > 0 ? p : 0;
            if (Double.doubleToLongBits(excess) == 0) {
                currentPosition.setDistanceFromStart(distanceFromStart);
                currentPosition.setLeg(currentLeg);
                //@TODO:
                Point newPosition = null;
                currentPosition.setPosition(newPosition);
                return;
            }
            distanceFromStart = excess;
        }
        setStartPosition();
    }

    private void setStartPosition() {
        currentPosition = new PositionInfo();
        currentPosition.setRunningId(this.runningId);
        Leg leg = legs.get(0);
        currentPosition.setLeg(leg);
        currentPosition.setPosition(leg.getStartPosition());
        currentPosition.setDistanceFromStart(0);
    }
}
