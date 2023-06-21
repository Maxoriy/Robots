package gui;


import models.RobotModel;

import java.util.Timer;
import java.util.TimerTask;

public class RobotMovementProducer {

    public RobotMovementProducer(RobotModel robotModel) {
        Timer timer = new Timer("events generator", true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                robotModel.updateRobotPosition();
            }
        }, 0, 10);
    }

}
