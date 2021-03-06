# In Game Functions tests

### Test: Buttons

**Description:** Each button should do what it&#39;s supposed to do

**Test steps:**

- Check start button
- Check exit button
- Check back button
- Check enemies button
- Check pick map buttons
- Check In-game buttons

**Result:** Every menu button works accordingly, the &quot;start round&quot; button doesn&#39;t do anything.
---
### Test: Move

**Description:** Being able to move with keyboard and card inputs

**Test steps:**

- Move with keyboard
- Move with in-game cards

**Result:** Can freely move with keyboard input, and cards work as they should
---
### Test: Health

**Description:** Taking damage should display visually and reduce number of cards available (shown visually with warning triangles over card slots)

**Test steps:**

- Take damage from any source
- Observe card slots and healthbar

**Result:** Damage result in visual warning triangles and loss of healthbar
---
### Test: Winning

**Description:** Collecting all 4 flags or making opponent(s) running out of life should win the game

**Test steps:**

- Collecting all 4 flags
- All AI&#39;s lose all life

**Result:** Said conditions do win you the game and winning brings you to the Victory Royale screen
---
### Test: ConveyorBelts

**Description:** Conveyor belts work correctly

**Test steps:**

- Move on the belts

**Result:** All belts work correctly
---
### Test: Laser

**Description:** Test if laser work properly

**Test steps:**

- Fire laser at an AI and have an AI fire laser at the PC

**Result:** Firing laser at AI will damage them. AI will damage PC with laser
---
### Test: Respawn

**Description:** Player and AI will respawn at end of round when losing a life

**Test steps:**

- Killing PC
- Killing AI

**Result:** Both respawn at the end of the current round if they die
---
### Test: Cards

**Description:** Selected cards will be executed in selected order and execute right command

**Test steps:**

- Test that every card issues the right command
- Test that cards are executed in the right order

**Result:** Cards work the way they&#39;re supposed to work
---
### Test: Losing

**Description:** Running out of like or having the AI collect all flags first should make you lose the game

**Test steps:**

- Have the opponent collect all flags
- Lose all of your life

**Result:** Said conditions make you lose and brings you to the Game Over screen
---
### Test: Music

**Description:** Music should be played during the game and while in menu

**Test steps:**

- Listen for music while in the menu and in game

**Result:** Music plays in lobby screen and in game. Sound effects come when taking damage and losing a whole life
---
### Test: Backgrounds

**Description:** Each background is the right color

**Test steps:**

- Observe each background

**Result:** Each background is the correct color
---
### Test: Flags

**Description:** Flags need to be acquired in the correct order (1-4) to win the game

**Test steps:**

- Collect the flags in every possible order

**Result:** Only the correct order results in a win
---
### Test: Walls

**Description:** Walls should block movement completely

**Test steps:**

- Walk into walls

**Result:** Walls completely block movement in the direction they&#39;re blocking
---
### Test: Repair tiles

**Description:** Test if repair tiles restore correct amount of health

**Test steps:**

- Move onto a repair tile while damaged

**Result:** Repair tiles restore the correct amount of health
---
### Test: Turn gear

**Description:** Turn gearshould turn a robut left or right, depending on which type of turn gear is stepped on

**Test steps:**

- Step on green and red turn gear

**Result:** Green gear turn clockwise and red gear turn counterclockwise, as expected
---
### Test: Holes

**Description:** Holes remove all current health and renders the robot immobilized until next rount start

**Test steps:**

- Step into a hole

**Result:** A hole deals damage equal to current health and cancels all further actions until the round is finished
---
### Test: Walking out of bounds

**Description:** Stepping outside of the map makes you lose a life

**Test steps:**

- Step outside the map

**Result:** Stepping outside the map makes you lose a life, however, stepping outside the map at the same locationan AI stepped outside the map crashes the game
