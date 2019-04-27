package Configs;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface Configurable {
    @Retention(RetentionPolicy.RUNTIME)
    @interface Configure{}

    @Retention(RetentionPolicy.RUNTIME)
    @interface Slider{
        int min();
        int max();
    }

    Configuration getConfiguration();
    String getName();

}
