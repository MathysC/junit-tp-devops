package datastruct;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyUnsortedListTest {
	
	private static MyUnsortedList<Integer> expectedList;
	private static MyUnsortedList<Integer> actualList;

	private static final Integer one = 1;
	private static final Integer two = 2;
	private static final Integer three = 3;
	private static final Integer four = 4;
	private static final Integer five = 5;
	private static final int size = 5;

	
	@Before
	public void setUp() throws Exception {
		expectedList = MyUnsortedList.of(one, two, three, four, five);
	}

	@After
	public void tearDown() throws Exception {
		actualList = MyUnsortedList.of();
	}

	@Test
	public final void testOfEArray() {
		actualList = MyUnsortedList.of(one, two, three, four, five);
		assertFalse("resList is not Empty", actualList.isEmpty());
		assertEquals("resList have a size = 5",size, actualList.size());
		assertEquals("resList equals witnessIntList",expectedList, actualList);
		assertEquals("first equals one",actualList.pop(), one);
		assertEquals("second equals two",actualList.pop(), two);
		assertEquals("third equals three",actualList.pop(), three);
		assertEquals("fourth equals four",actualList.pop(), four);
		assertEquals("fifth equals five", actualList.pop(), five);
	}

	@Test
	public final void testOfIterableOfE() {
		actualList = MyUnsortedList.of(Arrays.asList(one, two, three, four, five));
		assertFalse("resList is not Empty", actualList.isEmpty());
		assertEquals("resList have a size = 5",size, actualList.size());
		assertEquals("resList equals witnessIntList",expectedList, actualList);
		assertEquals("first equals one",actualList.pop(), one);
		assertEquals("second equals two",actualList.pop(), two);
		assertEquals("third equals three",actualList.pop(), three);
		assertEquals("fourth equals four",actualList.pop(), four);
		assertEquals("fifth equals five", actualList.pop(), five);
	}

	@Test
	public final void testIsEmpty() {
		assertFalse("expectedList is not Empty", expectedList.isEmpty());
		expectedList = MyUnsortedList.of();
		assertTrue("expectedList is Empty", expectedList.isEmpty());
	}

	@Test
	public void testSize() {
        actualList = MyUnsortedList.of(22, 25, 1, 4, 67);
        int currentSize = size;
        assertEquals(actualList.size(), size);
        for (Integer i = 1; i <= size/2; i++) {
        	actualList.pop();
            currentSize -= 1;
            assertEquals(actualList.size(), currentSize);
        }
        for (Integer i = 1; i <= size; i++) {
        	actualList.append(i*3);
            currentSize += 1;
            assertEquals(actualList.size(), currentSize);
        }
        
    }
	@Test
	public final void testPrepend() {
		
		// Check insertions
		actualList.prepend(five);
		actualList.prepend(four);
		actualList.prepend(three);
		actualList.prepend(two);
		actualList.prepend(one);
		assertEquals("resList is composed of five elements",5 , actualList.size());

		// Check order
		assertEquals("first equals one",actualList.pop(), one);
		assertEquals("second equals two",actualList.pop(), two);
		assertEquals("third equals three",actualList.pop(), three);
		assertEquals("fourth equals four",actualList.pop(), four);
		assertEquals("fifth equals five", actualList.pop(), five);
	}

	@Test
	public final void testAppend() {
		
		// Check insertions
		actualList.append(one);
		actualList.append(two);
		actualList.append(three);
		actualList.append(four);
		actualList.append(five);
		assertEquals("resList is composed of five elements",5 , actualList.size());

		// Check order
		assertEquals("first equals one",actualList.pop(), one);
		assertEquals("second equals two",actualList.pop(), two);
		assertEquals("third equals three",actualList.pop(), three);
		assertEquals("fourth equals four",actualList.pop(), four);
		assertEquals("fifth equals five", actualList.pop(), five);
	}

	@Test
    public void testInsert() {
        int size = 5;
        actualList = MyUnsortedList.of(22, 0, 1, 0, 67);
        MyUnsortedList<Integer> intListStatic = MyUnsortedList.of(22, 0, 1, 0, 67);
        // exception cases
        // on peut récupérer l'exception du assertThrows pour faire plus de test
        // Exception exception;
        assertThrows(IndexOutOfBoundsException.class, () -> {actualList.insert(1000,-20);});
        assertThrows(IndexOutOfBoundsException.class, () -> {actualList.insert(1000, 300);});
        // normal cases
        assertEquals(actualList, intListStatic);
        actualList.insert(1000, 3);
        actualList.insert(1000, 5);
        assertEquals(actualList.size(), size + 2);
        assertEquals((int) actualList.remove(5), 1000);
        assertEquals((int) actualList.remove(3), 1000);
        assertEquals(intListStatic, actualList);
        
        
        
    }

	@Test
	public final void testPop() {
		// TestPop
		actualList = MyUnsortedList.of(one, two, three, four, five);
		assertEquals("first equals one",actualList.pop(), one);
		assertEquals("second equals two",actualList.pop(), two);
		assertEquals("third equals three",actualList.pop(), three);
		assertEquals("fourth equals four",actualList.pop(), four);
		assertEquals("fifth equals five", actualList.pop(), five);
		assertTrue("actualList is now Empty", actualList.isEmpty());
		
		// Test Exception
		Exception exception = assertThrows(EmptyListException.class, () -> {
			actualList.pop();
	    });
		assertNull(exception.getMessage());
		}

	@Test(expected = EmptyListException.class)
	public final void testPopThrowsException() throws EmptyListException {
		assertTrue("actualList is Empty", actualList.isEmpty());		
		actualList.pop();
	}
	
	@Test
	public final void testPopLast() {
		actualList = MyUnsortedList.of(one, two, three, four, five);
		assertEquals("remove five", actualList.popLast(), five);
		assertEquals("remove four", actualList.popLast(), four);
		assertEquals("remove three", actualList.popLast(), three);
		assertEquals("remove two", actualList.popLast(), two);
		assertEquals("remove one", actualList.popLast(), one);
		assertEquals("actualList has now size of 0", 0, actualList.size());
		assertTrue("actualList is now Empty",actualList.isEmpty());
				
		// Test Exception
		Exception exception = assertThrows(EmptyListException.class, () -> {
			actualList.popLast();
	    });
		assertNull(exception.getMessage());
	}
	
	@Test(expected = EmptyListException.class)
	public final void testPopLastEmptyListThrowsException() throws IndexOutOfBoundsException{
		actualList.popLast();
	}

	@Test
	public final void testRemove() {
		actualList = MyUnsortedList.of(one, two, three, four, five);
		assertEquals("remove three", actualList.remove(2), three);
		assertEquals("remove five", actualList.remove(3), five);
		assertEquals("remove one", actualList.remove(0), one);
		assertEquals("remove four", actualList.remove(1), four);
		assertEquals("remove two", actualList.remove(0), two);
		assertEquals("actualList has now size of 0", 0, actualList.size());
		assertTrue("actualList is now Empty",actualList.isEmpty());
		
		
		// Test Exception
		Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
			expectedList.remove(-5);
	    });
		assertNull(exception.getMessage());

		exception = assertThrows(IndexOutOfBoundsException.class, () -> {
			expectedList.remove(expectedList.size()+1);
	    });
		assertNull(exception.getMessage());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public final void testRemoveNegativeThrowsException() throws IndexOutOfBoundsException {
		expectedList.remove(-5);
	}
		
	@Test(expected = IndexOutOfBoundsException.class)
	public final void testRemoveOverSizeThrowsException() throws IndexOutOfBoundsException {
		expectedList.remove(expectedList.size()+1);
	}
	
	@Test
    public void testEqualsObject() {
        MyUnsortedList<Integer> lA = MyUnsortedList.of(1, 2, 5, 8);
        MyUnsortedList<Integer> lB = MyUnsortedList.of(1, 2, 5, 8);
        MyUnsortedList<Integer> lC = MyUnsortedList.of(999, 2, 5, 8);
        int e;
        assertEquals(lA, lA);
        assertEquals(lA, lB);
        assertNotEquals(lB, lC);
        // pop
        e = lB.pop();
        assertNotEquals(lA, lB);
        lB.prepend(e);
        assertEquals(lA, lB);
        // pop last
        e = lB.popLast();
        assertNotEquals(lA, lB);
        lB.append(e);
        assertEquals(lA, lB);
        
        // remove
        e = lB.remove(3);
        assertNotEquals(lA, lB);
        
        //insert
        lB.insert(e, 3);
        assertEquals(lA, lB);
        
        // instanceof false
        assertNotEquals(lA,"toto");
        
//        // thatLink == Null
//        MyUnsortedList<Integer> lnull = MyUnsortedList.of(999, 2, 5, null);
//        assertNotEquals(lA,lnull);


    }

	@Test
	public final void testToString() {
		String expectedString = "MyUnsortedList { size = 5, [1, 2, 3, 4, 5] }";
		assertEquals(expectedString, expectedList.toString());
			
		expectedString = "MyUnsortedList { size = 0, [] }";
		assertEquals(expectedString, actualList.toString());
	}

}
