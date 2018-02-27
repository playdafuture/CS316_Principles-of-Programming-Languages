/**
 * The class of nonempty binary search trees containing integer keys.
 * Every key in the tree is unique.
 * @author Future
 */
class NonEmptyBST extends BST {
    int val; // the key value in the root
    BST left; //left subtree
    BST right; // right subtree

    NonEmptyBST(int v, BST l, BST r) {
        val = v;
        left = l;
        right = r;
    }

    public String toString() {
        return mark+":"+objId+":NonEmptyBSTNode:"+val;
    }

    void traverse(boolean newMark) {        
        mark = newMark;
        Output.println(this.toString());
        left.traverse(newMark);
        right.traverse(newMark);
    }
}