package models;

import java.util.Observable;

import static tools.MathTools.*;

public class RobotModel extends Observable {
    private volatile double m_robotPositionX = 100;
    private volatile double m_robotPositionY = 100;
    private volatile double m_robotDirection = 0;
    public static final double maxVelocity = 0.1;
    public static final double maxAngularVelocity = 0.001;
    private final TargetModel targetModel;

    public RobotModel(TargetModel targetModel) {
        this.targetModel = targetModel;
    }

    public double getM_robotPositionX() {
        return m_robotPositionX;
    }

    public double getM_robotPositionY() {
        return m_robotPositionY;
    }

    public double getM_robotDirection() {
        return m_robotDirection;
    }

    public void moveRobot(double velocity, double angularVelocity, double duration) {
        velocity = applyLimits(velocity, 0, maxVelocity);
        angularVelocity = applyLimits(angularVelocity, -maxAngularVelocity, maxAngularVelocity);
        double newX = m_robotPositionX;
        double newY = m_robotPositionY;
        double newDirection = m_robotDirection;
        if (angularVelocity == 0) {
            newX = m_robotPositionX + velocity * duration * Math.cos(m_robotDirection);
            newY = m_robotPositionY + velocity * duration * Math.sin(m_robotDirection);
        } else {
            newDirection = asNormalizedRadians(m_robotDirection + angularVelocity * duration);
        }
        m_robotPositionX = newX;
        m_robotPositionY = newY;
        m_robotDirection = newDirection;

        setChanged();
        notifyObservers(this);
    }

    public void updateRobotPosition() {
        double distance = distance(targetModel.getTargetX(), targetModel.getTargetY(), m_robotPositionX, m_robotPositionY);
        if (distance < 0.5) return;

        double angleToTarget = angleTo(m_robotPositionX, m_robotPositionY, targetModel.getTargetX(), targetModel.getTargetY());
        double angularVelocity = 0;

        if (angleToTarget > m_robotDirection + 0.01) angularVelocity = maxAngularVelocity;

        if (angleToTarget < m_robotDirection - 0.01) angularVelocity = -maxAngularVelocity;

        moveRobot(maxVelocity, angularVelocity, 10);

    }
}
