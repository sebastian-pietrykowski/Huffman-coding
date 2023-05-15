package pl.edu.pw.ee;

import java.io.File;
import java.nio.file.Paths;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.edu.pw.ee.files_manipulation.ResultFileCreator;

public class HuffmanTest {

    private String pathToTestDir;

    @Before
    public void setUp() {
        String absolutePathOfWorkingDir
                = Paths.get("").toAbsolutePath().toString();

        pathToTestDir = absolutePathOfWorkingDir
                + "/tests data/Huffman";
    }

    @Test(expected = IllegalArgumentException.class)
    public void huffman_ThrowException_PathToRootDirIsNull() {
        // given
        String pathToRootDir = null;
        boolean compress = true;
        Huffman instance = new Huffman();

        // when
        instance.huffman(pathToRootDir, compress);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void huffman_ThrowException_PathToRootDirDoesntExist() {
        String pathToRootDir = pathToTestDir + "/fwadefes";
        boolean compress = true;
        Huffman instance = new Huffman();

        // when
        instance.huffman(pathToRootDir, compress);

        // then
        assert false;
    }

    @Test
    public void huffman_ReturnCorrectNOfBits_WordToCompressIsNiemanie() {
        // given
        String pathToRootDir = pathToTestDir + "/Niemanie - word";
        boolean compress = true;
        Huffman instance = new Huffman();

        File sourceFile = new File(pathToRootDir + "/niemanie.txt");
        ResultFileCreator creator = new ResultFileCreator(sourceFile);

        // when
        int result = instance.huffman(pathToRootDir, compress);

        // then
        int expResult = 24;
        creator.createFileForCompressedText().delete();
        creator.createFileForTree().delete();

        assertEquals(expResult, result);
    }

    @Test
    public void huffman_ReturnCorrectNOfChars_WordToDecompressIsNiemanie() {
        // given
        String pathToRootDir = pathToTestDir + "/Niemanie - word";
        Huffman instance = new Huffman();
        instance.huffman(pathToRootDir, true);

        File sourceFile = new File(pathToRootDir + "/niemanie.txt");
        ResultFileCreator creator = new ResultFileCreator(sourceFile);

        // when
        int result = instance.huffman(pathToRootDir, false);

        // then
        int expResult = "NIEMANIE".length();
        creator.createFileForCompressedText().delete();
        creator.createFileForTree().delete();
        creator.createFileForDecompressedText().delete();

        assertEquals(expResult, result);
    }

    @Test
    public void huffman_CheckIfCompressedAndDecompressedTextAreTheSame_WordToCompressIsNiemanie() {
        // given
        String pathToRootDir = pathToTestDir + "/Niemanie - word";
        Huffman instance = new Huffman();
        instance.huffman(pathToRootDir, true);
        instance.huffman(pathToRootDir, false);

        File sourceFile = new File(pathToRootDir + "/niemanie.txt");
        ResultFileCreator creator = new ResultFileCreator(sourceFile);
        File decompressedTextFile = new File(pathToRootDir 
                + "/niemanie-decompressed.txt");

        // when
        boolean result = TestUtils.doFilesContainTheSameContent(
                sourceFile, decompressedTextFile);

        // then
        boolean expResult = true;
        decompressedTextFile.delete();
        creator.createFileForCompressedText().delete();
        decompressedTextFile.delete();

        assertEquals(expResult, result);
    }

    @Test
    public void huffman_ReturnCorrectNOfBits_WordToCompressIsABBCCCDDD() {
        // given
        String pathToRootDir = pathToTestDir + "/ABCD";
        boolean compress = true;
        Huffman instance = new Huffman();

        File sourceFile = new File(pathToRootDir + "/ABCD.ext");
        ResultFileCreator creator = new ResultFileCreator(sourceFile);

        // when
        int result = instance.huffman(pathToRootDir, compress);

        // then
        // A -> 000, B -> 001, C -> 01, D -> 1
        int expResult = 24;
        creator.createFileForCompressedText().delete();
        creator.createFileForTree().delete();

        assertEquals(expResult, result);
    }

    @Test
    public void huffman_ReturnCorrectNOfChars_WordToDecompressIsABBCCCDDD() {
        // given
        String pathToRootDir = pathToTestDir + "/ABCD";
        boolean compress = false;
        Huffman instance = new Huffman();
        instance.huffman(pathToRootDir, !compress);

        File sourceFile = new File(pathToRootDir + "/ABCD.ext");
        ResultFileCreator creator = new ResultFileCreator(sourceFile);

        // when
        int result = instance.huffman(pathToRootDir, compress);

        // then
        int expResult = "ABBCCCDDDD".length();
        creator.createFileForCompressedText().delete();
        creator.createFileForTree().delete();
        creator.createFileForDecompressedText().delete();

        assertEquals(expResult, result);
    }

    @Test
    public void huffman_CheckIfCompressedAndDecompressedTextAreTheSame_WordToCompressIsABBCCCDDD() {
        // given
        String pathToRootDir = pathToTestDir + "/ABCD";
        Huffman instance = new Huffman();
        instance.huffman(pathToRootDir, true);
        instance.huffman(pathToRootDir, false);

        File sourceFile = new File(pathToRootDir + "/ABCD.ext");
        ResultFileCreator creator = new ResultFileCreator(sourceFile);
        File decompressedTextFile = new File(pathToRootDir 
                + "/ABCD-decompressed.ext");

        // when
        boolean result = TestUtils.doFilesContainTheSameContent(
                sourceFile, decompressedTextFile);

        // then
        boolean expResult = true;
        decompressedTextFile.delete();
        creator.createFileForCompressedText().delete();
        decompressedTextFile.delete();

        assertEquals(expResult, result);
    }

    @Test
    public void huffman_CheckIfCompressedAndDecompressedTextAreTheSame_FileIsNiemanieChorus() {
        // given
        String pathToRootDir = pathToTestDir + "/Niemanie - chorus";
        Huffman instance = new Huffman();
        instance.huffman(pathToRootDir, true);
        instance.huffman(pathToRootDir, false);

        File sourceFile = new File(pathToRootDir + "/niemanie_refren.txt");
        ResultFileCreator creator = new ResultFileCreator(sourceFile);
        File decompressedTextFile = new File(pathToRootDir 
                + "/niemanie_refren-decompressed.txt");

        // when
        boolean result = TestUtils.doFilesContainTheSameContent(
                sourceFile, decompressedTextFile);

        // then
        boolean expResult = true;

        creator.createFileForCompressedText().delete();
        creator.createFileForTree().delete();
        decompressedTextFile.delete();

        assertEquals(expResult, result);
    }

    @Test
    public void huffman_CheckIfCompressedAndDecompressedTextAreTheSame_FileIsNiemanieSong() {
        // given
        String pathToRootDir = pathToTestDir + "/Niemanie - all song";
        Huffman instance = new Huffman();
        instance.huffman(pathToRootDir, true);
        instance.huffman(pathToRootDir, false);

        File sourceFile = new File(pathToRootDir + "/niemanie_calosc.txt");
        ResultFileCreator creator = new ResultFileCreator(sourceFile);
        File decompressedTextFile = new File(pathToRootDir 
                + "/niemanie_calosc-decompressed.txt");

        // when
        boolean result = TestUtils.doFilesContainTheSameContent(
                sourceFile, decompressedTextFile);

        // then
        boolean expResult = true;

        creator.createFileForCompressedText().delete();
        creator.createFileForTree().delete();
        decompressedTextFile.delete();

        assertEquals(expResult, result);
    }

    @Test
    public void huffman_CheckIfCompressedAndDecompressedTextAreTheSame_FileIsLong() {
        // given
        String pathToRootDir = pathToTestDir + "/A Christmas Carol";
        Huffman instance = new Huffman();
        instance.huffman(pathToRootDir, true);
        instance.huffman(pathToRootDir, false);

        File sourceFile = new File(pathToRootDir + "/A Christmas Carol.txt");
        ResultFileCreator creator = new ResultFileCreator(sourceFile);
        File decompressedTextFile = new File(pathToRootDir 
                + "/A Christmas Carol-decompressed.txt");

        // when
        boolean result = TestUtils.doFilesContainTheSameContent(
                sourceFile, decompressedTextFile);

        // then
        boolean expResult = true;

        creator.createFileForCompressedText().delete();
        creator.createFileForTree().delete();
        decompressedTextFile.delete();

        assertEquals(expResult, result);
    }

    @Test
    public void huffman_CheckIfCompressedAndDecompressedTextAreTheSame_ThereAre2FilesToCompressInDir() {
        // given
        String pathToRootDir = pathToTestDir + "/2 files";
        File sourceFile1 = new File(pathToRootDir + "/Ala ma kota.txt");
        ResultFileCreator creator1 = new ResultFileCreator(sourceFile1);
        File decompressedTextFile1 = new File(pathToRootDir
                + "/Ala ma kota-decompressed.txt");

        File sourceFile2 = new File(pathToRootDir + "/Hobbit quotes.txt");
        ResultFileCreator creator2 = new ResultFileCreator(sourceFile2);
        File decompressedTextFile2 = new File(pathToRootDir
                + "/Hobbit quotes-decompressed.txt");

        Huffman instance = new Huffman();
        instance.huffman(pathToRootDir, true);
        instance.huffman(pathToRootDir, false);

        boolean result1 = TestUtils.doFilesContainTheSameContent(
                sourceFile1, decompressedTextFile1);
        boolean result2 = TestUtils.doFilesContainTheSameContent(
                sourceFile2, decompressedTextFile2);
        boolean result = result1 && result2;

        // then
        boolean expResult = true;

        creator1.createFileForCompressedText().delete();
        creator1.createFileForTree().delete();
        decompressedTextFile1.delete();

        creator2.createFileForCompressedText().delete();
        creator2.createFileForTree().delete();
        decompressedTextFile2.delete();
        assertEquals(expResult, result);
    }

}
