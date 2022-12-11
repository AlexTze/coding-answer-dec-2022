## Question 1
The answer to this question is implemented in `MatchStrings1.java`.

## Question 2
The time complexity of the `contains` function is O(1), since the time 
complexity of checking whether an element is part of the HashSet is O(1).

## Question 3
With the introduction of the wildcard character, a simple approach would be 
utilizing the regex capabilities provided by Java:

**MatchStrings.java**
```java
import java.util.Arrays;
import java.util.HashSet;

public class MatchStrings {
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
		System.out.println(contains("*at")); // Expected: true
		System.out.println(contains("b*t")); // Expected: false
	}
}
```

## Question 4
### Question 4a
The `contains` function in the previous implementation has time complexity of 
O(n), as the worst case scenario would be iterating through the HashSet without
finding a match.

### Question 4b
To fulfill the requirements, a TreeSet is used in place of the previous HashSet,
as it provides additional capabilities while retaining comparable performance.

**MatchStrings.java**
```java
import java.util.*;

public class MatchStrings {
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
```

Time complexity analysis:
- When the query string contains no wildcard character, the time complexity for
the `contains` function is O(log n), as decided by the TreeSet's own `contains`
method.
- When the query string contains a wildcard character that is not at the start
of the string, in general cases the `contains` function would only have to 
search for matches within a subset of the stored strings (assuming an even
distribution)
- When the query string contains a wildcard character at the start of the 
string, the `contains` function would still have to iterate through the entire
TreeSet, thus making this situation the worst-case scenario.

## Question 5
To my understanding, there are other imperative approaches, but are not 
practical or outside of my current expertise.

- Alternative 1: Concatenate the strings into a big string, delimited by spaces.
Then, use a pattern matcher to get the desired results. This is not a good 
solution and suffers from performance and storage drawbacks.
- Alternative 2: Making pattern matching usable with the TreeSet's `contains` 
method. As of now I cannot think of actual implementations.
