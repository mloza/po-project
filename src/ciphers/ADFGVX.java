package ciphers;
import java.awt.Point;
import java.util.*;
public class ADFGVX 
{
	// Uzyte litery, ktore beda przeslane Morsem
	private static final char[] morse = { 'A', 'D', 'F', 'G', 'V', 'X' };
	// Klucz
	private String key;
	// losowo wypelniany grid
	private char[][] grid;
	// orginalne, nieposortowane kolumny
	private Column[] col;
	// Posortowane kolumny
	private Column[] colAlpha;

	/**
	 * Konstruktor ktory jako parametr otrzymuje klucz
	 */
	public ADFGVX(String key) {
		// Wypełniamy arraylist wszystkimi literami żeby sgrida zapełnić
		ArrayList<Character> al = new ArrayList<Character>();
		for (char c = 'A'; c <= 'Z'; c++)
			al.add(c);
		for (char c = '0'; c <= '9'; c++)
			al.add(c);
		// tworze generator randomowych liczb zeby losowo wyciągać znaki z
		// arraylist
		Random ran = new Random();
		// tworzymy naszego grida
		grid = new char[morse.length][morse.length];
		// wypełniamy
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				// generujemy losowa liczbe w przedziale od 0 do wielkosci
				// arraylist
				int index = ran.nextInt(al.size());
				// usuwamy znak w polu z tym indeksem i trzymamy go w gridzie
				grid[i][j] = al.remove(index);
			}
		}

		// wywolujemy metode ktora zarejestruje klucz
		setKey(key);
	}

	/**
	 * Uzywamy zeby zaincjalizowac klucz albo pozniej go zmienic
	 */
	public void setKey(String key) {
		// ijesli klucz = null to ignorujemy
		if (key == null) {
			this.key = "";
			return;
		}
		// kowertujemy to do tablicy char
		char[] digit = key.toCharArray();
		int len = digit.length;
		// klucz nie moze miec dwoch tych samych znakow
		for (int i = 0; i < len - 1; i++) {
			for (int j = i + 1; j < len; j++) {
				if (digit[i] == digit[j]) {
					this.key = "";
					return;
				}
			}
		}
		// klucz jest w porzadku
		this.key = key;
		// tworzymy nasze kolumny z kazdym znakiem klucza jako naglowki
		col = new Column[len];
		colAlpha = new Column[len];
		for (int i = 0; i < len; i++) {
			// orginalne kolumny
			col[i] = new Column(digit[i]);
			// i te ktore beda pozniej posortowane (Ale wciaz ta sama kolumna)
			colAlpha[i] = col[i];
		}
		// sortujemy druga serie kolumn w porzadku alfabetycznym
		Arrays.sort(colAlpha);

	}

	/**
	 * Żeby zakodować wiadomość
	 */
	public String encode(String clear) {
		// standardowa metodsa sprawfdzajaca czy klucz jest w porzadku i ze
		// wiadomosc
		// tez jest w porzadku. Jednoczesnie blokuje niezezwolone znaki w
		// wiadomosci
		char[] digit = msgToProcess(clear, true);
		if (digit.length == 0)
			return "";

		// przygotowanie kolumn (mnozymy przez 2 bo bedziemy potrzebowac 2
		// litery do kodowania)
		// na kazda litere w orginalnej wiadomosci
		prepareColumns(digit.length * 2);

		// znajdujemy wspolrzedne kazdego znaku w orginalnej wiadomosci
		// i dodajemy je wiersz po wierszu w kazdej kolumnie
		int k = 0; // index w tablicy kolumn
		for (char c : digit) {
			Point p = findPos(c);
			// dodajemy 2 litery
			col[k++].add(morse[p.x]);
			// upierniczamy 2 litery na koncu kazdej kolumny
			k %= col.length;
			col[k++].add(morse[p.y]);
			k %= col.length;
		}

		// uzywamy stringbuildera zeby polaczyc znaki w kaxzdej kolumnie
		StringBuilder sb = new StringBuilder(digit.length * 2);
		for (Column c : colAlpha) {
			sb.append(c.toString());
		}
		// zwraca calkowicie zakodowany string ale z ' ' pomiedzy kazda para
		digit = sb.toString().toCharArray();
		sb = new StringBuilder(digit.length + (digit.length / 2));
		// wrzucamy 2 pierwsze cyfry
		sb.append(digit[0]);
		sb.append(digit[1]);
		// potem kazda pare po ktorej nastepuje ' '
		for (int i = 2; i < digit.length; i += 2) {
			sb.append(' ');
			sb.append(digit[i]);
			sb.append(digit[i + 1]);
		}

		return sb.toString();
	}

	/**
	 * odkodowywuje zakodowany string otrzymany jako parametr
	 */
	public String decode(String coded) {
		// standardowa metoda sprawdzajaca czy klucz jest ok
		// i ze wiadomosc jest ok
		char[] digit = msgToProcess(coded, false);
		if (digit.length == 0)
			return "";

		// przygotowanie kolumn
		prepareColumns(digit.length);

		// kopiujemy do pierwszych kolumn
		int k = 0; // index
		for (Column c : colAlpha) {
			int size = c.getSize(); // ilosc znakow w kolumnie
			for (int i = 0; i < size; i++)
				c.add(digit[k++]); // dodajemy nast
		}

		// cofamy linia po lini
		StringBuilder sb = new StringBuilder(digit.length);
		int size = col[0].getSize(); // najdluzsza
		// skanujemy kazdy wiersz
		for (int row = 0; row < size; row++) {
			// skanujemy w poszukiwaniu intersekcji wiersza i kolumny
			for (Column c : col) {
				// jesli wierza nie ma nie kontynuujemy
				//
				if (row >= c.getSize())
					break;
				// dodajemy znak do wiersza
				sb.append(c.getChar(row));
			}
		}

		// robimy tablice znakow ze StringBuildera
		digit = sb.toString().toCharArray();
		char[] decoded = new char[digit.length / 2];
		// podajemy 2 znaki stringa
		// zeby odpowiedni grid znaleźć
		for (int i = 0; i < digit.length; i += 2) {
			// found the X coordinate in the grid
			int x = 0;
			for (; x < morse.length; x++) {
				if (digit[i] == morse[x])
					break;
			}
			// znanalz y grida
			int y = 0;
			for (; y < morse.length; y++) {
				if (digit[i + 1] == morse[y])
					break;
			}
			// zapisz wartosc z grida
			decoded[i / 2] = grid[x][y];
		}
		return new String(decoded).toLowerCase();
	}

	/**
	 * For both coding and decoding returns an array of char of the message to
	 * code/decode The boolean coding is true for encoding and false for
	 * decoding Returns an empty array if the key is not valid
	 */
	private char[] msgToProcess(String str, boolean coding) {
		// if message is null return nothing
		if (str == null)
			return new char[0];
		// if I do not have a valid key return
		if (key.length() == 0)
			return new char[0];
		// keep only valid characters that we will stored in a StringBuilder
		StringBuilder sb = new StringBuilder(key.length());
		// convert to uppercase
		char[] digit = str.toUpperCase().toCharArray();
		// pass through each digit
		for (char c : digit) {
			if (coding) {
				// if encoding if a letter or a digit add it
				if ((c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))
					sb.append(c);
			} else {
				// when decoding only the morse letters are permitted
				for (char m : morse) {
					// if letter contained in morse letters
					if (m == c) {
						sb.append(c);
						break; // no need to continue loop in the morse
								// permitted letters
					}
				}
			}
		}
		// have digit to now be an array of just the valid character
		digit = sb.toString().toCharArray();
		// if empty return it
		if (digit.length == 0)
			return digit;
		// when decoding the number of letter must be even
		if (!coding) {
			if (digit.length % 2 != 0)
				return new char[0];
		}
		// return the array of letters to process
		return digit;
	}

	/**
	 * Prepare/initialize the col and colAlpha objects
	 */
	private void prepareColumns(int len) {
		// calculate the number of letters that will be in each column
		int nbPerCol = len / col.length;
		// make an array of these length
		int[] nb = new int[col.length];
		for (int i = 0; i < col.length; i++)
			nb[i] = nbPerCol;
		// now if the message length is not an exact multiple of the message
		// length
		// the first columns will have one more row
		int reminder = len - (col.length * nbPerCol);
		for (int i = 0; i < reminder; i++)
			nb[i]++;

		// we can now set the size of each of our column object
		for (int i = 0; i < col.length; i++) {
			col[i].setSize(nb[i]);
		}
	}

	/**
	 * Find the position of the character in the grid the X and Y position will
	 * be index in the Morse array to find the ADFGVX letters to use to
	 * represent that digit
	 */
	private Point findPos(char c) {
		// scan the Grid
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 6; y++) {
				// if match return the coords
				if (c == grid[x][y])
					return new Point(x, y);
			}
		}
		throw new IllegalStateException("Character " + c + " not found in Grid");
	}

	/**
	 * For debug purpose a method to display the Grid
	 */
	public void dumpGrid() {
		// header
		System.out.println("      GRID");
		System.out.println();
		// gap before printing A D F G V X
		System.out.print("    ");
		// the letters A D F G V X
		for (int i = 0; i < morse.length; i++)
			System.out.print(" " + morse[i]);
		System.out.println();
		// +---------- under the A D F G V X at the top
		System.out.print("  +--");
		for (int i = 0; i < morse.length; i++)
			System.out.print("--");
		System.out.println();
		// now the different row
		for (int i = 0; i < morse.length; i++) {
			// the letter at the beginning of the row
			System.out.print(morse[i] + " | ");
			// the Grid contents for that line
			for (int j = 0; j < morse.length; j++) {
				System.out.print(" " + grid[i][j]);
			}
			// ready for next line
			System.out.println();
		}
	}

	/**
	 * For the GUI
	 */
	public char[][] getGrid() {
		return grid;
	}

	public char[] getMorse() {
		return morse;
	}

	/**
	 * To test the class
	 */

	/**
	 * An internal class to hold the data (the character of each column) it
	 * implements comparable so the column could be sorted by alpahbetical order
	 */
	private class Column implements Comparable<Column> {

		// the letter A D F G V X at the head of the column
		private char header;
		// all the letters in the column
		private char[] letters;
		// use when we cumulate the digits in the letters array
		private int index;

		/**
		 * Constructor that receives the letter as header
		 */
		Column(char header) {
			this.header = header;
		}

		/**
		 * To set the number of elements in the column
		 */
		void setSize(int size) {
			// build array to receive all elements
			letters = new char[size];
			// reset that we are at element 0
			index = 0;
		}

		/**
		 * To return, while decoding, the number of characters to insert in the
		 * Column
		 */
		int getSize() {
			return letters.length;
		}

		/**
		 * To add a letter to the column
		 */
		void add(char c) {
			letters[index++] = c;
		}

		/**
		 * To get a single letter
		 */
		char getChar(int n) {
			return letters[n];
		}

		/**
		 * To return as a String the letters in the column
		 */
		public String toString() {
			return new String(letters);
		}

		/**
		 * To sort the columns by header
		 */
		public int compareTo(Column other) {
			return header - other.header;
		}
	}
}
