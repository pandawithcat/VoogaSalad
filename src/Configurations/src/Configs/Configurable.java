package Configs;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface Configurable {
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Configure{}


    Configuration getConfiguration();
<<<<<<< HEAD

=======
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860
}
