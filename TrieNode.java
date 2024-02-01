/**
 * TP1 - IFT2015 Structure de données et algorithmes <p>
 * Fait par Hoang Quan Tran - 20249088 <p>
 */

/**
 * Represents a node in a Trie data structure.
 * Each TrieNode has 26 children, one for each letter of the alphabet.
 * The node also stores a word, which is used for easy access when the node represents the end of a word.
 */
public class TrieNode {
    private final TrieNode[] children = new TrieNode[26];

    // Usually, we would use a boolean to indicate whether the current node is a
    // word or not. However, we use a String to store the word for the ease of access
    private String word;

    /**
     * Constructs a new TrieNode
     */
    public TrieNode() {
    }

    /**
     * Returns the child TrieNode at the given index.
     *
     * @param i the index of the child to return
     * @return the child TrieNode at the given index
     */
    public TrieNode getChildren(int i) {
        return this.children[i];
    }

    /**
     * Sets the child TrieNode at the given index.
     *
     * @param i the index of the child to set
     * @param node the TrieNode to set as the child
     */
    public void setChildren(int i, TrieNode node) {
        this.children[i] = node;
    }

    /**
     * Returns the word stored in this TrieNode.
     *
     * @return the word stored in this TrieNode
     */
    public String getWord() {
        return this.word;
    }

    /**
     * Sets the word stored in this TrieNode.
     *
     * @param word the word to store in this TrieNode
     */
    public void setWord(String word) {
        this.word = word;
    }
}