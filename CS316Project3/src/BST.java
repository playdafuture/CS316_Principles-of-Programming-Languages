/**
 * The class of binary search trees containing integer keys.
 * Every key in the tree is unique. * 
 * @author Template
 */
abstract class BST {

    @Override
    public abstract String toString();

    /**
     * Searches for key value k in the target tree.
     * Returns the tree whose root contains k.
     * If k does not exist in the tree, issues a message and returns the empty tree.
     * @param k
     * @return 
     */
    abstract BST search(int k);

    /**
     * Returns the tree obtained by inserting key value k into the target tree.
     * If k already exists in the tree, issues a message and returns the target tree unchanged.
     * @param k
     * @return 
     */
    abstract BST insert(int k);

    /**
     * Returns the tree obtained by deleting key value k from the target tree.
     * If k does not exist in the tree, issues a message and returns the target tree unchanged.
     * @param k
     * @return 
     */
    abstract BST delete(int k);

}
