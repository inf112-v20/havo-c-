package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.*;

public class Board {
    // The map itself
    TiledMap Map;
    // Map layers
    TiledMapTileLayer Holes;
    TiledMapTileLayer Flags;
    TiledMapTileLayer Players;

    public Board(TiledMap Map){
        // Takes in tiledMap from input
        this.Map = Map;
        // Gets the layers from the Map
        this.Holes = (TiledMapTileLayer) Map.getLayers().get("Holes");
        this.Flags = (TiledMapTileLayer) Map.getLayers().get("Flags");
        this.Players = (TiledMapTileLayer) Map.getLayers().get("PlayerLayer");
    }

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
    }
    // Methods to get elements from Board
    public TiledMap getMap() {
        return Map;
    }
    public TiledMapTileLayer getPlayerLayer(){
        return Players;
    }
}
