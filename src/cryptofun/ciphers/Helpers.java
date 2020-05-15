package cryptofun.ciphers;

import java.util.LinkedList;

public class Helpers {

	
	public static String makeNice(String inputString) {
		inputString = inputString.replace("ä", "ae");
		inputString = inputString.replace("ö", "oe");
		inputString = inputString.replace("ü", "ue");
		inputString = inputString.toUpperCase();
		inputString = inputString.replaceAll("[^a-zA-Z]", "");
		return inputString;
	}


	public static void buildAlphabetLists(String[] uppercaseLetters, String[] lowercaseLetters, LinkedList<String> alphabetUppercase, LinkedList<String> alphabetLowercase) {
		for(int i =0; i < uppercaseLetters.length; i++) {
			alphabetUppercase.add(uppercaseLetters[i]);
		}
		for(int i =0; i < lowercaseLetters.length; i++) {
			alphabetLowercase.add(lowercaseLetters[i]);
		}
	}
}
