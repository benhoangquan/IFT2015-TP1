/**
 * TP1 - IFT2015 Structure de données et algorithmes <p>
 * Fait par Hoang Quan Tran - 20249088 <p>
 */

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    /**
     * Solution inspired by and built on
     * https://leetcode.com/problems/word-search-ii/discuss/59780/Java-15ms-Easiest-Solution-(100.00)<p>
     * Some change to the original solution: <p>
     * 1. Use Deque instead of List to have O(1) time complexity for add operation<p>
     * 2. Add path argument to dfs to keep track of the path<p>
     * 3. Separate TrieNode class to an independent file and add setter/getter methods
     */
    public static final int[][] DIR = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, -1 }, { -1, 1 },
            { 1, -1 }, {0, 0} };

    /**
     * Finds all words in the given board that are in the given list of words.
     *
     * @param board the board to search in
     * @param words the words to search for
     * @return a deque of strings representing the found words and their paths
     */
    public Deque<String> findWords(char[][] board, String[] words) {
        Deque<String> results = new ArrayDeque<>();
        TrieNode root = buildTrie(words);
        String path = "";

        // For each position in the board, perform a new depth-first search from root
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                dfs(board, i, j, root, results, path);

        return results;
    }

    /**
     * Builds a trie from the given list of words.
     *
     * @param words the words to build the trie from
     * @return the root of the built trie
     */
    public TrieNode buildTrie(String[] words) {
        // Standard trie building
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode r = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (r.getChildren(i) == null)
                    r.setChildren(i, new TrieNode());
                r = r.getChildren(i);
            }
            r.setWord(w);
        }
        return root;
    }

    /**
     * Performs a depth-first search in the given board starting from the given position.
     *
     * @param board the board to search in
     * @param i the row index of the starting position
     * @param j the column index of the starting position
     * @param node the starting node in the trie
     * @param results the deque to store the found words and their paths
     * @param path the current path in the board
     */
    public void dfs(char[][] board, int i, int j, TrieNode node, Deque<String> results, String path) {
        // Verify that the current position is within the board
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length)
            return;

        // Base case of the recursion: the current position is not in the trie
        // Otherwise, get to the next node in the trie
        char c = board[i][j];
        if (node.getChildren(c - 'a') == null)
            return;
        node = node.getChildren(c - 'a');

        // If the current node is a word/terminal node
        // add it and its path to the result
        if (node.getWord() != null) {
            // last node in trie
            path += String.format("(%s,%s)", i, j);
            results.add(String.format("%s %s", node.getWord(), path));
        }

        // For each direction, perform a recursive depth-first search
        for (int[] d : DIR) {
            int nextI = i + d[0];
            int nextJ = j + d[1];

            // Add the current position to the path
            dfs(board, nextI, nextJ, node, results, path + String.format("(%s,%s)->", i, j));
        }
    }

    /**
     * The main method.
     *
     * @param args the path to the input file
     */
    public static void main(String[] args) {
        File file = new File(args[0]);

        // Set to true to show performance metrics
        boolean showPerf = false;

        try (Scanner s = new Scanner(file)) {
            int counter = 1;
            while (s.hasNextLine()){
                // Parse the input
                int m = s.nextInt();
                int n = s.nextInt();
                s.nextLine(); // Consume newline left-over

                StringBuilder inputBoard = new StringBuilder();
                for (int i = 0; i < m; i++)
                    inputBoard.append(s.nextLine()).append("\n");

                String inputWord = s.nextLine();

                // Convert the input strings to the correct formats
                char[][] board = Util.string2Board(m, n, inputBoard.toString());
                String[] words = Util.string2Words(inputWord);

                if (showPerf) {
                    long cumDuration = 0;
                    int nbOfIteration = 100000;
                    for (int i = 0; i < nbOfIteration; i++) {
                        // Measure time execution
                        long startTime = System.currentTimeMillis();

                        // Construct main instance and run the algorithm
                        Main main = new Main();
                        Deque<String> results = main.findWords(board, words);
                        List<String> sortedResults = new ArrayList<>(results);
                        sortedResults.sort(String::compareTo);

                        long endTime = System.currentTimeMillis();
                        long duration = (endTime - startTime);
                        cumDuration += duration;
                    }
                    System.out.printf("Query %d:\n", counter);
                    System.out.printf("Cumulative time for %d iterations: %d ms\n", nbOfIteration, cumDuration);
                    System.out.printf("Average time for %d iterations: %d ms\n", nbOfIteration, cumDuration / nbOfIteration);

                } else {
                    // Construct main instance and run the algorithm
                    Main main = new Main();
                    Deque<String> results = main.findWords(board, words);

                    // Sort the results (last minute addition)
                    List<String> sortedResults = new ArrayList<>(results);
                    sortedResults.sort(String::compareTo);

                    // Print the results
                    System.out.printf("Query %d:\n", counter);
                    for (String sr : sortedResults)
                        System.out.println(sr);

                }

                // Next query
                counter++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}