class ARdelete extends AR {
    BST target; // the target BST object for a call to delete()
    int k;
    int pred; // local var
    BST returnVal;

    public String toString() {
        return "ARdelete "+"target = "+target+" k = "+k+" pred "+pred;
    }

    void traverse() {
        
    }

    void delete() {
        if ( target.getClass() == NonEmptyBST.class ) {
            NonEmptyBST t1 = (NonEmptyBST)target;
            if ( k < t1.val ) {
                ARdelete newARdelete = new ARdelete();
                newARdelete.target = t1.left;
                newARdelete.k = k;
                RuntimeStack.push(newARdelete);
                newARdelete.delete(); // call left.delete(k)
                t1.left = newARdelete.returnVal;
                RuntimeStack.pop();
                returnVal = t1;
            } else if ( k > t1.val ) {
                ARdelete newARdelete = new ARdelete();
                newARdelete.target = t1.right;
                newARdelete.k = k;
                RuntimeStack.push(newARdelete);
                newARdelete.delete(); // call right.delete(k)
                t1.right = newARdelete.returnVal;
                RuntimeStack.pop();
                returnVal = t1;
            } else { // k == t1.val
                if ( t1.left == EmptyBST.emptyBST ) {
                    returnVal = t1.right;
                } else if ( t1.right == EmptyBST.emptyBST ) {
                    returnVal = t1.left;
                } else { // t1.left != EmptyBST.emptyBST & t1.right != EmptyBST.emptyBST
                    ARmaxKey newARmaxKey = new ARmaxKey();
                    newARmaxKey.target = (NonEmptyBST)t1.left;
                    RuntimeStack.push(newARmaxKey);
                    newARmaxKey.maxKey(); //call maxKey();
                    pred = newARmaxKey.returnVal;
                    RuntimeStack.pop();
                    t1.val = pred;

                    ARdelete newARdelete = new ARdelete();
                    newARdelete.target = t1.left;
                    newARdelete.k = pred;
                    RuntimeStack.push(newARdelete);
                    newARdelete.delete(); // call left.delete(pred)
                    t1.left = newARdelete.returnVal;
                    RuntimeStack.pop();
                    returnVal = t1;					
                }
            }
        } else { // target.getClass() == EmptyBST.class
            System.out.println("Key value "+k+" does not exist in the tree.");
            returnVal = EmptyBST.emptyBST;
        }
    }
}