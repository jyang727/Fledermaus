package com.juntest.misc;

import java.util.ArrayList;
import java.util.List;

/**
The gray code is a binary numeral system where two successive values differ 
in only one bit.

Given a non-negative integer n representing the total number of bits in the 
code, print the sequence of gray code. A gray code sequence must begin with 
0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
*/

public class GrayCode {
	
	public List<Integer> grayCode(int n)
    {
        List<Integer> al = new ArrayList<Integer>();
        al.add(0);

        for (int i = 1; i <= n; i++)
        {
            int a = 1 << (i - 1);

            for (int j = al.size() - 1; j >= 0; j--)
            {
                al.add(al.get(j) | a);
            }
        }
        return al;
    }
	
	public static void main(String[] args){
		
		GrayCode gc = new GrayCode();
		
		List<Integer> code = gc.grayCode(3);
		
		for (Integer i : code){
			System.out.println(i);
		}
		
	}

}
