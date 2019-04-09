package Configs;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MapFeature {
    private int gridXPos;
    private int gridYPos;
    private double pixelXPos;
    private double pixelYPos;
    private double direction;
    @XStreamOmitField
    private TransferImageView myImageView;
    private View view;



    public MapFeature(int gridXPos, int gridYPos, double direction, View view) {
        setGridPos(gridXPos,gridYPos,direction);
        this.view = view;
        myImageView = new TransferImageView(new Image(view.getImage()));
    }

    public MapFeature(double pixelXPos, double pixelYPos, double direction, View view) {
        setPixelPos(pixelXPos,pixelYPos,direction);
        this.view = view;
        myImageView = new TransferImageView(new Image(view.getImage()));
    }

    public double getPixelXPos() {
        return pixelXPos;
    }

    public double getPixelYPos() {
        return pixelYPos;
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
        myImageView.setFitHeight(view.getHeight());
        myImageView.setFitWidth(view.getWidth());
        myImageView.setX(pixelXPos);
        myImageView.setY(pixelYPos);
        return myImageView;
    }



}
