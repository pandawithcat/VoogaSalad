package Configs;

import javafx.scene.image.ImageView;

public class Info {

    private String name;
    private String image;

    public Info(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getImage(){
        return image;
    }

    public String getName(){
        return name;
    }

}
