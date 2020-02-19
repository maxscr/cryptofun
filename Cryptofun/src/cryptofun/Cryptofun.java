package cryptofun;

public class Cryptofun {
	
	public static void main(String[] args) {
		//CryptoWorld cryptomania = new CryptoWorld();
		//cryptomania.open();
		Encrypter encrypter = new Encrypter();
		String test = encrypter.toCaesar("Abcd", 2);
		System.out.println(test);
	}
}
