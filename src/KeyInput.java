import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyInput implements KeyListener {



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

        switch (e.getKeyCode()) {
            case (KeyEvent.VK_ESCAPE) -> System.exit(0);
            case (KeyEvent.VK_UP) -> directionQueue.add(Direction.NORTH);
            case (KeyEvent.VK_DOWN) -> directionQueue.add(Direction.SOUTH);
            case (KeyEvent.VK_LEFT) -> directionQueue.add(Direction.WEST);
            case (KeyEvent.VK_RIGHT) -> directionQueue.add(Direction.EAST);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
