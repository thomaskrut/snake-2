import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AI extends Thread {

    private Snake snake;
    private Maze maze;
    private Food food;

    List<Direction> directionQueue = new ArrayList<>();

    public AI(Snake snake, Maze maze, Food food) {
        this.snake = snake;
        this.maze = maze;
        this.food = food;
        this.start();
    }

    public synchronized Direction getNextDirection() {

        if (directionQueue.size() > 0) {
            return directionQueue.remove(0);
        }
        else {
            return null;
        }
    }

    private synchronized void setNextDirection(Direction newDirection) {
        directionQueue.add(newDirection);
    }

    @Override
    public void run() {

        System.out.println("AI started");

        Random rand = new Random();

        int distanceToFood = 0;

        int distanceToObstacle;

        int x;

        int y;

        while (true) {

            try {
                sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            x = snake.getSinglePosition().x;
            y = snake.getSinglePosition().y;

            Point positionOfFood = food.getSinglePosition();

            if (positionOfFood != null) {

                if (positionOfFood.y == y) {
                    if (positionOfFood.x > x) {
                        snake.setDirection(Direction.EAST);
                        distanceToFood = positionOfFood.x - x;
                    }
                    if (positionOfFood.x < x) {
                        snake.setDirection(Direction.WEST);
                        distanceToFood = x - positionOfFood.x;
                    }
                }

                if (positionOfFood.x == x) {
                    if (positionOfFood.y < y) {
                        snake.setDirection(Direction.NORTH);
                        distanceToFood = y - positionOfFood.y;
                    }
                    if (positionOfFood.y > y) {
                        snake.setDirection(Direction.SOUTH);
                        distanceToFood = positionOfFood.y - y;
                    }
                }



                if (rand.nextInt(15) == 1) {
                    if (positionOfFood.x > x) {
                        snake.setDirection(Direction.EAST);
                    }

                    if (positionOfFood.x < x) {
                        snake.setDirection(Direction.WEST);
                    }

                    if (positionOfFood.y > y) {
                        snake.setDirection(Direction.SOUTH);
                    }

                    if (positionOfFood.y < y) {
                        snake.setDirection(Direction.NORTH);
                    }
                }



                distanceToObstacle = maze.hasObstacleAlongAxis(x, y, snake.getCurrentDirection());


                if (distanceToObstacle < distanceToFood) {

                    switch (snake.getCurrentDirection()) {

                        case NORTH, SOUTH -> {
                            if (rand.nextInt(2) == 1) {
                                snake.setDirection(Direction.WEST);
                            } else {
                                snake.setDirection(Direction.EAST);
                            }
                        }

                        case EAST, WEST -> {
                            if (rand.nextInt(2) == 1) {
                                snake.setDirection(Direction.NORTH);
                            } else {
                                snake.setDirection(Direction.SOUTH);
                            }
                        }

                    }


                }


            }

        }


    }


}
