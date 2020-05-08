package inf112.skeleton.app.Interfaces;

import inf112.skeleton.app.Enums.Direction;

public interface IDirCtrl {
    Direction turnLeft(Direction dir);

    Direction turnRight(Direction dir);

    Direction invertDirection(Direction dir);
}
