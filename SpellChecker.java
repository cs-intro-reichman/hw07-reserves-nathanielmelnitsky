
public class SpellChecker {

	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(toLowerCase(word), threshold, dictionary);
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
		String correctWord;
		if(!existInDictionary(word, dictionary)){
			for(int i = 0; i < 3000; i++){
				if(levenshtein(word, dictionary[i]) <= threshold){
					correctWord = dictionary[i];
					return correctWord;
				}	
			}
		}
		else{
			return word;
		}

		return word;
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
	//used to check if the given word might be correct before substituting it
	private static boolean existInDictionary(String word, String dictionary[]) {
		//boolean exists = false;
		//used to find index of the words
		char firstCharGivenWord = word.charAt(0);

		//find start index
		int startIndex = 0;
		for(int i = 0; i < 3000; i++){
			//if the first char is the same
			if(firstCharGivenWord == dictionary[i].charAt(0)){
				break;
			}
			//if not we continue
			else{
				startIndex++;
			}
		}

		//find end index of relevant words
		int endIndex = startIndex;
		for(int i = startIndex; i < 3000; i++){
			if(firstCharGivenWord != dictionary[i].charAt(0)){
				break;
			}
			//if not we continue
			else{
				endIndex++;
			}
		}

	//make new array of relevant words
	String[] newArr = new String[endIndex - startIndex];
	int j = 0;
	for(int i = startIndex; i < endIndex; i++){
		newArr[j] = dictionary[i];
		//System.out.println(newArr[j]);
		j++;
	}

	//now loop through new arr
	for(int i = 0; i < newArr.length; i++){
		if(word.length() == newArr[i].length()){
			//uses recursive private functioni added to check
			if(recursiveChecker(word, newArr[i])){
				//System.out.println("TRUE");
				return true;
			}
		}
	}

		//System.out.println("false");
		return false;
	}
		//used to check if word already exists
		//checks if two strings are the same recursively
		private static boolean recursiveChecker(String s1, String s2){
			//base case
			if(s1.length() == 1 && s1.charAt(0) == s2.charAt(0)){
				return true;
			}
			//if the first chars are same calls on itslef
			else if(s1.charAt(0) == s2.charAt(0)){
				return recursiveChecker(s1.substring(1), s2.substring(1));
			}
			//if chars arent the same false
			else{
				return false;
			}
		}

}
