# Use Cases 2

Authoring:

1. Allow for click-and-drag 'paintbrush' style definition of terrain blocks

2. Give 'default' types for terrains, a couple of towers, and a couple of enemies:
So users don't have to define EVERYTHING 

3. Allow users to define the unlock parameters for a weapon in the arsenal

4. Implement checks for having entrances/exits for enemies

5. Allow users to define the Game Type once new Game Modes are defined

6. Give users option to utilize preloaded images to use when defining components

7. Allow removal of various components once selected in a given configuration

Configuration:

1. Add AmmoExpirable behavior for Weapons:
Cause weapons to expire after firing a certain amount of times

2. Add HealthExpirable behavior for Weapons:
Cause weapons to expire after enemies hit them with their projectiles with a certain amount of power

3. Add Power to projectiles:
So they do a certain amount of damage

4. Add Health to Enemies and Current Health to ActiveEnemies:
So that they start with a certain amount of health taht can be depleted

5. Allow for weapons to be placed on the path as a PathPlaceable behavior:
Things like spikes and traps should affect enemies on contact

6. Allow for weapons to be Movable: Click and drag during Play period of a level should be able to move towers

7. Unlink Arsenals from Levels so that they're associated with Games as a whole

8. Allow for unlockables that stay through the game

9. Implement TimeExpirable Behavior for weapons
10. Implement Linear Shooter behavior for towers
11. Implement Laser Shooter behavior for towers
12. Implement Vehicles as a type of weapon that moves around and shoots
13. Implement Stealthy behavior for enemies so they're undetectable by certain weapons
14. Implement different Speed factors on different Terrain blocks that affect enemy speed over different types of terrain
15. Implement Homing behavior for projectiles that locks on to enemies
16. Implement Random waves that give interesting types of enemies within certain bounds
17. Implement Boss enemies that are part of their own wave and can have additional defined behavior
18. Add the Game Mode (between the different types of games) as part of the Game Info configurable
19. Implement additional Logic for different game modes
20. Allow users to change the scores/money that different components dying give the user
21. Allow users to change how much different unlockable Weapons cost
22. Add tracking of the player's score to the Game
23. Implement Terrain components that have an effect on more than just the enemies that are walking on top of it
24. Implement an isValid method that will allow the frontend to know whether the placement of an object at a certain position is valid or not.

GamePlay:

1. Display current info of an enemy/weapon/projectile on mouseover during the game

2. Show which weapons are unlocked in the arsenal by fading/blacking them out
3. Allow for restarting the current level with one click
4. Allow for saving between levels
5. Allow users to select the music that their game will play in the player
6. Connect the Game Player to a database that leverages player info hosted online to provide leaderboards for each kind of authored game 
7. Implement cheat codes that update/kill enemies based on clicks, etc
8. Show HUD that gives user score, lives, level, type of game, etc
9. Allow user to take screenshots to share online/with friends
10. Allow user to share high scores with friends and link to repo so people can pull our project
11. Implement login feature so users can keep track of their scores and store in a database that is hosted online