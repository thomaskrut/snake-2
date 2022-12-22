import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InputMonitor implements KeyListener {



    List<Direction> directionQueue = new ArrayList<>();

    public Direction getNextDirection() {
        if (directionQueue.size() > 0) {
            return directionQueue.remove(0);
        }
        else {
            return null;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        if (e.getKeyCode() == KeyEvent.VK_UP) {
            directionQueue.add(Direction.UP);
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            directionQueue.add(Direction.DOWN);
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            directionQueue.add(Direction.LEFT);
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            directionQueue.add(Direction.RIGHT);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
