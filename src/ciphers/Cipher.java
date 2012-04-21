package ciphers;

public enum Cipher {
	ROT13 {
		public String getCipher(String toCipher) {
			char[] temp = toCipher.toCharArray();

			for (int i = 0; i < temp.length; i++) {
				int index = temp[i];
				index += 3;
				temp[i] = (char) index;
			}
			return new String(temp);
		}

		public String getDecipher(String toDecipher) {
			char[] temp = toDecipher.toCharArray();

			for (int i = 0; i < temp.length; i++) {
				int index = temp[i];
				index -= 3;
				temp[i] = (char) index;
			}
			return new String(temp);
		}
	},
	CAESAR {
		public String getCipher(String throughRot13){
			String temp = throughRot13;
			String temp2 = "";
			for(int i = 0; i < temp.length(); i++){
			char c = temp.charAt(i);
			        if      (c >= 'a' && c <= 'm') c += 13;
			        else if  (c >= 'n' && c <= 'z') c -= 13;
			        else if  (c >= 'A' && c <= 'M') c += 13;
			        else if  (c >= 'N' && c <= 'Z') c -= 13;
			        temp2 += c;
			}
			return new String(temp2);
		} 
		
		public String getDecipher(String toDecipher) {
			return getCipher(toDecipher);
		}
	},
	ADFGVC
	{
		ADFGVX szyfr;
		{
			szyfr = new ADFGVX("tajnyKlucz");
		}
		public String getCipher(String toCipher){
			//szyfr.dumpGrid();
			String coded = szyfr.encode(toCipher);
			return coded;
		}
		public String getDecipher(String toDecipher){
			//szyfr.dumpGrid();
			String decoded = szyfr.decode(toDecipher);
			return decoded;
		}
	};

	public abstract String getCipher(String opis);
	public abstract String getDecipher(String readObject);
}
