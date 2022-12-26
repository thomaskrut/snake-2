import javax.swing.*;
import java.awt.*;

public class Game {

    JFrame f = new JFrame();

    KeyInput keyInput;

    GamePanel gamePanel;

    Snake snake;

    Snake snake2;

    Maze maze;

    Food food;

    AI Ai;

    public static final int GAME_WIDTH = 80;
    public static final int GAME_HEIGHT = 60;
    public static final int ELEMENT_SIZE = 10;

    public Game() throws InterruptedException {

        keyInput = new KeyInput();

        gamePanel = new GamePanel();

        gamePanel.addKeyListener(keyInput);
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

        maze = new Maze();

        food = new Food(maze);

        snake = new Snake(new Point(5, 5), 14);

        snake2 = new Snake(new Point(25, 25), 14);

        Ai = new AI(snake2, maze, food);

        gamePanel.addDrawableObject(snake);
        gamePanel.addDrawableObject(snake2);
        gamePanel.addDrawableObject(new Wall(maze, 0, 0, GAME_HEIGHT, Alignment.VERTICAL));
        gamePanel.addDrawableObject(new Wall(maze, 0, 0, GAME_WIDTH, Alignment.HORIZONTAL));
        gamePanel.addDrawableObject(new Wall(maze, GAME_WIDTH-1, 0, GAME_HEIGHT, Alignment.VERTICAL));
        gamePanel.addDrawableObject(new Wall(maze, 0, GAME_HEIGHT-1, GAME_WIDTH, Alignment.HORIZONTAL));
        gamePanel.addDrawableObject(food);

        while (true) {

            Thread.sleep(50);

            food.generateNewFood(maze);

            snake.setDirection(keyInput.getNextDirection());

            snake.move();

            snake2.move();

            checkCollisions();

            gamePanel.repaint();

        }


    }

    private void checkCollisions() {
        if (Collisions.checkForCollision(snake.getSinglePosition(), snake)) {
            System.exit(1);
        }
        if (Collisions.checkForCollision(snake.getSinglePosition(), maze)) {
            System.exit(2);
        }
        if (Collisions.checkForCollision(snake.getSinglePosition(), food)) {
            food.eatFood(snake.getSinglePosition());
            snake.grow(5);
        }
    }

}
