package inf112.skeleton.app;




import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class SmarterMonkey {


    MainGameScreen game;
    Board board;
    MonkeyAI monkey;
    int test = 8;

    Vector2 loaction;
    Direction dir;


    public SmarterMonkey(MainGameScreen game, MonkeyAI monkey) {
        this.game = game;
        this.monkey = monkey;

        board = game.getGameBoard();
        findFlag();
        System.out.println("nearby fields" + nearbyFields(monkey));

    }

    private void setLocDir(IPlayer player) {
        loaction = player.getPlayerloc().cpy();
        dir = player.getPlayerDir();
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

    // Gives all the fields around the player that can be moved to with one card
    private ArrayList<Vector2> nearbyFields(IPlayer player) {
        ArrayList<Vector2> closestFields= new ArrayList<>();
        setLocDir(player);
        Boolean addition = false;
        Boolean moveAlongXAxis = false;

        if(dir == Direction.EAST || dir == Direction.WEST) {
            addition = additionOrSubtraktion(dir);
            moveAlongXAxis = true;
        }
        else if (dir == Direction.NORTH || dir == Direction.SOUTH) {
            addition = additionOrSubtraktion(dir);
            moveAlongXAxis = false;
        }



        if(addition && moveAlongXAxis) {
            for (int i = -1; 4 > i; i++) {
                Vector2 temp = new Vector2();
                temp.set(player.getX() + i, player.getY());
                closestFields.add(temp);
            }
        }
        else if (addition && !moveAlongXAxis) {
            for (int i = -1; 4 > i; i++) {
                Vector2 temp = new Vector2();
                temp.set(player.getX(), player.getY() + i);
                closestFields.add(temp);
            }
        }
        else if (!addition && moveAlongXAxis) {
            for (int i = 4; -1 < i; i--) {
                Vector2 temp = new Vector2();
                temp.set(player.getX() - i, player.getY());
                closestFields.add(temp);
            }
        }
        else if (!addition && !moveAlongXAxis) {
            for (int i = 4; -1 < i; i--) {
                Vector2 temp = new Vector2();
                temp.set(player.getX() , player.getY() - i);
                closestFields.add(temp);
            }
        }

        return closestFields;
    }

    // Decide which direction the player can move to
    private Boolean additionOrSubtraktion (Direction direction) {
        if(direction == Direction.NORTH || direction == Direction.EAST) {
            return true;
        }
        else {
            return false;
        }
    }
    private void smartPath() {

    }


}
