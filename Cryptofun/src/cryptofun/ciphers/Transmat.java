package cryptofun.ciphers;

import java.util.LinkedList;

public class Transmat implements Cipher{

	@Override
	public String encrypt(String inputString, String inputkey) {
		String returnString ="";
		inputString = Helpers.makeNice(inputString);
		String[] keys = inputkey.split(":");
		Integer key1 = Integer.valueOf(keys[0]);
		Integer key2 = Integer.valueOf(keys[1]);
		int rest = inputString.length() % (key1*key2);
		for(int i =0; i < key1*key2 - rest; i++) {
			inputString = inputString + "A";
		}
		char[] inputChar = inputString.toCharArray();
		LinkedList<Character> input = new LinkedList<Character>();
		   for (char c : inputChar) {
		        input.add(c);
		    }
		int number = inputString.length()/(key1*key2);	
		for(int j = 0; j < number; j++) {
			for(int k=0; k < key1; k++) {
				for(int l = 0; l < key2; l++) {
					returnString = returnString + input.pop(); 
				}
				returnString = returnString + "\n";
			}
			returnString = returnString + "\n \n";
		}
		return returnString;
	}

	@Override
	public String decrypt(String inputString, String inputkey) {
		// TODO Auto-generated method stub
		return null;
	}

}
