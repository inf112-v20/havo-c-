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
        // Only allows movement if player is alive
        if (playerState != PlayerState.DEAD) {
            // Player location coordinates
            Integer xLoc = getX();
            Integer yLoc = getY();
            //Clears player icon from old location
            playerLayer.setCell(xLoc, yLoc, null);
            // Changes coordinates in the correct manner
            if (playerDir == Direction.NORTH) {
                playerLoc.set(xLoc, yLoc + 1);
            } else if (playerDir == Direction.SOUTH) {
                playerLoc.set(xLoc, yLoc - 1);
            } else if (playerDir == Direction.WEST) {
                playerLoc.set(xLoc - 1, yLoc);
            } else if (playerDir == Direction.EAST) {
                playerLoc.set(xLoc + 1, yLoc);
            }
            // Sets PlayerState of the player to dead if it is out of bounds
            if (getX() < 0 || getX() > playerLayer.getWidth() || getY() < 0 || getY() > playerLayer.getHeight()) {
                setPlayerState(PlayerState.DEAD);
            }
        }
    }
    public void Turn(int keycode){
        // Turns the player
        // Only allows turning if the player is alive
        if (playerState != PlayerState.DEAD) {
            // Turns player left
            if (keycode == Input.Keys.Q) {
                playerDir = dirController.turnLeft(playerDir);
            }
            // Turns player right
            else if (keycode == Input.Keys.E) {
                playerDir = dirController.turnRight(playerDir);
            }
            // Updates rotation of player icon
            updateIconRotation();
        }
    }
    // Updates rotation of the player icon based on what the player direction is
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
    }
    // Sets the player to the provided PlayerState and updates player icon accordingly
    public void setPlayerState(PlayerState newState) {
        // Sets player to the new PlayerState
        playerState = newState;
    }
    // Respawns a dead player
    public  void respawn(Integer xCoord, Integer yCoord, Direction dir){
        // Checks if player is out of lives
        if (lives > 0) {
            // Removes player from location of death
            playerLayer.setCell(getX(),getY(),null);
            // Sets the coordinates and direction the player is facing when respawning in addition of setting the playerState to alive
            playerLoc.set(xCoord, yCoord);
            playerDir = dir;
            setPlayerState(PlayerState.ALIVE);
            // Updates the player icons rotation
            updateIconRotation();
            // Subtracts the life lost
            lives--;
        }
        // Game Over mechanics not implemented yet
        else {
            // Game Over mechanics go here
        }
    }
    // Updates the player icon
    public void updatePlayerIcon(){
        // Coordinates required to place player icon
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
    // Functions to access information of the Player
    public Integer getX(){
        return Math.round(playerLoc.x);
    }
    public Integer getY(){
        return Math.round(playerLoc.y);
    }
    public PlayerState getPlayerState(){
        return playerState;
    }
}