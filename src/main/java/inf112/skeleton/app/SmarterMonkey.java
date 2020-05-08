package inf112.skeleton.app;




import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Enums.Direction;
import inf112.skeleton.app.Interfaces.IPlayer;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class SmarterMonkey {


    MainGameScreen game;
    Board board;
    MonkeyAI monkey;


    Vector2 location;
    Direction dir;


    public SmarterMonkey(MainGameScreen game, MonkeyAI monkey) {
        this.game = game;
        this.monkey = monkey;

        board = game.getGameBoard();
        findFlag();


    }

    private void setLocDir(IPlayer player) {
        location = player.getPlayerloc().cpy();
        dir = player.getPlayerDir();
    }





    private ArrayList<Vector2> findFlag() {
       return board.findAllFlags();
    }

    // Gets the the next flag that should be visited
    private Vector2 findTheRightFlag() {

        Vector2 rightFlag = new Vector2();
        ArrayList<Vector2> findallFlags = findFlag();
        int xLoc;
        int yLoc;
        for(int i = 0; i < findallFlags.size(); i++) {
            xLoc = ((int) findallFlags.get(i).x);
            yLoc = ((int) findallFlags.get(i).y);
            if (board.Flags.getCell(xLoc, yLoc) != null) {
                Integer tileId = board.Flags.getCell(xLoc, yLoc).getTile().getId();

                if (tileId == 55 && monkey.getFlags() == 0) {
                    rightFlag.set(xLoc, yLoc);
                } else if (tileId == 63 && monkey.getFlags() == 1) {
                    rightFlag.set(xLoc, yLoc);

                } else if (tileId == 71 && monkey.getFlags() == 2) {
                    rightFlag.set(xLoc, yLoc);

                } else if (tileId == 79 && monkey.getFlags() == 3) {
                    rightFlag.set(xLoc, yLoc);

                }
            }
        }
        return rightFlag;
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

    // Checks if the coordinates is inside the map
    private Boolean insideMap(Vector2 pos) {
        Boolean output = false;
        if (0 <= pos.x && pos.x < board.getBoard().getWidth()) {
            if (0 <= pos.y && pos.y < board.getBoard().getHeight()) {
                output = true;
            }
        }
        return output;
    }

    // Checks if the point given is closer to the flags
    private Boolean closerToFlag(Vector2 pos) {
        Vector2 flag = findFlag().get(0);

        float distancePosToFlag = abs((flag.x - pos.x) + (flag.y - pos.y));
        float distancePlayerToFlag = abs((flag.x - location.x) + (flag.y - location.y));

        // Returns true if position to flag is closer or equal
        if(distancePlayerToFlag >= distancePosToFlag) { return true; }

        return false;

    }

    public ArrayList<Direction> smartestDirection() {
        return mostValuableDirection(findTheRightFlag());

    }

    // Returns the smartest picks for the AI to make
    public ArrayList<Vector2> smartPath() {
        ArrayList<Vector2> nearbyfields = nearbyFields(monkey);
        ArrayList<Vector2> valuableFields = new ArrayList<>();

        for (int i = 0; nearbyfields.size() > i; i++) {
            if(closerToFlag(nearbyfields.get(i)) && insideMap(nearbyfields.get(i))) {
                valuableFields.add(nearbyfields.get(i).cpy());
            }
        }
        return valuableFields;
    }


}
