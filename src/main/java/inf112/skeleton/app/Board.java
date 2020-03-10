package inf112.skeleton.app;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Board implements IBoard {
    // The map itself
    TiledMap Map;
    // Map layers
    TiledMapTileLayer Holes;
    TiledMapTileLayer Flags;
    TiledMapTileLayer TurnGears;
    TiledMapTileLayer ConveyorBelts;
    TiledMapTileLayer Players;

    public Board(TiledMap Map){
        // Takes in tiledMap from input
        this.Map = Map;
        // Gets the layers from the Map
        this.Holes = (TiledMapTileLayer) Map.getLayers().get("Holes");
        this.Flags = (TiledMapTileLayer) Map.getLayers().get("Flags");
        this.Players = (TiledMapTileLayer) Map.getLayers().get("PlayerLayer");
        this.ConveyorBelts = (TiledMapTileLayer) Map.getLayers().get("ConveyorBelts");
        this.TurnGears = (TiledMapTileLayer) Map.getLayers().get("TurnGears");
    }

    @Override
    public void checkForSpecialTiles(Player player){
        // Coordinates of the player used to check for other things in the map
        Integer xLoc = player.getX();
        Integer yLoc = player.getY();
        // Checks through maplayers for overlap and gives appropriate response
        if (Flags.getCell(xLoc, yLoc) != null) {
            player.setPlayerState(PlayerState.WINNER);
        }
        else if (Holes.getCell(xLoc, yLoc) != null){
            player.setPlayerState(PlayerState.DEAD);
        }
        else if (TurnGears.getCell(xLoc, yLoc) != null){
            Integer tileId = TurnGears.getCell(xLoc, yLoc).getTile().getId();
            // Turn right
            if(tileId == 54){
                player.Turn(Input.Keys.E);
            }
            // Turn left
            else if(tileId == 53){
                player.Turn(Input.Keys.Q);
            }
        }
        else if (ConveyorBelts.getCell(xLoc, yLoc) != null){
            Integer tileId = ConveyorBelts.getCell(xLoc, yLoc).getTile().getId();
            // Yellow belts
            if (tileId == 42 || tileId == 43 || tileId == 49 || tileId == 57 || tileId == 65 || tileId == 69){
                player.Move(Direction.NORTH);
            }
            else if(tileId == 33 || tileId == 36 || tileId == 50 || tileId == 59 || tileId == 62 || tileId == 67){
                player.Move(Direction.SOUTH);
            }
            else if(tileId == 35 || tileId == 41 || tileId == 52 || tileId == 58 || tileId == 61 || tileId == 66){
                player.Move(Direction.EAST);
            }
            else if(tileId == 34 || tileId == 44 || tileId == 51 || tileId == 60 || tileId == 68 || tileId == 70){
                player.Move(Direction.WEST);
            }
            // Blue belts
            if (tileId == 13 || tileId == 26 || tileId == 27 || tileId == 73 || tileId == 77 || tileId == 84){
                player.Move(Direction.NORTH);
            }
            else if (tileId == 17 || tileId == 20 || tileId == 21 || tileId == 75 || tileId == 82 || tileId == 86){
                player.Move(Direction.SOUTH);
            }
            else if (tileId == 14 || tileId == 19 || tileId == 25 || tileId == 74 || tileId == 78 || tileId == 81){
                player.Move(Direction.EAST);
            }
            else if (tileId == 18 || tileId == 22 || tileId == 28 || tileId == 76 || tileId == 82 || tileId == 85){
                player.Move(Direction.WEST);
            }
        }
    }
    // Methods to get elements from Board
    public TiledMap getMap() { return Map; }
    public TiledMapTileLayer getPlayerLayer(){
        return Players;
    }


}
