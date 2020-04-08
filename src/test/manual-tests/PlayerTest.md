Set-up
- Open game, the player should see a menu screen with "Start" and "Exit".
---
Exit test
- Right-click the exit button, the game should quit.
---
Start test
- Right-click the start button, the game should start. 
The player should start at a 10x10 board on a 14x10 screen 
with cards and such on the right side of the map. The player
should start at the (0,0) coordinate.
---
Player move test
- Right click the arrow with a 1 , the player should move a single tile upwards.
- Right click the arrow with a 2, the player should move two tiles upwards.
- Right click the arrow with a 3, the player should move three tiles upwards.
- (Alternatively press W, the player icon should move a single tile upwards.)
---
Player turn test
- Restart Game
- Right click the arrow facing left, the player icon should be pointing left.
- Right click the arrow with a 1, the player should move one tile left, this 
signifies that turning the player turns the direction it moves when the arrow 
facing left is pressed.
- Right click the arrow facing right, the player icon should be pointing right.
- Right click the arrow with a 1, the player should move one tile right, this 
signifies that turning the player turns the direction it moves when the arrow 
facing right is pressed.
- Alternatively:
- Press E, the player icon should be pointing right.
- Press W, the player should move one tile right, this signifies
that turning the player turns the direction it moves when W is
pressed.
- Press Q, the icon should turn back upwards, signifying that
Q turns the player in the reverse direction than E.
- Press W, to test that the movement direction of the player has
been reverted to its original direction.
---
Player icon change test
- Restart Game
- Move the Player to the flag:
- Upon reaching the flag the playericon should change graphics
to a similar one with a blue outline
- Maneuver the Player into the hole on the top right side of the gamemap.
- Upon reaching the hole the playericon should change graphics
into a standard player icon with red eyes.
Additionally the player should no longer be able to turn or move
---
Player respawn mechanics test
- Restart Game
- Press R, this button is bound to the respawn method and
should only activate if the player is dead. It should not
do anything now, as the player is alive.
- Move the Player into a hole.
- When the player reaches the hole, the player should die and
this should reflect on the player icon. Additionally it should
not be able to move or turn.
- Press R, this should now remove the player from the hole and
replace him at the coordinates (0,0) facing up.
- Move the Player off the board.
- Press R, this should replace the player onto the board at
the same location as previously in facing the same direction.
- Repeat the two previous steps, the same things should occur.
- Repeat the steps again, this time pressing R should have no
effect as the player has spent his 3 lives and is unable to
respawn.
---
Turn Gear interaction test
- Move the player to a turning gear with green arrows going clockwise. 
- When entering the tile with that turning gear, the player icon should
rotate one step clockwise. 
- Move the player to a turning gear with red arrows going counter-clockwise.
- When entering the tile with that turning gear, the player icon should
rotate one step counter-clockwise. 
---
Conveyor belt interactions test
- Move the player to the yellow conveyor belt.
- When entering the tile with that conveyor belt, the player icon should
move one step in the direction the arrow is pointing. 
- Move the player to the blue conveyor belt.
- When entering the tile with that conveyor belt, the player icon should
move two steps in the direction the arrow is pointing. 
