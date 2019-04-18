package Configs;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface Configurable {
    @Retention(RetentionPolicy.RUNTIME)
    @interface Configure{}

    String getLabel();

    Configuration getConfiguration();

}
