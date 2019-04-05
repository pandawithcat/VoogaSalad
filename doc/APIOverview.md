# API Outline

What methods does your module need from other places?

## Authoring Environment/

interface Accessor{
  public void setGameConfig();
  public void setTowerlists();
  public void addTower();
  public void addPath();
}

class Grid implements Accessor{

   Game myGame;
   public void setGameConfig(){
     Game.setConfig();
   } 
   public void addTower(){
       public void Game.getTowerList.get(a).addTower();
   }
   public void addPath(){
       public void Game.getPath.addPath();
   }
   
   public void setTowerlists();
   
}

APIs in the authoring environment should all be getters and setters calling classes from the Game engine. 

## GamePlayer
`boolean isValidPosition(Weapon weapon, int xPos, int yPos)`
`WeaponStatus getWeaponStatus(Weapon weapon)`
`User getUser(String username)`
`Game getGameConfig(String gameType)`
purchases


## Interfaces

## Game Player Visualization/IO
Game State from the GamePlayer (GameState Object)
Lists of Weapons, 






