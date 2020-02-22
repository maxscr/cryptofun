package cryptofun;

import java.util.HashMap;
import java.util.LinkedList;

public class Encrypter {
	
	LinkedList<String> alphabetUppercase = new LinkedList<String>();
	LinkedList<String> alphabetLowercase = new LinkedList<String>();
	String[] uppercaseLetters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	String[] lowercaseLetters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	
	
	public Encrypter() {
		for(int i =0; i < uppercaseLetters.length; i++) {
			alphabetUppercase.add(uppercaseLetters[i]);
		}
		for(int i =0; i < lowercaseLetters.length; i++) {
			alphabetLowercase.add(lowercaseLetters[i]);
		}
	}

	public String toCaesar(String inputString, int caesar) {
		String returnString = "";
		inputString = umlautGone(inputString);
		inputString = inputString.replaceAll("[^a-zA-Z]", "");
		for(int i = 0; i < inputString.length();i++) {
			String currentLetter = Character.toString(inputString.charAt(i));
			if(alphabetUppercase.contains(currentLetter)) {
				LinkedList<String> localcopy = alphabetUppercase;
				int counter = localcopy.indexOf(currentLetter);
				for(int j = 0; j < (counter + caesar); j++) {
					String builder = localcopy.removeFirst();
					localcopy.add(builder);
				}
				String add = localcopy.getFirst();
				returnString = returnString + add;
				
			} else if(alphabetLowercase.contains(currentLetter)) {
				LinkedList<String> localcopy = alphabetLowercase;
				int counter = localcopy.indexOf(currentLetter);
				for(int j = 0; j < (counter + caesar); j++) {
					String builder = localcopy.removeFirst();
					localcopy.add(builder);
				}
				String add = localcopy.getFirst();
				returnString = returnString + add;
				
			} else {
				returnString = returnString + currentLetter;
			}
		}
		return returnString;
	}
	
	public String toVigenere(String inputString, String inputkey) {
		String returnString = "";
		inputString = inputString.toUpperCase();
		inputString = inputString.replaceAll("[^a-zA-Z]", "");
		inputkey = inputkey.toUpperCase();
		inputkey = inputkey.replaceAll("[^a-zA-Z]", "");
		HashMap<String, String> mystery = new HashMap<String, String>();
		for(int i = 0; i < 26;i++) {
			for(int j = 0; j < 26;j++) {
				String key = uppercaseLetters[i] + uppercaseLetters[j];
				String input = uppercaseLetters[(i+j)%26];
				mystery.put(key, input);
			}
		}
		
		for(int i = 0; i < inputString.length(); i++) {
			String currentLetter = Character.toString(inputString.charAt(i));
			String currentLetterKey = Character.toString(inputkey.charAt(i%inputkey.length()));
			String currentKey = currentLetter + currentLetterKey;
			returnString = returnString + mystery.get(currentKey);
		}
		return returnString;
	}
	
	private String umlautGone(String inputString) {
		inputString = inputString.replace("ä", "ae");
		inputString = inputString.replace("ö", "oe");
		inputString = inputString.replace("ü", "ue");
		return inputString;
	}
}
