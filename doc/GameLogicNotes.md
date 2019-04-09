Work Allocation II indicates sprint II Work

Christina & Kunal: 
Work to create supply approriaate interfaces to author game
Work on creating/instantiating ___Instance objects on the logic 
Loading XML into authoring environment
Creating game XML using XStream

Loading and Saving XML onto player side

Brian: XML -> Game Object Conversion

Game Object -> Game Instance

Interface between Game Model and Game Player.View (passing of viewables)

II: User authentication in top level game logic so view can handle viewing

Sachal:

II: Querying database with user info to get user info

Rishi:






Things to do before we can do this:

Define behavior and interface of all the different INSTANCE types of objects that we’ll be working with (weapons, tiles, projectiles)

Parts of game play steps (Controller perspective):

General Steps:
	* User Login
	* Game Options displayed
	* User Selects Game Option
	* Load in/Setup Phase
	* Splash Screen Displays with options of start where left off or start from beginning
	* Build Phase based on level
	* Run Phase/Loop

Load game in();


Run Game Loop{
	Execute Current Step (step could be splash screen, build phase, or game run phase)
	Move to next level();
}

Splash Screen{
	Essentially wait until click, frontend will determine when to move forward;
}

Build Phase{
	Load map of current level ();
	Load arsenal of current level, with appropriate things highlighted and not();
	Show enemy path to user();
	Allow for setup();
	Mouse event handler()
		On drag and drop, add elements (weapons most probably) to the map();
		    validate whether the location of the drop is valid();
		        if so place and update player wallet()
		        if not, tell user was invalid
	 e from build phase into run phase();
}


Game Run Phase{
	Check if Level Complete();
		either all enemies gone or too many enemies made it to exit
	Move enemies according to path();
		Move active enemies to new location based on movement algorithm();  —Kunal
		make sure they’re not hitting walls, etc
		Check if any active enemies have reached the exit and update the game accordingly();
		Check to see if this is the correct time to place a new enemy at the entrance point();
	Check if any enemies are dead, remove from screen if so();
	Update all active projectiles and weapons to see if they’re hitting any enemies or walls();
		If hitting something, update that something, update score();
		Otherwise update position of that projectileConfig based on how it’s supposed to move();
	Check all weapons to see if it’s the right time to shoot, if so, shoot and create new projectiles/etc();
		make sure to update ammo, health, etc of the weaponConfig itself();
	Render new state

}