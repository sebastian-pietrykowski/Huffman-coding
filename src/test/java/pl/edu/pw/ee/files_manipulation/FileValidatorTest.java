package pl.edu.pw.ee.files_manipulation;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import pl.edu.pw.ee.TestUtils;

public class FileValidatorTest {

    private File testFile;

    @Before
    public void setUp() throws IOException {
        testFile = new File("fileForTesting.aafew");

        TestUtils.validateIfFileExistsForTesting(testFile);

        testFile.createNewFile();
    }

    @After
    public void tearDown() {
        testFile.delete();
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateIfFileIsNotNull_ThrowException_FileIsNull() {
        // given
        File file = null;
        FileValidator fileValidator = new FileValidator(file);

        // when
        fileValidator.validateIfFileIsNotNull();

        // then
        assert false;
    }

    @Test
    public void validateIfFileIsNotNull_Pass_FileIsNotNull() {
        // given
        File file = testFile;
        FileValidator fileValidator = new FileValidator(file);

        // when
        fileValidator.validateIfFileIsNotNull();

        // then
        assert true;
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateFileForWriting_ThrowException_FileIsNull() {
        // given
        File file = null;
        FileValidator fileValidator = new FileValidator(file);

        // when
        fileValidator.validateFileForWriting();

        // then
        assert false;
    }

    @Test
    public void validateFileForWriting_Pass_FileIsNotNull() {
        // given
        File file = testFile;
        FileValidator fileValidator = new FileValidator(file);

        // when
        fileValidator.validateFileForWriting();

        // then
        assert true;
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateFileForReading_ThrowException_FileIsNull() {
        // given
        File file = null;
        FileValidator fileValidator = new FileValidator(file);

        // when
        fileValidator.validateFileForReading();

        // then
        assert false;
    }

    @Test
    public void validateFileForReading_Pass_FileIsNotNull() {
        // given
        File file = testFile;
        FileValidator fileValidator = new FileValidator(file);

        // when
        fileValidator.validateFileForReading();

        // then
        assert true;
    }

    @Test
    public void validateIfFileExists_Pass_FileExists() {
        // given
        File file = testFile;
        FileValidator fileValidator = new FileValidator(file);

        // when
        fileValidator.validateIfFileExists();

        // then
        assert true;
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateIfFileIsDirectory_ThrowException_FileIsNotDirectory() {
        // given
        File file = testFile;
        FileValidator fileValidator = new FileValidator(file);

        // when
        fileValidator.validateIfFileIsDirectory();

        // then
        assert true;
    }
}
