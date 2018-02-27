/**
 * The class of nonempty binary search trees containing integer keys.
 * Every key in the tree is unique.
 * @author Template
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

    @Override
    public String toString() {
        return "["+val+", "+left.toString()+", "+right.toString()+"]";
    }
    
    /**
     * Searches for key value k in the nonempty target tree.
     * Returns the tree whose root contains k; returns the empty tree if k is not found.
     * @param k
     * @return 
     */
    @Override
    BST search(int k) {
        ARsearch newARsearch = new ARsearch();
        newARsearch.target = this;
        newARsearch.k = k;
        newARsearch.RuntimeStack.push(newARsearch);
        
        BST temp;
        if ( k == val ) {
            temp = this;
        } else if ( k < val ) {            
            //newARsearch.target = left;
            temp = left.search(k);
            //newARsearch.t = temp;            
        } else { // k > val
            //newARsearch.target = right;
            temp = right.search(k);
        }
        newARsearch.t = temp;
        newARsearch.search();
        newARsearch.RuntimeStack.pop();
        return temp;
    }
    
    /**
     * Returns the tree obtained by inserting key value k into the nonempty target tree.
     * If k already exists in the tree, issues a message and returns the target tree unchanged.
     * @param k
     * @return 
     */
    @Override
    BST insert(int k) {     
        ARinsert newARinsert = new ARinsert();        
        newARinsert.target = this;
        newARinsert.k = k;
        newARinsert.RuntimeStack.push(newARinsert);
       
        if (k < val) {       
            left = left.insert(k);
        } else if (k > val) {           
            right = right.insert(k);
        } else { // k == val
            System.out.println("Key value "+k+" already exists in the tree.");
        }
        newARinsert.RuntimeStack.pop();
        return this;
    }

    /**
     * Returns the tree obtained by deleting key value k from the nonempty target tree.
     * If k does not exist in the tree, issues a message and returns the target tree unchanged.
     * @param k
     * @return 
     */
    @Override
    BST delete(int k) {
        ARdelete newARdelete = new ARdelete();
        newARdelete.target = this;
        newARdelete.k = k;
        newARdelete.t = val;
        newARdelete.RuntimeStack.push(newARdelete);
        newARdelete.delete();
        BST temp;
        if ( k < val ) {
            left = left.delete(k);
            temp = this;
        } else if ( k > val ) {
            right = right.delete(k);
            temp = this;
        } else {
            // k == val               
            if (left == EmptyBST.emptyBST) {
                temp = right;
            } else if ( right == EmptyBST.emptyBST ) {
                temp = left;
            } else {
                // left != EmptyBST.emptyBST & right != EmptyBST.emptyBST
                ARmaxKey newARmaxKey = new ARmaxKey();                
                int pred = ((NonEmptyBST)left).maxKey();
                newARmaxKey.t = pred;
                newARmaxKey.maxKey();
                // get the predecessor of k, which is the maximum key in the left subtree                
                val = pred; // replace val by the predecessor
                newARdelete.pred = newARmaxKey.returnVal;
                left = left.delete(pred); // replace left the by tree obtained by deleting the predecessor
                temp = this;                
            }
        }
        
        newARdelete.returnVal = temp;        
        newARdelete.RuntimeStack.pop();
        return temp;        
    }

    /**
     * Returns the maximum (rightmost) key in the nonempty target tree.
     * @return 
     */
    int maxKey() {
        NonEmptyBST t = this;
        while (t.right != EmptyBST.emptyBST) {
            t = (NonEmptyBST)t.right;
        }
        return t.val;
    }
}
