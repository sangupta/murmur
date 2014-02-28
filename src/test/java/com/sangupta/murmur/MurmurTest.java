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

import org.junit.Test;


/**
 * Test the hash algorithm for Murmur 1.
 * 
 * All english words: http://www.sitopreferito.it/html/all_english_words.html
 * Numbers from 1 to 216553
 * 216553 random type-4 UUIDs
 * 
 */
public class MurmurTest {
	
	private static final int MAX_ELEMENTS = 216553;
	
	@Test
	public void testMurmur1() {
		long start = System.currentTimeMillis();
		
		for(int i = 0; i < MAX_ELEMENTS; i++) {
			String text = String.valueOf(i);
			
			byte[] bytes = text.getBytes();
			Murmur1.hash(bytes, bytes.length, 0xB0F57EE3);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Time taken to compute Murmur1 hash: " + (end - start) + "ms.");
	}
	
	@Test
	public void testMurmur2() {
		long start = System.currentTimeMillis();
		
		for(int i = 0; i < MAX_ELEMENTS; i++) {
			String text = String.valueOf(i);
			
			byte[] bytes = text.getBytes();
			Murmur2.hash(bytes, bytes.length, 0xB0F57EE3);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Time taken to compute Murmur2 hash: " + (end - start) + "ms.");
	}
	
	@Test
	public void testMurmur3() {
		long start = System.currentTimeMillis();
		
		for(int i = 0; i < MAX_ELEMENTS; i++) {
			String text = String.valueOf(i);
			
			byte[] bytes = text.getBytes();
			Murmur3.hash_x64_128(bytes, bytes.length, 0xB0F57EE3);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Time taken to compute Murmur3 hash: " + (end - start) + "ms.");
	}
	
}
