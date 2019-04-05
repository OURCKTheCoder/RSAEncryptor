package top.ourck.mainconsole;

import java.math.BigInteger;
import java.util.List;

import top.ourck.processor.RSADecryptor;
import top.ourck.processor.RSAEncryptor;
import top.ourck.processor.RSAKeyGenerator;

public class FrontEnd {

	public static void main(String[] args) {
		List<BigInteger> kList = RSAKeyGenerator.generate();
		BigInteger sk = new BigInteger("65537");//kList.get(0);
		BigInteger pk = kList.get(1);
		BigInteger N = kList.get(2);
		
//		byte[] SK = sk.toByteArray();
//		byte[] M = TypeConvert.str2Bytes("Eat something tonight?");
//		byte[] C = new RSAEncryptor().encrypt(M, SK, N);
//		
//		byte[] PK = pk.toByteArray();
//		byte[] M_2 = new RSADecryptor().decrypt(C, PK, N);
//		
//		System.out.println(TypeConvert.bytes2Str(M_2));
		
		String msg = "Eat something tonight? ";
		String c = new RSAEncryptor().encrypt(msg, sk, N);
		String decM = new RSADecryptor().decrypt(c, pk, N);
		
		System.out.println(msg);
		//System.out.println(c);
		System.out.println(decM);
	}
}
