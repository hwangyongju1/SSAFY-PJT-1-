package com.ssafy.myssafit.util;

import java.util.ArrayList;

public class CategorySearch {
	static ArrayList<Integer> list;
	public int[] getPi(String pattern) {
		int len = pattern.length();
		int[] pi = new int[len];
		char[] pt = pattern.toCharArray();
		
		int j = 0;
		for (int i = 1; i < len; i++) {
			while (j > 0 && pt[i] != pt[j]) {
				j = pi[j-1];
			}
			if (pt[i] == pt[j]) {
				pi[i] = ++j;
			}
		}
		return pi;
	}
	public boolean KMP(String text, String pattern) {
		int[] pi = getPi(pattern);
		char[] org = text.toCharArray();
		char[] pt = pattern.toCharArray();
		
		int j = 0;
		for (int i = 0; i < text.length(); i++) {
			while (j > 0 && org[i] != pt[j]) {
				j = pi[j-1];
			}
			if (org[i] == pt[j]) {
				if (j == pattern.length() - 1) {
					return true;
				} else {
					j++;
				}
			}
		}
		return false;
	}
}
