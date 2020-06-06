package cryptofun.ciphers;

import java.util.LinkedList;

public class Caesar implements Cipher{
	
	
	@Override
	public String encrypt(String inputString, String inputkey) {
		String returnString = "";
		Helpers.buildAlphabetLists(uppercaseLetters, lowercaseLetters, alphabetUppercase, alphabetLowercase);
		inputString = Helpers.makeNice(inputString);
		for(int i = 0; i < inputString.length();i++) {
			String currentLetter = Character.toString(inputString.charAt(i));
			if(alphabetUppercase.contains(currentLetter)) {
				LinkedList<String> localcopy = alphabetUppercase;
				localcopy.addAll(alphabetUppercase);
				int counter = localcopy.indexOf(currentLetter);
				for(int j = 0; j < (counter + Integer.parseInt(inputkey)); j++) {
					String builder = localcopy.removeFirst();
					localcopy.add(builder);
				}
				String add = localcopy.getFirst();
				returnString = returnString + add;
			}
		}
		return returnString;
	}

	@Override
	public String decrypt(String inputString, String inputkey) {
		String returnString = "";
		Helpers.buildAlphabetLists(uppercaseLetters, lowercaseLetters, alphabetUppercase, alphabetLowercase);
		inputString = Helpers.makeNice(inputString);
		for(int i = 0; i < inputString.length();i++) {
			String currentLetter = Character.toString(inputString.charAt(i));
			if(alphabetUppercase.contains(currentLetter)) {
				LinkedList<String> localcopy = alphabetUppercase;
				localcopy.addAll(alphabetUppercase);
				int counter = localcopy.indexOf(currentLetter);
				for(int j = 0; j < (counter + 26 - Integer.parseInt(inputkey)); j++) {
					String builder = localcopy.removeFirst();
					localcopy.add(builder);
				}
				String add = localcopy.getFirst();
				returnString = returnString + add;
			}
		}
		return returnString;
	}



}
