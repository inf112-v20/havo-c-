package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.backends.lwjgl.audio.Wav;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Board implements IBoard {
    // The map itself
    TiledMap Map;
    // Map layers
    TiledMapTileLayer Board;
    TiledMapTileLayer ConveyorBelts;
    TiledMapTileLayer TurnGears;
    TiledMapTileLayer Holes;
    TiledMapTileLayer Flags;
    TiledMapTileLayer Players;
    TiledMapTileLayer Laser;
    TiledMapTileLayer Wall;

    Sound fallSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/fall.wav"));
    Sound flagSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/flag.wav"));
    Sound victorySound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/victory.mp3"));


    public Board(TiledMap Map){
        // Takes in tiledMap from input
        this.Map = Map;
        // Gets the layers from the Map
        this.Board = (TiledMapTileLayer) Map.getLayers().get("Board");
        this.ConveyorBelts = (TiledMapTileLayer) Map.getLayers().get("ConveyorBelts");
        this.TurnGears = (TiledMapTileLayer) Map.getLayers().get("TurnGears");
        this.Holes = (TiledMapTileLayer) Map.getLayers().get("Holes");
        this.Flags = (TiledMapTileLayer) Map.getLayers().get("Flags");
        this.Players = (TiledMapTileLayer) Map.getLayers().get("PlayerLayer");
        this.Laser = (TiledMapTileLayer) Map.getLayers().get("Laser");
        this.Wall = (TiledMapTileLayer) Map.getLayers().get("Walls");
    }

    @Override
    public void checkForSpecialTiles(Player player){
        // Coordinates of the player used to check for other things in the map
        Integer xLoc = player.getX();
        Integer yLoc = player.getY();
        // Checks through maplayers for overlap and gives appropriate response
        checkBelts(player, xLoc, yLoc);
        checkTurnGears(player, xLoc, yLoc);
        checkLasers(player, xLoc, yLoc);
        checkFlags(player, xLoc, yLoc);
    }
    private void checkFlags(Player player, Integer xLoc, Integer yLoc){
        if (Flags.getCell(xLoc, yLoc) != null) {
            flagSound.play();
            player.setPlayerState(PlayerState.WINNER);
            victorySound.play();
        }
    }
    public void checkHoles(Player player){
        Integer xLoc = player.getX();
        Integer yLoc = player.getY();
        if (Holes.getCell(xLoc, yLoc) != null){
            player.setPlayerState(PlayerState.DEAD);
            fallSound.play();
        }
    }
    private void checkTurnGears(Player player, Integer xLoc, Integer yLoc){
        if (TurnGears.getCell(xLoc, yLoc) != null){
            Integer tileId = TurnGears.getCell(xLoc, yLoc).getTile().getId();
            // Turn right
            if(tileId == 54){
                player.Turn(TurnDirection.RIGHT);
            }
            // Turn left
            else if(tileId == 53){
                player.Turn(TurnDirection.LEFT);
            }
        }
    }
    private void checkBelts(Player player, Integer xLoc, Integer yLoc){
        if (ConveyorBelts.getCell(xLoc, yLoc) != null){
            Integer tileId = ConveyorBelts.getCell(xLoc, yLoc).getTile().getId();
            checkFastBelts(player, tileId);
            Integer xLoc2 = player.getX();
            Integer yLoc2 = player.getY();
            tileId = ConveyorBelts.getCell(xLoc2, yLoc2).getTile().getId();
            // Yellow belts
            if (tileId == 42 || tileId == 43 || tileId == 49 || tileId == 57 || tileId == 65 || tileId == 69 ||
                    tileId == 13 || tileId == 26 || tileId == 27 || tileId == 73 || tileId == 77 || tileId == 84){
                player.Move(Direction.NORTH);
            }
            else if(tileId == 33 || tileId == 36 || tileId == 50 || tileId == 59 || tileId == 62 || tileId == 67 ||
                    tileId == 17 || tileId == 20 || tileId == 21 || tileId == 75 || tileId == 82 || tileId == 86){
                player.Move(Direction.SOUTH);
            }
            else if(tileId == 35 || tileId == 41 || tileId == 52 || tileId == 58 || tileId == 61 || tileId == 66 ||
                    tileId == 14 || tileId == 19 || tileId == 25 || tileId == 74 || tileId == 78 || tileId == 81){
                player.Move(Direction.EAST);
            }
            else if(tileId == 34 || tileId == 44 || tileId == 51 || tileId == 60 || tileId == 68 || tileId == 70 ||
                    tileId == 18 || tileId == 22 || tileId == 28 || tileId == 76 || tileId == 83 || tileId == 85){
                player.Move(Direction.WEST);
            }
        }
    }
    private void checkFastBelts(Player player, Integer tileId){
        if (tileId == 13 || tileId == 26 || tileId == 27 || tileId == 73 || tileId == 77 || tileId == 84){
            player.Move(Direction.NORTH);
        }
        else if (tileId == 17 || tileId == 20 || tileId == 21 || tileId == 75 || tileId == 82 || tileId == 86){
            player.Move(Direction.SOUTH);
        }
        else if (tileId == 14 || tileId == 19 || tileId == 25 || tileId == 74 || tileId == 78 || tileId == 81){
            player.Move(Direction.EAST);
        }
        else if (tileId == 18 || tileId == 22 || tileId == 28 || tileId == 76 || tileId == 83 || tileId == 85){
            player.Move(Direction.WEST);
        }
    }
    private void checkLasers(Player player, Integer xLoc, Integer yLoc){
        if (Laser.getCell(xLoc, yLoc) != null){
            Integer tileId = Laser.getCell(xLoc, yLoc).getTile().getId();
            // Single laser
            if(tileId == 39 || tileId == 47){
                player.takeDamage(1);
            }
            // Single laser intersection or double laser
            else if(tileId == 40 || tileId == 101 || tileId == 103){
                player.takeDamage(2);
            }
            // Double laser intersection
            else if(tileId == 102){
                player.takeDamage(4);
            }
        }
    }
    public boolean wallCheck(Player player, Direction dir, Boolean second){
        Integer xLoc = player.getX();
        Integer yLoc = player.getY();
        Direction dirInUse = dir;
        if (second){
            if (dir == Direction.NORTH) {
                yLoc++;
            }
            else if (dir == Direction.SOUTH){
                yLoc--;
            }
            else if (dir == Direction.WEST){
                xLoc--;
            }
            else if (dir == Direction.EAST){
                xLoc++;
            }
            DirCtrl dirCtrl = new DirCtrl();
            dirInUse = dirCtrl.invertDirection(dir);
        }
        if (dirInUse == Direction.NORTH) {
            if (Wall.getCell(xLoc,yLoc) != null) {
                Integer tileId = Wall.getCell(xLoc, yLoc).getTile().getId();
                if (tileId == 1 || tileId == 9 || tileId == 16 || tileId == 24 || tileId == 31 || tileId == 45 || tileId == 94) {
                    return false;
                }
            }
        }
        else if (dirInUse == Direction.SOUTH) {
            if (Wall.getCell(xLoc,yLoc) != null) {
                Integer tileId = Wall.getCell(xLoc, yLoc).getTile().getId();
                if (tileId == 3 || tileId == 8 || tileId == 11 || tileId == 29 || tileId == 32 || tileId == 37 || tileId == 87) {
                    return false;
                }
            }
        }
        else if (dirInUse == Direction.WEST) {
            if (Wall.getCell(xLoc,yLoc) != null) {
                Integer tileId = Wall.getCell(xLoc, yLoc).getTile().getId();
                if (tileId == 4 || tileId == 12 || tileId == 24 || tileId == 30 || tileId == 32 || tileId == 38 || tileId == 93) {
                    return false;
                }
            }
        }
        else if (dirInUse == Direction.EAST) {
            if (Wall.getCell(xLoc,yLoc) != null) {
                Integer tileId = Wall.getCell(xLoc, yLoc).getTile().getId();
                if (tileId == 2 || tileId == 8 || tileId == 10 || tileId == 16 || tileId == 23 || tileId == 46 || tileId == 95) {
                    return false;
                }
            }
        }
        if (!second) {
            if (!wallCheck(player, dir, true)){
                return false;
            }
        }
        return true;
    }
    // Methods to get elements from Board
    public TiledMap getMap(){
        return Map;
    }
    public TiledMapTileLayer getBoard(){
        return Board;
    }
    public TiledMapTileLayer getPlayerLayer(){
        return Players;
    }


}
