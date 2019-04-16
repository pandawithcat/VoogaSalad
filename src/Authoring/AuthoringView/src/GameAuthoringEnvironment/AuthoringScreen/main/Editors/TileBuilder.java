package GameAuthoringEnvironment.AuthoringScreen.main.Editors;

public class TileBuilder {
    public SquareCell getTile(String type, double r, double c, double width, double height){

        if(type.equals("Water")){
            return new WaterTile(r,c,width,height);
        }
        else if(type.equals("Dirt")){
            return new DirtTile(r,c,width,height);
        }
        else if(type.equals("Grass")){
            return new GrassTile(r,c,width,height);
        }
        else{
            return null;
        }
    }
}
