package Configs;

public class MapFeature {
    private int gridXPos;
    private int gridYPos;
    private double pixelXPos;
    private double pixelYPos;
    private double direction;
    private TransferImageView myImageView;


    public MapFeature(int gridXPos, int gridYPos, double direction) {
        setGridPos(gridXPos,gridYPos,direction);
    }


    public void setPixelPos(double x, double y, double direction) {
        pixelXPos = x;
        pixelYPos = y;
        this.direction = direction;
        //TODO: CALCULATE GRID POSITION FROM THIS

        //set imageview x and y in this
    }

    public void setGridPos(int gridXPos, int gridYPos, double direction) {
        this.gridXPos = gridXPos;
        this.gridYPos = gridYPos;
        this.direction = direction;
        //TODO: CALCULATE PIXEL POSITION FROM THIS
        //set imageview x and y in this

    }
    public TransferImageView getImageView() {
        return myImageView;
    }

    public void setImageView(double height, double width) {
        myImageView.setFitHeight(height);
        myImageView.setFitWidth(width);
        myImageView.setX(pixelXPos);
        myImageView.setY(pixelYPos);

    }



}
