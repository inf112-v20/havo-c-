package inf112.skeleton.app;




import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class SmarterMonkey {


    MainGameScreen game;
    Board board;
    MonkeyAI monkey;
    int test = 8;



    public SmarterMonkey(MainGameScreen game, MonkeyAI monkey) {
        this.game = game;
        this.monkey = monkey;

        board = game.getGameBoard();
        findFlag();

    }




    private ArrayList<Vector2> findFlag() {
       return board.findAllFlags();
    }
    private Vector2 aiLocation() {
        return monkey.getPlayerloc();
    }

    private void shortestPath(ArrayList<Vector2> allFlags, Vector2 playerLocation) {

    }


    // Returns an ArrayList of the most valuable direction(s) based on what abs(x or y) is the biggest
    private ArrayList<Direction> mostValuableDirection(Vector2 flagXY) {
        ArrayList<Direction> smartestDir = new ArrayList<>();
        int flagX = Math.round(flagXY.x);
        int flagY = Math.round(flagXY.y);
        int x = flagX - monkey.getX();
        int y = flagY - monkey.getY();

        // If the x pos and y pos is the same distance from the flag
        if (abs(x) == abs(y)) {
            smartestDir.add(xDirection(x));
            smartestDir.add(yDirection(y));
        }
        // If the x pos is farthest away from the flag
        else if(abs(x) > abs(y)) {
            smartestDir.add(xDirection(x));
        }
        // If the x pos is farthest away from the flag
        else if (abs(x) < abs(y)) {
            smartestDir.add(yDirection(y));
        }
        return smartestDir;
    }

    private Direction xDirection(int value) {
        Direction output;
        if(value > 0) {
            output = Direction.EAST;
        } else {
            output = Direction.WEST;
        }
        return output;
    }
    private Direction yDirection(int value) {
        Direction output;
        if(value > 0) {
            output = Direction.NORTH;
        } else {
            output = Direction.SOUTH;
        }
        return output;
    }

    private void smartPath() {

    }


}
