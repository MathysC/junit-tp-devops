package datastruct;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class MyUnsortedListTest_Geo {
	
	@Rule
	public Timeout globalTimeout = Timeout.seconds(5);
	
	
	@Test
	public void testCreationListe() {
		UnsortedList<Integer> liste = MyUnsortedList.of();
		assertNotNull(liste);
		
	}
	
	@Test
	public void testCreationListeNonVide() {
		UnsortedList<Integer> liste = MyUnsortedList.of(1,2,3,4);
		assertNotNull(liste);
		
		UnsortedList<String> liste2 = MyUnsortedList.of("a","bcd","efg");
		assertNotNull(liste2);
	}

	@Test
	public void testListeVide() {
		UnsortedList<Integer> liste = MyUnsortedList.of();
		assertTrue("liste vide après création", liste.isEmpty());
		assertEquals("liste de taille 0", 0, liste.size());
	}
	
	@Test
	public void testSize() {
		UnsortedList<Integer> liste = MyUnsortedList.of(1,2,3,4,5);
		assertEquals(5, liste.size());
		
		UnsortedList<Integer> liste2 = MyUnsortedList.of(42, 204);
		assertEquals(2, liste2.size());
	}
	
	@Test
	public void testPopLastInt() {
		UnsortedList<Integer> liste = MyUnsortedList.of(1,2,3);
		assertEquals("liste de taille 3", 3, liste.size());
		assertEquals("dernier élément de la liste vaut 3",  Integer.valueOf(3), liste.popLast());
		assertEquals("liste de taille 2", 2, liste.size());
		assertEquals("dernier élément de la liste vaut 2",  Integer.valueOf(2), liste.popLast());
		assertEquals("liste de taille 1", 1, liste.size());
		assertEquals("dernier élément de la liste vaut 1",  Integer.valueOf(1), liste.popLast());
		assertEquals("liste de taille 0", 0, liste.size());
	}
	
	@Test(expected = EmptyListException.class)
	public void testPopLastListeVide() throws Exception {
		UnsortedList<Integer> liste = MyUnsortedList.of();
		liste.popLast();
	}
	
	@Test
	public void testAppendInt() {
		UnsortedList<Integer> liste = MyUnsortedList.of();
		liste.append(1);
		assertFalse("liste non vide après ajout", liste.isEmpty());
		assertEquals("liste de taille 1", 1, liste.size());
		
		liste.append(42);
		liste.append(101);
		assertEquals("liste de taille 3", 3, liste.size());
	}
	
	@Test
	public void testAppendAndPopLastInt() {
		UnsortedList<Integer> liste = MyUnsortedList.of();
		liste.append(1);
		liste.append(2);
		liste.popLast();
		liste.append(3);
		assertEquals("dernier ajouté est 3",  Integer.valueOf(3), liste.popLast());
		assertEquals("liste de taille 1", 1, liste.size());
		
	}
	
	@Test
	public void testPopInt() {
		UnsortedList<Integer> liste = MyUnsortedList.of(1,2,3,4);
		assertEquals("liste de taille 4", 4, liste.size());
		assertEquals("premier élément de la liste vaut 1", Integer.valueOf(1), liste.pop());
		assertEquals("liste de taille 3", 3, liste.size());
		assertEquals("premier élément de la liste vaut 2", Integer.valueOf(2), liste.pop());
		assertEquals("liste de taille 2", 2, liste.size());
		assertEquals("premier élément de la liste vaut 3", Integer.valueOf(3), liste.pop());
		assertEquals("liste de taille 1", 1, liste.size());
		assertEquals("premier élément de la liste vaut 4",  Integer.valueOf(4), liste.pop());
		assertEquals("liste de taille 0", 0, liste.size());
	}
	
	@Test(expected = EmptyListException.class)
	public void testPopListeVide() throws Exception {
		UnsortedList<Integer> liste = MyUnsortedList.of();
		liste.popLast();
	}
	
	@Test
	public void testPrependInt() {
		UnsortedList<Integer> liste = MyUnsortedList.of();
		liste.prepend(1);
		assertFalse("liste non vide après ajout", liste.isEmpty());
		assertEquals("liste de taille 1", 1, liste.size());
		
		liste.prepend(42);
		liste.prepend(101);
		assertEquals("liste de taille 3", 3, liste.size());
	}
	
	@Test
	public void testPrependAndPopInt() {
		UnsortedList<Integer> liste = MyUnsortedList.of();
		liste.prepend(1);
		liste.prepend(2);
		assertEquals("retrait du premier qui est 2",  Integer.valueOf(2), liste.pop());
		liste.prepend(3);
		assertEquals("premier est 3",  Integer.valueOf(3), liste.pop());
		assertEquals("liste de taille 1", 1, liste.size());
		assertEquals("premier est 1",  Integer.valueOf(1), liste.pop());
		assertEquals("liste de taille 0", 0, liste.size());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveListeVide() {
		UnsortedList<Integer> liste = MyUnsortedList.of();
		liste.remove(2);
	}
	
	@Test
	public void testRemoveInt() {
		UnsortedList<Integer> liste = MyUnsortedList.of(1,2,3,4);
		assertEquals("liste de taille 4", 4, liste.size());
		assertEquals("retrait de l'élément à l'indice 2", Integer.valueOf(3), liste.remove(2));
		assertEquals("liste de taille 3", 3, liste.size());
		assertEquals("retrait de l'élément à l'indice 0", Integer.valueOf(1), liste.remove(0));
		assertEquals("liste de taille 2", 2, liste.size());
		assertEquals("retrait de l'élément à l'indice 1", Integer.valueOf(4), liste.remove(1));
		assertEquals("liste de taille 1", 1, liste.size());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testInsertIndexIncorrect() {
		UnsortedList<Integer> liste = MyUnsortedList.of();
		liste.insert(42,2);
	}
	
	@Test
	public void testInsertInt() {
		UnsortedList<Integer> liste = MyUnsortedList.of();
		liste.insert(1,0);
		liste.insert(2,1);
		liste.insert(3,2);
		assertEquals("liste de taille 3", 3, liste.size());
		assertEquals("premier élément de la liste est 1", Integer.valueOf(1), liste.remove(0));
		assertEquals("second élément de la liste est 2", Integer.valueOf(2), liste.remove(0));
		assertEquals("troisieme élément de la liste est 3", Integer.valueOf(3), liste.remove(0));
	}
	
	@Test
	public void testInsertAndRemoveInt() {
		UnsortedList<Integer> liste = MyUnsortedList.of();
		liste.insert(1,0);
		liste.insert(2,1);
		liste.insert(3,2);
		liste.remove(1); // retrait de 2
		liste.insert(42, 1);
		liste.remove(2); // retrait de 3
		assertEquals("premier élément de la liste est 1", Integer.valueOf(1), liste.remove(0));
		assertEquals("second élément de la liste est 42", Integer.valueOf(42), liste.remove(0));
	}
	
}
