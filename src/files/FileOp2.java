package files;

import java.io.*;
import java.util.Collection;

public class FileOp2 {

	private static String caesarCipher(String toCipher) {
		char[] temp = toCipher.toCharArray();

		for (int i = 0; i < temp.length; i++) {
			int index = temp[i];
			index += 3;
			temp[i] = (char) index;
		}
		return new String(temp);

	}

	private static String caesarDecipher(String toDecipher) {
		char[] temp = toDecipher.toCharArray();

		for (int i = 0; i < temp.length; i++) {
			int index = temp[i];
			index -= 3;
			temp[i] = (char) index;
		}
		return new String(temp);

	}

	private static String rot13Cipher(String throughRot13) {
		String temp = throughRot13;
		String temp2 = "";
		for (int i = 0; i < temp.length(); i++) {
			char c = temp.charAt(i);
			if (c >= 'a' && c <= 'm')
				c += 13;
			else if (c >= 'n' && c <= 'z')
				c -= 13;
			else if (c >= 'A' && c <= 'M')
				c += 13;
			else if (c >= 'N' && c <= 'Z')
				c -= 13;
			temp2 += c;
		}
		return new String(temp2);
	}

	public static boolean read(Object object, String src) throws CipherNotFoundException {
		String obiekt = "";
		try {
			DataInputStream file = new DataInputStream(new BufferedInputStream(
					new FileInputStream("data/" + src)));
			obiekt = file.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		String cipher = (String) obiekt.subSequence(0, obiekt.indexOf("\n"));
		obiekt = (String) obiekt.subSequence(obiekt.indexOf("\n")+1,
				obiekt.length());
		
		String string = "";
		if (cipher.contentEquals("caesar")) {
			obiekt = caesarDecipher(obiekt);
		} else if (cipher.contentEquals("rot13")) {
			obiekt = rot13Cipher(obiekt);
		} else if (cipher.contentEquals("none")) {
		} else {
			throw new CipherNotFoundException();
		}

		System.out.println(obiekt);
		InputStream is;
		try {
			is = new ByteArrayInputStream(obiekt.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		try {
			ObjectInputStream we = new ObjectInputStream(is);
			object = we.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}

		System.out.println("tttt");
		((choroby.Choroba) object).printValues();

		return true;
	}

	public static boolean write(Object object, String src, String cipherMethod) {
		OutputStream output = new OutputStream() {
			private StringBuilder string = new StringBuilder();

			@Override
			public void write(int b) throws IOException {
				this.string.append((char) b);
			}

			public String toString() {
				return this.string.toString();
			}
		};

		String string = "";
		try {
			ObjectOutputStream out = new ObjectOutputStream(output);
			out.writeObject(object);

			DataOutputStream file = new DataOutputStream(
					new BufferedOutputStream(
							new FileOutputStream("data/" + src)));

			
			if (cipherMethod == "caesar") {
				string = "caesar\n" + caesarCipher(output.toString());
			} else if (cipherMethod == "rot13") {
				string = "rot13\n" + rot13Cipher(output.toString());
			} else if (cipherMethod == "none") {
				string = "none\n" + output.toString();
			} else {
				return false;
			}
			file.writeUTF(string);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		//return true;
		System.out.println("TtttttT");
		InputStream is;
		try {
			is = new ByteArrayInputStream(output.toString().getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		try {
			ObjectInputStream we = new ObjectInputStream(is);
			object = we.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public static boolean read(Collection object, String str) {
		return true;
	}

	public static boolean write(Collection object, String str) {
		return true;
	}
}
