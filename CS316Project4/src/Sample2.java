public class Sample2 {
    static BSTArray sampleBSTArray = new BSTArray(new BST[2]);
    public static void main(String[] argv) {      
        if (argv.length > 0) {
            Output.setOutput(argv[0]);
        }
        Heap.add(sampleBSTArray);
        Heap.root = sampleBSTArray;
        Heap.idCount++;
        
        BST bst1 = new EmptyBST();
        bst1 = insert(6,bst1);
        bst1 = insert(4,bst1);
        bst1 = insert(2,bst1);
        bst1 = insert(1,bst1);
        bst1 = insert(3,bst1);
        bst1 = insert(5,bst1);
        bst1 = insert(8,bst1);
        bst1 = insert(7,bst1);
        bst1 = insert(9,bst1);
        bst1 = insert(10,bst1);
        
        BST bst2 = new EmptyBST();
        bst2 = insert(6,bst2);
        bst2 = insert(4,bst2);
        bst2 = insert(2,bst2);
        bst2 = insert(1,bst2);
        bst2 = insert(3,bst2);
        bst2 = insert(5,bst2);
        bst2 = insert(8,bst2);
        bst2 = insert(7,bst2);
        bst2 = insert(9,bst2);
        bst2 = insert(10,bst2);
        
        sampleBSTArray.bstArray[0] = bst1;
        sampleBSTArray.bstArray[1] = bst2;
        
        Heap.markAndSweep();
        
        Heap.root = null;
        Heap.markAndSweep();
        if (Output.isSet) {
            Output.closeOutput();
        }
    }
    
    static BST insert(int val, BST bst) {
        ARinsert ARI = new ARinsert();
        ARI.k = val;
        ARI.target = bst;
        ARI.insert();
        bst = ARI.returnVal;
        Heap.root = sampleBSTArray;
        return bst;
    }
    
    static BST delete(int val, BST bst) {
        ARdelete ARD = new ARdelete();
        ARD.k = val;
        ARD.target = bst;
        ARD.delete();
        bst = ARD.returnVal;
        Heap.root = sampleBSTArray;
        return bst;
    }
}
