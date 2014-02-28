/**
 *
 * murmurhash - Pure Java implementation of the Murmur Hash algorithms.
 * Copyright (c) 2014, Sandeep Gupta
 * 
 * http://sangupta.com/projects/murmur
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.sangupta.murmur;

/**
 * A pure Java implementation of the Murmur 1 hashing algorithm as presented
 * at <a href="https://sites.google.com/site/murmurhash/">Murmur Project</a>
 * 
 * Code is ported from original C++ source at 
 * <a href="https://sites.google.com/site/murmurhash/MurmurHash.cpp?attredirects=0">
 * MurmurHash.cpp</a>
 * 
 * @author sangupta
 * @since 1.0
 */
public class Murmur1 implements MurmurConstants {
	
	/**
	 * Compute the Murmur1 hash as described in the original source code.
	 * 
	 * @param data
	 *            the data that needs to be hashed
	 * 
	 * @param length
	 *            the length of the data that needs to be hashed
	 * 
	 * @param seed
	 *            the seed to use to compute the hash
	 * 
	 * @return the computed hash value
	 */
	public static long hash(final byte[] data, int length, long seed) {
		final long m = 0xc6a4a793l;
		final int r = 16;
		
		long h = seed ^ (length * m);
		
		// Mix 4 bytes at a time into the hash
		int length4 = length >> 2;

		for (int i = 0; i < length4; i++) {
			final int i4 = i << 2;
			
			long k = (data[i4] & UNSIGNED_MASK);
			k |= (data[i4 + 1] & UNSIGNED_MASK) << 8;
			k |= (data[i4 + 2] & UNSIGNED_MASK) << 16;
			k |= (data[i4 + 3] & UNSIGNED_MASK) << 24;
			
			h = ((h + k) & UINT_MASK);
		    h = ((h * m) & UINT_MASK);
		    h ^= ((h >> 16) & UINT_MASK);
		}
		
		// remaining bytes
		int offset = length4 << 2;
		switch (length & 3) {
			case 3:
				h += ((data[offset + 2] << 16) & UINT_MASK);
			
			case 2:
				h += ((data[offset + 1] << 8) & UINT_MASK);
			
			case 1:
				h += ((data[offset]) & UINT_MASK);
				h = ((h * m) & UINT_MASK);
				h ^= ((h >> r) & UINT_MASK);
		}

		// final operations
		h = ((h * m) & UINT_MASK);
		h ^= ((h >> 10) & UINT_MASK);
		h = ((h * m) & UINT_MASK);
		h ^= ((h >> 17) & UINT_MASK);

		// return the hash
		return h;
	}

	/**
	 * Compute the Murmur1 hash (aligned version) as described in the original source code.
	 * 
	 * Code ported from original C++ version at
	 * <a href="https://sites.google.com/site/murmurhash/MurmurHashAligned.cpp?attredirects=0">MurmurHashAligned.cpp</a>
	 * 
	 * @param data
	 *            the data that needs to be hashed
	 * 
	 * @param length
	 *            the length of the data that needs to be hashed
	 * 
	 * @param seed
	 *            the seed to use to compute the hash
	 * 
	 * @return the computed hash value
	 */
	public static long hashAligned(final byte[] data, int length, long seed) {
		return hash(data, length, seed);
	}
	
}
