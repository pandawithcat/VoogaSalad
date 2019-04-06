package Configs.Behaviors;

import Configs.Configurable;
import Configs.Updatable;
import Configs.Viewable;

/**
 * marker interface
 * @param <T>
 */

public interface Behavior<T> extends Updatable, Configurable, Viewable {
}
