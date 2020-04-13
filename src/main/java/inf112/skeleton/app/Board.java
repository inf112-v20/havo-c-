package inf112.skeleton.app;

import com.badlogic.gdx.Input;
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
    }

    @Override
    public void checkForSpecialTiles(Player player){
        // Coordinates of the player used to check for other things in the map
        Integer xLoc = player.getX();
        Integer yLoc = player.getY();
        // Checks through maplayers for overlap and gives appropriate response
        checkHoles(player, xLoc, yLoc);
        checkBelts(player, xLoc, yLoc);
        checkTurnGears(player, xLoc, yLoc);
        checkLasers(player, xLoc, yLoc);
        checkFlags(player, xLoc, yLoc);
    }
    private void checkFlags(Player player, Integer xLoc, Integer yLoc){
        if (Flags.getCell(xLoc, yLoc) != null) {
            player.setPlayerState(PlayerState.WINNER);
        }
    }
    private void checkHoles(Player player, Integer xLoc, Integer yLoc){
        if (Holes.getCell(xLoc, yLoc) != null){
            player.setPlayerState(PlayerState.DEAD);
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
