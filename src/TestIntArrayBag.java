import edu.uwm.cs351.IntArrayBag;
import junit.framework.TestCase;

public class TestIntArrayBag extends TestCase {

	@Override
	public void setUp() {
		try {
			assert 1/(5^5) == 42 : "OK";
			System.err.println("Assertions must be enabled to use this test suite.");
			System.err.println("In Eclipse: add -ea in the VM Arguments box under Run>Run Configurations>Arguments");
			assertFalse("Assertions must be -ea enabled in the Run Configuration>Arguments>VM Arguments",true);
		} catch (ArithmeticException ex) {
			return;
		}
	}
	/*
	 * This has been provided for you to test your implementation of the
	 * ensureCapacity.
	 */
	public void testEnsureCapacity() {
		IntArrayBag bag = new IntArrayBag();
		bag.add(1);
		bag.add(2);
		bag.add(3);

		// checking below current capacity
		bag.ensureCapacity(3);
		assertFalse(bag.getCapacity() == 3);
		assertTrue(bag.size() == 3);
		assertEquals(1, bag.countOccurrences(1));
		assertEquals(1, bag.countOccurrences(2));
		assertEquals(1, bag.countOccurrences(3));
		assertEquals(0, bag.countOccurrences(4));
		
		for(int i=0; i<8; i++) {
			bag.add(0);
		}
		assertTrue(bag.getCapacity() == 20);
		assertTrue(bag.size() == 11);		

		// checking above current capacity
		bag.ensureCapacity(54);
		assertTrue(bag.getCapacity() == 54);
		assertTrue(bag.size() == 11);		
		assertEquals(1, bag.countOccurrences(1));
		assertEquals(1, bag.countOccurrences(2));
		assertEquals(1, bag.countOccurrences(3));
	}

	public void testRemove() {
		IntArrayBag bag = new IntArrayBag();
		bag.add(1);
		bag.add(2);
		bag.add(3);
		assertEquals(3, bag.size());
		bag.remove(1);
		assertEquals(2, bag.size());
		assertEquals(0, bag.countOccurrences(1));
		assertEquals(1, bag.countOccurrences(2));
		assertEquals(1, bag.countOccurrences(3));
	}


	public void testAdd() {
		IntArrayBag bag = new IntArrayBag(0);
		assertEquals(0, bag.countOccurrences(1));
		bag.add(1);
		assertEquals(1, bag.countOccurrences(1));
		assertEquals(0, bag.countOccurrences(2));
		bag.add(2);
		assertEquals(1, bag.countOccurrences(2));
		bag.add(10);
		assertEquals(1, bag.countOccurrences(10));
		bag.add(1);
		assertEquals(2, bag.countOccurrences(1));
		assertEquals(1, bag.countOccurrences(2));
		assertEquals(1, bag.countOccurrences(10));
		assertEquals(0, bag.countOccurrences(0));
	}
}

