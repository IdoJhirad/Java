
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SinglyLinkedListTest {

    private SinglyLinkedList list;

    @BeforeEach
    public void setUp() {
        list = new SinglyLinkedList();
    }

    @Test
    public void testSize() {
        assertEquals(0, list.size(), "Size of new list should be 0");
        list.pushFront(1);
        assertEquals(1, list.size(), "Size of new list should be 0");
        list.pushFront(2);
        assertEquals(2, list.size(), "Size of new list should be 0");
        list.pushFront(3);
        assertEquals(3, list.size(), "Size of new list should be 0");
        list.pushFront(4);
        assertEquals(4, list.size(), "Size of new list should be 0");

    }

    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty(), "New list should be empty");
        list.pushFront(3);
        assertFalse(list.isEmpty(), "New list should be not empty");

    }

    @Test
    public void testPush() {
        list.pushFront(1);
        assertEquals(1, list.size(), "Size of new list should be 0");
        list.pushFront(2);
        assertEquals(2, list.size(), "Size of new list should be 0");
        list.pushFront(3);
        assertEquals(3, list.size(), "Size of new list should be 0");
        list.pushFront(4);
        assertEquals(4, list.size(), "Size should be 4 after pushing 4 elements");
        assertFalse(list.isEmpty(), "New list should be not empty");
    }

    @Test
    public void testPopFront() {
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        list.pushFront(4);

        assertEquals(4, list.popFront(), "Popped value should be 4");
        assertEquals(3, list.size(), "Size of new list should be 0");

        assertEquals(3, list.popFront(), "Popped value should be 3");
        assertEquals(2, list.size(), "Size of new list should be 0");

        assertEquals(2, list.popFront(), "Popped value should be 2");
        assertEquals(1, list.size(), "Size of new list should be 0");

        assertEquals(1, list.popFront(), "Popped value should be 1");
        assertEquals(0, list.size(), "Size of new list should be 0");

    }
    @Test
    public void testFind() {
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        list.pushFront(4);

        ListIterator iterator = list.find(2);
        assertNotNull(iterator, "Iterator should not be null");
        assertEquals(2, iterator.next(), "Found value should be 2");
    }

    @Test
    public void testIterator() {
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        list.pushFront(4);

        ListIterator iterator = list.begin();
        assertEquals(4, iterator.next(), "First value should be 4");
        assertEquals(3, iterator.next(), "Second value should be 3");
        assertEquals(2, iterator.next(), "Third value should be 2");
        assertEquals(1, iterator.next(), "Fourth value should be 1");
    }
    @Test
    public void testHasNext() {
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        list.pushFront(4);

        ListIterator iterator = list.begin();

        assertTrue(iterator.hasNext(), "Iterator should have next");
        iterator.next();
        assertTrue(iterator.hasNext(), "Iterator should have next");
        iterator.next();
        assertTrue(iterator.hasNext(), "Iterator should have next");
        iterator.next();
        assertTrue(iterator.hasNext(), "Iterator should have next");
        iterator.next();
        assertFalse(iterator.hasNext(), "Iterator should not have next");

    }
}
