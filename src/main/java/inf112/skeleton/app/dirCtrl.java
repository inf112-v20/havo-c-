package inf112.skeleton.app;

public class dirCtrl {
    public Direction turnLeft(Direction dir){
        if (dir == Direction.NORTH){
            return Direction.WEST;
        }
        else if (dir == Direction.WEST){
            return Direction.SOUTH;
        }
        else if (dir == Direction.SOUTH){
            return Direction.EAST;
        }
        else if (dir == Direction.EAST){
            return Direction.NORTH;
        }
        else {
            return null;
        }
    }

    public Direction turnRight(Direction dir){
        if (dir == Direction.NORTH){
            return Direction.EAST;
        }
        else if (dir == Direction.EAST){
            return Direction.SOUTH;
        }
        else if (dir == Direction.SOUTH){
            return Direction.WEST;
        }
        else if (dir == Direction.WEST){
            return Direction.NORTH;
        }
        else {
            return null;
        }
    }

    public Direction invertDirection(Direction dir){
        if (dir == Direction.NORTH){
            return Direction.SOUTH;
        }
        else if (dir == Direction.SOUTH){
            return Direction.NORTH;
        }
        else if (dir == Direction.WEST){
            return Direction.EAST;
        }
        else if (dir == Direction.EAST){
            return Direction.WEST;
        }
        else {
            return null;
        }
    }
}
