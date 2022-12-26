public class AI extends Thread {

    private Snake snake;
    private Maze maze;
    private Food food;

    public AI(Snake snake, Maze maze, Food food) {
        this.snake = snake;
        this.maze = maze;
        this.food = food;
        this.start();
    }

    @Override
    public void run() {

        System.out.println("AI started");

        while(true) {



        }


    }


}
