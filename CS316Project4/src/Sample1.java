public class Sample1 {
    static BST sampleRoot = new EmptyBST();
    
    public static void main(String[] argv) {
        if (argv.length > 0) {
            Output.setOutput(argv[0]);
        }
        Heap.root = sampleRoot;
        Heap.idCount++;
        
        insert(6);
        Heap.markAndSweep();
        insert(4);
        Heap.markAndSweep();
        insert(2);
        Heap.markAndSweep();
        insert(1);
        Heap.markAndSweep();
        insert(3);
        Heap.markAndSweep();
        insert(5);
        Heap.markAndSweep();
        insert(8);
        Heap.markAndSweep();
        insert(7);
        Heap.markAndSweep();
        insert(9);
        Heap.markAndSweep();
        insert(10);
        Heap.markAndSweep();
        
        delete(6);
        Heap.markAndSweep();
        delete(4);
        Heap.markAndSweep();
        delete(2);
        Heap.markAndSweep();
        delete(1);
        Heap.markAndSweep();
        delete(3);
        Heap.markAndSweep();
        delete(5);
        Heap.markAndSweep();
        delete(8);
        Heap.markAndSweep();
        delete(7);
        Heap.markAndSweep();
        delete(9);
        Heap.markAndSweep();
        delete(10);
        //Heap.markAndSweep();
        if (Output.isSet) {
            Output.closeOutput();
        }
    }
    
    static void insert(int val) {
        ARinsert ARI = new ARinsert();
        ARI.k = val;
        ARI.target = sampleRoot;
        ARI.insert();
        sampleRoot = ARI.returnVal;
        Heap.root = sampleRoot;
    }
    
    static void delete(int val) {
        ARdelete ARD = new ARdelete();
        ARD.k = val;
        ARD.target = sampleRoot;
        ARD.delete();
        sampleRoot = ARD.returnVal;
        Heap.root = sampleRoot;
    }
}
