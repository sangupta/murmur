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

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;


/**
 * Test the hash algorithm for all Murmur hashes (1/2/3) that check hashes
 * of all English words against hashes computed using actual Murmur C++ code.
 * 
 * All english words: http://www.sitopreferito.it/html/all_english_words.html
 * Numbers from 1 to 216553
 * 216553 random type-4 UUIDs
 * 
 */
public class MurmurEnglishTest implements MurmurConstants {
	
	/**
	 * The murmur seed to be used
	 */
	private static final long MURMUR_SEED = 0x7f3a21eal;
	
	/**
	 * The base path where all data files are stored
	 */
	private static final String BASE_PATH = new File(((URL) MurmurEnglishTest.class.getResource("/english/")).getFile()).getAbsolutePath();
	
	private final StringBuilder builder = new StringBuilder(100);
	
	@Test
	public void testEnglishWordsMurmur1() throws IOException {
		testHashes("hash1-v1.txt", new StringHashFunction() {
			
			public String getHash(byte[] bytes) {
				return String.valueOf(Murmur1.hash(bytes, bytes.length, MURMUR_SEED));
			}
			
		});
	}
	
	@Test
	public void testEnglishWordsMurmur1_Aligned() throws IOException {
		testHashes("hash1-v1.txt", new StringHashFunction() {
			
			public String getHash(byte[] bytes) {
				return String.valueOf(Murmur1.hashAligned(bytes, bytes.length, MURMUR_SEED));
			}
			
		});
	}
	
	@Test
	public void testEnglishWordsMurmur2_hash() throws IOException {
		testHashes("hash2-v1.txt", new StringHashFunction() {
			
			public String getHash(byte[] bytes) {
				return String.valueOf(Murmur2.hash(bytes, bytes.length, MURMUR_SEED));
			}
			
		});
	}
	
	@Test
	public void testEnglishWordsMurmur2_hash64() throws IOException {
		testHashes("hash2-v2.txt", new StringHashFunction() {
			
			public String getHash(byte[] bytes) {
				return String.valueOf(Murmur2.hash64(bytes, bytes.length, MURMUR_SEED));
			}
			
		});
	}
	
	@Test
	public void testEnglishWordsMurmur3_x86_32() throws IOException {
		testHashes("hash3-x86-32.txt", new StringHashFunction() {
			
			public String getHash(byte[] bytes) {
				return String.valueOf(Murmur3.hash_x86_32(bytes, bytes.length, MURMUR_SEED));
			}
			
		});
	}
	
//	@Test
//	public void testEnglishWordsMurmur3_x86_128() throws IOException {
//		testHashes("hash3-x86-128.txt", new StringHashFunction() {
//			
//			public String getHash(byte[] bytes) {
//				return String.valueOf(Murmur3.hash_x86_128(bytes, bytes.length, MURMUR_SEED));
//			}
//			
//		});
//	}
	
	@Test
	public void testEnglishWordsMurmur3_x64_128() throws IOException {
		testHashes("hash3-x64-128.txt", new StringHashFunction() {
			
			public String getHash(byte[] bytes) {
				builder.setLength(0);
				
				long[] longs = Murmur3.hash_x64_128(bytes, bytes.length, MURMUR_SEED);
				int i1 = (int) (longs[0] >>> 32);
				int i2 = (int) (longs[0] & LONG_MASK);
				
				int i3 = (int) (longs[1] >>> 32);
				int i4 = (int) (longs[1] & LONG_MASK);
				
				builder.append(String.valueOf(i2));
				builder.append(',');
				builder.append(String.valueOf(i1));
				builder.append(',');
				builder.append(String.valueOf(i4));
				builder.append(',');
				builder.append(String.valueOf(i3));
				
				return builder.toString();
			}
			
		});
	}

	/**
	 * The main core logic for all testing.
	 * 
	 * @param outputFileName
	 * @param function
	 * @throws IOException
	 */
	private void testHashes(String outputFileName, StringHashFunction function) throws IOException {
		LineIterator iterator = FileUtils.lineIterator(new File(BASE_PATH + "/english-wordlist.txt"));
		LineIterator results = FileUtils.lineIterator(new File(BASE_PATH + "/" + outputFileName));
		
		int matched = 0;
		int total = 0;
		
		while(iterator.hasNext()) {
			String line = iterator.next();
			
			byte[] bytes = line.getBytes();
			String computed = function.getHash(bytes);
			String actual = results.next();
			
			if(actual.contains(",")) {
				// result has multiple values
				String[] act = actual.split(",");
				String[] com = computed.split(",");
				if(act.length == com.length) {
					boolean allMatch = true;
					for(int index = 0; index < act.length; index++) {
						allMatch = allMatch & bigMatch(act[index], com[index]);
					}
					
					if(allMatch) {
						matched++;
					} else {
//						System.out.println("Failed for word: " + line);
//						System.out.println("actual: " + actual);
//						System.out.println("computed: " + computed);
//						
//						throw new RuntimeException("mismatch found");
					}
				}
			} else {
				// result has only a single value
				if(actual.equals(computed)) {
					matched++;
				} else {
					if(bigMatch(actual, computed)) {
						matched++;
					}
				}
			}
			
			total++;
		}
		
		Assert.assertEquals("Total number of hashes did not match", total, matched);
	}
	
	private boolean bigMatch(String actual, String computed) {
		// try with big decimal
		try {
			BigDecimal in = new BigDecimal(actual);
			
			long x = in.longValue();
			if(computed.equals(String.valueOf(x))) {
				return true;
			}
			
			int y = in.intValue();
			if(computed.equals(String.valueOf(y))) {
				return true;
			}
		} catch(NumberFormatException e) {
			System.out.println("actual: " + actual);
			System.out.println("computed: " + computed);

			throw new RuntimeException("Failed");
//			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * Hash function
	 * 
	 * @author sangupta
	 *
	 */
	private static interface StringHashFunction {
		
		public String getHash(byte[] bytes);
		
	}
	
}
