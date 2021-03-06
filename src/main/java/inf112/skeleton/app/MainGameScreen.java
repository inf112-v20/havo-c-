package inf112.skeleton.app;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import inf112.skeleton.app.Enums.Direction;
import inf112.skeleton.app.Enums.PlayerState;
import inf112.skeleton.app.Enums.TurnDirection;
import inf112.skeleton.app.Interfaces.IPlayer;


import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;


public class MainGameScreen extends InputAdapter implements Screen {

    Robo game;
    GUI gui;
    private BitmapFont font;


    // Map related elements
    private OrthogonalTiledMapRenderer mapRenderer;
    public OrthographicCamera camera = new OrthographicCamera();
    private TmxMapLoader mapLoader = new TmxMapLoader();

    private Board gameBoard;
    private Integer register = 0;

    // Variables for Player
    private IPlayer player;
    private MonkeyAI monkey;
    public ArrayList<IPlayer> players;


    Random rand;
    DirCtrl dirCtrl = new DirCtrl();
    private ArrayList<Card> guiCardhand = new ArrayList<Card>();

    // Width and Height og the grid
    private int board_width;
    private int board_height;


    private  int rightRow1;
    private int rightRow4;
    private int middleRow;

    // Array for tracking all the players
    public ArrayList<Vector2> allLoc = new ArrayList<Vector2>();

    // Variables for handling AI
    private boolean aiHavemadeThepicks = false;

    private Music backgroundSound = Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/backgroundloop.mp3"));
    private Sound collisionsound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/bruh2.wav"));

    // Which order cards should be played in
    ArrayList<Card> cardOrder = new ArrayList<>();
    private int cardOrderIndex = 0;
    private int roundNumber = 0;

    public MainGameScreen(Robo robo, String mapName, int numberOfEnemies) {

        this.game = robo;

        gameBoard = new Board(mapLoader.load(mapName));

        player = new Player(gameBoard.getSpawnPoint(1).getSpawnLoc(), Direction.NORTH, this);
        monkey = new MonkeyAI(gameBoard.getSpawnPoint(2).getSpawnLoc(), Direction.NORTH, this);

        board_width = gameBoard.getBoard().getWidth();
        board_height = gameBoard.getBoard().getHeight();

        rightRow1 = board_width - 1;
        rightRow4 = board_width - 4;
        middleRow = board_height/2;

        setRightScreenSize();
        Gdx.input.setInputProcessor(this);
        gui = new GUI(game, player, gameBoard);

        players = new ArrayList<IPlayer>();
        players.add(player);
        players.add(monkey);
        addLoc();
        rand = new Random();
        handleCardValues();
        gameBoard.acquirePlayers(players);
    }

    @Override
    public void show() {
        font = new BitmapFont();
        font.setColor(Color.MAGENTA);
        // Input adapter shenanigans;

        // Code for setting up map
        camera.setToOrtho(false, board_width, board_height);
        camera.update();
        mapRenderer = new OrthogonalTiledMapRenderer(gameBoard.getMap(), 1/300f);
        mapRenderer.setView(camera);
        // Code for defining player and start location
    }

    private static final int BUTTON_WIDTH = 50;
    @Override
    public void render(float v) {

        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        // Code for drawing the GUI
        game.batch.begin();

        gui.drawButtons();
        gui.drawCards();
        gui.drawHealthbar(player.getHp());

        game.batch.end();

        // Renders map
        mapRenderer.render();

        backgroundSound.play();
        backgroundSound.setVolume(0.03f);
        backgroundSound.setLooping(true);

        // When user touches the cards
        if(Gdx.input.getX() > BUTTON_WIDTH * rightRow4 && Gdx.input.getX() < 50 * rightRow1 &&
                Gdx.input.getY() > BUTTON_WIDTH * (middleRow - 1) && Gdx.input.getY() < BUTTON_WIDTH * (middleRow + 2)) {
            if (Gdx.input.justTouched()) {
               gui.touchCards(Gdx.input.getX(), Gdx.input.getY());
            }
        }

        // When the user touches the buttons
        if(Gdx.input.getX() > BUTTON_WIDTH * rightRow4 && Gdx.input.getX() < BUTTON_WIDTH * rightRow1 &&
                Gdx.input.getY() > BUTTON_WIDTH * (middleRow + 4) && Gdx.input.getY() < BUTTON_WIDTH *(middleRow + 5)) {
            if (Gdx.input.justTouched()) {
                gui.touchedButtons(Gdx.input.getX());
            }
        }

    // probably want to be a function
        if (!aiHavemadeThepicks) {
            System.out.println("AI making all the card picks");
            monkey.makeMonkeyHand();
            monkey.pickAllCards();
            aiHavemadeThepicks = true;

        }

        if(player.getReady()) {
            player.setReady(false);
            doTurn();
        }

        for (Integer i = 0; i < players.size(); i++){
            players.get(i).updatePlayerIcon();
        }

        if (player.getPlayerState() == PlayerState.WINNER || monkey.getLives() == 0) {
            game.setScreen(new MainVictoryScreen(game));
            this.dispose();
        }
        if (player.getLives() == 0 || monkey.getPlayerState() == PlayerState.WINNER) {
            game.setScreen(new MainGameOverScreen(game));
            this.dispose();
        }

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
        else if (keycode == Input.Keys.L){
            player.fireLaser();
        }
        else if (keycode == Input.Keys.K){
            gameBoard.fireAllLasers();
        }
        else if (keycode == Input.Keys.D) {
            player.takeDamage(1);
            if(player.getHp() < 5) {
                gui.handleBurntCards(player.getHp());
            }
        }
        // Monkey test movement
        if (keycode == Input.Keys.DPAD_UP){
            monkey.Move(monkey.getPlayerDir());

        }
        else if (keycode == Input.Keys.DPAD_LEFT || keycode == Input.Keys.DPAD_RIGHT) {
            if (keycode == Input.Keys.DPAD_LEFT) {
                monkey.Turn(TurnDirection.LEFT);
            } else {
                monkey.Turn(TurnDirection.RIGHT);
            }
        }
        // Checks if player is dead before allowing player to respawn
        else if (player.getPlayerState() == PlayerState.DEAD && keycode == Input.Keys.R){
            // Barebones respawn system that feeds in start coordinates and direction, a better one will be developed later
            player.respawn(Direction.NORTH);
            gui.resestBurntCards();
            gui.resetcards();
        }
        // Checks if player is standing on special tiles
        for (Integer i = 0; i < players.size(); i++){
            gameBoard.checkForSpecialTiles(players.get(i));
        }
        updateLoc();

        return true;
    }

    public void doTurn() {
        player.setReady(false);

        if (player.getPowerdown()) {
            // Insert code for powerdown here
        }
        // Insert delay here
        gui.tempCardPick = 0;

        gui.cards.clear();

        for (int j = 0; j < 5; j++) {
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    int playerHpBefore = player.getHp();
                    for (Integer i = 0; i < players.size(); i++) {
                        IPlayer currentActor = players.get(i);
                        //Playing cards
                        currentActor.playHand(register);
                        if (currentActor.equals(player)) {
                            System.out.println(currentActor.getHand().get(register).getCommand());
                        } else {
                            aiHavemadeThepicks = false;
                        }
                        // Burnt in cards system
                        if (player.getHp() < playerHpBefore && player.getHp() <= 5) {
                            gui.handleBurntCards(player.getHp());
                        }
                    }
                    for (int i = 0; players.size() > i; i++) {
                        IPlayer currentActor = players.get(i);
                        gameBoard.checkForSpecialTiles(currentActor);
                    }
                    register++;
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            gameBoard.fireAllLasers();
                            for (int i = 0; players.size() > i; i++) {
                                IPlayer currentActor = players.get(i);
                                if (currentActor.getPlayerState() == PlayerState.ALIVE) {
                                    currentActor.fireLaser();
                                }
                            }
                        }
                    }, 1);
                }
            },2*j);
        }

        gui.cardHand.clear();
        gui.cards.clear();
        gui.selectedCards.clear();
        gui.indexSelectedCards.clear();

        gui.deck.shuffleDeck();
        gui.loadCards();

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                player.getHand().clear();

                player.clearCardValues();
                handleCardValues();

                if (player.getPowerdown()){
                    player.bootUp();
                }

                for (int i = 0; players.size() > i; i++){
                    if (players.get(i) != null) {
                        IPlayer currentActor = players.get(i);
                        if (currentActor.getPlayerState() == PlayerState.DEAD) {
                            currentActor.respawn(currentActor.getPlayerDir());
                        }
                    }
                }
                register = 0;
            }
        }, 11);
    }

    // Updates all the location in allLoc to the current position of the players
    private void updateLoc() {
        for(int i = 0; i < players.size(); i++) {
            allLoc.set(i, players.get(i).getPlayerloc());
        }
        System.out.println("playerloc: " + allLoc);
    }
    private void addLoc() {
        for(int i = 0; i < players.size(); i++) {
            allLoc.add(i, players.get(i).getPlayerloc());
        }
        System.out.println("playerloc: " + allLoc);
    }
    private Boolean checkForPlayerCollision(IPlayer movingPlayer) {
        int movingPlayerInt = players.indexOf(movingPlayer);
        for(int i = 0;  i < players.size(); i++) {
            if(i != movingPlayerInt && players.get(i).getPlayerloc().equals(movingPlayer.getPlayerloc())) {

                System.out.println("collision");
                return true;
            }
        }
        return false;
    }

    private IPlayer getCollisionVictim(IPlayer movingPlayer) {
        int movingPlayerInt = players.indexOf(movingPlayer);
        for (int i = 0; i < players.size(); i++) {
            if (i != movingPlayerInt && players.get(i).getPlayerloc().equals(movingPlayer.getPlayerloc())) {
                //Returns collision victim
                return players.get(i);
            }
        }
        //Returns the moving player
        return movingPlayer;
    }

    private void handleCollision(IPlayer movingPlayer) {
        IPlayer collisionVictim = getCollisionVictim(movingPlayer);
        Direction movingPlayerDir = movingPlayer.getPlayerDir();
        System.out.println("movingplayer" + movingPlayer);
        System.out.println("collisionVictim: " + collisionVictim);
        if (gameBoard.wallCheck(collisionVictim.getX(), collisionVictim.getY(), movingPlayerDir,false)) {
            collisionVictim.Move(movingPlayerDir);
        }
        else {
            movingPlayer.Move(dirCtrl.invertDirection(movingPlayerDir));
        }
    }
    public void collision(IPlayer movingPlayer) {
        if(checkForPlayerCollision(movingPlayer)) {
            handleCollision(movingPlayer);
            collisionsound.play();
        }
    }
    public void handleCardValues() {

        for(int i = 0; i < players.size(); i++) {

            if(players.get(i).equals(player)) {
                guiCardhand = gui.getCardHand();
                for (int p = 0; p < guiCardhand.size(); p++) {

                    player.addCardvalues(cardValue(guiCardhand.get(p)));

                }
            }
            else {
                for (int j = 0; j < players.get(i).getHand().size(); j++) {
                    players.get(i).addCardvalues(cardValue(players.get(i).getHand().get(j)));

                }
            }
        }
    }

    /*
    backup: 6 kort (430 - 480)
    u-turn: 6 kort (10 - 60)
    rotate right: 18 kort (80-420, intervall 20)
    rotate left: 18 kort (70-410, intervall 20)
    move 1: 18 kort (490 - 650, intervall 10)
    move 2: 12 kort (670 - 780, intervall 10)
    move 3: 6 kort (790 - 840, intervall 10)
     */
    private Integer cardValue(Card card) {

        int value = 0;

        if(card.getCommand() == "Move1") {
            value = 490;
            value = value + rand.nextInt(16) * 10;
        }
        else if (card.getCommand() == "Move2") {
            value = 670;
            value = value + rand.nextInt(11) * 10;
        }
        else if (card.getCommand() == "Move3") {
            value = 790;
            value =value + rand.nextInt(5) * 10;
        }
        else if (card.getCommand() == "TurnRight") {
            value = 80;
            value = value + rand.nextInt(34) * 10;
        }
        else if (card.getCommand() == "TurnLeft") {
            value = 70;
            value = value + rand.nextInt(34) * 10;
        }
        else if (card.getCommand() == "MoveBack") {
            value = 430;
            value = value + rand.nextInt(5) * 10;
        }
        else if (card.getCommand() == "Turn180") {
            value = 10;
            value = value + rand.nextInt(5) * 10;
        }

        return value;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    private void setRightScreenSize() {
        int screenWidth = board_width * BUTTON_WIDTH;
        int screenHeight = board_height * BUTTON_WIDTH;
        Gdx.graphics.setWindowedMode(screenWidth, screenHeight);
        game.batch.getProjectionMatrix().setToOrtho2D(0, 0, screenWidth, screenHeight);

    }
    private void handelCardOrder() {
        ArrayList<Card> oneTurn = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            // Insert delay here that allows players to choose their cards
            for (int j = 0; j < players.size(); j++) {
                oneTurn.add(players.get(j).getHand().get(i));
            }
            cardOrder.addAll(sortOneTurn(oneTurn));
            oneTurn.clear();
        }
    }

    private ArrayList<Card> sortOneTurn(ArrayList<Card> unordered) {

        System.out.println("unordered size " + unordered.size());
        Card temp;
        for (int i = 0; i < unordered.size() - 1; i++) {
            System.out.println(unordered.size());
            int min_index = i;
            for(int j = i + 1; j < unordered.size(); j++) {
                if(unordered.get(j).getValue() < unordered.get(min_index).getValue()) {
                    min_index = j;
                }
                System.out.println("ping");
                temp = unordered.get(min_index);
                unordered.set(min_index, unordered.get(i));
                unordered.set(i, temp);
            }
        }
        ArrayList<Card> orderedList = new ArrayList<>();
        orderedList.addAll(unordered);
        Collections.reverse(orderedList);
        return orderedList;
    }
    private void playCard() {
        cardOrder.get(cardOrderIndex).playCard(cardOrder.get(cardOrderIndex).getOwner());
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
}