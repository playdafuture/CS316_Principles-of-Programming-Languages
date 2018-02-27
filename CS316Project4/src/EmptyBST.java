/**
 * The class of the empty binary search tree.
 * @author Jinqiu Liu
 */
class EmptyBST extends BST {
    /**
     * This object is always reachable and never garbage collected.
     * It will receive an object ID integer when it is created.
     */
    static final EmptyBST emptyBST = new EmptyBST(); 
    
    @Override
    public String toString() {
        return "e";
    }    

    @Override
    void traverse(boolean newMark) {
        //Output.println(this.toString());
        mark = newMark;
    }
}
