import BackendAPI.IModel;
import BackendExternal.Model;
import FrontendAPI.IView;

public class View implements IView {
    public View() {
        IModel myModel = new Model(this);
        myModel.register(this);
    }

    @Override
    public void notifyChange() {

    }
}
