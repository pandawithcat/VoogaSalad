package Configs;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class TransferImageView extends ImageView implements ImmutableImageView{
    public TransferImageView(){
        super();
    }
    public TransferImageView(Image image){
        super(image);
    }

    @Override
    public Node getAsNode() {
        return this;
    }
}
