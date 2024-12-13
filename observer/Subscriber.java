package observer;
import java.util.function.Consumer;
public class Subscriber<T> {

    private T data;
    private Callback<T> callback;

    public Subscriber() {
        callback = new Callback<>(new ConsumerIMP(), ()->{});//TODO
    }
    public Subscriber(Consumer<T> consumer , Runnable runnable) {
        callback = new Callback<>(consumer, runnable);
    }

    public void register(Publisher<T> publisher) {
        publisher.register(callback);
    }
    public void unregister() {
        callback.unregister();
    }

    public T getData() {
        return data;
    }

    class ConsumerIMP implements Consumer<T> {

        @Override
        public void accept(T t) {
            Subscriber.this.data = t;
        }
    }
}

