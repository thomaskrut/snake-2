import java.awt.*;
import java.util.List;

public class Collisions {

    public static boolean checkForCollision(GameObject o1, GameObject o2) {

        return false;

    }

    public static boolean checkForCollision(Point point, GameObject o) {

        List<Point> objectPoints = o.getPositionsList();

        for (Point objectPoint : objectPoints) {
            if (objectPoint.equals(point)) {
                return true;
            }
        }

        return false;
    }

}
