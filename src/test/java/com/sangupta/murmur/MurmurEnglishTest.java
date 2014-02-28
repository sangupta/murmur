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
 * Test the hash algorithm for Murmur 1.
 * 
 * All english words: http://www.sitopreferito.it/html/all_english_words.html
 * Numbers from 1 to 216553
 * 216553 random type-4 UUIDs
 * 
 */
public class MurmurEnglishTest implements MurmurConstants {
	
	private static final long MURMUR_SEED = 0x7f3a21eal;
	
	private static final String BASE_PATH = "c:/git/uber/murmur/src/test/resources/english/";
	
}
