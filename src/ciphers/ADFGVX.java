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
		Random ran = new Random(47);
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
	 * Dla zarówno kodowania i dekodowania zwraca listę znaków wiadomości do zakodowania/zdekodowania.
	 * boolean coding jest ustawiona na prawde dla  kodowania i fałsz dla dekodowania. Zwraca pustą listę gdy klucz nie jest ok
	 */
	private char[] msgToProcess(String str, boolean coding) {
		// jesli pusta wiadomosc zwroc pusta tablice
		if (str == null)
			return new char[0];
		// jesli nie mam dobreo klucza tez
		if (key.length() == 0)
			return new char[0];
		StringBuilder sb = new StringBuilder(key.length());
		// zamien na duze litery
		char[] digit = str.toUpperCase().toCharArray();
		//przejdz przez kazda cyfre
		for (char c : digit) {
			if (coding) {
				if ((c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))
					sb.append(c);
			} else {
				// podczas dekodowania tylko litery z morsa sa dozwolone
				for (char m : morse) {
					if (m == c) {
						sb.append(c);
						break; // nie ma potrzeby aby kontynuowac dla liter morsa
							
					}
				}
			}
		}
		digit = sb.toString().toCharArray();
		// jesli pusty zwroc
		if (digit.length == 0)
			return digit;
		// podczas dekodowania ilosc liter musi byc rowna
		if (!coding) {
			if (digit.length % 2 != 0)
				return new char[0];
		}
		// zwroc liste litter do procesu
		return digit;
	}

	/**
	 *przygotowanie i inicjalizacja the col i colAlpha 
	 */
	private void prepareColumns(int len) {
		// policz ilosc litter ktore beda w kazdej kolumnie
		int nbPerCol = len / col.length;
		// zwrob liste z tymi dlugosciami
		int[] nb = new int[col.length];
		for (int i = 0; i < col.length; i++)
			nb[i] = nbPerCol;
		
		int reminder = len - (col.length * nbPerCol);
		for (int i = 0; i < reminder; i++)
			nb[i]++;

		// teraz ustawiamy wielkosc kazdej kolumny naszego obiektu
		for (int i = 0; i < col.length; i++) {
			col[i].setSize(nb[i]);
		}
	}

	/**
	 * Znajdz pozycje znaku w gridzie x,y
	 */
	private Point findPos(char c) {
		// skanuj grida
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 6; y++) {
				// jesli pasuje zwroc x i y
				if (c == grid[x][y])
					return new Point(x, y);
			}
		}
		throw new IllegalStateException("Character " + c + " not found in Grid");
	}

	/**
	 * Dla debugowania
	 */
	public void dumpGrid() {
		// naglowek
		System.out.println("      GRID");
		System.out.println();
		// odstep do wydruku A D F G V X
		System.out.print("    ");
		// literki A D F G V X
		for (int i = 0; i < morse.length; i++)
			System.out.print(" " + morse[i]);
		System.out.println();

		System.out.print("  +--");
		for (int i = 0; i < morse.length; i++)
			System.out.print("--");
		System.out.println();
		// reraz inny wiersz
		for (int i = 0; i < morse.length; i++) {
			System.out.print(morse[i] + " | ");
			for (int j = 0; j < morse.length; j++) {
				System.out.print(" " + grid[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * Dla GUI
	 */
	public char[][] getGrid() {
		return grid;
	}

	public char[] getMorse() {
		return morse;
	}

	/**
	 * Aby wytestowac
	 */

	/**
	 *wewnetrzna klasa aby trzymac dane

	 */
	private class Column implements Comparable<Column> {

		// literki  A D F G V X na szczycie kolumny
		private char header;
		// wszystkie w kolumnie
		private char[] letters;
		private int index;

		/**
		 * konstruktor otrzymujacy literke jako naglowek
		 */
		Column(char header) {
			this.header = header;
		}

		/**
		 * aby ustalic ilosc w kol
		 */
		void setSize(int size) {
			letters = new char[size];
			index = 0;
		}

		/**
		 * odzyskanie wielkosci
		 */
		int getSize() {
			return letters.length;
		}

		/**
		 * Aby dodac litere do kolumny
		 */
		void add(char c) {
			letters[index++] = c;
		}

		/**
		 * Aby otrzymac pojedyncza litere
		 */
		char getChar(int n) {
			return letters[n];
		}

		/**
		 * Żeby zwrocic jako string litery
		 */
		public String toString() {
			return new String(letters);
		}

		/**
		 * aby posortowac po naglowku
		 */
		public int compareTo(Column other) {
			return header - other.header;
		}
	}
}
