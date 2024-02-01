# IFT2015-TP1

## Problem Overview

The objective is to develop an Object-Oriented Program that efficiently solves a word search puzzle. The puzzle consists of a two-dimensional M x N board filled with letters, where the task is to find and list words provided in a separate list. Words can be formed by linking letters adjacent to each other in any direction: horizontally, vertically, or diagonally. The solution should identify all occurrences of the words, mapping out their paths on the board, and present the results in lexicographical order.

## How we approach the problem

1. **Trie Construction**: The solution starts with building a Trie from the list of words provided. This is achieved in the `buildTrie` method, where each word is broken down into characters and added to the Trie. This structure allows for efficient word searches within the board.

2. **Direction Matrix**: A matrix named `DIR` is defined to navigate through the board. It includes directions to move horizontally, vertically, and diagonally, enabling the algorithm to explore all possible adjacent cells from any given position.

3. **DFS Search**: At the heart of the algorithm is the `findWords` method, which performs a Depth-First Search (DFS) from every cell on the board. The `dfs` method is recursively called to explore adjacent cells, constructing words by concatenating characters, and checking these against the Trie to identify valid words.

4. **Path Tracking**: An enhancement over the original approach includes utilizing a `Deque` for tracking the search path in the DFS, instead of a `List`. This optimizes the add operation to O(1) time complexity, improving the efficiency of path tracking during the search.

5. **Results Collection**: As the DFS uncovers valid words, they are added to a results `Deque` along with their paths. This collection of found words and their corresponding paths on the board is then returned as the output of the `findWords` method.

## Acknowledgement

Special thanks to M. Francois Major for his invaluable guidance in this course IFT 2015.

