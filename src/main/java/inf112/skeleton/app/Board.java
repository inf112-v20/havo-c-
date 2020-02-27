package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.*;

public class Board {
    TiledMap Map;
    TiledMapTileLayer Holes;
    TiledMapTileLayer Flags;
    TiledMapTileLayer Players;

    public Board(TiledMap Map){
        this.Map = Map;

        this.Holes = (TiledMapTileLayer) Map.getLayers().get("Holes");
        this.Flags = (TiledMapTileLayer) Map.getLayers().get("Flags");
        this.Players = (TiledMapTileLayer) Map.getLayers().get("PlayerLayer");
    }

    public void checkForSpecialTiles(Player player){
        Integer xLoc = player.getX();
        Integer yLoc = player.getY();
        if (Flags.getCell(xLoc, yLoc) != null) {
            player.setPlayerState(PlayerState.WINNER);
        }
        else if (Holes.getCell(xLoc, yLoc) != null){
            player.setPlayerState(PlayerState.DEAD);
        }
    }

    public TiledMap getMap() {
        return Map;
    }
    public TiledMapTileLayer getPlayerLayer(){
        return Players;
    }
}
