package pl.edu.pw.ee.files_manipulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class FilesToWorkFinderTest {

    private String pathToTestDir;

    @Before
    public void setUp() {
        String absolutePathOfWorkingDir
                = Paths.get("").toAbsolutePath().toString();

        pathToTestDir = absolutePathOfWorkingDir
                + "/tests data/Files manipulation/FilesToWorkFinder";
    }

    @Test(expected = IllegalArgumentException.class)
    public void findFilesToCompress_ThrowException_ArgumentIsNull() {
        // given
        String pathToRootDir = null;
        FilesToWorkFinder instance = new FilesToWorkFinder();

        // when
        try {
            instance.findFilesToCompress(pathToRootDir);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilesToWorkFinderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void findFilesToCompress_ThrowException_DirectoryFromArgumentDoesntExist() {
        // given
        String pathToRootDir = pathToTestDir + "/findFilesToCompress/fafD";
        FilesToWorkFinder instance = new FilesToWorkFinder();

        // when
        try {
            instance.findFilesToCompress(pathToRootDir);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilesToWorkFinderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // then
        assert false;
    }

    @Test
    public void findFilesToCompress_WorkCorrectly_ThereIsNoFileToCompressInDirectory() {
        // given
        String pathToRootDir = pathToTestDir + "/findFilesToCompress/0 files";
        File rootDir = new File(pathToRootDir);
        rootDir.mkdir();
        FilesToWorkFinder instance = new FilesToWorkFinder();

        // when
        List<File> foundFilesToCompress = null;
        try {
            foundFilesToCompress = instance.findFilesToCompress(pathToRootDir);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilesToWorkFinderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        int resultNOfFilesToCompress = foundFilesToCompress.size();

        // then
        int expectedNOfFilesToCompress = 0;
        assertEquals(expectedNOfFilesToCompress, resultNOfFilesToCompress);
    }

    @Test
    public void findFilesToCompress_WorkCorrectly_ThereIs1FileToCompressInDirectory() {
        // given
        String pathToRootDir = pathToTestDir + "/findFilesToCompress/1 file";
        FilesToWorkFinder instance = new FilesToWorkFinder();

        // when
        List<File> foundFilesToCompress = null;
        try {
            foundFilesToCompress = instance.findFilesToCompress(pathToRootDir);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilesToWorkFinderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        int resultNOfFilesToCompress = foundFilesToCompress.size();

        // then
        int expectedNOfFilesToCompress = 1;
        assertEquals(expectedNOfFilesToCompress, resultNOfFilesToCompress);
    }

    @Test
    public void findFilesToCompress_WorkCorrectly_ThereIs1EmptyFileToCompressInDirectory() {
        // given
        String pathToRootDir = pathToTestDir + "/findFilesToCompress/1 empty file";
        FilesToWorkFinder instance = new FilesToWorkFinder();

        // when
        List<File> foundFilesToCompress = null;
        try {
            foundFilesToCompress = instance.findFilesToCompress(pathToRootDir);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilesToWorkFinderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        int resultNOfFilesToCompress = foundFilesToCompress.size();

        // then
        int expectedNOfFilesToCompress = 1;
        assertEquals(expectedNOfFilesToCompress, resultNOfFilesToCompress);
    }

    @Test
    public void findFilesToCompress_WorkCorrectly_ThereAre1FileToCompressAnd2FilesNotToInDirectory() {
        // given
        String pathToRootDir = pathToTestDir
                + "/findFilesToCompress/1 file and 2 ghosts";
        FilesToWorkFinder instance = new FilesToWorkFinder();

        // when
        List<File> foundFilesToCompress = null;
        try {
            foundFilesToCompress = instance.findFilesToCompress(pathToRootDir);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilesToWorkFinderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        int resultNOfFilesToCompress = foundFilesToCompress.size();

        // then
        int expectedNOfFilesToCompress = 1;
        assertEquals(expectedNOfFilesToCompress, resultNOfFilesToCompress);
    }

    @Test
    public void findFilesToCompress_WorkCorrectly_ThereAre4FilesToCompressInDirectory() {
        // given
        String pathToRootDir = pathToTestDir + "/findFilesToCompress/4 files";
        FilesToWorkFinder instance = new FilesToWorkFinder();

        // when
        List<File> foundFilesToCompress = null;
        try {
            foundFilesToCompress = instance.findFilesToCompress(pathToRootDir);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilesToWorkFinderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        int resultNOfFilesToCompress = foundFilesToCompress.size();

        // then
        int expectedNOfFilesToCompress = 4;
        assertEquals(expectedNOfFilesToCompress, resultNOfFilesToCompress);
    }

    @Test
    public void findFilesToCompress_WorkCorrectly_ThereAre3FileToCompressAnd5NotToInDirectory() {
        // given
        String pathToRootDir = pathToTestDir
                + "/findFilesToCompress/3 files and 5 ghosts";
        FilesToWorkFinder instance = new FilesToWorkFinder();

        // when
        List<File> foundFilesToCompress = null;
        try {
            foundFilesToCompress = instance.findFilesToCompress(pathToRootDir);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilesToWorkFinderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        int resultNOfFilesToCompress = foundFilesToCompress.size();

        // then
        int expectedNOfFilesToCompress = 3;
        assertEquals(expectedNOfFilesToCompress, resultNOfFilesToCompress);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findFilesToDecompress_ThrowException_ArgumentIsNull() {
        // given
        String pathToRootDir = null;
        FilesToWorkFinder instance = new FilesToWorkFinder();

        // when
        try {
            instance.findFilesToDecompress(pathToRootDir);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilesToWorkFinderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void findFilesToDecompress_ThrowException_DirectoryFromArgumentDoesntExist() {
        // given
        String pathToRootDir = pathToTestDir + "/findFilesToDecompress_String/fafD";
        FilesToWorkFinder instance = new FilesToWorkFinder();

        // when
        try {
            instance.findFilesToDecompress(pathToRootDir);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilesToWorkFinderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // then
        assert false;
    }

    @Test
    public void findFilesToDecompress_String_WorkCorrectly_ThereAreNoPairsOfFilesToBeDecompressedInDirectory() {
        // given
        String pathToRootDir = pathToTestDir
                + "/findFilesToDecompress_String/0 pairs";
        File rootDir = new File(pathToRootDir);
        rootDir.mkdir();
        FilesToWorkFinder instance = new FilesToWorkFinder();

        // when
        List<PairOfFilesToBeDecompressed> foundPairsOfFilesToDecompress = null;
        try {
            foundPairsOfFilesToDecompress = instance.findFilesToDecompress(
                    pathToRootDir);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilesToWorkFinderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        int resultNOfFoundPairs = foundPairsOfFilesToDecompress.size();

        // then
        int expectedNOfFoundPairs = 0;
        assertEquals(expectedNOfFoundPairs, resultNOfFoundPairs);
    }

    @Test
    public void findFilesToDecompress_String_WorkCorrectly_ThereIs1PairsOfFilesToBeDecompressedInDirectory() {
        // given
        String pathToRootDir = pathToTestDir
                + "/findFilesToDecompress_String/1 pair";
        FilesToWorkFinder instance = new FilesToWorkFinder();

        // when
        List<PairOfFilesToBeDecompressed> foundPairsOfFilesToDecompress = null;
        try {
            foundPairsOfFilesToDecompress = instance.findFilesToDecompress(
                    pathToRootDir);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilesToWorkFinderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        int resultNOfFoundPairs = foundPairsOfFilesToDecompress.size();

        // then
        int expectedNOfFoundPairs = 1;
        assertEquals(expectedNOfFoundPairs, resultNOfFoundPairs);
    }

    @Test
    public void findFilesToDecompress_String_WorkCorrectly_ThereAre3PairsOfFilesToBeDecompressedAnd3FilesNotToInDirectory() {
        // given
        String pathToRootDir = pathToTestDir
                + "/findFilesToDecompress_String/3 pairs and 3 ghosts";
        FilesToWorkFinder instance = new FilesToWorkFinder();

        // when
        List<PairOfFilesToBeDecompressed> foundPairsOfFilesToDecompress = null;
        try {
            foundPairsOfFilesToDecompress = instance.findFilesToDecompress(
                    pathToRootDir);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilesToWorkFinderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        int resultNOfFoundPairs = foundPairsOfFilesToDecompress.size();

        // then
        int expectedNOfFoundPairs = 3;
        assertEquals(expectedNOfFoundPairs, resultNOfFoundPairs);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findFilesToDecompress_File_ThrowException_ArgumentIsNull() {
        // given
        FilesToWorkFinder instance = new FilesToWorkFinder();
        File sourceFile = null;

        // when
        instance.findFilesToDecompress(sourceFile);

        // then
        assert false;
    }

    @Test
    public void findFilesToDecompress_File_FindCorrectTreeFile_ThereExistFilesForDecompressingFile() {
        // given
        FilesToWorkFinder instance = new FilesToWorkFinder();
        String pathToSourceDir = pathToTestDir
                + "/findFilesToDecompress_File/exist";
        String pathToSourceFile = pathToSourceDir + "/poi.txt";
        File sourceFile = new File(pathToSourceFile);

        // when
        PairOfFilesToBeDecompressed foundPairOfFilesToDecompress
                = instance.findFilesToDecompress(sourceFile);
        File resultTreeFile = foundPairOfFilesToDecompress.getTreeFile();

        // then
        File expectedTreeFile = new File(pathToSourceDir + "/poi-tree.txt");

        assertEquals(expectedTreeFile, resultTreeFile);
    }

    @Test
    public void findFilesToDecompress_File_FindCorrectCompressedTextFile_ThereExistFilesForDecompressingFile() {
        // given
        FilesToWorkFinder instance = new FilesToWorkFinder();
        String pathToSourceDir = pathToTestDir
                + "/findFilesToDecompress_File/exist";
        String pathToSourceFile = pathToSourceDir + "/poi.txt";
        File sourceFile = new File(pathToSourceFile);

        // when
        PairOfFilesToBeDecompressed foundPairOfFilesToDecompress
                = instance.findFilesToDecompress(sourceFile);
        File resultCompressedTextFile
                = foundPairOfFilesToDecompress.getCompressedTextFile();

        // then
        File expectedCompressedTextFile = new File(pathToSourceDir
                + "/poi-compressed.txt");

        assertEquals(expectedCompressedTextFile, resultCompressedTextFile);
    }

    @Test
    public void findFilesToDecompress_File_WorkCorrectly_ThereDontExistTreeFile() {
        // given
        FilesToWorkFinder instance = new FilesToWorkFinder();
        String pathToSourceDir = pathToTestDir
                + "/findFilesToDecompress_File/dont exist tree";
        String pathToSourceFile = pathToSourceDir + "/poi.txt";
        File sourceFile = new File(pathToSourceFile);

        // when
        PairOfFilesToBeDecompressed resultPair
                = instance.findFilesToDecompress(sourceFile);

        // then
        PairOfFilesToBeDecompressed expPair = null;
        assertEquals(expPair, resultPair);
    }

    @Test
    public void findFilesToDecompress_File_WorkCorrectly_ThereDontExistCompressedTextFile() {
        // given
        FilesToWorkFinder instance = new FilesToWorkFinder();
        String pathToSourceDir = pathToTestDir
                + "/findFilesToDecompress_File/dont exist compressed text";
        String pathToSourceFile = pathToSourceDir + "/poi.txt";
        File sourceFile = new File(pathToSourceFile);

        // when
        PairOfFilesToBeDecompressed resultPair
                = instance.findFilesToDecompress(sourceFile);

        // then
        PairOfFilesToBeDecompressed expPair = null;
        assertEquals(expPair, resultPair);
    }
}
