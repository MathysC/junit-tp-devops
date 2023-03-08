package kata;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringCalculatorTest {

	@Test
	public final void testAddEmpty() {
		assertEquals(0, StringCalculator.Add(""));
        assertThrows(IllegalArgumentException.class, () -> {StringCalculator.Add(null);});
        assertThrows(DelimiterArgumentException.class, () -> {StringCalculator.Add(",");});
        assertThrows(DelimiterArgumentException.class, () -> {StringCalculator.Add("\n");});

	}
	
	@Test
	public final void testAddOne() {
		assertEquals(1, StringCalculator.Add("1"));
		assertEquals(2, StringCalculator.Add("2"));
		assertEquals(69, StringCalculator.Add("69"));
		assertEquals(122, StringCalculator.Add("122"));
        assertThrows(IllegalArgumentException.class, () -> {StringCalculator.Add(",10");});
        assertThrows(DelimiterArgumentException.class, () -> {StringCalculator.Add("10,");});
	}
	
	@Test
	public final void testAddTwoNumbersComaDelimited() {
		assertEquals(3, StringCalculator.Add("1,2"));
		assertEquals(69, StringCalculator.Add("60,9"));
		assertEquals(122, StringCalculator.Add("120,2"));
        assertThrows(IllegalArgumentException.class, () -> {StringCalculator.Add(",10,10");});
        assertThrows(DelimiterArgumentException.class, () -> {StringCalculator.Add("10,10,");});
	}
	
	@Test
	public final void testAddThreeNumbersComaDelimited() {
		assertEquals(8, StringCalculator.Add("1,2,5"));
		assertEquals(69, StringCalculator.Add("60,6,3"));
		assertEquals(122, StringCalculator.Add("100,20,2"));
        assertThrows(IllegalArgumentException.class, () -> {StringCalculator.Add(",10,10,10");});
        assertThrows(DelimiterArgumentException.class, () -> {StringCalculator.Add("10,10,10,");});
	}
	
	@Test
	public final void testAddThreeNumbersNewLineDelimited() {
		assertEquals(8, StringCalculator.Add("1\n2\n5"));
		assertEquals(69, StringCalculator.Add("60\n6\n3"));
		assertEquals(122, StringCalculator.Add("100\n20\n2"));
        assertThrows(IllegalArgumentException.class, () -> {StringCalculator.Add("\n10\n10\n10");});
        assertThrows(DelimiterArgumentException.class, () -> {StringCalculator.Add("10\n10\n10\n");});
	}
	
	@Test
	public final void testNegativeArgument() {
        assertThrows(NegativeNotAllowedException.class, () -> {StringCalculator.Add("-1");});
        assertThrows(NegativeNotAllowedException.class, () -> {StringCalculator.Add("12,-4");});
        assertThrows(NegativeNotAllowedException.class, () -> {StringCalculator.Add("12\n-4");});
	}
	
	@Test
	public final void testMoreThanOneThousandIgnored() {
		assertEquals(1000, StringCalculator.Add("1000"));
		assertEquals(0, StringCalculator.Add("1002"));
		assertEquals(69, StringCalculator.Add("1001\n69"));
		assertEquals(420, StringCalculator.Add("400\n10000,20"));
		assertEquals(16, StringCalculator.Add("9,7,1001"));
    }
	
	@Test
	public final void testCharDefinedDelimiter() {
		// //delimiter\n[sum]
		assertEquals(8, StringCalculator.Add("//$\n8"));
		assertEquals(10, StringCalculator.Add("//+\n1+2+7"));
		assertEquals(15, StringCalculator.Add("//!\n5!5\n5"));
		assertEquals(69, StringCalculator.Add("//\n\n40\n10\n10\n9"));
        assertThrows(IllegalArgumentException.class, () -> {StringCalculator.Add("\\*\n10*10*");});
        assertThrows(IllegalArgumentException.class, () -> {StringCalculator.Add("\\*10*10*");});
        assertThrows(IllegalArgumentException.class, () -> {StringCalculator.Add("\\*\n*10*10");});
	}
	
	@Test
	public final void testStringDefinedDelimiter() {
		// //[delimiter]\n[sum]
        assertThrows(IllegalArgumentException.class, () -> {StringCalculator.Add("\\[*\n10");});
        assertThrows(IllegalArgumentException.class, () -> {StringCalculator.Add("\\*]\n10");});
		assertEquals(8, StringCalculator.Add("//[toto]\n8"));
		assertEquals(15, StringCalculator.Add("//[!toto]\n5!toto5\n5"));
		assertEquals(16, StringCalculator.Add("//[toto]\n8toto8"));
	}
	
	@Test
	public final void testMultipleStringDefinedDeliniters() {
		// //[delimiter][delimiter]\n[sum]
		assertEquals(8, StringCalculator.Add("//[toto][tutu]\n2toto2tutu2toto2"));
		assertEquals(15, StringCalculator.Add("//[toto][toto]\n5toto5toto5"));
        assertThrows(DelimiterArgumentException.class, () -> {StringCalculator.Add("//[toto][toto]\n5toto5toto5toto");});
        assertThrows(IllegalArgumentException.class, () -> {StringCalculator.Add("//[tuu][toto]5toto5toto5");});
        assertThrows(IllegalArgumentException.class, () -> {StringCalculator.Add("//[tuu][toto]\ntuu5toto5toto5");});
	}
}
