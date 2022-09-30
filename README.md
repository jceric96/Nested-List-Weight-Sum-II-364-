## 364.Nested List Weight Sum II

Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists (Please see the coding area for detail).

Note that the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:


Input: [[1,1],2,[1,1]]
Output: 8
Explanation: four 1's at depth 1, one 2 at depth 2

Example 2:


Input: [1,[4,[6]]]
Output: 17
Explanation: one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1 x 3 + 4 x 2 + 6 x 1 = 17


input description:
A list of nested Integer (may be a list)

output description:
the sum of all integers in the list weighted by their depth.

input:
[1,[4,[6]]]

output:
17