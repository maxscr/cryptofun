package cryptofun.decryptiontools;

import java.util.HashMap;
import java.util.LinkedList;

public class Decrypter {

	LinkedList<Character> alphabetUppercase = new LinkedList<Character>();
	LinkedList<Character> alphabetLowercase = new LinkedList<Character>();
	private char[] uppercaseLetters = { 'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	/*private char[] lowercaseLetters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	*/
	private NgramSearcher ngramSearcher = new NgramSearcher(new CryptoSplitter());
	
	
	public Decrypter() {
		for(int i =0; i < uppercaseLetters.length; i++) {
			alphabetUppercase.add(uppercaseLetters[i]);
		}

	}
	
	public HashMap<String, Integer> findNgrams(String input, int n){
		HashMap<String, Integer> results = new HashMap<String, Integer>();
		results = ngramSearcher.find(input, n);
		return results; 
	}
	

	/*
	
	
	public int[] analyzeLetters(String input) {
		int[] counts = new int[26];
		for(int i = 0; i < input.length(); i++) {
			for(int j = 0; j < 26; j++) {
				if(input.charAt(i) == uppercaseLetters[j]) {
					counts[j]++;
					break;
				}
			}
		}
		return counts;
	}
	
	public int[][] analyzeBigrams(String input) {

			int[][] counts = new int[26][26];
			for(int i = 0; i< input.length() - 1; i++) {
				for(int j = 0; j < 26; j++) {
					for(int k = 0; k < 26; k++) {
						if(input.charAt(i)== uppercaseLetters[j]  && input.charAt(i+1) == uppercaseLetters[k] ) {
							counts[j][k]++;
						}
					}
				}
			}
			return counts;
				
	}
	
	public int[][][] analyzeTrigrams(String input){
		int[][][] counts = new int[26][26][26];
		for(int i = 0; i< input.length() - 2; i++) {
			for(int j = 0; j < 26; j++) {
				for(int k = 0; k < 26; k++) {
					for(int l = 0; l < 26; l++) {
						if(input.charAt(i)== uppercaseLetters[j]  && input.charAt(i+1) == uppercaseLetters[k] && input.charAt(i+2) == uppercaseLetters[l]) {
							counts[j][k][l]++;
						}
					}
				}
			}
		}
		return counts;
	}
	*/
	public char[] getUppercaseLetters() {
		return this.uppercaseLetters;
	}
}
