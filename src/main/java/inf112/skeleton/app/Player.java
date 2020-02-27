package inf112.skeleton.app;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

public class Player {
    // Lives are not yet utilized but are required for the eventual death and respawn mechanics
    private Integer lives = 3;
    // Control class
    private dirCtrl dirController = new dirCtrl();
    // Elements the Player needs to function on the board
    private Vector2 playerLoc;
    private Direction playerDir;
    private PlayerState playerState = PlayerState.ALIVE;
    private TiledMapTileLayer playerLayer;
    // Player icon objects
    private TiledMapTileLayer.Cell playerCell = new TiledMapTileLayer.Cell();
    private TiledMapTileLayer.Cell playerDiedCell = new TiledMapTileLayer.Cell();
    private TiledMapTileLayer.Cell playerWonCell = new TiledMapTileLayer.Cell();

    // Constructor
    public Player(Vector2 location, Direction dir, TiledMapTileLayer layer){
        this.playerLoc = location;
        this.playerDir = dir;
        this.playerLayer = layer;

        // Graphics for the player
        TextureRegion[][] playerIcon = new TextureRegion(new Texture("assets/player.png")).split(300,300);
        playerCell.setTile(new StaticTiledMapTile(playerIcon[0][0]));
        playerDiedCell.setTile(new StaticTiledMapTile(playerIcon[0][1]));
        playerWonCell.setTile(new StaticTiledMapTile(playerIcon[0][2]));
    }

    public void Move() {
        // Moves the player forward based on the player direction
        // Player location coordinates
        Integer xLoc = getX();
        Integer yLoc = getY();
        //Clears player icon from old location
        playerLayer.setCell(xLoc, yLoc, null);
        // Changes coordinates in the correct manner
        if (playerDir == Direction.NORTH){
            playerLoc.set(xLoc,yLoc + 1);
        }
        else if (playerDir == Direction.SOUTH){
            playerLoc.set(xLoc,yLoc - 1);
        }
        else if (playerDir == Direction.WEST){
            playerLoc.set(xLoc - 1,yLoc);
        }
        else if (playerDir == Direction.EAST){
            playerLoc.set(xLoc + 1,yLoc);
        }
        // Updates the player icon so that is lands in the correct location
        updatePlayerIcon();
        // Sets PlayerState of the player to dead if it is out of bounds
        if (getX() < 0 || getX() > playerLayer.getWidth() || getY() < 0 || getY() > playerLayer.getHeight()){
            setPlayerState(PlayerState.DEAD);
        }
    }
    public void Turn(int keycode){
        if (keycode == Input.Keys.Q){
            playerDir = dirController.turnLeft(playerDir);
        }
        else if (keycode == Input.Keys.E){
            playerDir = dirController.turnRight(playerDir);
        }

        updateIconRotation();
    }

    private void updateIconRotation() {
        if (playerDir == Direction.NORTH){
            playerCell.setRotation(TiledMapTileLayer.Cell.ROTATE_0);
            playerDiedCell.setRotation(TiledMapTileLayer.Cell.ROTATE_0);
            playerWonCell.setRotation(TiledMapTileLayer.Cell.ROTATE_0);
        }
        else if (playerDir == Direction.EAST){
            playerCell.setRotation(TiledMapTileLayer.Cell.ROTATE_270);
            playerDiedCell.setRotation(TiledMapTileLayer.Cell.ROTATE_270);
            playerWonCell.setRotation(TiledMapTileLayer.Cell.ROTATE_270);
        }
        else if (playerDir == Direction.SOUTH){
            playerCell.setRotation(TiledMapTileLayer.Cell.ROTATE_180);
            playerDiedCell.setRotation(TiledMapTileLayer.Cell.ROTATE_180);
            playerWonCell.setRotation(TiledMapTileLayer.Cell.ROTATE_180);
        }
        else if (playerDir == Direction.WEST){
            playerCell.setRotation(TiledMapTileLayer.Cell.ROTATE_90);
            playerDiedCell.setRotation(TiledMapTileLayer.Cell.ROTATE_90);
            playerWonCell.setRotation(TiledMapTileLayer.Cell.ROTATE_90);
        }
        setPlayerState(playerState);
    }
    // Sets the player to the provided PlayerState and updates player icon accordingly
    public void setPlayerState(PlayerState newState) {
        // Sets player to the new PlayerState
        playerState = newState;

        updatePlayerIcon();
    }
    public void updatePlayerIcon(){
        // Coordinates required to update player icon
        Integer xLoc = getX();
        Integer yLoc = getY();
        // Updates player icon based on PlayerState
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
    // Functions to easily access coordinates of the Player
    public Integer getX(){
        return Math.round(playerLoc.x);
    }
    public Integer getY(){
        return Math.round(playerLoc.y);
    }
}
