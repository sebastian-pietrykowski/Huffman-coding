package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestUtils {

    public static void validateIfFileExistsForTesting(File file) {
        if (file.exists()) {
            throw new IllegalStateException(
                    "There already exists file with the same name "
                    + "as file to create for tests: " + file.getAbsolutePath()
                    + ".");
        }
    }

    public static boolean doFilesContainTheSameContent(File file1, File file2) {
        BufferedReader reader1 = null;
        BufferedReader reader2 = null;
        boolean didFindDifference = false;
        try {
            reader1 = new BufferedReader(new FileReader(file1));
            reader2 = new BufferedReader(new FileReader(file2));
            while (!didFindDifference) {
                int char1 = reader1.read();
                int char2 = reader2.read();
                if (char1 != char2) {
                    didFindDifference = true;
                }
                if (char1 == -1 || char2 == -1) {
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestUtils.class.getName()).log(
                    Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TestUtils.class.getName()).log(
                    Level.SEVERE, null, ex);
        } finally {
            try {
                reader1.close();
                reader2.close();
            } catch (IOException ex) {
                Logger.getLogger(TestUtils.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }

        return !didFindDifference;
    }
}
