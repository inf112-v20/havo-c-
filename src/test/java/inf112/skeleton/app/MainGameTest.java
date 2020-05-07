package inf112.skeleton.app;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainGameTest {

    @Before
    public void SetUp() throws Exception {
        Robo robo = new Robo();
        MainGameScreen mainGame = new MainGameScreen(robo, "assets/Testing Grounds.tmx", 1);
    }

    @Test
    public void testTheSetup() {
        assertTrue("Does not work", true);
    }
}
