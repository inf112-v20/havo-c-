package inf112.skeleton.app;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;

import java.util.ArrayList;
import java.util.Random;

public class MainGameScreen extends InputAdapter implements Screen {

    Robo game;
    private BitmapFont font;
    Texture move1;
    Texture move2;
    Texture move3;
    Texture moveRight;
    Texture moveLeft;
    Texture startround;
    Texture resetcards;
    Texture poweroff;
    Texture heart;


    // Temp Arraylist to test a system
    ArrayList<Texture> cards = new ArrayList<Texture>();
    // Temp Arraylist Random numbers
    ArrayList<Integer> cardNumbers = new ArrayList<Integer>();
    // Temp this array contain the numbers 1 to 5
    ArrayList<Texture> pickedCards = new ArrayList<Texture>();

    // Temp Var for player, I think this variable should be put into player class
    int tempCardPick = 0;


    // Map related elements
    private OrthogonalTiledMapRenderer mapRenderer;
    public OrthographicCamera camera = new OrthographicCamera();
    private TmxMapLoader mapLoader = new TmxMapLoader();
    private Board gameBoard = new Board(mapLoader.load("assets/Testing Grounds.tmx"));

    // Variables for Player
    private Player player;
    private CardDeck deck = new CardDeck();

    // Width and Height og the grid
    private final int BOARD_WIDTH = gameBoard.getPlayerLayer().getWidth();
    private final int BOARD_HEIGHT = gameBoard.getPlayerLayer().getHeight();


    public MainGameScreen(Robo robo) {

        this.game = robo;
        move1 = new Texture("assets/Move1.png");
        move2 = new Texture("assets/move2.png");
        move3 = new Texture("assets/move3.png");
        moveRight = new Texture("assets/moveright.png");
        moveLeft = new Texture("assets/moveleft.png");
        startround = new Texture("assets/startround1.png");
        resetcards = new Texture("assets/resetcards1.png");
        poweroff = new Texture("assets/poweroff1.png");
        heart = new Texture("assets/heart.png");
        loadCards();
        fillPickedCards();
        Gdx.input.setInputProcessor(this);
    }


    @Override
    public void show() {
        font = new BitmapFont();
        font.setColor(Color.MAGENTA);
        // Input adapter shenanigans;

        // Code for setting up map
        camera.setToOrtho(false, BOARD_WIDTH, BOARD_HEIGHT);
        camera.update();
        mapRenderer = new OrthogonalTiledMapRenderer(gameBoard.getMap(), 1/300f);
        mapRenderer.setView(camera);
        // Code for defining player and start location
        Vector2 startLoc = new Vector2(0,0);
        player = new Player(startLoc, Direction.NORTH, gameBoard.getPlayerLayer());




    }


    private static final int BUTTON_HEIGHT = 50;
    private static final int BUTTON_WIDTH = 50;





    @Override
    public void render(float v) {

        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        // Code for drawing the GUI
        game.batch.begin();

        drawButtons();
        drawCards();

        game.batch.end();


        // Renders map
        mapRenderer.render();

        // When user touches the cards
        if(Gdx.input.getX() > 50 * 10 && Gdx.input.getX() < 50 * 13 &&
                Gdx.input.getY() > 50 * 4 && Gdx.input.getY() < 50 *7) {
            if (Gdx.input.justTouched()) {
               touchCards(Gdx.input.getX(), Gdx.input.getY());
            }
        }

        // When the user touches the buttons
        if(Gdx.input.getX() > 50 * 10 && Gdx.input.getX() < 50 * 13 &&
                Gdx.input.getY() > 50 * 9 && Gdx.input.getY() < 50 *10) {
            if (Gdx.input.justTouched()) {
                touchedButtons(Gdx.input.getX());
            }
        }


        //Sets in player
        player.updatePlayerIcon();

    }

    public boolean keyUp(int keycode) {
        // Not done yet, will get possiblity of returning False when illegal moves are coded in
        // Logic gate for movement related input
        if (keycode == Input.Keys.W){
            player.Move(player.getPlayerDir());
        }
        else if (keycode == Input.Keys.Q || keycode == Input.Keys.E) {
            if (keycode == Input.Keys.Q) {
                player.Turn(TurnDirection.LEFT);
            } else {
                player.Turn(TurnDirection.RIGHT);
            }
        }
        // Checks if player is dead before allowing player to respawn
        else if (player.getPlayerState() == PlayerState.DEAD && keycode == Input.Keys.R){
            // Barebones respawn system that feeds in start coordinates and direction, a better one will be developed later
            player.respawn(0,0, Direction.NORTH);
        }
        // Checks if player is standing on special tiles
        gameBoard.checkForSpecialTiles(player, Boolean.FALSE);


        return true;
    }

    public void doTurn(){
        if(player.powerdown){
            // Insert code for powerdown here
        }
        // Insert delay here
        deck.dealCards(player);
        // Insert delay here that allows players to choose their cards
        for(Integer i = 0; i<5; i++){
            // Must be improved to make card priority a thing
            player.playHand(i);
            // Must be improved so that the different parts act in the correct order
            gameBoard.checkForSpecialTiles(player, false);
        }
        // Insert cleanup phase here
        deck.collectCards(player);
    }


    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void drawCards() {

        // The start of where we want to place the cards, these will not change during the for-loop
        int x_pos = 10;
        int y_pos = 5;

        // These will make sure the cards end up in the right spot
        int x = 0;
        int y = 0;
        for(int i = 0; 9 > i; i++) {

            if(x == 3) {
                x = 0;
                y--;
            }
            game.batch.draw(cards.get(i), BUTTON_WIDTH * (x_pos + x), BUTTON_HEIGHT * (y_pos + y), 50, 50);
            x++;
        }
    }
    // This function will be changed. Works ATM to load cards into Array
    public void loadCards() {
        cardNumbers.clear();
        Random random = new Random();
        int upperbound = 5;
        int randomNumber;
        for (int i = 0; 9 > i; i++) {
            randomNumber = random.nextInt(upperbound);
            cardNumbers.add(randomNumber);
        }

        for (int i = 0; 9 > i; i++) {
            if(cardNumbers.get(i) == 0) {
                cards.add(move1);
            }
            else if (cardNumbers.get(i) == 1) {
                cards.add(move2);
            }
            else if (cardNumbers.get(i) == 2) {
                cards.add(move3);
            }
            else if (cardNumbers.get(i) == 3) {
                cards.add(moveRight);
            }
            else if (cardNumbers.get(i) == 4) {
                cards.add(moveLeft);
            }

        }
    }

    public void touchCards(int x,int y) {
        int cardX = 0;
        int cardY = 0;


        // These if-statments take care of x input and changes cardX to the correct column number(1-3)
        if(x >= BUTTON_WIDTH * 10 && x < BUTTON_WIDTH * 11) {
            cardX = 1;

        }
        else if(x >= BUTTON_WIDTH * 11 && x < BUTTON_WIDTH * 12) {
            cardX = 2;

        }
        else if (x >= BUTTON_WIDTH * 12 && x <= BUTTON_WIDTH * 13) {
            cardX = 3;

        }

        // These if-statments take care of y input and changes cardX to the correct column number(0-2)
        if(y >= BUTTON_HEIGHT * 4 && y < BUTTON_HEIGHT * 5) {
            cardY = 0;

        }
        else if(y >= BUTTON_HEIGHT * 5 && y < BUTTON_HEIGHT * 6) {
            cardY = 1;

        }
        else if (y >= BUTTON_HEIGHT * 6 && y <= BUTTON_HEIGHT * 7) {
            cardY = 2;

        }

        int cardXY = cardX + (cardY * 3) - 1;

        // Gives the right command depending on which card the user touched
        if(cardNumbers.get(cardXY) == 0) {
            player.Move(player.getPlayerDir());
        }
        else if (cardNumbers.get(cardXY) == 1) {

            player.Move(player.getPlayerDir());
            player.Move(player.getPlayerDir());
        }
        else if (cardNumbers.get(cardXY) == 2) {

            player.Move(player.getPlayerDir());
            player.Move(player.getPlayerDir());
            player.Move(player.getPlayerDir());

        }
        else if (cardNumbers.get(cardXY) == 3) {
            player.Turn(TurnDirection.RIGHT);
        }
        else if (cardNumbers.get(cardXY) == 4) {
            player.Turn(TurnDirection.LEFT);
        }

        gameBoard.checkForSpecialTiles(player, Boolean.FALSE);


        handleTouchedCards(cardXY);

    }

    // This function changes the touched cards to a number so that the user can pick 5 cards
    public void handleTouchedCards(int cardXY) {

        if(tempCardPick == 5) {

        }
        else {

            cards.set(cardXY, pickedCards.get(tempCardPick));


        }

        tempCardPick++;

    }

    public void drawButtons() {

        game.batch.draw(resetcards, BUTTON_WIDTH * 10, BUTTON_HEIGHT * 0, 50, 50);
        game.batch.draw(startround, BUTTON_WIDTH * 11, BUTTON_HEIGHT * 0, 50, 50);
        game.batch.draw(poweroff, BUTTON_WIDTH * 12, BUTTON_HEIGHT * 0, 50, 50);

        for (Integer i=0; i<player.getLives(); i++ ){
            game.batch.draw(heart, BUTTON_WIDTH *(10+i), BUTTON_HEIGHT * 9, 50, 50);
        }
    }

    public void touchedButtons(int x) {
        int cardX = 0;

        // These if-statments take care of x input and changes cardX to the correct column number(1-3)
        if(x >= BUTTON_WIDTH * 10 && x < BUTTON_WIDTH * 11) {
            cardX = 1;

        }
        else if(x >= BUTTON_WIDTH * 11 && x < BUTTON_WIDTH * 12) {
            cardX = 2;

        }
        else if (x >= BUTTON_WIDTH * 12 && x <= BUTTON_WIDTH * 13) {
            cardX = 3;

        }

        if(cardX == 1) {
            cards.clear();
            cardNumbers.clear();

            loadCards();
        }
        else if (cardX == 2) {
            System.out.println("Start Round");
        }
        else if (cardX == 3) {
            System.out.println("Power Down");

        }


    }

    private void fillPickedCards() {
        Texture one = new Texture("assets/pickedCards/One.png");
        Texture two = new Texture("assets/pickedCards/Two.png");
        Texture three = new Texture("assets/pickedCards/three.png");
        Texture four = new Texture("assets/pickedCards/four.png");
        Texture five = new Texture("assets/pickedCards/five.png");

        pickedCards.add(one);
        pickedCards.add(two);
        pickedCards.add(three);
        pickedCards.add(four);
        pickedCards.add(five);

    }

}
