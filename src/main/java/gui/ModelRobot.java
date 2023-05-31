package gui;

import java.util.Observable;

import static gui.GameVisualizer.*;
import static tools.MathTools.*;

public class ModelRobot extends Observable {
    private volatile double m_robotPositionX = 100;
    private volatile double m_robotPositionY = 100;
    private volatile double m_robotDirection = 0;

    public double getM_robotPositionX() {
        return m_robotPositionX;
    }

    public void setM_robotPositionX(double positionX) {
        m_robotPositionX = positionX;
    }

    public double getM_robotPositionY() {
        return m_robotPositionY;
    }

    public void setM_robotPositionY(double positionY) {
        m_robotPositionY = positionY;
    }

    public double getM_robotDirection() {
        return m_robotDirection;
    }

    public void setM_robotDirection(double direction) {
        m_robotDirection = direction;
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
        setM_robotPositionX(newX);
        setM_robotPositionY(newY);
        setM_robotDirection(newDirection);

        setChanged();
        notifyObservers();
    }

}
