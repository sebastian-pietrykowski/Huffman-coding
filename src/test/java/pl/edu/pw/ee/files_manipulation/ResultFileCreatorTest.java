package pl.edu.pw.ee.files_manipulation;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.edu.pw.ee.TestUtils;

public class ResultFileCreatorTest {

    private File testFile;

    @Before
    public void setUp() throws IOException {
        testFile = new File("test.file_.test.file-qgaq+2a.doc");

        TestUtils.validateIfFileExistsForTesting(testFile);

        testFile.createNewFile();
    }

    @After
    public void tearDown() {
        testFile.delete();
        new File("test.file_.test.file-qgaq+2a-tree.doc").delete();
        new File("test.file_.test.file-qgaq+2a-compressed.doc").delete();
        new File("test.file_.test.file-qgaq+2a-decompressed.doc").delete();
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_ThrowException_ArgumentIsNull() {
        // given
        File baseFile = null;

        // when
        new ResultFileCreator(baseFile);

        // then
        assert false;
    }

    @Test
    public void createFileForTree_WorkCorrectly_FileHasStrangeName() {
        // given
        ResultFileCreator instance = new ResultFileCreator(testFile);

        // when
        File result = instance.createFileForTree();

        // then
        File expResult = new File("test.file_.test.file-qgaq+2a-tree.doc");
        assertEquals(expResult, result);
    }

    @Test
    public void createFileForCompressedText_WorkCorrectly_FileHasStrangeName() {
        // given
        ResultFileCreator instance = new ResultFileCreator(testFile);

        // when
        File result = instance.createFileForCompressedText();

        // then
        File expResult = new File("test.file_.test.file-qgaq+2a-compressed.doc");
        assertEquals(expResult, result);
    }

    @Test
    public void createFileForDecompressedText_WorkCorrectly_FileHasStrangeName() {
        // given
        ResultFileCreator instance = new ResultFileCreator(testFile);

        // when
        File result = instance.createFileForDecompressedText();

        // then
        File expResult = new File("test.file_.test.file-qgaq+2a-decompressed.doc");
        assertEquals(expResult, result);
    }

}
