package pl.edu.pw.ee.decompression;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.edu.pw.ee.TestUtils;

public class DecompressedTextFileWriterTest {

    private File testFile;

    @Before
    public void setUp() throws IOException {
        testFile = new File("writing decompressed text.txt");
        testFile.delete();

        TestUtils.validateIfFileExistsForTesting(testFile);

        testFile.createNewFile();
    }

    @After
    public void tearDown() {
        testFile.delete();
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_ThrowException_ArgumentStringIsNull() {
        // given
        String text = null;

        // when
        new DecompressedTextFileWriter(text);

        // when
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void write_ThrowException_FileDoesntExist() {
        // given
        File fileToWrite = new File("fafawe.rawddfw");
        String text = "ala ma kota";
        DecompressedTextFileWriter instance = new DecompressedTextFileWriter(text);

        // when
        try {
            instance.write(fileToWrite);
        } catch (IOException ex) {
            Logger.getLogger(DecompressedTextFileWriterTest.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        // then
        assert false;
    }

    @Test
    public void write_WorkCorrectly_WritingStrangeMessage() {
        // given
        File fileToWrite = testFile;
        String text = "ggwadc242/Aa\nraw\tqw.;'l\n\n";
        DecompressedTextFileWriter instance = new DecompressedTextFileWriter(text);

        // when
        try {
            instance.write(fileToWrite);
        } catch (IOException ex) {
            Logger.getLogger(DecompressedTextFileWriterTest.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        // then
        String readText = null;
        try ( BufferedReader reader = new BufferedReader(new FileReader(testFile))) {
            int character;
            StringBuilder sb = new StringBuilder();
            while ((character = reader.read()) != -1) {
                sb.append((char) character);
            }
            readText = sb.toString();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DecompressedTextFileWriterTest.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DecompressedTextFileWriterTest.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        assertEquals(text, readText);
    }

}
