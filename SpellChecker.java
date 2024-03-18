
public class SpellChecker {

	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);

		//System.out.println(levenshtein("love", "ov0dsej"));

	}

	public static String tail(String str) {
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		//case where word1 length is 0
		if(word1.length() == 0){
			//System.out.println(word2.length());
			return word2.length();
		}
		//case where word2 length is 0
		if(word2.length() == 0){
			//System.out.println(word1.length());
			return word1.length();
		}
		//case where first chars are the same
		if(word1.charAt(0) == word2.charAt(0)){
			return levenshtein(tail(word1), tail(word2));
		}
		//case where chars are different
		if(word1.charAt(0) != word2.charAt(0)){
			//find three subcase vals recursively

			//c1 lev(tail(a),b)
			//c2 lev(a, tail(b))
			//c3 lev(tail(a), tail(b))

			//finds min val from above three cases
			int min = findMin(levenshtein(tail(word1), word2), levenshtein(word1, tail(word2)), levenshtein(tail(word1), tail(word2)));

			//System.out.println(min + 1);
			return min + 1;
		}
		//System.out.println("failed");
		return -1;
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);

		//loops through for each line in file adds to next index of array
		for(int i = 0; i < 3000; i++){
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		return "dsjc";
	}

	//used to process in lev method to make sure case sensitive
	private static String toLowerCase(String given){
		String proccessedString = "";
		for(int i = 0; i < given.length(); i++){
			if((int) given.charAt(i) <= 90 && (int) given.charAt(i) >= 65){
				int val = ((int) given.charAt(i)) + 32;
				char newChar = (char) val;
				proccessedString += newChar;
			}
			else{
				char newChar = given.charAt(i);
				proccessedString += newChar;
			}
		}
		//System.out.println(proccessedString);
		return proccessedString;
	}
	//used to find min int in lev algorithm
	private static int findMin(int n1, int n2, int n3){
		int min = n1;

		if(n2 < n1){
			min = n2;
		}
		if(n3 < min){
			min = n3;
		}
		return min;
	}

}
