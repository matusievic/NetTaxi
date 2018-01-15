package by.tc.web.domain.point;

import java.io.Serializable;

public class Point implements Serializable {
    private static final long serialVersionUID = 250354050051348684L;
    private String sqlTypeName;
    private float x;
    private float y;

    public Point() {}

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }

        Point point = (Point) obj;
        if (x != point.x) { return false; }
        if (y != point.y) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = (int) x;
        hash = 31 * hash + (int) y;
        return hash;
    }
}
