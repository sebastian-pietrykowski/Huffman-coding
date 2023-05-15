package pl.edu.pw.ee.decompression;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.edu.pw.ee.compression.CompressedTextFileWriter;
import pl.edu.pw.ee.TestUtils;

public class CompressedTextFileReaderTest {

    public static final int N_OF_BITS_IN_BYTE = 8;

    private File testFile;

    @Before
    public void setUp() throws IOException {
        testFile = new File("reading compressed text.txt");
        testFile.delete();

        TestUtils.validateIfFileExistsForTesting(testFile);

        testFile.createNewFile();
    }

    @After
    public void tearDown() {
        testFile.delete();
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_ThrowException_ArgumentIsNull() {
        // given
        File fileToRead = null;

        // when
        new CompressedTextFileReader(fileToRead);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_ThrowException_FileDoesntExist() {
        // given
        File fileToRead = new File("fdda.a");
        if (fileToRead.exists()) {
            TestUtils.validateIfFileExistsForTesting(fileToRead);
            assert false;
        }

        // when
        new CompressedTextFileReader(fileToRead);

        // then
        assert false;
    }

    @Test(expected = IOException.class)
    public void read_ThrowException_FileIsEmpty() throws IOException {
        // given
        File fileToRead = testFile;
        CompressedTextFileReader instance = new CompressedTextFileReader(
                fileToRead);
        try {
            testFile.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(CompressedTextFileReaderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
            assert false;
        }

        // when
        instance.read();

        // then
        assert false;
    }

    @Test
    public void read_CorrectlyReadText_FileContainsMessage() {
        // given
        File fileToRead = testFile;
        CompressedTextFileReader instance = new CompressedTextFileReader(
                fileToRead);
        byte[] text = {1, 35, -100};
        int nOfActualBits = text.length * N_OF_BITS_IN_BYTE;
        try {
            new CompressedTextFileWriter(text, nOfActualBits).write(fileToRead);
        } catch (IOException ex) {
            Logger.getLogger(CompressedTextFileReaderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // when
        byte[] resultText = null;
        try {
            resultText = instance.read();
        } catch (IOException ex) {
            Logger.getLogger(CompressedTextFileReaderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // then
        assertArrayEquals(text, resultText);
    }

    @Test
    public void read_CorrectlyReadText_TextIsLongerThanNOfActualBits() {
        // given
        File fileToRead = testFile;
        CompressedTextFileReader instance = new CompressedTextFileReader(
                fileToRead);
        byte[] text = {0, -35, 98, 5};
        int nOfActualBits = text.length * N_OF_BITS_IN_BYTE - 3;
        try {
            new CompressedTextFileWriter(text, nOfActualBits).write(fileToRead);
        } catch (IOException ex) {
            Logger.getLogger(CompressedTextFileReaderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // when
        byte[] resultText = null;
        try {
            resultText = instance.read();
        } catch (IOException ex) {
            Logger.getLogger(CompressedTextFileReaderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // then
        assertArrayEquals(text, resultText);
    }

    @Test
    public void getNOfActualBits_CorrectlyReadNOfBits_FileContainsMessage() {
        // given
        File fileToRead = testFile;
        CompressedTextFileReader instance = new CompressedTextFileReader(
                fileToRead);
        byte[] text = {21, 35, -100, 11, 3};
        int nOfActualBits = text.length * N_OF_BITS_IN_BYTE - 2;
        try {
            new CompressedTextFileWriter(text, nOfActualBits).write(fileToRead);
        } catch (IOException ex) {
            Logger.getLogger(CompressedTextFileReaderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // when
        try {
            instance.read();
        } catch (IOException ex) {
            Logger.getLogger(CompressedTextFileReaderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        int result = instance.getNOfActualBits();

        // then
        assertEquals(nOfActualBits, result);
    }

}
