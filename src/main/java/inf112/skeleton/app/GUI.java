package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.ArrayList;

public class GUI {
    Robo game;
    IPlayer player;
    // Dimensions of the buttons
    private static final int BUTTON_HEIGHT = 50;
    private static final int BUTTON_WIDTH = 50;
    // Textures
    Texture move1;
    Texture move2;
    Texture move3;
    Texture moveRight;
    Texture moveLeft;
    Texture moveback;
    Texture turn180;
    Texture startround;
    Texture resetcards;
    Texture poweroff;
    Texture heart;
    // Arraylist filled with the textures to the cards
    ArrayList<Texture> cards = new ArrayList<Texture>();
    // Arraylist Random numbers
    ArrayList<Card> cardHand = new ArrayList<>();
    // Temp this array contain the numbers 1 to 7
    ArrayList<Texture> pickedCards = new ArrayList<Texture>();
    // Temp this array contains which cards the user have selected
    ArrayList<Texture> selectedCards = new ArrayList<Texture>();
    // Temp this array contains the index of the selected cards
    ArrayList<Integer> indexSelectedCards = new ArrayList<Integer>();
    // This array will contain the graphics for the healthbar
    public ArrayList<Texture> healthbar = new ArrayList<Texture>();

    private BitmapFont font;
    // Temp Var for player, I think this variable should be put into player class
    int tempCardPick = 0;
    // Map related elements
    private OrthogonalTiledMapRenderer mapRenderer;
    public OrthographicCamera camera = new OrthographicCamera();
    private TmxMapLoader mapLoader = new TmxMapLoader();
    private Board gameBoard = new Board(mapLoader.load("assets/Testing Grounds.tmx"));
    // Variables for Player
    public CardDeck deck = new CardDeck();
    // Width and Height og the grid
    private final int BOARD_WIDTH = gameBoard.getPlayerLayer().getWidth();
    private final int BOARD_HEIGHT = gameBoard.getPlayerLayer().getHeight();

    public GUI (Robo robo, IPlayer player) {
        this.game = robo;
        this.player = player;
        // Loads textures
        move1 = new Texture("assets/cards/Move1.png");
        move2 = new Texture("assets/cards/move2.png");
        move3 = new Texture("assets/cards/move3.png");
        moveRight = new Texture("assets/cards/moveright.png");
        moveLeft = new Texture("assets/cards/moveleft.png");
        moveback = new Texture("assets/cards/moveback.png");
        turn180 = new Texture("assets/cards/turn180.png");
        startround = new Texture("assets/buttons/startround1.png");
        resetcards = new Texture("assets/buttons/resetcards1.png");
        poweroff = new Texture("assets/buttons/poweroff1.png");
        heart = new Texture("assets/heart.png");
        loadCards();
        fillPickedCards();
        fillhealthbar();

        font = new BitmapFont();
        font.setColor(Color.MAGENTA);
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
            font.draw(game.batch, player.getOneCardValue(i).toString(), BUTTON_WIDTH * (x_pos + x) + 25, BUTTON_HEIGHT * (y_pos + y) + 10);
            x++;
        }
    }
    // This function loads the right cards into the hand the player can pick amongst, then loads the right textures into
    // an new array "cards" that contains the right texture for the right command in the right order.
    public void loadCards() {
        // cardHand is the Arraylist for the cards/commands, which the player can pick amongst
        // Clears the hand so we are 100% sure non of the cards from last round is there.
        cardHand.clear();
        // Should reload the new cards into the cardHand
        cardHand = deck.dealCards(player);
        // This for-loop loads the right texture in the right order into cards (which is the array list for the textures)
        for (int i = 0; 9 > i; i++) {
            if(cardHand.get(i).getCommand() == "Move1") {
                cards.add(move1);
            }
            else if (cardHand.get(i).getCommand() == "Move2") {
                cards.add(move2);
            }
            else if (cardHand.get(i).getCommand() == "Move3") {
                cards.add(move3);
            }
            else if (cardHand.get(i).getCommand() == "TurnRight") {
                cards.add(moveRight);
            }
            else if (cardHand.get(i).getCommand() == "TurnLeft") {
                cards.add(moveLeft);
            }
            else if (cardHand.get(i).getCommand() == "MoveBack") {
                cards.add(moveback);
            }
            else if (cardHand.get(i).getCommand() == "Turn180") {
                cards.add(turn180);
            }
        }
    }

    public void touchCards(int x,int y) {
        int cardX = 0;
        int cardY = 0;
        // These if-statements take care of x input and changes cardX to the correct column number(1-3)
        if (x >= BUTTON_WIDTH * 10 && x < BUTTON_WIDTH * 11) {
            cardX = 1;
        } else if (x >= BUTTON_WIDTH * 11 && x < BUTTON_WIDTH * 12) {
            cardX = 2;
        } else if (x >= BUTTON_WIDTH * 12 && x <= BUTTON_WIDTH * 13) {
            cardX = 3;
        }
        // These if-statements take care of y input and changes cardX to the correct column number(0-2)
        if (y >= BUTTON_HEIGHT * 4 && y < BUTTON_HEIGHT * 5) {
            cardY = 0;
        } else if (y >= BUTTON_HEIGHT * 5 && y < BUTTON_HEIGHT * 6) {
            cardY = 1;
        } else if (y >= BUTTON_HEIGHT * 6 && y <= BUTTON_HEIGHT * 7) {
            cardY = 2;
        }
        int cardXY = cardX + (cardY * 3) - 1;
        handleTouchedCards(cardXY);

    }
    public void movePlayer(int cardXY){
        // Gives the right command depending on which card the user touched
        Card activeCard = cardHand.get(cardXY);
        activeCard.playCard(player);
        gameBoard.checkForSpecialTiles(player);
        handleTouchedCards(cardXY);
    }

    // This function changes the touched cards to a number so that the user can pick 5 cards
    public void handleTouchedCards(int cardXY) {
        if(tempCardPick == 5 || player.getPlayerState() != PlayerState.ALIVE || indexSelectedCards.contains(cardXY)) {
            System.out.println("stopper");
        }
        else {
            // Adds the Texture and integer of touched card into a Arraylist
            selectedCards.add(cards.get(cardXY));
            indexSelectedCards.add(cardXY);

            cards.set(cardXY, pickedCards.get(tempCardPick));

            player.addToHand(cardHand.get(cardXY));
            // movePlayer(cardXY);
            tempCardPick++;
        }
    }


    public void drawButtons() {
        // Draws
        game.batch.draw(resetcards, BUTTON_WIDTH * 10, BUTTON_HEIGHT * 0, 50, 50);
        game.batch.draw(startround, BUTTON_WIDTH * 11, BUTTON_HEIGHT * 0, 50, 50);
        game.batch.draw(poweroff, BUTTON_WIDTH * 12, BUTTON_HEIGHT * 0, 50, 50);

        for (Integer i=0; i<player.getLives(); i++ ){
            game.batch.draw(heart, BUTTON_WIDTH *(10+i), BUTTON_HEIGHT * 9, 50, 50);
        }
    }
    public void drawHealthbar(int life) {
        game.batch.draw(healthbar.get(life), BUTTON_WIDTH * 10, BUTTON_HEIGHT *8, BUTTON_WIDTH * 3, BUTTON_HEIGHT - 20);
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
        // Reset cards
        if(cardX == 1) {
            for (int i = 0; tempCardPick > i; i++) {
                cards.set(indexSelectedCards.get(i), selectedCards.get(i));
            }
            tempCardPick = 0;
            selectedCards.clear();
            indexSelectedCards.clear();
        }
        // Start round
        else if (cardX == 2) {
            System.out.println();
            System.out.println();
            System.out.println("######################################################################################");
            System.out.println();
            System.out.println();
            player.setReady(true);
        }
        // power down
        else if (cardX == 3) {
            System.out.println("Power Down");
            player.powerdown();

            player.getHand();
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
    private void fillhealthbar() {
        // Loads in the textures
        Texture Healthbar_0 = new Texture("assets/healthbar/Healthbar_0.png");
        Texture Healthbar_1 = new Texture("assets/healthbar/Healthbar_1.png");
        Texture Healthbar_2 = new Texture("assets/healthbar/Healthbar_2.png");
        Texture Healthbar_3 = new Texture("assets/healthbar/Healthbar_3.png");
        Texture Healthbar_4 = new Texture("assets/healthbar/Healthbar_4.png");
        Texture Healthbar_5 = new Texture("assets/healthbar/Healthbar_5.png");
        Texture Healthbar_6 = new Texture("assets/healthbar/Healthbar_6.png");
        Texture Healthbar_7 = new Texture("assets/healthbar/Healthbar_7.png");
        Texture Healthbar_8 = new Texture("assets/healthbar/Healthbar_8.png");
        Texture Healthbar_full = new Texture("assets/healthbar/Healthbar_full.png");
        // Adds the textures into the arraylist
        healthbar.add(Healthbar_0);
        healthbar.add(Healthbar_1);
        healthbar.add(Healthbar_2);
        healthbar.add(Healthbar_3);
        healthbar.add(Healthbar_4);
        healthbar.add(Healthbar_5);
        healthbar.add(Healthbar_6);
        healthbar.add(Healthbar_7);
        healthbar.add(Healthbar_8);
        healthbar.add(Healthbar_full);
    }
    public ArrayList<Card> getCardHand () {
        return cardHand;
    }
    private void drawCardvalue() {

    }

}
