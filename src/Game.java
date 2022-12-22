import javax.swing.*;
import java.awt.*;

public class Game {

    JFrame f = new JFrame();

    InputMonitor inputMonitor;

    GamePanel gamePanel;

    Snake snake;

    Maze maze;

    public static final int GAME_WIDTH = 100;
    public static final int GAME_HEIGHT = 80;
    public static final int ELEMENT_SIZE = 5;

    public Game() throws InterruptedException {

        inputMonitor = new InputMonitor();

        gamePanel = new GamePanel();

        gamePanel.addKeyListener(inputMonitor);
        gamePanel.setFocusable(true);

        f.add(gamePanel);
        f.setSize(GAME_WIDTH * ELEMENT_SIZE, GAME_HEIGHT * ELEMENT_SIZE);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setUndecorated(true);
        f.setVisible(true);

        startGame();

    }

    private void startGame() throws InterruptedException {

        snake = new Snake(new Point(4, 4), 20);

        gamePanel.addDrawableObject(snake);
        gamePanel.addDrawableObject(new Wall(0, 0, GAME_HEIGHT, Direction.DOWN));
        gamePanel.addDrawableObject(new Wall(0, 0, GAME_WIDTH, Direction.RIGHT));
        gamePanel.addDrawableObject(new Wall(GAME_WIDTH-1, 0, GAME_HEIGHT, Direction.DOWN));
        gamePanel.addDrawableObject(new Wall(0, GAME_HEIGHT-1, GAME_WIDTH, Direction.RIGHT));

        while (true) {

            Thread.sleep(60);

            snake.setDirection(inputMonitor.getNextDirection());
            snake.move();
            if (Collisions.checkForCollision(snake.getSinglePosition(), snake)) {
                System.exit(1);
            }
            gamePanel.repaint();

        }


    }

}
