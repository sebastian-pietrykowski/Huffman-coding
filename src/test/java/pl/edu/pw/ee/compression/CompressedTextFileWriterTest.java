package pl.edu.pw.ee.compression;

import pl.edu.pw.ee.TestUtils;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class CompressedTextFileWriterTest {

    private File testFile;

    @Before
    public void setUp() throws IOException {
        testFile = new File("test_file_writing.tx");

        TestUtils.validateIfFileExistsForTesting(testFile);

        testFile.createNewFile();
    }

    @After
    public void tearDown() {
        testFile.delete();
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_ThrowException_ArgumentBytesArrayIsNull() {
        // given
        byte[] compressedText = null;
        int nOfActualBits = 5;

        // when
        new CompressedTextFileWriter(compressedText, nOfActualBits);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_ThrowException_ArgumentNOfActualBitsIsNegative() {
        // given
        byte[] compressedText = {5, 0};
        int nOfActualBits = -1;

        // when
        new CompressedTextFileWriter(compressedText, nOfActualBits);

        // then
        assert false;
    }

    @Test
    public void write_CorrectlyWriteNOfBits_Writing1Byte() throws Exception {
        // given
        byte[] compressedText = {100};
        int nOfActualBits = 8;
        File fileToWrite = testFile;
        CompressedTextFileWriter instance
                = new CompressedTextFileWriter(compressedText, nOfActualBits);

        // when
        instance.write(fileToWrite);

        // then
        DataInputStream inputStream = new DataInputStream(
                new FileInputStream(fileToWrite));

        int readNOfActualBits = inputStream.readInt();
        inputStream.close();

        assertEquals(nOfActualBits, readNOfActualBits);
    }

    @Test
    public void write_CorrectlyWriteBytesArray_Writing1Byte() throws Exception {
        // given
        byte[] compressedText = {102};
        int nOfActualBits = 8;
        File fileToWrite = testFile;
        CompressedTextFileWriter instance
                = new CompressedTextFileWriter(compressedText, nOfActualBits);

        // when
        instance.write(fileToWrite);

        // then
        DataInputStream inputStream = new DataInputStream(
                new FileInputStream(fileToWrite));

        inputStream.readInt();
        byte[] readBytes = inputStream.readAllBytes();
        inputStream.close();

        assertArrayEquals(compressedText, readBytes);
    }

    @Test
    public void write_CorrectlyWriteNOfBits_Writing5Bytes() throws Exception {
        // given
        byte[] compressedText = {-128, 0, 127, 55, -3};
        int nOfActualBits = 38;
        File fileToWrite = testFile;
        CompressedTextFileWriter instance
                = new CompressedTextFileWriter(compressedText, nOfActualBits);

        // when
        instance.write(fileToWrite);

        // then
        DataInputStream inputStream = new DataInputStream(
                new FileInputStream(fileToWrite));

        int readNOfActualBits = inputStream.readInt();
        inputStream.close();

        assertEquals(nOfActualBits, readNOfActualBits);
    }

    @Test
    public void write_CorrectlyWriteBytesArray_Writing5Bytes() throws Exception {
        // given
        byte[] compressedText = {0, 12, -100, -128, 127};
        int nOfActualBits = 38;
        File fileToWrite = testFile;
        CompressedTextFileWriter instance
                = new CompressedTextFileWriter(compressedText, nOfActualBits);

        // when
        instance.write(fileToWrite);

        // then
        DataInputStream inputStream = new DataInputStream(
                new FileInputStream(fileToWrite));

        inputStream.readInt();
        byte[] readBytes = inputStream.readAllBytes();
        inputStream.close();

        assertArrayEquals(compressedText, readBytes);
    }
}
