package top.ourck.utils;

import java.io.UnsupportedEncodingException;

public class TypeConvert {
	
//	private static char byteToChar(byte b1, byte b2) {
//        char c = (char) (((b1 & 0xFF) << 8) | (b2 & 0xFF));
//        return c;
//    }
//	
//	/**
//	 * Turn a byte[]'s String representation into a byte[].
//	 * Just like this: "1010" -> {1,0,1,0}
//	 * @param str String
//	 * @return byte[]
//	 */
//	public static byte[] parseByteAryStr(String str) {
//		char[] mcs = str.toCharArray();
//		byte[] mbs = new byte[mcs.length];
//		for(int i = 0; i < mbs.length; i++) {
//			mbs[i] = (byte)(mcs[i] - '0'); // Unsafe!
//		}
//		return mbs;
//	}
//	
//	/**
//	 * Turn a byte[] into a String.
//	 * Just like this: {1,0,1,0} -> "1010"
//	 * @param b byte[]
//	 * @return String
//	 */
//	public static String parseByteAry(byte[] b) {
//		StringBuilder stb = new StringBuilder();
//		for(int i = 0; i < b.length; i++) {
//			stb.append(String.valueOf(b[i]));
//		}
//		return stb.toString();
//	}
//
//	/**
//	 * Turn a byte[]'s String value representation into a String.
//	 * Just like this: "1010" -> "AA"
//	 * @param byteStr a byte[]'s String value representation
//	 * @return String object.
//	 * TODO What if we input a single-byte string?
//	 */
//	public static String byteValStrToStr(String byteStr) {
//		byte[] b = parseByteAryStr(byteStr);
//		return byteValToStr(b);
//	}
//	
//	/**
//	 * Turn a byte[]'s value into a String. <br>
//	 * Just like this: {1,0,1,0} -> "AA"
//	 * @param b byte[]
//	 * @return String object.
//	 * TODO What if we input a single-byte string?
//	 */
//	public static String byteValToStr(byte[] b) {
//		StringBuilder stb = new StringBuilder();
//		for(int i = 0; i < b.length; i += 2) {
//			stb.append(byteToChar(b[i], b[i + 1]));
//		}
//		return stb.toString();
//	}
//	
//	public static String strToByteValStr(String str) {
//		byte[] bVal = str.getBytes();
//		byte[] bits = new byte[64]; // TODO 64x Hard-coded.
//		for(int j = 0; j < bVal.length; j++) {
//			int val = bVal[j];
//			for(int i = 0; i < 8; i++) {
//				bits[8 - 1 - i] = (byte)(val & 1);
//				val >>>= 1;
//			}
//		}
//		
//		return parseByteAry(bits);
//	}
	
	/**
	 * Convert String's byte[] data into String <br>
	 * with character encoding = <b>ISO-8859-1</b>. 
	 * @param bs byte[]
	 * @return String
	 */
	public static String bytes2Str(byte[] bs) {
		try {
			return new String(bs, "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Convert String into String's byte[] data<br>
	 * with character encoding = <b>ISO-8859-1</b>. 
	 * @param str String
	 * @return byte[]
	 */
	public static byte[] str2Bytes(String str) {
		try {
			return str.getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static byte[] bytes2Bits(byte[] bs) {
		byte[] bits = new byte[bs.length * 8]; // TODO 64x Hard-coded.
		for(int j = 0; j < bs.length; j++) {
			int val = bs[j];
			for(int i = 0; i < 8; i++) {
				bits[8 - 1 - i + j * 8] = (byte)(val & 1);
				val >>>= 1;
			}
		}
		
		return bits;
	}

	public static byte[] bits2Bytes(byte[] bits) {
		byte[] bs = new byte[bits.length / 8];
		for(int i = 0; i < bs.length; i++) {
			for(int j = 0; j < 8; j++)
				bs[i] += bits[i * 8 + j] * Math.pow(2, 7 - j);
		}
		
		return bs;
	}
	
	public static byte[] str2Bits(String str) {
		return bytes2Bits(str2Bytes(str));
	}
	
	public static String bits2Str(byte[] bits) {
		return bytes2Str(bits2Bytes(bits));
	}
	
	
	// Convenience method.
	/**
	 * Turn a byte[]'s String representation into bits.
	 * Just like this: "1010" -> {1,0,1,0}
	 * @param bitsStr String
	 * @return Bits.
	 */
	public static byte[] parseStr2Bits(String bitsStr) {
		char[] mcs = bitsStr.toCharArray();
		byte[] mbs = new byte[mcs.length];
		for(int i = 0; i < mbs.length; i++) {
			mbs[i] = (byte)(mcs[i] - '0'); // Unsafe!
		}
		return mbs;
	}

	/**
	 * Turn a byte[]'s String representation into bits.
	 * Just like this: "1010" -> {1,0,1,0}
	 * @param bitsStr String
	 * @return Bits.
	 */
	public static String parseBits2Str(byte[] bits) {
		StringBuilder stb = new StringBuilder();
		for(int i = 0; i < bits.length; i++) {
			stb.append(String.valueOf(bits[i]));
		}
		return stb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(bytes2Str(str2Bytes("ABCDEFGHI")));
		
		byte[] bs = new byte[] {34, 38, -59, -109, 25, -72, 45, 42, 43, -104, 96, -18, -116, -41, -26, 61, -33, -1, -44, 79, -78, -61, 95, 57, 81, 127, 45, 32, -110, 112, 56, -94, 4, 64, 48, -118, -34, -36, 83, 64, -62, -35, 101, -84, -118, -49, -31, 41, -24, -99, 119, 19, -124, -40, 26, 103, 31, 80, 77, 110, 123, 122, -54, -72, 99, -80, 39, -128, -82, 25, 99, 123, 4, -120, -27, -112, 20, -122, -47, 86, -46, -74, 31, 48, -124, -7, 93, -38, 89, 26, 15, -13, 50, -72, -74, 103, 118, -64, -40, 17};
		byte[] bss = str2Bytes(bytes2Str(bs));
		System.out.println(new String(bs));
		System.out.println(new String(bss));
	}
}
