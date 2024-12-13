import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PairTest {

    Pair<String ,Integer> pairStringInt;
    Pair<Double ,String> pairDoubleString;

    @BeforeEach
    void setUp() {
        pairStringInt = new Pair<>("Ido",100);
        pairDoubleString = new Pair<>(18.5 ,"A");
    }
    @Test
    void getKey() {
        assertEquals("Ido", pairStringInt.getKey());
        assertEquals(18.5, pairDoubleString.getKey());
    }

    @Test
    void getValue() {
        assertEquals(100, pairStringInt.getValue());
        assertEquals("A", pairDoubleString.getValue());
    }

    @Test
    void setValue() {
        assertEquals(100, pairStringInt.setValue(50));
        assertEquals(50, pairStringInt.getValue());

        assertEquals("A", pairDoubleString.setValue("E"));
        assertEquals("E", pairDoubleString.getValue());
    }


    @Test
    void testToString() {
        System.out.println(pairDoubleString.toString());
        System.out.println(pairStringInt.toString());
    }

    @Test
    void swap() {
       Pair<Integer, String> swapPair =  Pair.swap(pairStringInt);
        assertEquals("Ido", swapPair.getValue());
        assertEquals(100, swapPair.getKey());

        Pair<String, Double> swapPair2 = Pair.swap(pairDoubleString);
        assertEquals(18.5 , swapPair2.getValue());
        assertEquals("A", swapPair2.getKey());
    }

    @Test
    void of() {
        Pair<String , Integer> t = Pair.of("Ido", 100);
        assertTrue( t.equals(pairStringInt));
        assertTrue(  pairStringInt.equals(t));
    }

    @Test
    void minmax() {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(75);
        list.add(62);
        list.add(54);
        list.add(-2854);
        list.add(1524);

        Pair<Integer, Integer> pair = Pair.minmax(list);

        assertEquals(1524 , pair.getValue());
        assertEquals(-2854, pair.getKey());

    }



    @Test
    void testEquals() {
        Pair<String , Integer> t = Pair.of("Ido", 100);
        assertTrue( t.equals(pairStringInt));
        assertTrue(  pairStringInt.equals(t));
    }

    @Test
    void testHashCode() {
        Pair<String , Integer> t = Pair.of("Ido", 100);
        assertEquals(t.hashCode(), pairStringInt.hashCode());

        Pair<Double ,String> p = new Pair<>(18.5 ,"A");
        assertEquals(p.hashCode(), pairDoubleString.hashCode());

    }
}