package observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher<T> {

    Dispatcher<T> dispatcher = new Dispatcher<>();
    public Publisher() {

    }

    public void register(Callback<T> cb) {
        dispatcher.register(cb);
    }
    public void produce(T data) {
        dispatcher.updateAll(data);
    }
    public void close() {
        dispatcher.stopService();
    }
}
