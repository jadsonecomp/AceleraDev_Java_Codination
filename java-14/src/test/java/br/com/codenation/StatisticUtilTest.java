package br.com.codenation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StatisticUtilTest {

	@Test
	public void testAverage() {
		assertEquals(3, StatisticUtil.average(new int[] {1,2,3,4,5}));
	}

	@Test
	public void testMode() {
		assertEquals(3, StatisticUtil.mode(new int[] {1,2,3,3}));
	}
	
	@Test
	public void testMedian() {
		assertEquals(3, StatisticUtil.median(new int[] {1,2,3,4,5}));
	}

	@Test
	public void testMedianWithArrayUnOrderedAndNegativeNumbers() {
		assertEquals(-3, StatisticUtil.median(new int[] {-2,-1,-5,-4,-3}));
	}

	@Test
	public void testMedianWithEvenArrayUnOrderedAndNegativeNumbers() {
		assertEquals(-3, StatisticUtil.median(new int[] {-2,-1,-5,-4}));
	}
}
