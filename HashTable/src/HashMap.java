import java.lang.Math;

import java.util.*;


public class HashMap<K, V> implements Map<K, V> {

    public static final int DEFAULT_INITIAL_CAPACITY = 16;

    private int numOfBucket;
    private int modificationCounter;
    private int size;
    private List<List<Map.Entry<K, V>>> hashList;

    private Set<K> setOfKeys;
    private Collection<V> collectionOfVal;
    private Set<Entry<K, V>> setOfEntry;

    public HashMap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public HashMap(int capacity) {

        this.numOfBucket = capacity;
        hashList = new ArrayList<>(this.numOfBucket);

        for (int i = 0; i < this.numOfBucket; ++i) {
            hashList.add(new LinkedList<>());
        }

    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object o) {
        int code = 0;
        if (o != null) {
            //Object.hash and not pair abs
            code = Math.abs(o.hashCode() % this.numOfBucket);
        }
        List<Map.Entry<K, V>> list = hashList.get(code);
        for (Map.Entry<K, V> pair : list) {
            if (o == null && pair.getKey() == null || (pair.getKey() != null && pair.getKey().equals(o))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object o) {
        Collection<V> vCollection = values();
        for (V value : vCollection) {
            if (o == null && value == null || value != null && value.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int hashCode = 0;
        if (key != null) {
            hashCode = Math.abs(key.hashCode() % this.numOfBucket); // use the object hash code and not pair hash, pair hash return always positive
        }

        List<Map.Entry<K, V>> list = hashList.get(hashCode);
        for (Map.Entry<K, V> p : list) {
            if (key == null && p.getKey() == null || (p.getKey() != null && p.getKey().equals(key))) {
                return p.getValue();
            }
        }

        return null;
    }

    @Override
    public V put(K k, V v) {
        Map.Entry<K, V> newPair = new Pair<>(k, v);
        int hashCode = 0;

        if (k != null) {
            hashCode = newPair.hashCode() % this.numOfBucket;
        }

        List<Map.Entry<K, V>> list = hashList.get(hashCode);
        int index = list.indexOf(newPair);
        Map.Entry<K, V> lastPair = null;
        V lastPairVal;

        if (index != -1) {
            lastPair = list.get(index);
            lastPairVal = lastPair.getValue();
            lastPair.setValue(v);

            return lastPairVal;
        }
        list.add(newPair);
        ++size;
        ++modificationCounter;

        return null;
    }

    @Override
    public V remove(Object key) {
        int code = 0;
        List<Map.Entry<K, V>> list;
        if (key != null) {
            code = key.hashCode() % this.numOfBucket;
        }
        list = hashList.get(code);
        for (Map.Entry<K, V> p : list) {
            if (key == null && p.getKey() == null || (p.getKey() != null && p.getKey().equals(key))) {
                V ret = p.getValue();
                list.remove(p);
                --size;
                ++modificationCounter;

                return ret;
            }
        }
        return null;

    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> e : map.entrySet()) {
            this.put(e.getKey(), e.getValue());
        }
    }

    @Override
    public void clear() {
        hashList = null;
        hashList = new ArrayList<>(this.numOfBucket);
        for (int i = 0; i < this.numOfBucket; ++i) {
            hashList.add(new LinkedList<>());
        }
        this.size = 0;
        ++ modificationCounter;

    }

    @Override
    public Set<K> keySet() {
        if (null == setOfKeys) {
            setOfKeys = new SetOfKeys();
        }
        return setOfKeys;
    }

    @Override
    public Collection<V> values() {
        if (null == collectionOfVal) {
            collectionOfVal = new CollectionOfValues();
        }
        return collectionOfVal;
    }


    @Override
    public Set<Entry<K, V>> entrySet() {
        if (null == setOfEntry) {
            setOfEntry = new SetOfEntries();
        }
        return setOfEntry;
    }


    private class SetOfEntries extends AbstractSet<Map.Entry<K, V>> {

        @Override
        public Iterator<Entry<K, V>> iterator() {

            return new EntriesIterator();

        }

        @Override
        public int size() {
            return size;
        }

        private class EntriesIterator implements Iterator<Entry<K, V>> {

            int modificationCounter = HashMap.this.modificationCounter;

            Map.Entry<K, V> entry;
            Iterator<List<Map.Entry<K, V>>> bucketIterator = HashMap.this.hashList.iterator(); ;
            Iterator<Map.Entry<K,V>> listIterator = HashMap.this.hashList.get(0).iterator();

            
            public EntriesIterator() {
                findNextFree();
            }
            private void findNextFree() {
                if (listIterator.hasNext()) {
                    entry = listIterator.next();
                } else {
                    while (bucketIterator.hasNext()) {
                        listIterator = (bucketIterator.next()).iterator();
                        if (listIterator.hasNext()) {
                            entry = listIterator.next();
                            return;
                        }
                    }
                    entry = null;
                }
            }




//                private void findNextFree() {
//                List<Map.Entry<K, V>> list;
//                for (; bucketIndex < hashList.size(); ++bucketIndex) {
//                    list = hashList.get(bucketIndex);
//                    if (listIndex < list.size()) {
//                        entry = list.get(listIndex);
//                        ++listIndex;
//                        return;
//                    }
//
//                    listIndex = 0;
//                }
//                entry = null;
//            }


            @Override
            public boolean hasNext() {
                if (HashMap.this.modificationCounter != modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                return entry != null;
            }

            @Override
            public Entry<K, V> next() {
                if (HashMap.this.modificationCounter != modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                Map.Entry<K, V> ret = entry;
                findNextFree();

                return ret;
            }


        }
    }

    private class SetOfKeys extends AbstractSet<K> {


        @Override
        public Iterator<K> iterator() {
            return new KeysIterator();
        }

        @Override
        public int size() {
            return size;
        }

        private class KeysIterator implements Iterator<K> {

            //based on EntriesIterator to get first pair
            SetOfEntries.EntriesIterator entriesIterator;

            public KeysIterator() {
                entriesIterator = new SetOfEntries().new EntriesIterator();
            }

            @Override
            public boolean hasNext() {
                return entriesIterator.hasNext();
            }

            @Override
            public K next() {
                Map.Entry<K, V> entry = entriesIterator.next();
                return entry.getKey();

            }
        }
    }

    private class CollectionOfValues extends AbstractCollection<V> {
        @Override
        public Iterator<V> iterator() {
            return new ValueMapIterator();

        }

        @Override
        public int size() {
            return size;
        }

        private class ValueMapIterator implements Iterator<V> {

            SetOfEntries.EntriesIterator currEntry;

            public ValueMapIterator() {
                currEntry = new SetOfEntries().new EntriesIterator();
            }

            @Override
            public boolean hasNext() {
                return currEntry.hasNext();
            }

            @Override
            public V next() {
                Map.Entry<K, V> entry = currEntry.next();
                return entry.getValue();
            }
        }
    }

}


