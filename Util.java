/**
 * TP1 - IFT2015 Structure de données et algorithmes <p>
 * Fait par Hoang Quan Tran - 20249088 <p>
 */

/**
 * Utility class for converting strings to other data structures.
 * Note: strings must be formatted according to the specifications in the problem statement.
 */
public class Util {

    /**
     * Converts a string representation of a board into a 2D char array.
     *
     * @param x the number of rows in the board
     * @param y the number of columns in the board
     * @param inputBoard the string representation of the board, with rows separated by newlines
     *        and characters in each row separated by spaces
     * @return a 2D char array representing the board
     */
    public static char[][] string2Board(int x, int y, String inputBoard) {
        char[][] board = new char[x][y];
        String[] lines = inputBoard.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split(" ");
            for (int j = 0; j < line.length; j++) {
                board[i][j] = line[j].charAt(0);
            }
        }
        return board;
    }

    /**
     * Converts a string of words into an array of words.
     *
     * @param inputWord the string of words, with words separated by spaces
     * @return an array of words
     */
    public static String[] string2Words(String inputWord) {
        return inputWord.split(" ");
    }
}