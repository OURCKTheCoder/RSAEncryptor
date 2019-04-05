package top.ourck.mainconsole;

import java.math.BigInteger;
import java.util.Random;

import top.ourck.utils.GcdUtils;

public class MainConsole {

	// C = M ^ e mod n, M = C ^ d mod n
	public static void main(String[] args) {
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
		System.out.println(GcdUtils.extGcd(e, phiN, x_y)); // x = x_y[0] = d!
		BigInteger d = x_y[0];
		
		BigInteger M = new BigInteger("2333333333333333333333333");
		BigInteger C = M.modPow(e, n);
		System.out.println(C);
		
		M = C.modPow(d, n);
		System.out.println(M);
	}
}
