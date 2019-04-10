package Configs;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface Configurable {
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Configure{}


    Configuration getConfiguration();
<<<<<<< HEAD

=======
>>>>>>> a0fbae9c7c0c83ef94a96e04f9852a12117d70ef
}
