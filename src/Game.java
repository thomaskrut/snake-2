import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Game implements ActionListener {

    JFrame f = new JFrame();
    KeyInput keyInput;
    GamePanel gamePanel;
    Snake snake;
    Maze maze;
    Food food;

    List<MovableObject> movableObjects;

    Timer timer = new Timer(50, this);

    public static final int GAME_WIDTH = 80;
    public static final int GAME_HEIGHT = 60;
    public static final int ELEMENT_SIZE = 8;

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

        buildMaze(maze, gamePanel);

        food = new Food(maze);

        snake = new Snake(new Point(5, 5), 14);

        movableObjects = List.of(snake);

        gamePanel.addDrawableObject(snake);

        gamePanel.addDrawableObject(food);

        timer.start();

    }

    private void update() {
        food.generateNewFood(maze);

        snake.setDirection(keyInput.getNextDirection());

        movableObjects.forEach(MovableObject::move);

        checkCollisions();

        gamePanel.repaint();
    }

    private void buildMaze(Maze maze, GamePanel gamePanel) {


        gamePanel.addDrawableObject(new Wall(maze, 0, 0, GAME_HEIGHT, Alignment.VERTICAL));
        gamePanel.addDrawableObject(new Wall(maze, 0, 0, GAME_WIDTH, Alignment.HORIZONTAL));
        gamePanel.addDrawableObject(new Wall(maze, GAME_WIDTH-1, 0, GAME_HEIGHT, Alignment.VERTICAL));
        gamePanel.addDrawableObject(new Wall(maze, 0, GAME_HEIGHT-1, GAME_WIDTH, Alignment.HORIZONTAL));

        gamePanel.addDrawableObject(new Wall(maze, 50,20, 20, Alignment.VERTICAL));
        gamePanel.addDrawableObject(new Wall(maze, 30,30, 40, Alignment.HORIZONTAL));
        gamePanel.addDrawableObject(new Wall(maze, 30,10, 45, Alignment.VERTICAL));
    }

    private void checkCollisions() {

        for (MovableObject o : movableObjects) {
            if (Collisions.checkForCollision(o.getSinglePosition(), snake)) {
                System.exit(1);
            }
            if (Collisions.checkForCollision(o.getSinglePosition(), maze)) {
                System.exit(2);
            }
            if (Collisions.checkForCollision(o.getSinglePosition(), food)) {
                food.eatFood(o.getSinglePosition());
                o.grow(5);
            }
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }
}
