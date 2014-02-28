murmur
======

`murmur` is a pure Java implementation of all Murmur hashes, namely, Murmur1, Murmur2 and Murmur3.
The library is a direct Java implementation of the C++ source code. Hash generation has been 100%
unit tested against the hashes generated using the C++ code. The library should help in building 
out bloom filters, or to just compute the hash for checking sanity of data, as Murmur3 is much faster
than MD5 and SHA computations.

Why `murmur`
------------

`murmur` was developed as we could not find pure Java implementations for `Murmur1` and `Murmur2` hashes.
Implementations were available for `Murmur3` but for some of the legacy code that I maintain, I needed
the `Murmur1` and `Murmur2` hashes. Thus, I ported the original implementations.

Features
--------
* Pure Java implementations of various Murmur hashes
* 100% hash compatibility with original C++ code
* No dependencies

Builds
------

**1.0.0**

* First release with Murmur 1/2/3 hashes

Downloads
---------

The library can be downloaded from Maven Central using:

```xml
<dependency>
    <groupId>com.sangupta</groupId>
    <artifactId>murmur</artifactId>
    <version>1.0.0</version>
</dependency>
```

Continuous Integration
----------------------
The library is continuously integrated and unit tested using the *Travis CI system.

Current status of branch `MASTER`: [![Build Status](https://secure.travis-ci.org/sangupta/murmur.png?branch=master)](http://travis-ci.org/sangupta/murmur)

The library is tested against

* Oracle JDK 7
* Open JDK 7

Note: JDK6 has been removed from the list due to end-of-life from Oracle, and as support has been dropped by
http://travis-ci.org as well.

Versioning
----------

For transparency and insight into our release cycle, and for striving to maintain backward compatibility, 
`murmur` will be maintained under the Semantic Versioning guidelines as much as possible.

Releases will be numbered with the follow format:

`<major>.<minor>.<patch>`

And constructed with the following guidelines:

* Breaking backward compatibility bumps the major
* New additions without breaking backward compatibility bumps the minor
* Bug fixes and misc changes bump the patch

For more information on SemVer, please visit http://semver.org/.

License
-------
	
	Copyright (c) 2014, Sandeep Gupta

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
