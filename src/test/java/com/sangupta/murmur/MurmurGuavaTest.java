package com.sangupta.murmur;

import java.nio.ByteBuffer;
import java.util.UUID;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * Test to see if the hash code generated is compatible with 
 * Google Guava library or not.
 * 
 * @author sangupta
 *
 */
public class MurmurGuavaTest {
	
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

	public static void main(String[] args) {
		final int seed = 42;
		HashFunction guava = Hashing.murmur3_128(seed);

		String uuid = UUID.randomUUID().toString();
		byte[] bytes = uuid.getBytes();
		
		HashCode guavaCode = guava.hashBytes(bytes);
		long[] myCodes = Murmur3.hash_x64_128(bytes, bytes.length, seed);
		
		System.out.println("Hash from guava: " + guavaCode);
		System.out.println("Hash from murmur: " + display(myCodes[0]) + " " + display(myCodes[1]));
	}
	
	public static String display(long x) {
		return bytesToHex(longToBytes(x));
	}
	
	public static byte[] longToBytes(long x) {
	    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
	    buffer.putLong(x);
	    return buffer.array();
	}

	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
}
