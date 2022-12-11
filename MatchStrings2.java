import java.util.Arrays;
import java.util.HashSet;

public class MatchStrings2 {
	private static HashSet<String> strSet;

	/**
	 * Initializes a HashSet with the contents of the list
	 * of Strings.
	 *
	 * @param strList The list of Strings to be stored in the HashSet
	 */
	private static void init(String[] strList) {
		strSet = new HashSet<>(Arrays.asList(strList));
	}

	/**
	 * Checks whether the provided string has a matching
	 * entry in the HashSet.
	 * 
	 * @param compStr The string to be compared with HashSet entries.
	 * @return "true" when there is a match for the provided 
	 * string; "false" otherwise.
	 */
	private static boolean contains(String compStr) {
        // replace '*' with '.', the any character wildcard in Java's regex
		compStr = compStr.replaceAll("\\*", "\\."); 
		for (String s : strSet) {
			if (s.matches(compStr))
				return true;
		}
		return false;
	}
	
	// Driver code
	public static void main(String[] args) {
		String[] strList = { "cat", "dog", "bird", "blur" };
		init(strList);
		System.out.println(contains("dog")); // Expected: true
		System.out.println(contains("*at")); // Expected: true
		System.out.println(contains("b*t")); // Expected: false
		System.out.println(contains("b**")); // Expected: false
	}
}
