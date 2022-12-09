## Question 1
To answer this question, the following Java program is created:

**MatchStrings.java**
```java
import java.util.Arrays;
import java.util.HashSet;

public class MatchStrings {
	private static HashSet<String> strSet;

	/**
	 * Initializes a HashSet with the contents of the list of Strings provided
     * by the parameter.
	 *
	 * @param strList The list of Strings to be stored in the HashSet
	 */
	private static void init(String[] strList) {
		strSet = new HashSet<>(Arrays.asList(strList));
	}

	/**
	 * Checks whether the provided string has a matching entry in the HashSet.
	 * 
	 * @param compStr The string to be compared with HashSet entries.
	 * @return "true" when there is a match with the provided 
	 * string; "false" otherwise.
	 */
	private static boolean contains(String compStr) {
		return strSet.contains(compStr);
	}
	
	// Driver code
	public static void main(String[] args) {
		String[] strList = { "cat", "dog", "bird", "blur" };
		init(strList);
		System.out.println(contains("cat")); // Expected: true
		System.out.println(contains("human")); // Expected: false
	}
}
```

## Question 2
The time complexity of the `contains` function is O(1), since the time 
complexity of checking whether an element is part of the HashSet is O(1).