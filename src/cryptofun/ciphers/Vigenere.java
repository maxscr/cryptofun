package cryptofun.ciphers;

import java.util.HashMap;

public class Vigenere implements Cipher{

	@Override
	public String encrypt(String inputString, String inputkey) {
		String returnString = "";
		inputString = Helpers.makeNice(inputString);
		inputkey = Helpers.makeNice(inputString);
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

	@Override
	public String decrypt(String inputString, String inputkey) {
		// TODO Auto-generated method stub
		return null;
	}

}
