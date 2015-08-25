package misc;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class LookSaySupplier implements Supplier<BigInteger> {

	private AtomicBoolean useFormula = new AtomicBoolean();
	private BigInteger lastValue;

	public LookSaySupplier(BigInteger n) {
		useFormula.set(false);
		lastValue = n;
	}

	public BigInteger get() {
		boolean b = useFormula.compareAndSet(false, true);
		// b == true means original useFormula is false and is set to true now.
		if (b) {
			return lastValue;
		} else {
			synchronized (lastValue) {
				lastValue = StreamFactoryJava.lookSay(lastValue);
			}
			return lastValue;
		}
	}
}