
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class CircularLinkedListTest {

    private CircularLinkedList<String> testList;
    private Iterator<String> testIterator;
    private String playerOne;
    private String playerTwo;
    private String playerThree;
    private String playerFour;

    public CircularLinkedListTest() {
        testList = new CircularLinkedList<>();
        testIterator = testList.iterator();
        playerOne = "Arthur";
        playerTwo = "Dutch";
        playerThree = "John";
        playerFour = "Micah";
    }

    @Test
    void testGetSize() {
        assertEquals(0, testList.getSize());

        testList.add(playerOne);
        assertEquals(1, testList.getSize());

        testList.add(playerTwo);
        testList.add(playerThree);
        testList.add(playerFour);
        assertEquals(4, testList.getSize());

        testList.remove(0);
        assertEquals(3, testList.getSize());

        testList.remove(playerThree);
        testList.remove(playerFour);
        assertEquals(1, testList.getSize());
    }

    @Test
    void testGet() {
        testList.add(playerOne);
        assertEquals(playerOne, testList.get(0));

        testList.add(playerTwo);
        testList.remove(playerOne);
        assertEquals(playerTwo, testList.get(0));
    }

    @Test
    void testAdd() {
        testList.add(playerOne);
        assertEquals(playerOne, testList.get(0));
        assertEquals(1, testList.getSize());

        assertEquals(playerOne, testIterator.next());
        assertTrue(testIterator.hasNext());
        assertEquals(playerOne, testIterator.next());

        testList.add(playerTwo);
        assertEquals(playerTwo, testList.get(1));
        assertEquals(2, testList.getSize());

        assertEquals(playerOne, testIterator.next());
        assertEquals(playerTwo, testIterator.next());
        assertEquals(playerOne, testIterator.next());
    }

    @Test
    void testRemoveValue() {
        testList.add(playerOne);
        testList.add(playerTwo);
        testList.add(playerThree);
        testList.add(playerFour);

        testList.remove(playerOne);
        assertEquals(playerTwo, testList.get(0));
        assertEquals(3, testList.getSize());

        assertEquals(playerTwo, testIterator.next());
        assertEquals(playerThree, testIterator.next());
        assertEquals(playerFour, testIterator.next());
        assertEquals(playerTwo, testIterator.next());

        testList.remove(playerFour);
        assertEquals(playerThree, testList.get(1));
        assertEquals(2, testList.getSize());

        assertEquals(playerThree, testIterator.next());
        assertEquals(playerTwo, testIterator.next());
        assertEquals(playerThree, testIterator.next());
    }

    @Test
    void testRemovePosition() {
        testList.add(playerOne);
        testList.add(playerTwo);
        testList.add(playerThree);
        testList.add(playerFour);

        testList.remove(0);
        assertEquals(playerTwo, testList.get(0));
        assertEquals(3, testList.getSize());

        assertEquals(playerTwo, testIterator.next());
        assertEquals(playerThree, testIterator.next());
        assertEquals(playerFour, testIterator.next());
        assertEquals(playerTwo, testIterator.next());

        testList.remove(2);
        assertEquals(playerThree, testList.get(1));
        assertEquals(2, testList.getSize());

        assertEquals(playerThree, testIterator.next());
        assertEquals(playerTwo, testIterator.next());
        assertEquals(playerThree, testIterator.next());
    }

    @Test
    void iterator() {
        testList.add(playerOne);
        testList.add(playerTwo);
        testList.add(playerThree);
        testList.add(playerFour);

        assertTrue(testIterator.hasNext());
        assertEquals(playerOne, testIterator.next());
        assertEquals(playerTwo, testIterator.next());
        assertEquals(playerThree, testIterator.next());
        assertEquals(playerFour, testIterator.next());
        assertTrue(testIterator.hasNext());
        assertEquals(playerOne, testIterator.next());
    }

    @Test
    void testExceptions() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () ->
            {testList.get(-1);
        });

        IllegalArgumentException thrownTwo = Assertions.assertThrows(IllegalArgumentException.class, () ->
        {testList.get(testList.getSize());
        });
    }
}