# murmur

[![Build Status](https://img.shields.io/travis/sangupta/murmur.svg)](https://travis-ci.org/sangupta/murmur)
[![Coverage Status](https://img.shields.io/coveralls/sangupta/murmur.svg)](https://coveralls.io/github/sangupta/murmur?branch=master)
[![license](https://img.shields.io/github/license/sangupta/murmur.svg)](https://opensource.org/licenses/Apache-2.0)
[![Maven Central](https://img.shields.io/maven-central/v/com.sangupta/murmur.svg)](https://maven-badges.herokuapp.com/maven-central/com.sangupta/murmur)

`murmur` is a pure Java implementation of all Murmur hashes, namely, Murmur1, Murmur2 and Murmur3.
The library is a direct Java implementation of the C++ source code. Hash generation has been 100%
unit tested against the hashes generated using the C++ code. The library should help in building 
out bloom filters, or to just compute the hash for checking sanity of data, as Murmur3 is much faster
than **MD5** and **SHA** computations.

The library is tested on the following JDK versions:

* Oracle JDK 11
* Oracle JDK 8
* Open JDK 11
* Open JDK 10
* Open JDK 9
* Open JDK 8

## Why `murmur`?

`murmur` was developed as we could not find pure Java implementations for `Murmur1` and `Murmur2` hashes.
Implementations were available for `Murmur3` but for some of the legacy code that I maintain, I needed
the `Murmur1` and `Murmur2` hashes. Thus, I ported the original implementations.

You may find the hash inconsistent with [Google Guava]() library. The hash value is the same, it is
the endian-ness of the hash that makes it look different. Refer to [Issue #3](https://github.com/sangupta/murmur/issues/3)
for more details.

To convert the hash into `byte[]` or a `hex-string` you may use the following code:

```java
/**
 * Convert a given long value to byte-array.
 * 
 * @param x the long value
 * 
 * @return the byte[] array representation of it
 */
public static byte[] longToBytes(long x) {
	ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);

	// The ByteOrder.LITTLE_ENDIAN format matches the Google Guava toString() format
	buffer.order(ByteOrder.LITTLE_ENDIAN);

	buffer.putLong(x);
	return buffer.array();
}

/**
 * Convert a byte-array to hex string.
 * 
 * @param bytes the byte-array
 * 
 * @return the hex string
 */
public static String bytesToHex(byte[] bytes) {
	char[] hexChars = new char[bytes.length * 2];
	for (int j = 0; j < bytes.length; j++) {
		int v = bytes[j] & 0xFF;
		hexChars[j * 2] = hexArray[v >>> 4];
		hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	}
	return new String(hexChars);
}
```

## Features

* Pure Java implementations of various Murmur hashes
* 100% hash compatibility with original C++ code
* No dependencies
* Ultra light-weight, just 7KB in size

## Performance

The `MurmurPerformanceTests.java` file contains tests to compute hashes of 1-million random type-4 UUIDs
between various Murmur hashes, and `MD5`, `SHA-1`, `SHA-256`, and `SHA-512` hashes. 

The results of a sample run on my dev machine are as under:

```
Windows
-------
Intel i7-2660 CPU @ 3.40Ghz
16-GB RAM
Windows 7, 64-bit, Service Pack 1
Oracle JDK 1.7.0_51 build 13, 64-bit Server VM

OS X
----
Intel i7-4870HQ CPU @ 2.50GHz
16-GB RAM
macOS Sierra 10.12.1
Oracle JDK 1.8.0_101 build 13, 64-bit Server VM
```

| Algorithm    | Time Taken Windows (ms) | Time Taken OSX (ms) |
| :----------- | ----------------------: | ------------------: |
| MD5          | 369                     | 338                 |
| SHA-1        | 482                     | 415                 |
| SHA-256      | 677                     | 642                 |
| SHA-512      | 906                     | 782                 |
| Murmur-1     | 143                     | 101                 |
| Murmur-2     | 135                     | 123                 |
| Murmur-2-64  | 102                     |  92                 |
| Murmur-3     | 168                     | 119                 |
| Murmur-3-128 | 160                     | 261                 |

## Builds

**1.0.0**

* First release with Murmur 1/2/3 hashes

## Downloads

The library can be downloaded from Maven Central using:

```xml
<dependency>
    <groupId>com.sangupta</groupId>
    <artifactId>murmur</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Versioning

For transparency and insight into our release cycle, and for striving to maintain backward compatibility, 
`murmur` will be maintained under the Semantic Versioning guidelines as much as possible.

Releases will be numbered with the follow format:

`<major>.<minor>.<patch>`

And constructed with the following guidelines:

* Breaking backward compatibility bumps the major
* New additions without breaking backward compatibility bumps the minor
* Bug fixes and misc changes bump the patch

For more information on SemVer, please visit http://semver.org/.

## License

```
murmur - Pure Java implementation of the Murmur Hash algorithms
Copyright (c) 2014-2018, Sandeep Gupta

The project uses various other libraries that are subject to their
own license terms. See the distribution libraries or the project
documentation for more details.

The entire source is licensed under the Apache License, Version 2.0 
(the "License"); you may not use this work except in compliance with
the LICENSE. You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
