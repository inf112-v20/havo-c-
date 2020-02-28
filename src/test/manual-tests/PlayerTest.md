Set-up
- Open game, the player should start at a 5x5 board with the
player at the (0,0) coordinates, a hole at the (3,3)
coordinates and a flag at (5,5) coordinates.

Player move test
- Press W, the player icon should move a single tile upwards.

Player turn test
- Restart Game
- Press W, the player icon should move a single tile upwards.
- Press E, the player icon should turn so that the head of the
owl of the player icon should be pointing right.
- Press W, the player should move one tile right, this signifies
that turning the owl turns the direction it moves when W is
pressed.
- Press Q, the icon should turn back upwards, signifying that
Q turns the player in the reverse direction than E.
- Press W, to test that the movement direction of the owl has
been reverted to its original direction.

Player icon change test
- Restart Game
- Use the following commands to move the Player to the flag:
4xW, E, 4xW
- Upon reaching the flag the playericon should change graphics
to a similar one with blue firework-like additions
- Maneuver the Player into the hole in the middle of the gamemap
with the following commands: E, 2xW, E, 2xW
- Upon reaching the hole the playericon should change graphics
into a standard player icon with red X in front of the eyes.
Additionally the player should no longer be able to turn or move

Player respawn mechanics test
- Restart Game
- Press W, this should move the player one tile upwards
- Press R, this button is bound to the respawn method and
should only activate if the player is dead. It should not
do anything now, as the player is alive.
- Use the following commands to move the Player into a hole:
W, E, 2xW.
- When the player reaches the hole, the player should die and
this should reflect on the player icon. Additionally it should
not be able to move or turn.
- Press R, this should now remove the player from the hole and
replace him at the coordinates (0,0) facing up.
- Use the following commands to move the Player off the board:
Q, W.
- Press R, this should replace the player onto the board facing
up.
- Repeat the two previous steps, the same things should occur.
- Repeat the steps again, this time pressing R should have no
effect as the player has spent his 3 lives and is unable to
respawn.