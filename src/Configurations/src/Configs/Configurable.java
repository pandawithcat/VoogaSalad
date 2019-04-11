package Configs;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface Configurable {
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Configure{}

    String getLabel();

    Configuration getConfiguration();
//<<<<<<< HEAD
//
////    void default setConfiguration(){
////
////    }
//=======
//>>>>>>> d898fe4184eca2ca34e66f5d655b4df4f2237341
}
