/*
 */
package modelchecker;

import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author repoman
 */
public class ModelChecker {
    public interface Debug {
        public static final boolean DEBUG = true;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("tests/exmp01_pt40.txt");
//        File file = new File("tests/exmp01_pt60.txt");
//        File file = new File("tests/exmp01_pt80.txt");
//        File file = new File("tests/exmp01_pt100.txt");
        Parser.parse(file);
    }
}
