package pl.edu.pw.ee.compression;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class FileCompressorTest {

    private String pathToTestDir;

    @Before
    public void setUp() {
        String absolutePathOfWorkingDir
                = Paths.get("").toAbsolutePath().toString();

        pathToTestDir = absolutePathOfWorkingDir
                + "/tests data/Compression/FileCompressor";
    }

    @Test(expected = IllegalArgumentException.class)
    public void compress_ThrowException_ArgumentIsNull() {
        // given
        String pathToRootDir = null;
        FileCompressor instance = new FileCompressor();

        // when
        try {
            instance.compress(pathToRootDir);
        } catch (IOException ex) {
            Logger.getLogger(FileCompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // then
        assert false;
    }

    @Test
    public void compress_DontCreateCompressedFiles_FileToCompressIsEmpty() {
        // given
        String pathToRootDir = pathToTestDir + "/Empty file";
        FileCompressor instance = new FileCompressor();

        // when
        try {
            instance.compress(pathToRootDir);
        } catch (IOException ex) {
            Logger.getLogger(FileCompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // then
        File treeFile = new File(pathToRootDir + "/empty-tree.txt");
        File compressedTextFile = new File(pathToRootDir
                + "/empty-compressed.txt");
        boolean fail = checkIfAnyFileExistAndDeleteIfTheyDo(
                treeFile, compressedTextFile);

        if (fail) {
            assert false;
        } else {
            assert true;
        }
    }

    @Test
    public void compress_CreateCompressedFiles_FileToCompressContainsOneLetter() {
        // given
        String pathToRootDir = pathToTestDir + "/One letter text";
        FileCompressor instance = new FileCompressor();

        // when
        try {
            instance.compress(pathToRootDir);
        } catch (IOException ex) {
            Logger.getLogger(FileCompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // then
        File treeFile = new File(pathToRootDir + "/text-tree.txt");
        File compressedTextFile = new File(pathToRootDir
                + "/text-compressed.txt");
        boolean fail = !checkIfAnyFileExistAndDeleteIfTheyDo(
                treeFile, compressedTextFile);

        if (fail) {
            assert false;
        } else {
            assert true;
        }
    }

    @Test
    public void compress_CreateCompressedFiles_FileToCompressContains2Lines() {
        // given
        String pathToRootDir = pathToTestDir + "/Simple text - 2 lines";
        FileCompressor instance = new FileCompressor();

        // when
        try {
            instance.compress(pathToRootDir);
        } catch (IOException ex) {
            Logger.getLogger(FileCompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // then
        File treeFile = new File(pathToRootDir
                + "/Simple text - 2 lines-tree.txt");
        File compressedTextFile = new File(pathToRootDir
                + "/Simple text - 2 lines-compressed.txt");
        boolean fail = !checkIfAnyFileExistAndDeleteIfTheyDo(
                treeFile, compressedTextFile);

        if (fail) {
            assert false;
        } else {
            assert true;
        }
    }

    @Test
    public void compress_CreateCompressedFiles_DirectoryContains3FilesToCompress() {
        // given
        String pathToRootDir = pathToTestDir + "/3 files";
        FileCompressor instance = new FileCompressor();

        // when
        try {
            instance.compress(pathToRootDir);
        } catch (IOException ex) {
            Logger.getLogger(FileCompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // then
        File treeFile1 = new File(pathToRootDir + "/Ala ma kota-tree.txt");
        File compressedTextFile1 = new File(pathToRootDir
                + "/Ala ma kota-compressed.txt");
        boolean fail1 = !checkIfAnyFileExistAndDeleteIfTheyDo(
                treeFile1, compressedTextFile1);

        File treeFile2 = new File(pathToRootDir + "/Fire and Ice-tree.txt");
        File compressedTextFile2 = new File(pathToRootDir
                + "/Fire and Ice-compressed.txt");
        boolean fail2 = !checkIfAnyFileExistAndDeleteIfTheyDo(
                treeFile2, compressedTextFile2);

        File treeFile3 = new File(pathToRootDir + "/Hobbit quotes-tree.txt");
        File compressedTextFile3 = new File(pathToRootDir
                + "/Hobbit quotes-compressed.txt");
        boolean fail3 = !checkIfAnyFileExistAndDeleteIfTheyDo(
                treeFile3, compressedTextFile3);

        boolean fail = fail1 & fail2 & fail3;
        if (fail) {
            assert false;
        } else {
            assert true;
        }
    }

    @Test
    public void getSumOfBitsAfterCompression_ReturnCorrectNumber_WordToCompressIsNiemanie() {
        // given
        String pathToRootDir = pathToTestDir + "/niemanie - word";
        FileCompressor instance = new FileCompressor();

        // when
        int result = 0;
        try {
            instance.compress(pathToRootDir);
            result = instance.getSumOfBitsAfterCompression();
        } catch (IOException ex) {
            Logger.getLogger(FileCompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // then
        int expResult = 24;
        File treeFile = new File(pathToRootDir
                + "/niemanie-tree.txt");
        File compressedTextFile = new File(pathToRootDir
                + "/niemanie-compressed.txt");
        checkIfAnyFileExistAndDeleteIfTheyDo(treeFile, compressedTextFile);
        assertEquals(expResult, result);
    }

    private boolean checkIfAnyFileExistAndDeleteIfTheyDo(File file1, File file2) {
        boolean doExist = false;

        if (file1.exists()) {
            doExist = true;
            file1.delete();
        }

        if (file2.exists()) {
            doExist = true;
            file2.delete();
        }

        return doExist;
    }
}
