# Answers to Coding Questions
## Question 1
The answer to this question is implemented in `MatchStrings1.java`.

## Question 2
The time complexity of the `contains` function in `MatchStrings1.java`
is O(1), since the time complexity of checking whether an element is part of 
the HashSet is O(1).

## Question 3
With the introduction of the wildcard character, a simple approach would be 
utilizing the regex capabilities provided by Java. Please see 
`MatchStrings2.java`.

## Question 4
### Question 4a
The `contains` function in `MatchStrings2.java` has time complexity of 
O(n), as the worst case scenario would be iterating through the HashSet without
finding a match.

### Question 4b
To fulfill the requirements, a TreeSet is used in place of the previous HashSet,
as it provides additional capabilities while retaining comparable performance.
Please see `MatchStrings3.java`.

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
solution as it suffers from performance and storage drawbacks.
- Alternative 2: Making pattern matching usable with the TreeSet's `contains` 
method. As of now I cannot think of actual implementations.
