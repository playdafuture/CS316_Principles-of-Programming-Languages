/**
 * The class of the empty binary search tree.
 * @author Template
 */
class EmptyBST extends BST {
    @Override
    public String toString() {
        return "e";
    }

    static EmptyBST emptyBST = new EmptyBST();

    /**
     * Issues a message and returns the empty tree.
     * @param k
     * @return 
     */
    @Override
    BST search(int k) {
        System.out.println("Key value "+k+" does not exist in the tree.");
        return emptyBST;
    }

    /**
     * Returns the tree obtained by inserting key value k into the empty target tree.
     * @param k
     * @return 
     */
    @Override
    BST insert(int k) {
        ARinsert newARinsert = new ARinsert();
        newARinsert.target = emptyBST;
        newARinsert.k = k;
        newARinsert.RuntimeStack.push(newARinsert);
        newARinsert.insert();
        newARinsert.RuntimeStack.pop();
        return new NonEmptyBST(k, emptyBST, emptyBST);
    }

    /**
     * Issues a message and returns the empty tree.
     * @param k
     * @return 
     */
    @Override
    BST delete(int k) {
        System.out.println("Key value "+k+" does not exist in the tree.");
        return emptyBST;
    }
}
