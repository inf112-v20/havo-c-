package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import org.lwjgl.opengl.EXTFogCoord;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Player implements IPlayer{
    // Variables of the player robot
    private Integer lives = 3;
    private Integer hp = 9;
    private Boolean powerdown = false;
    private ArrayList<Card> hand = new ArrayList<>();
    private Integer flagsVisited = 0;
    private Boolean ready = false;
    private Boolean player = true;
    private Boolean you = true;
    // Control class
    private DirCtrl dirController = new DirCtrl();
    // Elements the Player needs to function on the board
    private Vector2 playerLoc;
    private Direction playerDir;
    private PlayerState playerState = PlayerState.ALIVE;
    private TiledMapTileLayer playerLayer;
    private MainGameScreen game;
    private Board gameBoard;
    private Vector2 spawnPoint = new Vector2();
    // Player icon objects
    private TiledMapTileLayer.Cell playerCell = new TiledMapTileLayer.Cell();
    private TiledMapTileLayer.Cell playerDiedCell = new TiledMapTileLayer.Cell();
    private TiledMapTileLayer.Cell playerWonCell = new TiledMapTileLayer.Cell();
    // Player laser objects
    private TiledMapTileLayer.Cell horLaser = new TiledMapTileLayer.Cell();
    private TiledMapTileLayer.Cell verLaser = new TiledMapTileLayer.Cell();

    private Sound wallcollisionsound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/wallboink.wav"));
    private Sound gameoversound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/gameover.wav"));


    // Card values
    private ArrayList<Integer> cardValues= new ArrayList<Integer> ();

    // Constructor
    public Player(Vector2 location, Direction dir, MainGameScreen game){
        this.playerLoc = location;
        this.playerDir = dir;
        this.game = game;
        this.gameBoard = game.getGameBoard();
        this.playerLayer = gameBoard.getPlayerLayer();
        // Graphics for the player
        TextureRegion[][] playerIcon = new TextureRegion(new Texture("assets/Cyborg-Up.png")).split(300,300);
        playerCell.setTile(new StaticTiledMapTile(playerIcon[0][0]));
        playerDiedCell.setTile(new StaticTiledMapTile(playerIcon[0][1]));
        playerWonCell.setTile(new StaticTiledMapTile(playerIcon[0][2]));
        // Graphics for player laser
        TextureRegion[][] laser = new TextureRegion(new Texture("assets/laser.png")).split(300,300);
        verLaser.setTile(new StaticTiledMapTile(laser[0][0]));
        horLaser.setTile(new StaticTiledMapTile(laser[0][1]));

        spawnPoint.set(playerLoc.cpy());
    }

    public void Move(Direction dir) {
        // Moves the player forward based on the player direction
        // Only allows movement if player is alive
        if (playerState != PlayerState.DEAD) {
            // Player location coordinates
            Integer xLoc = getX();
            Integer yLoc = getY();
            //Clears player icon from old location
            if (gameBoard.wallCheck(xLoc, yLoc, dir,false)) {
                playerLayer.setCell(xLoc, yLoc, null);
                // Changes coordinates in the correct manner
                if (dir == Direction.NORTH) {
                    playerLoc.set(xLoc, yLoc + 1);
                } else if (dir == Direction.SOUTH) {
                    playerLoc.set(xLoc, yLoc - 1);
                } else if (dir == Direction.WEST) {
                    playerLoc.set(xLoc - 1, yLoc);
                } else if (dir == Direction.EAST) {
                    playerLoc.set(xLoc + 1, yLoc);
                }
                // Sets PlayerState of the player to dead if it is out of bounds
                if (gameBoard.outOfBoundsCheck(getX(),getY())) {
                    setPlayerState(PlayerState.DEAD);
                    gameoversound.play();
                }
                gameBoard.checkHoles(this);
            }
            else {
                wallcollisionsound.play();
            }
        }

        game.collision(this);
    }
    public void Turn(TurnDirection dir){
        // Turns the player
        // Only allows turning if the player is alive
        if (playerState != PlayerState.DEAD) {
            // Turns player left
            if (dir == TurnDirection.LEFT) {
                playerDir = dirController.turnLeft(playerDir);
            }
            // Turns player right
            else if (dir == TurnDirection.RIGHT) {
                playerDir = dirController.turnRight(playerDir);
            }
            // Turns player 180 degrees
            else if (dir == TurnDirection.BACKWARDS){
                playerDir = dirController.invertDirection(playerDir);
            }
            // Updates rotation of player icon
            updateIconRotation();
        }
    }
    public void fireLaser(){
        Integer xCoord = getX();
        Integer yCoord = getY();
        TiledMapTileLayer.Cell activeLaser = new TiledMapTileLayer.Cell();

        if (playerDir == Direction.NORTH || playerDir == Direction.SOUTH){
            activeLaser = verLaser;
        }
        else if (playerDir == Direction.WEST || playerDir == Direction.EAST){
            activeLaser = horLaser;
        }

        if (gameBoard.wallCheck(xCoord,yCoord,playerDir,false)) {
            while (true) {
                if (playerDir == Direction.NORTH) {
                    yCoord++;
                } else if (playerDir == Direction.SOUTH) {
                    yCoord--;
                } else if (playerDir == Direction.WEST) {
                    xCoord--;
                } else if (playerDir == Direction.EAST) {
                    xCoord++;
                }
                gameBoard.getLaserLayer().setCell(xCoord,yCoord,activeLaser);
                if(gameBoard.checkForObstacles(xCoord,yCoord,playerDir)){
                    break;
                }
            }
            if (gameBoard.getPlayerLayer().getCell(xCoord,yCoord) != null){
                ArrayList<IPlayer> players = gameBoard.getPlayers();
                for(Integer i = 0; i < players.size(); i++){
                    IPlayer actor = players.get(i);
                    if(actor.getX() == xCoord && actor.getY() == yCoord){
                        actor.takeDamage(1);
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
    public void addToHand(Card card){
        hand.add(card);
    }
    public void playHand(Integer i){
        hand.get(i).playCard(this);
    }
    public void emptyHand(){
        hand = new ArrayList<>();
    }
    public void takeDamage(Integer amount){
        if (playerState != PlayerState.DEAD) {
            hp -= amount;
            if (hp <= 0) {
                setPlayerState(PlayerState.DEAD);
                hp = 0;
            }
            else if (hp > 9){
                hp = 9;
            }
        }
    }
    public void powerdown(){
        powerdown = true;
        hp = 9;
    }
    public void bootUp(){
        powerdown = false;
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
    public void respawn(Direction dir){
        // Checks if player is out of lives
        if (lives > 0) {
            // Removes player from location of death
            playerLayer.setCell(getX(),getY(),null);
            // Sets the coordinates and direction the player is facing when respawning in addition of setting the playerState to alive
            playerLoc.set(spawnPoint.cpy());
            playerDir = dir;
            hp = 9;
            setPlayerState(PlayerState.ALIVE);
            // Updates the player icons rotation
            updateIconRotation();
            // Subtracts the life lost
            lives--;
        }
        // Game Over mechanics not implemented yet
        else {
            // Game Over mechanics go here
            gameoversound.play();
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
    public Direction getPlayerDir(){
        return playerDir;
    }
    public void setPlayerDir(Direction dir){
        playerDir = dir;
    }
    public Integer getLives(){
        return lives;
    }
    public Integer getHp(){
        return hp;
    }
    public PlayerState getPlayerState(){
        return playerState;
    }
    public Integer getFlags(){
        return flagsVisited;
    }
    public void visitFlag(){
        flagsVisited++;
    }
    public void setSpawnPoint(Vector2 spawnVector) {
        spawnPoint.set(spawnVector.cpy());
    }
    public ArrayList<Card> getHand(){
        return hand;
    }
    public Boolean getPowerdown(){
        return powerdown;
    }
    public void setPowerdown(Boolean value){
        powerdown = value;
    }
    public Boolean getReady(){
        return ready;
    }
    public void setReady(Boolean value){
        ready = value;
    }
    public Vector2 getPlayerloc(){
        return playerLoc;
    }

    // This should be refactored:
    public void playFullHand() {

    }

    public void addCardvalues(Integer value){
        cardValues.add(value);
    }
    public void clearCardValues(){
        cardValues.clear();
    }
    public Integer getOneCardValue(Integer index){
        return cardValues.get(index);
    }
    public ArrayList<Integer> getCardValues(){
        return cardValues;
    }

    public Boolean isPlayer() {
        return player;
    }

    public Boolean isYou() {
        return you;
    }
}
