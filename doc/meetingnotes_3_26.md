Design discussion:
    positioning of information across the screen for authoring environment

Design Discussion:
    should NOT be able to create new types of components while playing the game
    
    
    
Shared Language:
    Game Engine: Define relationships of objects in the game
    Contains the objects that can be used
       Authoring environments should communicate with interfaces
       These interfaces should be implemented in logic, which is then utilized by the player to run the game
    Game GamePlayer: Interaction between objects
       
       
  Runthrough:
    Game object
        setLevel(Level)
    
    Level:
        has an myArsenal
        has a Map
        
    Arsenal: Set of available towers
    Weapon: the base abstration for a weaponConfig
            has name, image, type, speed