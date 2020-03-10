package inf112.skeleton.app;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;

public class ScreenSwitcher extends Game {

    private LoadingScreen loadingScreen;
    private MainMenuScreen mainMenuScreen;
    private Game game;
    //private EndScreen endScreen;

    public final static int MENU = 0;
    public final static int GAME = 1;


    @Override
    public void create () {
        //loadingScreen = new LoadingScreen(this);
        //setScreen(loadingScreen);
        mainMenuScreen = new MainMenuScreen(this);
        setScreen(mainMenuScreen);

    }



    public void changeScreen(int screen) {
        switch (screen) {
            case MENU:
                if (mainMenuScreen == null) mainMenuScreen = new MainMenuScreen(this); // added (this)
                this.setScreen(mainMenuScreen);
                break;
        }
    }

}
