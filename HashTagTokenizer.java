

public class HashTagTokenizer {

	public static void main(String[] args) {

		String rawhashTag = "iloverecursion";//args[0];
		String hashtag = toLowerCase(rawhashTag);
		String dictionary[] = readDictionary("dictionary.txt");
		breakHashTag(hashtag, dictionary);
		//String word = "quit";

		//existInDictionary(word, dictionary);
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

	public static boolean existInDictionary(String word, String dictionary[]) {
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

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();

        for (int i = 1; i <= N; i++) {
			if(existInDictionary(hashtag.substring(0, i), dictionary)){
				System.out.println(hashtag.substring(0, i));
				breakHashTag(hashtag.substring(i), dictionary);
			}
        }
    }
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

	private static String toLowerCase(String given){
		String proccessedString = "";
		for(int i = 0; i < given.length(); i++){
			if((int) given.charAt(i) <= 90){
				int val = ((int) given.charAt(i)) + 32;
				char newChar = (char) val;
				proccessedString += newChar;
			}
			else{
				char newChar = given.charAt(i);
				proccessedString += newChar;
			}
		}
		System.out.println(proccessedString);
		return proccessedString;
	}

}
