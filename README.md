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

**In Game lobby you can pick among 4 maps**

Cluster Cross and Hell have little bug testing and can be somewhat buggy.

You can only play against 1 enemy in current version.

**Other ways to controll the player**
The GUI on the side gives you 9 card options, you can pick 5 and then start the turn, the player and enemy will move accordingly.

**D button:** player takes 1 damaged

**L button:** fire player laser
**K button:** fire all lasers
**R button:* respawn

Our manual-tests are located in test/manual-tests and the Junit ones are located in
test/java/inf112.skeleton.app.

## Known bugs
Currently throws "WARNING: An illegal reflective access operation has occurred", 
when the java version used is >8. This has no effect on function or performance, and is just a warning.

More bugs described in ObligatoriskOppgave4.md in Deliverables.
