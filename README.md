# INF112 RoboRally Documentation 
The game is started by running the code from src/main/java/inf112.skeleton.app/Main.java.

This will open a menu with a "Start" and an "Exit" button. Pressing start will start up the game.
Pressing exit will close the application window.

When start is pressed, the screen will change to the gamescreen. Here the player can move with
either the keys "q", "w" or "e" or the cards on the side of the screen. "q" and "e" will turn
the player left and right respectively while "w" will move the player into the direction it is
pointing. The cards can be used similarly. The ones pointing upward with numbers in them move
the player the specified amount of spaces into the direction the player is pointing. The arrows
pointing to the sides will turn the player left and right based on what direction the arrow is
pointing.

Our manual-tests are located in test/manual-tests and the Junit ones are located in
test/java/inf112.skeleton.app.

## Known bugs
Currently throws "WARNING: An illegal reflective access operation has occurred", 
when the java version used is >8. This has no effect on function or performance, and is just a warning.

