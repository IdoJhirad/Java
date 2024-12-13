package LinkedListGen.src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListGenTest {

    LinkedListGen<String> list;

    @BeforeEach
    void setUp() {
        list =new LinkedListGen<>();
    }

    @Test
    void size() {
        assertEquals(0 , list.size());
        list.pushFront("Ido ");
        assertEquals(1 , list.size());
        list.pushFront("Jhirad ");
        assertEquals(2 , list.size());

        String str = list.popFront();
        assertEquals("Jhirad ", str);
        assertEquals(1 , list.size());
    }
    @Test
    void pushFront() {
        list.pushFront("Ido ");
        list.pushFront("Jhirad ");
        assertEquals(2, list.size());
        list.pushFront("Ido ");
        list.pushFront("Jhirad ");
        assertEquals(4, list.size());

    }

    @Test
    void popFront() {
        list.pushFront("Ido ");
        list.pushFront("Jhirad ");
        assertEquals(2, list.size());
        list.pushFront("Ido ");
        list.pushFront("Jhirad ");
        String str = list.popFront();
        assertEquals("Jhirad ", str);
        assertEquals(3 , list.size());
        assertEquals("Ido ", list.popFront());
        assertEquals(2 , list.size());
        list.popFront();
        list.popFront();
        assertEquals(0 , list.size());
        assertThrowsExactly(InvalidOperation.class ,() -> {
            list.popFront();
        });
    }

    @Test
    void isEmpty() {
        assertTrue(list.isEmpty());
        list.pushFront("Ido");
        assertFalse(list.isEmpty());
        list.popFront();
        assertTrue(list.isEmpty());;
    }

    @Test
    void find() {
        list.pushFront("Ido");
        list.pushFront("Io");
        list.pushFront("In");
        list.pushFront("out");
        list.pushFront("assert");
        list.pushFront("Ido");
        list.pushFront("Io");
        list.pushFront("In");
        list.pushFront("out");
        Iterator<String> found = list.find("assert");
        assertNotNull(found);
        found = list.find("Hamelec");
        assertNull(found);
        Iterator<String> f = list.iterator();
        list.pushFront("LL");
        assertThrowsExactly(ConcurrentModificationException.class , f::hasNext);

    }

    @Test
    void newReverse() {
        list.pushFront("Ido");
        list.pushFront("Jhirad");
        list.pushFront("Was");
        list.pushFront("Here");
        LinkedListGen.printList(list);
        list = LinkedListGen.newReverse(list);
        LinkedListGen.printList(list);
    }
    @Test
    void mergeLists() {
        LinkedListGen<String> secondList = new LinkedListGen<>();
        list.pushFront("IDO");
        list.pushFront("IDO");
        list.pushFront("IDO");
        secondList.pushFront("jhirad");
        secondList.pushFront("jhirad");
        secondList.pushFront("jhirad");
        list = LinkedListGen.mergeLists(list, secondList);
        assertEquals(6, list.size());
        for(String s : list){
            System.out.print(s);
        }
        System.out.println();
    }


    @Test
    void iterator() {
        list.pushFront("IDO");
        list.pushFront("Jhirad");
        list.pushFront("From FS");
        Iterator<String> iter = list.iterator();
        assertTrue(iter.hasNext());
        assertEquals("From FS", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("Jhirad", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("IDO", iter.next());
        assertFalse(iter.hasNext());
        list.pushFront("159-160");
        assertThrowsExactly(ConcurrentModificationException.class ,() -> {
            iter.hasNext();});
    }

}