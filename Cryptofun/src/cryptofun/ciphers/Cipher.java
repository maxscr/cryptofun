package cryptofun.ciphers;

import java.util.LinkedList;

public interface Cipher {
	
	static LinkedList<String> alphabetUppercase = new LinkedList<String>();
	static LinkedList<String> alphabetLowercase = new LinkedList<String>();
	static String[] uppercaseLetters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	static String[] lowercaseLetters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	
	public String encrypt(String inputString, String inputkey);
	public String decrypt(String inputString, String inputkey);
	
}
