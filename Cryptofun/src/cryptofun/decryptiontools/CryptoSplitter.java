package cryptofun.decryptiontools;

public class CryptoSplitter implements Splitter{

	@Override
	public String[] split(String input) {
		// Splits characterwise
		String[] results = new String[input.length()];
		for(int i = 0; i < input.length(); i++) {
			String insert = Character.toString(input.charAt(i));
			results[i] = insert;
		}
		return results;
	}

}
