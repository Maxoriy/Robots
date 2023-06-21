package models;

import java.awt.*;

public class TargetModel {
    private volatile int m_targetPositionX = 150;
    private volatile int m_targetPositionY = 100;

    public void setTargetPosition(Point p) {
        m_targetPositionX = p.x;
        m_targetPositionY = p.y;
    }

    public int getTargetX() {
        return m_targetPositionX;
    }

    public int getTargetY() {
        return m_targetPositionY;
    }
}
