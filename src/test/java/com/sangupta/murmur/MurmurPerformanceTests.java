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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Test the hash algorithms for Performance over a million
 * randomly generated UUIDs.
 * 
 * @author sangupta
 * @since 1.0
 */
public class MurmurPerformanceTests {
	
	private static final int MAX_ELEMENTS = 1000 * 1000;
	
	private static final List<String> UUIDs = new ArrayList<String>();
	
	private static final int SEED = 0xB0F57EE3;
	
	@BeforeClass
	public static void onlyOnce() {
		for(int index = 0; index < MAX_ELEMENTS; index++) {
			UUIDs.add(UUID.randomUUID().toString());
		}
	}
	
	@Test
	public void testMD5() throws NoSuchAlgorithmException {
		long start = System.currentTimeMillis();
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		for(String uuid : UUIDs) {
			byte[] bytes = uuid.getBytes();
			md.digest(bytes);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Time taken to compute MD5 hash: " + (end - start) + "ms.");
	}
	
	@Test
	public void testSHA1() throws NoSuchAlgorithmException {
		long start = System.currentTimeMillis();
		
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		for(String uuid : UUIDs) {
			byte[] bytes = uuid.getBytes();
			md.digest(bytes);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Time taken to compute SHA-1 hash: " + (end - start) + "ms.");
	}
	
	@Test
	public void testSHA256() throws NoSuchAlgorithmException {
		long start = System.currentTimeMillis();
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		for(String uuid : UUIDs) {
			byte[] bytes = uuid.getBytes();
			md.digest(bytes);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Time taken to compute SHA-256 hash: " + (end - start) + "ms.");
	}
	
	@Test
	public void testSHA512() throws NoSuchAlgorithmException {
		long start = System.currentTimeMillis();
		
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		for(String uuid : UUIDs) {
			byte[] bytes = uuid.getBytes();
			md.digest(bytes);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Time taken to compute SHA-512 hash: " + (end - start) + "ms.");
	}
	
	@Test
	public void testMurmur1() {
		long start = System.currentTimeMillis();
		
		for(String uuid : UUIDs) {
			byte[] bytes = uuid.getBytes();
			Murmur1.hash(bytes, bytes.length, SEED);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Time taken to compute Murmur1 hash: " + (end - start) + "ms.");
	}
	
	@Test
	public void testMurmur2() {
		long start = System.currentTimeMillis();
		
		for(String uuid : UUIDs) {
			byte[] bytes = uuid.getBytes();
			Murmur2.hash(bytes, bytes.length, SEED);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Time taken to compute Murmur2 hash: " + (end - start) + "ms.");
	}
	
	@Test
	public void testMurmur2hash64() {
		long start = System.currentTimeMillis();
		
		for(String uuid : UUIDs) {
			byte[] bytes = uuid.getBytes();
			Murmur2.hash64(bytes, bytes.length, SEED);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Time taken to compute Murmur2 hash64: " + (end - start) + "ms.");
	}
	
	@Test
	public void testMurmur3() {
		long start = System.currentTimeMillis();
		
		for(String uuid : UUIDs) {
			byte[] bytes = uuid.getBytes();
			Murmur3.hash_x86_32(bytes, bytes.length, SEED);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Time taken to compute Murmur3 hash: " + (end - start) + "ms.");
	}
	
	@Test
	public void testMurmur3hash128() {
		long start = System.currentTimeMillis();
		
		for(String uuid : UUIDs) {
			byte[] bytes = uuid.getBytes();
			Murmur3.hash_x64_128(bytes, bytes.length, SEED);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Time taken to compute Murmur3 hash128: " + (end - start) + "ms.");
	}
	
}
