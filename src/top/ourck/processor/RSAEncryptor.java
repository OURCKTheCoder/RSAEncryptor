package top.ourck.processor;

import java.math.BigInteger;

import top.ourck.utils.TypeConvert;

public class RSAEncryptor {

	/**
	 * Encrypt message.
	 * @param m Message's byte[] data.
	 * @param k Key's byte[] data.
	 * @param N mod n
	 * @return
	 */
	public byte[] encrypt(byte[] m, byte[] k, BigInteger N) {
		BigInteger message = new BigInteger(m);
		BigInteger key = new BigInteger(k);
		
		BigInteger cipher = message.modPow(key, N);
		return cipher.toByteArray();
	}
	
	/**
	 * Encrypt message.
	 * @param m Message.
	 * @param k Key.
	 * @param N mod n
	 * @return
	 */
	public String encrypt(String m, BigInteger k, BigInteger N) {
		byte[] msg = TypeConvert.str2Bytes(m);
		byte[] key = k.toByteArray();
		
		byte[] c = encrypt(msg, key, N);
		return TypeConvert.bytes2Str(c);
	}
	
}
