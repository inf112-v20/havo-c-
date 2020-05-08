package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import inf112.skeleton.app.Enums.Direction;
import inf112.skeleton.app.Interfaces.IPlayer;

import java.util.ArrayList;

public class LaserWall {
    // Variables of the wall
    private Vector2 wallLoc;
    private Direction laserDir;
    private Boolean  doubleOrNot;
    private Board gameBoard;
    // Laser graphic objects
    private TiledMapTileLayer.Cell laserCell = new TiledMapTileLayer.Cell();

    public LaserWall(Integer tileId, Vector2 loc, Board board){
        this.wallLoc = loc;
        this.gameBoard = board;
        // Setting up texture regions
        TextureRegion[][] singleLaser = new TextureRegion(new Texture("assets/laser.png")).split(300,300);
        TextureRegion[][] doubleLaser = new TextureRegion(new Texture("assets/doubleLaser.png")).split(300,300);
        // Checks if it is a double laser wall or not
        if(tileId == 87 || tileId == 93 || tileId == 94 || tileId == 95){
            this.doubleOrNot = true;
        }
        else{
            this.doubleOrNot = false;
        }
        //Checks the direction of the laser.
        if(tileId == 37 || tileId == 87){
            this.laserDir = Direction.NORTH;
            if(doubleOrNot){
                laserCell.setTile(new StaticTiledMapTile(doubleLaser[0][0]));
            }
            else{
                laserCell.setTile(new StaticTiledMapTile(singleLaser[0][0]));
            }
        }
        else if(tileId == 38 || tileId == 93){
            this.laserDir = Direction.EAST;
            if(doubleOrNot){
                laserCell.setTile(new StaticTiledMapTile(doubleLaser[0][1]));
            }
            else{
                laserCell.setTile(new StaticTiledMapTile(singleLaser[0][1]));
            }
        }
        else if(tileId == 45 || tileId == 94){
            this.laserDir = Direction.SOUTH;
            if(doubleOrNot){
                laserCell.setTile(new StaticTiledMapTile(doubleLaser[0][0]));
            }
            else{
                laserCell.setTile(new StaticTiledMapTile(singleLaser[0][0]));
            }
        }
        else if(tileId == 46 || tileId == 95){
            this.laserDir = Direction.WEST;
            if(doubleOrNot){
                laserCell.setTile(new StaticTiledMapTile(doubleLaser[0][1]));
            }
            else{
                laserCell.setTile(new StaticTiledMapTile(singleLaser[0][1]));
            }
        }

    }

    public void fireLaser(){
        Integer xCoord = Math.round(wallLoc.x);
        Integer yCoord =  Math.round(wallLoc.y);

        if (gameBoard.wallCheck(xCoord,yCoord,laserDir,false)) {
            gameBoard.getLaserLayer().setCell(xCoord,yCoord,laserCell);
            while (true) {
                if(gameBoard.checkForObstacles(xCoord,yCoord,laserDir)){
                    break;
                }
                if (laserDir == Direction.NORTH) {
                    yCoord++;
                } else if (laserDir == Direction.SOUTH) {
                    yCoord--;
                } else if (laserDir == Direction.WEST) {
                    xCoord--;
                } else if (laserDir == Direction.EAST) {
                    xCoord++;
                }
                gameBoard.getLaserLayer().setCell(xCoord,yCoord,laserCell);
            }
            if (gameBoard.getPlayerLayer().getCell(xCoord,yCoord) != null){
                ArrayList<IPlayer> players = gameBoard.getPlayers();
                for(Integer i = 0; i < players.size(); i++){
                    IPlayer target = players.get(i);
                    if(target.getX() == xCoord && target.getY() == yCoord){
                        if(doubleOrNot){
                            target.takeDamage(2);
                        }
                        target.takeDamage(1);
                    }
                }
            }
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    gameBoard.clearLasers();
                }
            }, 1);
        }
    }
}
