package pl.edu.pw.ee.compression;

import pl.edu.pw.ee.TestUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileReaderToCharsTest {

    private String pathToTestDir;

    @Before
    public void setUp() {
        String absolutePathOfWorkingDir
                = Paths.get("").toAbsolutePath().toString();

        pathToTestDir = absolutePathOfWorkingDir
                + "/tests data/Compression/FileReaderToChars";
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_ThrowException_ArgumentIsNull() {
        // given
        File fileToRead = null;

        // when
        new FileReaderToChars(fileToRead);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_ThrowException_FileDoesntExist() {
        // given
        String filePath = pathToTestDir + "/vade.ge";
        File fileToRead = new File(filePath);
        if (fileToRead.exists()) {
            TestUtils.validateIfFileExistsForTesting(fileToRead);
            assert false;
        }

        // when
        new FileReaderToChars(fileToRead);

        // then
        assert false;
    }

    @Test
    public void readChars_CorrectlyReadText_FileIsEmpty() {
        // given
        String filePath = pathToTestDir + "/Empty file.txt";
        File fileToRead = new File(filePath);
        FileReaderToChars instance = new FileReaderToChars(fileToRead);

        // when
        char[] result = null;
        try {
            result = instance.readChars();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReaderToCharsTest.class.getName()).log(
                    Level.SEVERE, null, ex);
            assert false;
        } catch (IOException ex) {
            Logger.getLogger(FileReaderToCharsTest.class.getName()).log(
                    Level.SEVERE, null, ex);
            assert false;
        }

        // then
        char[] expResult = {};
        assertArrayEquals(expResult, result);
    }

    @Test
    public void readChars_CorrectlyReadText_FileContainsOneLetter() {
        // given
        String filePath = pathToTestDir + "/One letter text.txt";
        File fileToRead = new File(filePath);
        FileReaderToChars instance = new FileReaderToChars(fileToRead);

        // when
        char[] result = null;
        try {
            result = instance.readChars();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReaderToCharsTest.class.getName()).log(
                    Level.SEVERE, null, ex);
            assert false;
        } catch (IOException ex) {
            Logger.getLogger(FileReaderToCharsTest.class.getName()).log(
                    Level.SEVERE, null, ex);
            assert false;
        }

        // then
        char[] expResult = {'a'};
        assertArrayEquals(expResult, result);
    }

    @Test
    public void readChars_CorrectlyReadText_FileContains2Lines() {
        // given
        String filePath = pathToTestDir + "/Simple text - 2 lines.txt";
        File fileToRead = new File(filePath);
        FileReaderToChars instance = new FileReaderToChars(fileToRead);

        // when
        char[] result = null;
        try {
            result = instance.readChars();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReaderToCharsTest.class.getName()).log(
                    Level.SEVERE, null, ex);
            assert false;
        } catch (IOException ex) {
            Logger.getLogger(FileReaderToCharsTest.class.getName()).log(
                    Level.SEVERE, null, ex);
            assert false;
        }

        // then
        char[] expResult = "qwerty\r\nzxcrty".toCharArray();
        assertArrayEquals(expResult, result);
    }
}
