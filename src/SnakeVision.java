import java.util.Random;

public class SnakeVision {

    private Snake snake;
    int distanceToWall;
    int distanceToFood;
    int shortestDistanceToFood;
    int x;
    int y;
    Direction newDirection;
    Random rand = new Random();

    public SnakeVision(Snake snake) {
        this.snake = snake;
    }


    public void checkLinesOfSight(Maze maze, Food food) {

        shortestDistanceToFood = 999;

        x = snake.getSinglePosition().x;
        y = snake.getSinglePosition().y;

        for (int i = 0; i < 4; i++) {
            Direction direction = Collisions.getNextDirection();
            distanceToFood = Collisions.hasObstacleOnAxis(food, x, y, direction);
            if (distanceToFood < shortestDistanceToFood) {
                newDirection = direction;
                shortestDistanceToFood = distanceToFood;
            }
        }

        snake.setDirection(newDirection);

        if (shortestDistanceToFood > 100 && rand.nextInt(25) == 1) {
            snake.setDirection(Collisions.getNextDirection(snake.getCurrentDirection()));
        }

        distanceToWall = Collisions.hasObstacleOnAxis(maze, x, y, snake.getCurrentDirection());

        if (distanceToWall < 2) {
            snake.setDirection(Collisions.getNextDirection(snake.getCurrentDirection()));
        }


    }

}

