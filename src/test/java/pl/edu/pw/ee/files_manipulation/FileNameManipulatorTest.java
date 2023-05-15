package pl.edu.pw.ee.files_manipulation;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.edu.pw.ee.TestUtils;

public class FileNameManipulatorTest {

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
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeExtension_ThrowException_ArgumentIsNull() {
        // given
        String fileName = null;
        FileNameManipulator instance = new FileNameManipulator();

        // when
        instance.removeExtension(fileName);

        // then
        assert false;
    }

    @Test
    public void removeExtension_WorkCorrectly_FileHasStrangeName() {
        // given
        String fileName = testFile.getName();
        FileNameManipulator instance = new FileNameManipulator();

        // when
        String result = instance.removeExtension(fileName);

        // then
        String expResult = "test.file_.test.file-qgaq+2a";
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getExtension_ThrowException_ArgumentIsNull() {
        // given
        String fileName = null;
        FileNameManipulator instance = new FileNameManipulator();

        // when
        instance.getExtension(fileName);

        // then
        assert false;
    }

    @Test
    public void getExtension_WorkCorrectly_FileHasStrangeName() {
        // given
        String fileName = testFile.getName();
        FileNameManipulator instance = new FileNameManipulator();

        // when
        String result = instance.getExtension(fileName);

        // then
        String expResult = ".doc";
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void endsWith_ThrowException_FirstArgumentIsNull() {
        // given
        String fileName = "";
        String postfix = null;
        FileNameManipulator instance = new FileNameManipulator();

        // when
        instance.endsWith(fileName, postfix);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void endsWith_ThrowException_SecondArgumentIsNull() {
        // given
        String fileName = null;
        String postfix = "afad";
        FileNameManipulator instance = new FileNameManipulator();

        // when
        instance.endsWith(fileName, postfix);

        // then
        assert false;
    }

    @Test
    public void endsWith_ReturnTrue_FileNameEndsWithSuchAnExtension() {
        // given
        String fileName = testFile.getName();
        String postfix = "+2a.doc";
        FileNameManipulator instance = new FileNameManipulator();

        // when
        boolean result = instance.endsWith(fileName, postfix);

        // then
        boolean expResult = true;
        assertEquals(expResult, result);
    }

    @Test
    public void endsWith_ReturnFalse_FileNameDoesntEndsWithSuchAnExtension() {
        // given
        String fileName = testFile.getName();
        String postfix = ".dac";
        FileNameManipulator instance = new FileNameManipulator();

        // when
        boolean result = instance.endsWith(fileName, postfix);

        // then
        boolean expResult = false;
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removePostfix_ThrowException_FirstArgumentIsNull() {
        // given
        String fileName = null;
        String postfix = "qgaq+2a.doc";
        FileNameManipulator instance = new FileNameManipulator();

        // when
        instance.removePostfix(fileName, postfix);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void removePostfix_ThrowException_SecondArgumentIsNull() {
        // given
        String fileName = testFile.getName();
        String postfix = null;
        FileNameManipulator instance = new FileNameManipulator();

        // when
        instance.removePostfix(fileName, postfix);

        // then
        assert false;
    }

    @Test
    public void removePostfix_ReturnCorrectResult_FileNameEndsWithSuchAPostfix() {
        // given
        String fileName = testFile.getName();
        String postfix = "qgaq+2a.doc";
        FileNameManipulator instance = new FileNameManipulator();

        // when
        String result = instance.removePostfix(fileName, postfix);

        // then
        String expResult = "test.file_.test.file-";
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removePostfix_ThrowException_FileNameDoesntEndWithSuchAPostfix() {
        // given
        String fileName = testFile.getName();
        String postfix = "docal";
        FileNameManipulator instance = new FileNameManipulator();

        // when
        instance.removePostfix(fileName, postfix);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void removePostfixBeforeExtension_ThrowException_FirstArgumentIsNull() {
        // given
        File fileToTest = null;
        String postfix = "+2a";
        FileNameManipulator instance = new FileNameManipulator();

        // when
        File result = instance.removePostfixBeforeExtension(fileToTest, postfix);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void removePostfixBeforeExtension_ThrowException_SecondArgumentIsNull() {
        // given
        String postfix = null;
        FileNameManipulator instance = new FileNameManipulator();

        // when
        File result = instance.removePostfixBeforeExtension(testFile, postfix);

        // then
        assert false;
    }

    @Test
    public void removePostfixBeforeExtension_WorkCorrectly_FileEndsWithSuchAPostfixBeforeExtension() {
        // given
        String postfix = "+2a";
        FileNameManipulator instance = new FileNameManipulator();

        // when
        File result = instance.removePostfixBeforeExtension(testFile, postfix);

        // then
        File expResult = new File("test.file_.test.file-qgaq.doc");
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removePostfixBeforeExtension_ThrowException_FileDoesntEndsWithSuchAPostfixBeforeExtension() {
        // given
        String postfix = "+2afaws";
        FileNameManipulator instance = new FileNameManipulator();

        // when
        instance.removePostfixBeforeExtension(testFile, postfix);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void addPostfixBeforeExtension_ThrowException_FirstArgumentIsNull() {
        // given
        File fileToTest = null;
        String postfix = "+./ga.de";
        FileNameManipulator instance = new FileNameManipulator();

        // when
        instance.addPostfixBeforeExtension(fileToTest, postfix);

        // then
        assert false;
    }

    @Test
    public void addPostfixBeforeExtension_WorkCorrectly_FileHasStrangeName() {
        // given
        String postfix = "+./ga.de";
        FileNameManipulator instance = new FileNameManipulator();

        // when
        File result = instance.addPostfixBeforeExtension(testFile, postfix);

        // then
        File expResult = new File("test.file_.test.file-qgaq+2a+./ga.de.doc");
        assertEquals(expResult, result);
    }
}
