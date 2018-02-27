public class BSTDemo extends AR {
    public static void main(String argv[]) {
        if (argv.length > 0) {
            Output.setOutput( argv[0] );
        }
        BST demoTree;// = new EmptyBST();
        demoTree = new EmptyBST();
//        
        demoTree = demoTree.insert(6);
        demoTree = demoTree.insert(4);
        demoTree = demoTree.insert(2);
        demoTree = demoTree.insert(1);
        demoTree = demoTree.insert(3);
        demoTree = demoTree.insert(5);
        demoTree = demoTree.insert(8);
        demoTree = demoTree.insert(7);
        demoTree = demoTree.insert(9);
        demoTree = demoTree.insert(10);
        
        System.out.println(demoTree);
        
        //System.out.println("------------------------");
        
        demoTree.search(6);
        demoTree.search(4);
        demoTree.search(2);
        demoTree.search(1);
        demoTree.search(3);
        demoTree.search(5);
        demoTree.search(8);
        demoTree.search(7);
        demoTree.search(9);
        demoTree.search(10);
        
        System.out.println(demoTree);
        //System.out.println("------------------------");
        
        demoTree = demoTree.delete(6);
        demoTree = demoTree.delete(4);
        demoTree = demoTree.delete(2);
        demoTree = demoTree.delete(1);
        demoTree = demoTree.delete(3);
        demoTree = demoTree.delete(5);
        demoTree = demoTree.delete(8);
        demoTree = demoTree.delete(7);
        demoTree = demoTree.delete(9);
        demoTree = demoTree.delete(10);
        
        System.out.println(demoTree);
        if (argv.length > 0) {
            Output.closeOutput();
        }
    }
}
