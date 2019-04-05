package top.ourck.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

	public static byte[] getDigest(byte[] msg) {
		byte[] md5Digest = null;
		try {
			MessageDigest mdg = MessageDigest.getInstance("MD5");
			mdg.update(msg);
			md5Digest = mdg.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return md5Digest;
	}
	
	/**
	 * Merge message & MD5 digest together as a new byte[]
	 * @param msg Message.
	 * @param digest MD5 Digest.
	 * @return Merged message.
	 */
	public static byte[] merge(byte[] msg, byte[] digest) {
		byte[] merged = new byte[msg.length + digest.length];
		System.arraycopy(msg, 0, merged, 0, msg.length);
		System.arraycopy(digest, 0, merged, msg.length, digest.length);
		
		return merged;
	}
	
	public static boolean compareDigest(byte[] calculated, byte[] gotten) {
		if(calculated.length != gotten.length) return false;
		
		for(int i = 0; i < calculated.length; i++) {
			if(calculated[i] != gotten[i]) return false;
		}
		return true;
	}
	
	public static String getDigest(String msg) {
		byte[] bs = TypeConvert.str2Bytes(msg);
		return TypeConvert.bytes2Str(getDigest(bs));
	}
	
	public static void main(String[] args) {
		System.out.println(MD5Utils.getDigest("Eat something tonight?"));
	}
}
