public class Tower {
    private ImageView towerImage;
    private ImageView radiusColor;
    private Group root;
    private GameInstance gameInstance;
    public Tower(Group root){
        this.root = root;
    }
    public void createHoverScreen(){
        if(gameInstance.isInArsenal(towerImage)){
            display(gameInstance.getHoverMessage(towerImage);
        }
    }
    public void checkIfValid(){
        if(gameInstance.checkValidLocation(towerImage)){
            setRadius(Color.GREEN);
        }else{
            setRadius(Color.RED);
        }
    }
    public void setRadius(Paint color){
        radiusColor.setX(towerImage.getX());
        radiusColor.setY(towerImage.getY());
        radiusColor.setColor(color);
        root.getChildren.add(radiusColor);

    }
    public void display(String message){
        Alert alert = new Alert(message);
        alert.display;
    }

}