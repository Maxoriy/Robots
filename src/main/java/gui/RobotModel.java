package gui;

import java.util.Observable;

import static gui.GameVisualizer.*;
import static tools.MathTools.*;

public class RobotModel extends Observable {
    private volatile double m_robotPositionX = 100;
    private volatile double m_robotPositionY = 100;
    private volatile double m_robotDirection = 0;

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
        notifyObservers();
    }

}
