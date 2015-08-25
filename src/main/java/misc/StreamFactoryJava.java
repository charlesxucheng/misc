package misc;

import java.math.BigInteger;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;

public class StreamFactoryJava {

	static Pair<List<Integer>, List<Integer>> span(List<Integer> l) {
		if (l != null && l.isEmpty()) {
			List<Integer> l1 = new Vector<Integer>();
			List<Integer> l2 = new Vector<Integer>();
			return Pair.of(l1, l2);
		}
		else {
			Integer x = l.get(0);
			int i = 0;
			while (i <= l.size()) {
			    if (i == l.size() || l.get(i) != x) break;
			    i++;
			}
			List<Integer> l1 = l.subList(0,i);
			List<Integer> l2 = i == l.size() ? new Vector<Integer>() : l.subList(i, l.size());  
			return Pair.of(l1, l2);
		}
	}
	
	static List<Pair<Integer, Integer>> countDigit(List<Integer> s) {
		if (s != null && s.isEmpty())
			return new Vector<Pair<Integer, Integer>>();
		else {
			Pair<List<Integer>, List<Integer>> p = span(s);
			List<Pair<Integer, Integer>> l = countDigit(p.getRight());
			l.add(0, Pair.of(p.getLeft().get(0), p.getLeft().size()));
			return l; 
		}
	}

	static BigInteger lookSay(BigInteger n) {
		List<Integer> digits = Stream.of(ArrayUtils.toObject(n.toString().toCharArray())).map(Character::getNumericValue).collect(Collectors.toList());
		Stream<Pair<Integer, Integer>> counts = countDigit(digits).stream();
		String result = counts.map(x -> x.getRight().toString().concat(x.getLeft().toString())).reduce("", (a,b) -> a.concat(b));
		return new BigInteger(result);
	}

	static Stream<BigInteger> getLookSayStream(BigInteger n) {
		Stream<BigInteger> s = Stream.generate(new LookSaySupplier(n.abs()));
		return s;
	}
}