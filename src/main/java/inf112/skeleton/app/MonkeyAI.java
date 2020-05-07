package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;


import java.util.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MonkeyAI implements IPlayer{
    // Variables of the player robot
    private Integer lives = 3;
    private Integer hp = 9;
    private Boolean powerdown = false;
    private ArrayList<Card> hand = new ArrayList<>();
    private Integer flagsVisited = 0;
    private Boolean ready = false;
    private MainGameScreen game;
    private Boolean player = true;
    private Boolean you = false;
    private Board gameBoard;
    private Vector2 spawnPoint = new Vector2();
    // Control class
    private DirCtrl dirController = new DirCtrl();
    // Elements the Player needs to function on the board
    private Vector2 playerLoc;
    private Direction playerDir;
    private PlayerState playerState = PlayerState.ALIVE;
    private TiledMapTileLayer playerLayer;
    // Player icon objects
    private TiledMapTileLayer.Cell playerCell = new TiledMapTileLayer.Cell();
    private TiledMapTileLayer.Cell playerDiedCell = new TiledMapTileLayer.Cell();
    private TiledMapTileLayer.Cell playerWonCell = new TiledMapTileLayer.Cell();




    // Card Values
    private ArrayList<Integer> cardValues= new ArrayList ();


    // Variables just for the AI
    CardDeck monkeyCardDeck;
    private ArrayList<Card> smallHandCardDeck = new ArrayList();
    Guineapig guineapig;
    private ArrayList<Card> pickedCards = new ArrayList();
    private ArrayList<Integer> indexPickedCards = new ArrayList<Integer>();
    Vector2 oldCoordinates;
    Direction oldDirection = Direction.NORTH;

    //private Sound AIwinsound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/gameover.wav"));
    //private Sound AIlosesound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/victory.wav"));

    // Constructor
    public MonkeyAI(Vector2 location, Direction dir, MainGameScreen game){
        this.playerLoc = location;
        this.playerDir = dir;
        this.game = game;
        this.gameBoard = game.getGameBoard();
        this.playerLayer = gameBoard.getPlayerLayer();
        // Graphics for the player
        TextureRegion[][] playerIcon = new TextureRegion(new Texture("assets/enemy.png")).split(300,300);
        playerCell.setTile(new StaticTiledMapTile(playerIcon[0][0]));
        playerDiedCell.setTile(new StaticTiledMapTile(playerIcon[0][1]));
        playerWonCell.setTile(new StaticTiledMapTile(playerIcon[0][2]));
        oldDirection = dir;
        Direction guineapigDir = Direction.NORTH;
        Vector2 guineapigStartLoc = new Vector2(location.cpy());
        oldCoordinates = new Vector2((location.cpy()));

        monkeyCardDeck = new CardDeck();
        guineapig = new Guineapig(guineapigStartLoc, guineapigDir, gameBoard, game);

        spawnPoint.set(playerLoc.cpy());
        SmarterMonkey smarterMonkey = new SmarterMonkey(game, this);

    }

    public void Move(Direction dir) {
        // Moves the player forward based on the player direction
        // Only allows movement if player is alive
        if (playerState != PlayerState.DEAD) {
            // Player location coordinates
            Integer xLoc = getX();
            Integer yLoc = getY();
            //Clears player icon from old location
            if (gameBoard.wallCheck(xLoc,yLoc,dir,false)) {
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
                if (getX() < 0 || getX() > (playerLayer.getWidth() - 1) || getY() < 0 || getY() > playerLayer.getHeight()) {
                    setPlayerState(PlayerState.DEAD);
                }
                gameBoard.checkHoles(this);
            }
            gameBoard.checkFlags(this, getX(), getY());
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

    }
    public void addToHand(Card card){
        hand.add(card);
    }
    public void playHand(Integer i){
        pickedCards.get(i).playCard(this);
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
    public Integer getLives(){
        return lives;
    }
    public Integer getHp(){
        return hp;
    }
    public PlayerState getPlayerState(){
        return playerState;
    }

    public Integer getFlags() {
        return flagsVisited;
    }
    public void visitFlag() {
        flagsVisited++;
    }

    public ArrayList<Card> getHand() {
        return pickedCards;
    }
    public Boolean getPowerdown() { return powerdown; }
    public void setPlayerDir(Direction dir) {playerDir = dir; }
    public void setPowerdown(Boolean value) {
        powerdown = value;
    }
    public Boolean getReady() {
        return ready;
    }

    public void setReady(Boolean value) {
        ready = value;
    }

    public Vector2 getPlayerloc() { return playerLoc; }

    public void addCardvalues(Integer value) {
        cardValues.add(value);
    }
    public void clearCardValues() {
        cardValues.clear();
    }
    public Integer getOneCardValue(Integer index) {
        return cardValues.get(index);
    }
    public ArrayList<Integer> getCardValues() {
        return cardValues;
    }

    public Boolean isPlayer() {
        return player;
    }

    public Boolean isYou() {
        return you;
    }

    public void setSpawnPoint(Vector2 spawnVector) {
        spawnPoint.set(spawnVector.cpy());
    }

    // Everything about how the monkeyAI thinks/works should be added under this section

    // Adds to small hand in CardDeck the 9 cards the monkey can pick amongst
    public void makeMonkeyHand() {
        smallHandCardDeck.clear();
        indexPickedCards.clear();
        pickedCards.clear();


        resetGuineapig();
        monkeyCardDeck.shuffleDeck();
        smallHandCardDeck = monkeyCardDeck.dealCards(this);
    }

    private void resetCard() {
        monkeyCardDeck.shuffleDeck();
        smallHandCardDeck.clear();
        makeMonkeyHand();
    }

    public void makeOneCardPick() {
        guineapig.setLocation(playerLoc.cpy());
        oldCoordinates.set(playerLoc.cpy());
        for(int i = 0; i < hp; i++) {
            if(!indexPickedCards.contains(i)) {

                if(cardCanNotKill(smallHandCardDeck.get(i))) {
                    System.out.println("Card added: " + smallHandCardDeck.get(i).getCommand());
                    pickedCards.add(smallHandCardDeck.get(i));
                    indexPickedCards.add(i);
                    break;
                }
;
            }
            else {
                if(indexPickedCards.contains(i)) {
                    System.out.println("Denied cards: " + smallHandCardDeck.get(i).getCommand() + " Since the card is already picked");
                }
                else {
                    System.out.println("Denied cards: " + smallHandCardDeck.get(i).getCommand());
                }
            }

        }
    }

    private Boolean cardCanNotKill(Card card) {
        if(cardKillsGuineapig(card)) { return true; }
        else { return false; }
    }

    public void pickAllCards() {
        for(int i = 0; i < 5; i++){

            makeOneCardPick();

        }

    }

    private void resetGuineapig() {
        guineapig.setPlayerDir(oldDirection);
        guineapig.setLocation(getOldCoordinates().cpy());
        guineapig.setPlayerState(playerState.ALIVE);
    }

    private Boolean cardKillsGuineapig(Card card) {
        System.out.println("Player location " + playerLoc + " old Location " + oldCoordinates + " guineapig location " + guineapig.getPlayerloc());
        Card guineapigCard = new Card(card.getCommand());

        guineapigCard.playCard(guineapig);


        if(guineapig.getPlayerState() == playerState.ALIVE && insideMap()) {

            oldCoordinates.set(guineapig.getPlayerloc().cpy());
            oldDirection = guineapig.getPlayerDir();

            return true; }
        else {

            resetGuineapig();
            return false; }
    }
    private Boolean insideMap() {
        if(guineapig.getX() >= 0 && guineapig.getX() <= 9
            && guineapig.getY() >= 0 && guineapig.getY() <= 9) {

            return true;
        }
        else {

            return  false;
        }
    }

    public void playFullHand() {

        for(int i = 0; i < pickedCards.size(); i++) {
            pickedCards.get(i).playCard(this);
            setOldCoordinates();

        }
        pickedCards.clear();
    }
    private void setOldCoordinates() {

        oldCoordinates.set(playerLoc);
    }
    private Vector2 getOldCoordinates() {
        return oldCoordinates;
    }
    private void smarterAI() {

    }

}
