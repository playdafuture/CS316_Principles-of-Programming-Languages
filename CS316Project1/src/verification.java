
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Jinqiu Liu
 */
public class verification {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader inStream1;
        BufferedReader inStream2;
        int matchCount = 0, totalCount = 0;
        boolean allGood = true;
        String[] vars = new String[2];
        
        for (int i = 1; i <= 8; i++) {
            vars[0] = "t"+i+".txt";
            vars[1] = "my"+i+".txt";
            main.main(vars);
            inStream1 = new BufferedReader(new FileReader(vars[1]));
            inStream2 = new BufferedReader(new FileReader("to"+i+".txt"));
            if (match(inStream1,inStream2)) {
                System.out.println("Output " + i + " MATCH the standard");
                matchCount++;
            } else {
                System.out.println("MISMATCH FOUND in file#" + i);
                allGood = false;
            }
            
            inStream1.close();
            inStream2.close();
            totalCount++;
        }
        
        if (allGood) {
            System.out.println("All Good!");
        }
        System.out.println("Matched " + matchCount + " out of " + totalCount);
    }

    private static boolean match(BufferedReader l, BufferedReader r) throws IOException {
        int a=0, b=0;
        while ( !(a==-1 && b==-1)) {
            a = l.read();
            while (Character.isWhitespace((char)a)) {
                a = l.read();
            }
            b = r.read();
            while (Character.isWhitespace((char)b)) {
                b = r.read();
            }
            if (a!=b) {
                return false;
            }
        }
        return true;
    }
}
