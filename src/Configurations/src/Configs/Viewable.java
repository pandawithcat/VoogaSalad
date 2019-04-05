package Configs;

import java.util.List;

public interface Viewable {
    List<View> getViews();
    void startImageViews();//called when the file is loaded from xstream
}
