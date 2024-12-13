import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
 public class Pair<K,V> implements Map.Entry<K,V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key =key;
        this.value = value;
    }

    public static <K,V> Pair<V, K> swap(Pair<K, V> pair) {

        return new Pair<V , K >( pair.value , pair.key );

    }

    public static <K, V> Pair<K, V> of(K key, V value) {

        return new Pair<K, V>(key, value);
    }


    public static <T extends Comparable<T>> Pair<T, T> minmax(ArrayList<T> elements) {
        T min = Collections.min(elements);
        T max = Collections.max(elements);

        return new Pair<T, T>(min , max);
    }

    @Override
    public V setValue(V value) {
        V lastVal = this.value;
        this.value = value;

        return lastVal;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "pair with key: " +key+ " and value: "+value;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o ) {
            return true;
        }
        if (!(o instanceof Pair<?, ?>)) {
            return false;
        }
        Pair<?, ?> e = (Pair<?, ?>) o;
        return e.key.equals(this.key);
    }

    @Override
    public int hashCode() {

        return Math.abs(key.hashCode());
    }
}