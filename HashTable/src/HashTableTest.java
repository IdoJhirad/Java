import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

class HashMapTest {

    HashMap<String , Integer> stringInteger;//capacity defult
    HashMap<Double , String> doubleString; //capacity 25

    @BeforeEach
    void setUp() {
        stringInteger = new HashMap<>();
        doubleString = new HashMap<>(25);
    }


    @Test
    void size() {
        assertEquals(0 ,stringInteger.size());
        assertEquals(0 , doubleString.size());
                            /*key*/     /*val*/
        doubleString.put(15.5 , "ido" );
        stringInteger.put("key" , 15);

        assertEquals(1 ,stringInteger.size());
        assertEquals(1 , doubleString.size());

        String output1 = doubleString.remove(15.5);
        Integer output2 = stringInteger.remove("key");

        assertEquals(0 ,stringInteger.size());
        assertEquals(0 , doubleString.size());

        doubleString.put(15.5 , "ido" );
        stringInteger.put("key" , 15);

        doubleString.put(17.0, "ido" );
        stringInteger.put("ss" , 15);

        assertEquals(2 ,stringInteger.size());
        assertEquals(2 , doubleString.size());
    }

    @Test
    void isEmpty() {
        assertTrue(doubleString.isEmpty());
        assertTrue(stringInteger.isEmpty());

                        /*key*/     /*val*/
        stringInteger.put("key" , 15);
        doubleString.put(15.5 , "ido" );

        assertFalse(doubleString.isEmpty());
        assertFalse(stringInteger.isEmpty());
    }

    @Test
    void containsKey() {
        assertFalse(doubleString.containsKey(65.2));

        doubleString.put(15.5 , "ido" );
        doubleString.put(17.0, "ido" );
        doubleString.put(65.4, "yaron");

        stringInteger.put("key" , 15);
        stringInteger.put("ss" , 15);
        stringInteger.put("ii", 54);

        assertFalse(stringInteger.containsKey("ido"));
        assertFalse(doubleString.containsKey(65.3));

        assertTrue(stringInteger.containsKey("key"));
        assertTrue(doubleString.containsKey(17.0));

    }

    @Test
    void containsValue() {
        assertFalse(stringInteger.containsValue(53));
        assertFalse(doubleString.containsValue("idod"));

        doubleString.put(15.5 , "ido" );
        doubleString.put(17.0, "ido" );
        doubleString.put(65.4, "yaron");

        stringInteger.put("key" , 15);
        stringInteger.put("ss" , 15);
        stringInteger.put("ii", 54);

        assertFalse(stringInteger.containsValue(53));
        assertFalse(doubleString.containsValue("idod"));

        assertTrue(stringInteger.containsValue(15));
        assertTrue(doubleString.containsValue("yaron"));
        assertTrue(stringInteger.containsValue(54));
        assertTrue(doubleString.containsValue("ido"));


    }

    @Test
    void get() {

        assertNull(doubleString.get(65.2));
        assertNull(stringInteger.get("yaron"));

        doubleString.put(15.5 , "ido" );
        doubleString.put(17.0, "ido" );
        doubleString.put(65.4, "yaron");

        stringInteger.put("key" , 15);
        stringInteger.put("ss" , 15);
        stringInteger.put("ii", 54);

        assertEquals("ido", doubleString.get(15.5));
        assertEquals("ido", doubleString.get(17.0));
        assertEquals("yaron", doubleString.get(65.4));

        assertEquals(15, stringInteger.get("key"));
        assertEquals(15, stringInteger.get("ss"));
        assertEquals(54, stringInteger.get("ii"));

        assertNull(doubleString.get(65.2));
        assertNull(stringInteger.get("yaron"));

    }

    @Test
    void put() {
        //normal put return null
        assertEquals(0, doubleString.size());
        String  res =  doubleString.put(15.5 , "ido" );
        assertNull(res);
        res = doubleString.put(17.0, "ido" );
        assertNull(res);
        res = doubleString.put(65.4, "yaron");
        assertNull(res);
        assertEquals(3, doubleString.size());

        assertEquals(0, stringInteger.size());
        Integer val =stringInteger.put("key" , 15);
        assertNull(val);
        val =stringInteger.put("ss" , 15);
        assertNull(val);
        val =stringInteger.put("ii", 54);
        assertNull(val);
        assertEquals(3, stringInteger.size());

        //put with the same key update val return val
        res =  doubleString.put(15.5 , "moshe" );
        assertEquals("ido", res);

        res = doubleString.put(17.0, "eran" );
        assertEquals("ido", res);

        res = doubleString.put(65.4, "shlomi");
        assertEquals("yaron", res);
        assertEquals(3, doubleString.size());

        val =stringInteger.put("key" , 20);
        assertEquals(15, val);
        assertEquals(20, stringInteger.get("key"));

        val =stringInteger.put("ss" , 32);
        assertEquals(15, val);
        assertEquals(32, stringInteger.get("ss"));

        val =stringInteger.put("ii", 15);
        assertEquals(15, stringInteger.get("ii"));
        assertEquals(54, val);
        assertEquals(3, stringInteger.size());

        //remove and put back
        stringInteger.remove("ii");
        assertEquals(2, stringInteger.size());
        val =stringInteger.put("ii" , 15);
        assertNull(val);
        assertEquals(3, stringInteger.size());

        stringInteger.put(null , 15);
        assertNull(val);
        assertEquals(4, stringInteger.size());

    }

    @Test
    void remove() {
                     /*key*/     /*val*/
        doubleString.put(15.5 , "ido" );
        stringInteger.put("key" , 15);

        assertEquals(1 ,stringInteger.size());
        assertEquals(1 , doubleString.size());

        String output1 = doubleString.remove(15.5);
        Integer output2 = stringInteger.remove("key");

        assertEquals(0 ,stringInteger.size());
        assertEquals(0 , doubleString.size());

        assertEquals("ido" , output1);
        assertEquals(15, output2);

        assertNull(stringInteger.remove("key"));
        assertNull(doubleString.remove(15.5));

    }

    @Test
    void testPutAll() {
        HashMap<Number , Number> numberHas = new HashMap<>(25);

        Map<Integer , Double> toPut = new ConcurrentHashMap<>(30);
        toPut.put(15,25.5);
        toPut.put(20,53.5);
        toPut.put(30,23.5);
        toPut.put(10,253.5);
        toPut.put(32,25.5);
        toPut.put(137,2.5);
        assertEquals(6, toPut.size());

        numberHas.putAll(toPut);
        assertEquals(6, numberHas.size());

    }

    @Test
    void clear() {
        doubleString.put(15.5 , "ido" );
        doubleString.put(17.0, "ido" );
        doubleString.put(65.4, "yaron");

        stringInteger.put("key" , 15);
        stringInteger.put("ss" , 15);
        stringInteger.put("ii", 54);

        doubleString.clear();
        stringInteger.clear();

        assertEquals(0 , doubleString.size());
        assertEquals(0, stringInteger.size());

    }

    @Test
    void testKeySet() {
        HashMap<Integer , String> hashMap = new HashMap<>(20);

        hashMap.put(6, "Shahar");
        hashMap.put(1, "Amit");
        hashMap.put(4, "Ido");
        hashMap.put(3, "Uriah");
        hashMap.put(2, "Yarden");
        hashMap.put(5, "Itay");
        Set<Integer> keySet = hashMap.keySet();
        Iterator<Integer> iter = keySet.iterator();

        assertTrue(iter.hasNext());
        System.out.println(iter.next());
        assertTrue(iter.hasNext());
        System.out.println(iter.next());
        assertTrue(iter.hasNext());
        System.out.println(iter.next());
        assertTrue(iter.hasNext());
        System.out.println(iter.next());
        assertTrue(iter.hasNext());
        System.out.println(iter.next());
        assertTrue(iter.hasNext());
        System.out.println(iter.next());
        assertFalse(iter.hasNext());


    }

    @Test
    void testValues() {

        HashMap<Integer , String> hashMap = new HashMap<>(20);
        hashMap.put(6, "Shahar");

        hashMap.put(1, "Amit");
        hashMap.put(4, "Ido");
        hashMap.put(3, "Uriah");
        hashMap.put(2, "Yarden");
        hashMap.put(5, "Itay");
        Collection<String> values = hashMap.values();
        Iterator<String> iter = values.iterator();

        assertTrue(iter.hasNext());
        System.out.println(iter.next());
        assertTrue(iter.hasNext());
        System.out.println(iter.next());
        assertTrue(iter.hasNext());
        System.out.println(iter.next());
        assertTrue(iter.hasNext());
        System.out.println(iter.next());
        assertTrue(iter.hasNext());
        System.out.println(iter.next());
        assertTrue(iter.hasNext());
        System.out.println(iter.next());
        assertFalse(iter.hasNext());

    }

    @Test
    void testEntrySet() {
        HashMap<Integer , String> hashMap = new HashMap<>(20);

        hashMap.put(6, "Shahar");
        hashMap.put(1, "Amit");
        hashMap.put(4, "Ido");
        hashMap.put(3, "Uriah");
        hashMap.put(2, "Yarden");
        hashMap.put(5, "Itay");
        Set<Map.Entry<Integer, String>> entrySet = hashMap.entrySet();
        Iterator<Map.Entry<Integer, String>> iter = entrySet.iterator();

        assertTrue(iter.hasNext());
        System.out.println(iter.next());
        assertTrue(iter.hasNext());
        System.out.println(iter.next());
        assertTrue(iter.hasNext());
        System.out.println(iter.next());
        assertTrue(iter.hasNext());
        System.out.println(iter.next());
        assertTrue(iter.hasNext());
        System.out.println(iter.next());
        assertTrue(iter.hasNext());
        System.out.println(iter.next());

        assertFalse(iter.hasNext());
    }
}