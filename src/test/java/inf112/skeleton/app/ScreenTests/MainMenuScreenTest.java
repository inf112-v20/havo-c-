package inf112.skeleton.app.ScreenTests;


import inf112.skeleton.app.MainMenuScreen;
import inf112.skeleton.app.Robo;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class MainMenuScreenTest {
    private MainMenuScreen mainMenuScreenTest;
    @Before
    public void setUp() throws Exception {
        Robo robo = new Robo();
        mainMenuScreenTest = new MainMenuScreen(robo);

    }

    @Test
    public void testPlayButtonTexture() {
        //assertTrue("The texture for the Play Button has been changed or removed", mainMenuScreenTest.getPlayButton().toString() == "assets/buttons/startKnapp.png");
        //assertTrue("The texture for the Exit Button has been changed or removed", mainMenuScreenTest.exitButton.toString() == "assets/buttons/ExitBtn.png");
    }


    }

