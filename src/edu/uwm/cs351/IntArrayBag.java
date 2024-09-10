package edu.uwm.cs351;

import junit.framework.TestCase;

public class IntArrayBag implements Cloneable {
	private Integer[] data;
	private int manyItems;

	public IntArrayBag() {
		final int INITIAL_CAPACITY = 10;
		manyItems = 0;
		data = new Integer[INITIAL_CAPACITY];
	}
	
	/**
	 * Initializes a bag of the given initial capacity
	 * @param initialCapacity, initial capacity of the bag
	 */
	public IntArrayBag(int initialCapacity) {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("initialCapacity is negative: "
					+ initialCapacity);
		}
		manyItems = 0;
		data = new Integer[initialCapacity];
	}

	/**
	 * Add the element to end of this bag
	 * @param element, element should not be null
	 * @throws IllegalArgumentException, if element is null
	 */
	public void add(Integer element) {
		if (element == null) throw new IllegalArgumentException();
		
		assert wellFormed() : "Failed at the start of add";
		ensureCapacity(manyItems + 1);		
		data[manyItems] = manyItems + 1;
		manyItems++;
		
		assert wellFormed() : "Failed at the end of add";
	}

	private boolean report(String message) {
		System.out.println(message);
		return false;
	}

	private boolean wellFormed() {
		// Make assertions about the invariant, returning false
		// if the invariant false. Taken from pg 123 (3rd ed.)

		// #1. manyItems should never be greater than data.length
		if (manyItems > data.length)
			return report("manyItems is greater than data.length");

		// #2. When the bag isn't empty, then items data[0] to data[manyItems-1]
		// should contain data and therefore not be null
		// (this is because null data are not allowed in this bag)
		// TODO Implement the 2nd Invariant

		// All invariant assertions passed so return true
		return true;
	}

	/**
	 * Remove an element from this bag
	 * @param target, element to be removed and
	 * target should not be null
	 * @return true if target exist and removed in this bag
	 * otherwise return false.
	 * @throws IllegalArgumentException, if the target is null
	 */
	public boolean remove(Integer target) {
		if (target == null) throw new IllegalArgumentException();
		
		assert wellFormed() : "Failed at the start of remove";

		int index = 0;
		while ((index < manyItems) && (target != data[index])) {
			index++;
		}

		if (index == manyItems) {
			return false;
		} else {
			data[index] = null;
			--manyItems;
			assert wellFormed() : "Failed at the end of remove";
			return true;
		}
	}

	/**
	 * Returns the size of the bag, or number of elements in the bag
	 * @return size of this bag
	 */
	public int size() {
		return manyItems;
	}

	/**
	 * return the true capacity of this bag
	 * @return capacity of this bag
	 */
	public int getCapacity() {
		return data.length;
	}
	
	/**
	 * Counts the occurrences of the target in this bag
	 * @param target, the element to count the occurrences of and
	 * target should not be null
	 * @return number of times the target appears in the bag
	 * @throws IllegalArgumentException, if the target is null
	 */
	public int countOccurrences(Integer target) {
		if (target == null) throw new IllegalArgumentException();
		
		assert wellFormed() : "Failed at the start of countOccurrences";
		
		int answer = 0;
		int index = 0;

		for (index = 0; index < manyItems; index++) {
			if (target == data[index]) {
				answer++;
			}
		}
		return answer;
	}

	public void ensureCapacity(int minimumCapacity) {
		// TODO implement this method
		// Do nothing if the current capacity is at least minimumCapacity
		// Otherwise create a new array: either double the current capacity
		//		or equal to minimumCapacity if that's not big enough
		// Then copy over all the elements into the new array,
		//		and use that as the data array
	}
	
	public static class TestInternals extends TestCase {
		
		private IntArrayBag bag;
		
		@Override
		protected void setUp() {
			bag = new IntArrayBag();
		}
		
		// first invariant - manyItems < data.length
		public void test01() {
			bag.data = new Integer[0];
			bag.manyItems = 1;
			assertFalse(bag.wellFormed());
			bag.manyItems = 0;
			assertTrue(bag.wellFormed());
		}
		
		// second invariant - no null items
		public void test02() {
			bag.data[0] = 1;
			bag.data[1] = 2;
			bag.data[2] = 3;
			bag.manyItems = 4;
			assertFalse(bag.wellFormed());
			bag.manyItems = 3;
			assertTrue(bag.wellFormed());
			bag.manyItems = 0;
			assertTrue(bag.wellFormed());
		}
		
		// second invariant - no null items
		public void test03() {
			bag.data[0] = 1;
			bag.data[1] = 2;
			bag.data[2] = 3;
			bag.manyItems = 3;
			assertTrue(bag.wellFormed());
			bag.data[1] = null;
			assertFalse(bag.wellFormed());
			bag.manyItems = 2;
			assertFalse(bag.wellFormed());
			bag.manyItems = 1;
			assertTrue(bag.wellFormed());
		}
		
		// more tests of second item
		public void test04() {
			bag.data = new Integer[10];
			bag.manyItems = 0;
			assertTrue(bag.wellFormed());
			bag.manyItems = 1;
			assertFalse(bag.wellFormed());
			bag.data[0] = 45;
			assertTrue(bag.wellFormed());
			bag.data[2] = 55;
			bag.manyItems = 2;
			assertFalse(bag.wellFormed());
			bag.data[1] = 0;
			assertTrue(bag.wellFormed());
			bag.manyItems = 3;
			assertTrue(bag.wellFormed());
			bag.data[4] = -44;
			bag.data[6] = 1_000_000;
			bag.manyItems = 5;
			assertFalse(bag.wellFormed());
			bag.data[3] = 1;
			assertTrue(bag.wellFormed());
		}
		
		//and some more just to be sure
		public void test05() {
			bag.data = new Integer[10];
			bag.data[0] = 5;
			bag.data[1] = 55;
			bag.data[2] = 555;
			bag.data[3] = 5555;
			bag.data[4] = 55555;
			bag.data[6] = 555555;
			bag.data[7] = 5555555;
			bag.data[8] = 55555555;
			bag.manyItems = 8;
			assertFalse(bag.wellFormed());
			bag.data[5] = -5;
			assertTrue(bag.wellFormed());
			bag.manyItems = 9;
			assertTrue(bag.wellFormed());
			bag.manyItems = 10;
			assertFalse(bag.wellFormed());
			bag.data[9] = 0;
			assertTrue(bag.wellFormed());
			bag.data[5] = null;
			assertFalse(bag.wellFormed());
		}
		
		// a specific situation:
		public void test06() {
			bag.data[0] = null;
			bag.data[1] = 2;
			bag.data[2] = 3;
			bag.manyItems = 2;
			assertFalse(bag.wellFormed());
		}
	}
}

