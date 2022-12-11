import java.util.*;

public class MatchStrings3 {
	private static TreeSet<String> strSet;

	/**
	 * Initializes a TreeSet with the contents of the list
	 * of Strings.
	 *
	 * @param strList The list of Strings to be stored in the TreeSet
	 */
	private static void init(String[] strList) {
		strSet = new TreeSet<>(Arrays.asList(strList));
	}

	/**
	 * Checks whether the provided string has a matching
	 * entry in the TreeSet.
	 * 
	 * @param compStr The string to be compared with TreeSet entries.
	 * @return "true": there is a match for the provided string;
	 * 		   "false": there is no match for the provided string, or the
	 * 		   provided string contains more than one wildcard character.
	 */
	private static boolean contains(String compStr) {
		// counts number of asterisks, if >1 return false
		long count = compStr.chars().filter(ch -> ch == '*').count();
		if (count > 1)
			return false;

		compStr = compStr.replaceAll("\\*", "\\.");
		int wcIndex = compStr.indexOf('.');

		if (wcIndex == -1) { // no wildcard is present
			return strSet.contains(compStr);
		} else if (wcIndex == 0) { // wildcard is present in the first char
			for (String s : strSet) {
				if (s.matches(compStr))
					return true;
			}
			return false;
		} else {
			// if a wildcard is present but not in the first char, we need to 
			// get two string slices:
			// the first is the substring before the wildcard
			// the second is the substring after the wildcard
			// the comparison of compStr and iterated entries in the set
			// can be simplified into:
			// 		1. compare the length of s and compStr
			//		2. check if s starts with first substring
			//		3. check if s ends with second substring
			// the search space for such matches can be narrowed down by 
			// constructing a subset based on the first string slice.
			
			String firstSlice = compStr.substring(0, wcIndex);
			String secondSlice = compStr.substring(wcIndex + 1);

			// builds the second bound of subset
			String subSetEnd = firstSlice + "\uFFFF";

			for (String s : strSet.subSet(firstSlice, subSetEnd)) {
				if (
					compStr.length() == s.length() &&
					s.startsWith(firstSlice) &&
					s.endsWith(secondSlice)
				)
					return true;
			}
		}

		return false;
	}

	// Driver code
	public static void main(String[] args) {
		String[] strList = { "cat", "dog", "bird", "blur" };
		init(strList);
		System.out.println(contains("*at")); // Expected: true
		System.out.println(contains("bird")); // Expected: true
		System.out.println(contains("**t")); // Expected: false
		System.out.println(contains("b*t")); // Expected: false
	}
}
