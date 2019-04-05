package top.ourck.processor;

import java.math.BigInteger;

import top.ourck.utils.TypeConvert;

public class RSADecryptor {
	
	/**
	 * Decrypt message.
	 * @param c Cipher's byte[] data.
	 * @param k Key's byte[] data.
	 * @param N mod n
	 * @return
	 */
	public byte[] decrypt(byte[] c, byte[] k, BigInteger N) {
		BigInteger cipher = new BigInteger(c);
		BigInteger key = new BigInteger(k);
		
		BigInteger message = cipher.modPow(key, N);
		return message.toByteArray();
	}
	
	/**
	 * Decrypt message.
	 * @param c Cipher.
	 * @param k Key.
	 * @param N mod n
	 * @return
	 */
	public String decrypt(String c, BigInteger k, BigInteger N) {
		byte[] cip = TypeConvert.str2Bytes(c);
		byte[] key = k.toByteArray();
		
		byte[] m = decrypt(cip, key, N);
		return TypeConvert.bytes2Str(m);
	}
	
}
