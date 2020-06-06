package cryptofun.ciphers;

import java.util.HashMap;

public class Masc implements Cipher{

	@Override
	public String encrypt(String inputString, String inputkey) {
		String returnString = "";
		inputString = Helpers.makeNice(inputString);
		inputkey = Helpers.makeNice(inputkey);
		String key = "";
		for(int i = 0; i < inputkey.length();i++) {
			if(key.indexOf(inputkey.charAt(i)) < 0 ) {
				key = key + inputkey.charAt(i);
			}
		}
		int l = key.length();
		String upperCases = String.join("", uppercaseLetters);
		for(int i =0; i < 26; i ++) {
			if(key.indexOf(upperCases.charAt(i)) < 0) {
				key = key + upperCases.charAt(i);
			}
		}
		HashMap<String, String> masc = new HashMap<String, String>();
		for(int i = 0; i < 26; i++) {
			String keyAdd = Character.toString(key.charAt(i));
			String alphAdd = Character.toString(upperCases.charAt(i));
			masc.put(alphAdd,keyAdd);
		}
		
		
		for(int i = 0; i < inputString.length(); i++) {
			String currentLetter = Character.toString(inputString.charAt(i));
			returnString = returnString + masc.get(currentLetter);
		}
		
		return returnString;
	}

	@Override
	public String decrypt(String inputString, String inputkey) {
		// TODO Auto-generated method stub
		return null;
	}

}
