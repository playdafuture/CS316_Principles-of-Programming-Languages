import java.io.*;

public abstract class Output {
    public static PrintWriter outStream;
    public static boolean isSet = false;

    public static void print(String s) {
        if (isSet) {
            outStream.print(s);
        } else {
            System.out.print(s);
        }
        
    }

    public static void println(String s) {
        if (isSet) {
            outStream.println(s);
        } else {
            System.out.println(s);
        }
    }
    
    public static void println() {
        if (isSet) {
            outStream.println();
        } else {
            System.out.println();
        }
    }
    
    /**
     * Sets the output stream to "outFile".
     * @param outFile Desired file name
     */
    public static void setOutput(String outFile)    {
        try {
            outStream = new PrintWriter( new FileOutputStream(outFile) );
            isSet = true;
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void closeOutput() {
        outStream.close();
        isSet = false;
    }
} 