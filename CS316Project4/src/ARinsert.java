class ARinsert extends AR {
    BST target; // the target BST object for a call to insert()
    int k;
    BST returnVal;

    public String toString() {
        return "ARinsert "+"target = "+target+" k = "+k;
    }

    void traverse() {
        
    }

    void insert() {
        if ( target.getClass() == NonEmptyBST.class ) {
            NonEmptyBST t1 = (NonEmptyBST)target;
            if ( k < t1.val ) {
                ARinsert newARinsert = new ARinsert();
                newARinsert.target = t1.left;
                newARinsert.k = k;
                RuntimeStack.push(newARinsert);
                newARinsert.insert(); // call left.insert(k)
                t1.left = newARinsert.returnVal;
                RuntimeStack.pop();
                returnVal = t1;
            } else if ( k > t1.val ) {
                ARinsert newARinsert = new ARinsert();
                newARinsert.target = t1.right;
                newARinsert.k = k;
                RuntimeStack.push(newARinsert);
                newARinsert.insert(); // call right.insert(k)
                t1.right = newARinsert.returnVal;
                RuntimeStack.pop();
                returnVal = t1;
            } else { // k == t1.val
                System.out.println("Key value "+k+" already exists in the tree.");
                returnVal = target;
            }
        } else { // target.getClass() == EmptyBST.class
            returnVal = new NonEmptyBST(k, EmptyBST.emptyBST, EmptyBST.emptyBST);
            Heap.add(returnVal);
        }
    }
}