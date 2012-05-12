package com.juntest.algorithms.util;

import java.math.BigInteger;
import java.util.UUID;

public class UuidTest {

	public static void main(String[] args){
		
		UUID uuid = UUID.randomUUID();
		
		System.out.println("uuid=" + uuid);
		System.out.println("uuid hash=" + uuid.hashCode());
						
		String uuidStr = uuid.toString().replaceAll("-", "");
		
		BigInteger b = new BigInteger(uuidStr, 16);
		
		System.out.println("BigInteger=" + b);
		
		BigInteger b2 = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF", 16);
		
		System.out.println("BigInteger=" + b2);
		
		BigInteger b3 = new BigInteger("340282366920938463463374607431768211455", 10);
		
		System.out.println("BigInteger=" + b3.toString(16));
		
		
		
	}
	
}
