package top.ourck.utils;

import java.math.BigInteger;

public class GcdUtils {
	
	public static BigInteger extGcd(BigInteger a, BigInteger b, BigInteger[] x_y) {
		if(b.equals(BigInteger.ZERO)) {
			BigInteger y = BigInteger.ZERO;
			BigInteger x = BigInteger.ONE;
			x_y[0] = x; x_y[1] = y;
			return a;
		}
		
		BigInteger result = extGcd(b, a.mod(b), x_y);
		BigInteger t = new BigInteger(x_y[1].toString());
		x_y[1] = x_y[0].subtract(a.divide(b).multiply(x_y[1])); // y = x - (1 / b) * y
		x_y[0] = t;
		
		return result;
	}
}
