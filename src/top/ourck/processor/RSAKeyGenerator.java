package top.ourck.processor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import top.ourck.utils.GcdUtils;

public class RSAKeyGenerator {

	/**
	 * Generate RSA keys.
	 * @return Key list. list[0] = SK, list[1] = PK, list[2] = N
	 */
	public static List<BigInteger> generate() {
		List<BigInteger> kList = new ArrayList<BigInteger>();
		BigInteger p = BigInteger.probablePrime(400, new Random(System.currentTimeMillis()));
		BigInteger q = BigInteger.probablePrime(400, new Random(System.currentTimeMillis())); // 121-bit decimal.
		BigInteger n = p.multiply(q);
		BigInteger e = new BigInteger("65537");
		
		BigInteger phiN = p.subtract(BigInteger.ONE).multiply(
				q.subtract(BigInteger.ONE));
		//System.out.println(phiN.gcd(e).equals(BigInteger.ONE));
		BigInteger[] x_y = new BigInteger[2];
		x_y[0] = BigInteger.ZERO;
		x_y[1] = BigInteger.ZERO;
		GcdUtils.extGcd(e, phiN, x_y); // x = x_y[0] = d!
		BigInteger d = x_y[0];
		
		// TODO Is this correct?
		kList.add(e);
		kList.add(d);
		kList.add(n);
		
		return kList;
	}
	
}
