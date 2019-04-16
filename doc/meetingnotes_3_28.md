Defining APIs:


Points of Discussion: 
    Functionality of Grid, how it interacts with other things
    Every Grid square has a terrain
<<<<<<< HEAD
    Can hold a weapon, or enemy
=======
    Can hold a weaponConfig, or enemyConfig
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860
        enemies can stack, not problematic since we can treat them as one blob
    Convert list of objects from game instance
    
    Frontend Sees:
        Map
        List of images and positions
Use Cases:




Deliverables:
    UML Diagrams
        Authoring
        GamePlayer
        Player
    WireFrames:
        Authoring
        Playing
    Use Cases
        Authoring FE 6-8
        GamePlayer 8
        Player FE
    Interfaces and APIs (External)
        Authoring IO/FrontEnd
            mirror classes
        Interfaces for creating Games in Authoring
        GamePlayer Interfaces
            .update() should be called on logic by frontend
            This should give the Gameplayer a map and a list of pictures/positions
            ID-Image pairing so the frontend can determine how things move 
            Determining legality of placements
        Handlers from Player GUI
            MouseClicks- starting/resetting 
            MouseDrags/ click or hover
            MouseHovers-- can be handled by
    Example Code for 5 Use Cases (using API Methods)
        May require 'mocked up' objects with no real functionality to demonstrate some points
    Design Document:

    