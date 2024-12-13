package observer;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher<T> {

    List<Callback<T>> subList = new ArrayList<>();


    public Dispatcher() {
    }

    public void register(Callback<T> cb) {
        subList.add(cb);
        cb.setDispatcher(this);
    }

    public void unregister(Callback<T> cb) {
        subList.remove(cb);
        cb.setDispatcher(null);//
    }

    public void updateAll(T data) {
        for (Callback<T> callback : subList) {
            callback.update(data);
        }
    }

    public void stopService() {
        for(int i = 0 ;i < subList.size();++i) {
            subList.get(i).stopUpdate();
            subList.get(i).setDispatcher(null);
        }
        subList.clear();
    }
}
