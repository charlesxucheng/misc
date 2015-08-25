package misc;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import misc.StreamFactoryJava;

public class StreamFactoryJavaTest {

	@Test
	public void testCountDigit() {
		List<Pair<Integer, Integer>> emptyList = new Vector<Pair<Integer, Integer>>();
		assertEquals(emptyList, StreamFactoryJava.countDigit(new Vector<Integer>()));

		List<Integer> input = new Vector<Integer>();
		input.add(1);
		List<Pair<Integer, Integer>> result = new Vector<Pair<Integer, Integer>>(emptyList);
		result.add(Pair.of(new Integer(1), new Integer(1)));
		assertEquals(result, StreamFactoryJava.countDigit(input));

		input.add(1);
		result.clear();
		result.add(Pair.of(new Integer(1), new Integer(2)));
		assertEquals(result, StreamFactoryJava.countDigit(input));

		input.clear();
		input.add(5);
		result.clear();
		result.add(Pair.of(new Integer(5), new Integer(1)));
		assertEquals(result, StreamFactoryJava.countDigit(input));

		input.clear();
		input.add(1);
		input.add(3);
		input.add(3);
		input.add(4);
		result.clear();
		result.add(Pair.of(new Integer(1), new Integer(1)));
		result.add(Pair.of(new Integer(3), new Integer(2)));
		result.add(Pair.of(new Integer(4), new Integer(1)));
		assertEquals(result, StreamFactoryJava.countDigit(input));

		input.clear();
		input.add(1);
		input.add(2);
		input.add(1);
		input.add(1);
		result.clear();
		result.add(Pair.of(new Integer(1), new Integer(1)));
		result.add(Pair.of(new Integer(2), new Integer(1)));
		result.add(Pair.of(new Integer(1), new Integer(2)));
		assertEquals(result, StreamFactoryJava.countDigit(input));

		input.clear();
		input.add(5);
		input.add(5);
		input.add(2);
		input.add(1);
		input.add(1);
		input.add(1);
		input.add(2);
		result.clear();
		result.add(Pair.of(new Integer(5), new Integer(2)));
		result.add(Pair.of(new Integer(2), new Integer(1)));
		result.add(Pair.of(new Integer(1), new Integer(3)));
		result.add(Pair.of(new Integer(2), new Integer(1)));
		assertEquals(result, StreamFactoryJava.countDigit(input));

		input.clear();
		input.add(0);
		input.add(0);
		input.add(2);
		input.add(1);
		input.add(1);
		input.add(1);
		input.add(0);
		result.clear();
		result.add(Pair.of(new Integer(0), new Integer(2)));
		result.add(Pair.of(new Integer(2), new Integer(1)));
		result.add(Pair.of(new Integer(1), new Integer(3)));
		result.add(Pair.of(new Integer(0), new Integer(1)));
		assertEquals(result, StreamFactoryJava.countDigit(input));
	}

	@Test
	public void testLookSay() {
		assertEquals(BigInteger.valueOf(11), StreamFactoryJava.lookSay(BigInteger.valueOf(1)));
		assertEquals(BigInteger.valueOf(1211), StreamFactoryJava.lookSay(BigInteger.valueOf(21)));
		assertEquals(BigInteger.valueOf(10), StreamFactoryJava.lookSay(BigInteger.valueOf(0)));
		assertEquals(BigInteger.valueOf(111221), StreamFactoryJava.lookSay(BigInteger.valueOf(1211)));
	}

	@Test
	public void testLookSayStream() {
//		StreamFactoryJava.getLookSayStream(BigInteger.valueOf(1)).limit(5).forEach(System.out::println);
		
		assertEquals(Stream.of(
				BigInteger.valueOf(1), 
				BigInteger.valueOf(11),
				BigInteger.valueOf(21),
				BigInteger.valueOf(1211),
				BigInteger.valueOf(111221)
				).collect(Collectors.toList()), 
				StreamFactoryJava.getLookSayStream(BigInteger.valueOf(1)).limit(5).collect(Collectors.toList()));

		assertEquals(Stream.of(
				BigInteger.valueOf(5), 
				BigInteger.valueOf(15),
				BigInteger.valueOf(1115),
				BigInteger.valueOf(3115),
				BigInteger.valueOf(132115)
				).collect(Collectors.toList()), 
				StreamFactoryJava.getLookSayStream(BigInteger.valueOf(5)).limit(5).collect(Collectors.toList()));
		
		assertEquals(Stream.of(
				BigInteger.valueOf(5), 
				BigInteger.valueOf(15),
				BigInteger.valueOf(1115),
				BigInteger.valueOf(3115),
				BigInteger.valueOf(132115)
				).collect(Collectors.toList()), 
				StreamFactoryJava.getLookSayStream(BigInteger.valueOf(-5)).limit(5).collect(Collectors.toList()));

		assertEquals(Stream.of(
				BigInteger.valueOf(0), 
				BigInteger.valueOf(10),
				BigInteger.valueOf(1110),
				BigInteger.valueOf(3110),
				BigInteger.valueOf(132110)
				).collect(Collectors.toList()), 
				StreamFactoryJava.getLookSayStream(BigInteger.valueOf(0)).limit(5).collect(Collectors.toList()));

		assertEquals(Stream.of(
				BigInteger.valueOf(42), 
				BigInteger.valueOf(1412),
				BigInteger.valueOf(11141112),
				BigInteger.valueOf(31143112),
				new BigInteger("132114132112")
				).collect(Collectors.toList()), 
				StreamFactoryJava.getLookSayStream(BigInteger.valueOf(42)).limit(5).collect(Collectors.toList()));

	}
}
