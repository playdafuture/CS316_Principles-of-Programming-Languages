class ARsearch extends AR {
    BST target; // the target BST object for a call to search()
    int k;
    BST t; // temporary var to store the return value
    BST returnVal;

    public String toString() {
        return "ARsearch "+"target = "+target+" k = "+k;
    }

    void traverse() {
        
    }

    void search() {
        if ( target.getClass() == NonEmptyBST.class ) {
            NonEmptyBST t1 = (NonEmptyBST)target;
            if ( k == t1.val ) {
                this.returnVal = target;
            } else { // k < t1.val | k > t1.val
                ARsearch newARsearch = new ARsearch();
                if ( k < t1.val )
                        newARsearch.target = t1.left;
                else // k > t1.val
                        newARsearch.target = t1.right;
                newARsearch.k = k;
                RuntimeStack.push(newARsearch);
                newARsearch.search(); // call left.search(k) or right.search(k)
                t = newARsearch.returnVal;
                RuntimeStack.pop();
                returnVal = t;
            }
        } else { // target.getClass() == EmptyBST.class
            System.out.println("Key value "+k+" does not exist in the tree.");
            returnVal = EmptyBST.emptyBST;
        }
    }
}