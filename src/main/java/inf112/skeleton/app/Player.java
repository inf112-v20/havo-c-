package inf112.skeleton.app;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Integer lives = 3;
    private Vector2 playerLoc;
    private PlayerState playerState = PlayerState.ALIVE;
    private TiledMapTileLayer playerLayer;
    private TiledMapTileLayer.Cell playerCell = new TiledMapTileLayer.Cell();
    private TiledMapTileLayer.Cell playerDiedCell = new TiledMapTileLayer.Cell();
    private TiledMapTileLayer.Cell playerWonCell = new TiledMapTileLayer.Cell();

    public Player(Vector2 location, TiledMapTileLayer layer){
        this.playerLoc = location;
        this.playerLayer = layer;

        TextureRegion[][] playerIcon = new TextureRegion(new Texture("assets/player.png")).split(300,300);
        playerCell.setTile(new StaticTiledMapTile(playerIcon[0][0]));
        playerDiedCell.setTile(new StaticTiledMapTile(playerIcon[0][1]));
        playerWonCell.setTile(new StaticTiledMapTile(playerIcon[0][2]));
    }

    public void Move(int keycode) {
        if (keycode == Input.Keys.W){
            Integer xLoc = getX();
            Integer newYLoc = getY() + 1;
            Integer oldYLoc = getY();
            playerLayer.setCell(xLoc, oldYLoc, null);
            playerLoc.set(xLoc,newYLoc);
            playerLayer.setCell(xLoc, newYLoc, playerCell);
        }
        else if (keycode == Input.Keys.S){
            Integer xLoc = getX();
            Integer newYLoc = getY() - 1;
            Integer oldYLoc = getY();
            playerLayer.setCell(xLoc, oldYLoc, null);
            playerLoc.set(xLoc,newYLoc);
            playerLayer.setCell(xLoc, newYLoc, playerCell);
        }
        else if (keycode == Input.Keys.A){
            Integer yLoc = getY();
            Integer newXLoc = getX() - 1;
            Integer oldXLoc = getX();
            playerLayer.setCell(oldXLoc,yLoc, null);
            playerLoc.set(newXLoc,yLoc);
            playerLayer.setCell(newXLoc,yLoc, playerCell);
        }
        else if (keycode == Input.Keys.D){
            Integer yLoc = getY();
            Integer newXLoc = getX() + 1;
            Integer oldXLoc = getX();
            playerLayer.setCell(oldXLoc,yLoc, null);
            playerLoc.set(newXLoc,yLoc);
            playerLayer.setCell(newXLoc,yLoc, playerCell);
        }
    }

    public void setPlayerState(PlayerState newState) {
        playerState = newState;

        Integer xLoc = getX();
        Integer yLoc = getY();

        if (playerState == PlayerState.WINNER){
            playerLayer.setCell(xLoc,yLoc, playerWonCell);
        }
        else if (playerState == PlayerState.DEAD){
            playerLayer.setCell(xLoc,yLoc, playerDiedCell);
        }
        else {
            playerLayer.setCell(xLoc,yLoc, playerCell);
        }
    }

    public Integer getX(){
        return Math.round(playerLoc.x);
    }
    public Integer getY(){
        return Math.round(playerLoc.y);
    }
}
