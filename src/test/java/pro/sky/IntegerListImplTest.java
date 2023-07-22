package pro.sky;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.exception.InvalidIndexException;
import pro.sky.exception.NullItemException;
import pro.sky.exception.StorageIsFullException;
import pro.sky.service.IntegerList;
import pro.sky.service.IntegerListImpl;

import static org.junit.jupiter.api.Assertions.*;

public class IntegerListImplTest {
    private IntegerList integerListOne;
    private IntegerList integerListTwo;

    @BeforeEach
    void setUp() {
        integerListOne = new IntegerListImpl(2);
        integerListTwo = new IntegerListImpl(2);
    }

    @Test
    void add() {
        assertThrows(NullItemException.class, () -> integerListOne.add(null));
        assertThrows(NullItemException.class, () -> integerListOne.add(1, null));
        assertThrows(InvalidIndexException.class, () -> integerListOne.add(3, 1));
    }

    @Test
    void set() {
        integerListOne.add(1);
        assertThrows(NullItemException.class, () -> integerListOne.set(0, null));
        assertThrows(InvalidIndexException.class, () -> integerListOne.set(3, 2));
    }

    @Test
    void remove() {
        assertThrows(NullItemException.class, () -> integerListOne.remove(null));
        assertThrows(InvalidIndexException.class, () -> integerListOne.remove(3));
    }

    @Test
    void contains() {
        integerListOne.add(1);
        assertTrue(integerListOne.contains(1));
    }

    @Test
    void indexOf() {
        integerListOne.add(0, 1);
        int actual = integerListOne.indexOf(1);
        int expected = 0;
        assertEquals(actual, expected);
    }

    @Test
    void lastIndexOf() {
        integerListOne.add(0, 1);
        int actual = integerListOne.lastIndexOf(1);
        int expected = 0;
        assertEquals(actual, expected);
    }

    @Test
    void get() {
        assertThrows(InvalidIndexException.class, () -> integerListOne.get(5));
    }

    @Test
    void equals() {
        integerListOne.add(0, 1);
        integerListTwo.add(0, 1);

        Integer[] actual = integerListOne.toArray();
        Integer[] expected = integerListTwo.toArray();
        assertArrayEquals(actual, expected);
    }

    @Test
    void size() {
        integerListOne.add(1);

        int actual = integerListOne.size();
        int expected = 1;
        assertEquals(actual, expected);
    }

    @Test
    void isEmpty() {
        assertTrue(integerListOne.isEmpty());
    }

    @Test
    void clear() {
        integerListOne.add(0, 1);
        integerListOne.add(2);
        integerListOne.clear();
        for (int i = 0; i < integerListOne.size(); i++) {
            assertNull(integerListOne.get(i));
        }
    }

    @Test
    void toArray() {
        integerListOne.add(1);
        integerListOne.add(2);
        Integer[] actual = integerListOne.toArray();
        Integer[] expected = {1, 2};
        assertArrayEquals(actual, expected);
    }
}
