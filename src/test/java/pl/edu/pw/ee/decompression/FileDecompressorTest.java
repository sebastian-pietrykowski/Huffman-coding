package pl.edu.pw.ee.decompression;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.edu.pw.ee.compression.FileCompressor;
import pl.edu.pw.ee.compression.FileCompressorTest;

public class FileDecompressorTest {

    private String pathToTestDir;

    @Before
    public void setUp() {
        String absolutePathOfWorkingDir
                = Paths.get("").toAbsolutePath().toString();

        pathToTestDir = absolutePathOfWorkingDir
                + "/tests data/Decompression/FileDecompressor";
    }

    @Test(expected = IllegalArgumentException.class)
    public void decompress_ThrowException_ArgumentIsNull() {
        // given
        String pathToRootDir = null;
        FileDecompressor instance = new FileDecompressor();

        // when
        try {
            instance.decompress(pathToRootDir);
        } catch (IOException ex) {
            Logger.getLogger(FileCompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void decompress_ThrowException_FileDoesntExist() {
        // given
        String pathToRootDir = "4waf.4awrrgfe";
        FileDecompressor instance = new FileDecompressor();

        // when
        try {
            instance.decompress(pathToRootDir);
        } catch (IOException ex) {
            Logger.getLogger(FileCompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void decompress_ThrowException_RootDirectoryIsNotDirectory() {
        // given
        String pathToRootDir = pathToTestDir + "/Empty tree/org-tree.txt";
        File rootDir = new File(pathToRootDir);
        if (!rootDir.exists()) {
            throw new IllegalStateException("File doesn't exist.");
        }
        FileDecompressor instance = new FileDecompressor();

        // when
        try {
            instance.decompress(pathToRootDir);
        } catch (IOException ex) {
            Logger.getLogger(FileCompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // then
        assert false;
    }

    @Test
    public void decompress_DontCreateDecompressedFile_DirectoryIsEmpty() {
        // given
        String pathToRootDir = pathToTestDir + "/Empty directory";
        File rootDir = new File(pathToRootDir);
        rootDir.mkdir();
        FileDecompressor instance = new FileDecompressor();

        // when
        try {
            instance.decompress(pathToRootDir);
        } catch (IOException ex) {
            Logger.getLogger(FileCompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
            assert false;
        }

        // then
        File[] filesInRootDir = rootDir.listFiles();

        for (File file : filesInRootDir) {
            file.delete();
            assert false;
        }

        assert true;
    }

    @Test
    public void decompress_DontCreateDecompressedFile_FileWithCompressedTextDoesntExist() {
        // given
        String pathToRootDir = pathToTestDir + "/Only tree file";
        FileDecompressor instance = new FileDecompressor();

        // when
        try {
            instance.decompress(pathToRootDir);
        } catch (IOException ex) {
            Logger.getLogger(FileCompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
            assert false;
        }

        // then
        File decompressedFile = new File(pathToRootDir
                + "/abc-decompressed.txt");
        if (decompressedFile.exists()) {
            decompressedFile.delete();
            assert false;
        }

        assert true;
    }

    @Test
    public void decompress_DontCreateDecompressedFile_FileWithTreeDoesntExist() {
        // given
        String pathToRootDir = pathToTestDir + "/Only compressed file";
        FileDecompressor instance = new FileDecompressor();

        // when
        try {
            instance.decompress(pathToRootDir);
        } catch (IOException ex) {
            Logger.getLogger(FileCompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
            assert false;
        }

        // then
        File decompressedFile = new File(pathToRootDir
                + "/abc-decompressed.txt");
        if (decompressedFile.exists()) {
            decompressedFile.delete();
            assert false;
        }

        assert true;
    }

    @Test
    public void decompress_DontCreateDecompressedFile_FileWithTreeIsEmpty() {
        // given
        String pathToRootDir = pathToTestDir + "/Empty tree";
        FileDecompressor instance = new FileDecompressor();

        // when
        try {
            instance.decompress(pathToRootDir);
        } catch (IOException ex) {
            Logger.getLogger(FileCompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
            assert false;
        }

        // then
        File decompressedFile = new File(pathToRootDir
                + "/org-decompressed.txt");
        if (decompressedFile.exists()) {
            decompressedFile.delete();
            assert false;
        }

        assert true;
    }

    @Test
    public void decompress_DontCreateDecompressedFile_FileWithCompressedTextIsEmpty() {
        // given
        String pathToRootDir = pathToTestDir + "/Empty compressedText";
        FileDecompressor instance = new FileDecompressor();

        // when
        try {
            instance.decompress(pathToRootDir);
        } catch (IOException ex) {
            Logger.getLogger(FileCompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
            assert false;
        }

        // then
        File decompressedFile = new File(pathToRootDir
                + "/abc-decompressed.txt");

        if (decompressedFile.exists()) {
            decompressedFile.delete();
            assert false;
        }

        assert true;
    }

    @Test
    public void decompress_DontCreateDecompressedFile_FilesWithTreeCompressedTextAreEmpty() {
        // given
        String pathToRootDir = pathToTestDir + "/Empty files";
        FileDecompressor instance = new FileDecompressor();

        // when
        try {
            instance.decompress(pathToRootDir);
        } catch (IOException ex) {
            Logger.getLogger(FileCompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
            assert false;
        }

        // then
        File decompressedFile = new File(pathToRootDir
                + "/Empty tree/jkl-decompressed.txt");
        if (decompressedFile.exists()) {
            decompressedFile.delete();
            assert false;
        }

        assert true;
    }

    @Test
    public void decompress_CreateDecompressedFile_DecompresssingOneLineText() {
        // given
        String pathToRootDir = pathToTestDir + "/Niemanie - word";
        FileCompressor compressor = new FileCompressor();
        try {
            compressor.compress(pathToRootDir);
        } catch (IOException ex) {
            Logger.getLogger(FileDecompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        FileDecompressor instance = new FileDecompressor();

        // when
        try {
            instance.decompress(pathToRootDir);
        } catch (IOException ex) {
            Logger.getLogger(FileCompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
            assert false;
        }

        // then
        File decompressedFile = new File(pathToRootDir
                + "/niemanie-decompressed.txt");
        System.out.println(decompressedFile);
        if (decompressedFile.exists()) {
            decompressedFile.delete();
            assert true;
        } else {
            assert false;
        }
    }

    @Test
    public void decompress_CreateDecompressedFile_DecompresssingMultiLineText() {
        // given
        String pathToRootDir = pathToTestDir + "/Niemanie - chorus";
        FileCompressor compressor = new FileCompressor();
        try {
            compressor.compress(pathToRootDir);
        } catch (IOException ex) {
            Logger.getLogger(FileDecompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        FileDecompressor instance = new FileDecompressor();

        // when
        try {
            instance.decompress(pathToRootDir);

        } catch (IOException ex) {
            Logger.getLogger(FileCompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // then
        File decompressedFile = new File(pathToRootDir
                + "/niemanie_refren-decompressed.txt");
        if (decompressedFile.exists()) {
            decompressedFile.delete();
            assert true;
        } else {
            assert false;
        }
    }

    @Test
    public void getSumOfCharsAfterDecompression_WorkCorrectly_DecompresssingOneLineText() {
        // given
        String pathToRootDir = pathToTestDir + "/Niemanie - word";
        FileCompressor compressor = new FileCompressor();
        try {
            compressor.compress(pathToRootDir);
        } catch (IOException ex) {
            Logger.getLogger(FileDecompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        FileDecompressor instance = new FileDecompressor();

        // when
        try {
            instance.decompress(pathToRootDir);
        } catch (IOException ex) {
            Logger.getLogger(FileCompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
            assert false;
        }
        int actual = instance.getSumOfCharsAfterDecompression();

        // then
        int expected = "NIEMANIE".length();

        File decompressedFile = new File(pathToRootDir
                + "/niemanie-decompressed.txt");
        if (decompressedFile.exists()) {
            decompressedFile.delete();
        }

        assertEquals(expected, actual);
    }

    @Test
    public void getSumOfCharsAfterDecompression_WorkCorrectly_DecompresssingOneLineText2Times() {
        // given
        String pathToRootDir = pathToTestDir + "/Niemanie - word 2 times";
        FileCompressor compressor = new FileCompressor();
        try {
            compressor.compress(pathToRootDir);
        } catch (IOException ex) {
            Logger.getLogger(FileDecompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        FileDecompressor instance = new FileDecompressor();

        // when
        try {
            instance.decompress(pathToRootDir);
        } catch (IOException ex) {
            Logger.getLogger(FileCompressorTest.class.getName()).log(
                    Level.SEVERE, null, ex);
            assert false;
        }
        int actual = instance.getSumOfCharsAfterDecompression();

        // then
        int expected = "NIEMANIE".length() * 2;

        File decompressedFile1 = new File(pathToRootDir
                + "/niemanie-decompressed.txt");
        File decompressedFile2 = new File(pathToRootDir
                + "/niemanie2-decompressed.txt");
        if (decompressedFile1.exists()) {
            decompressedFile1.delete();
        }
        if (decompressedFile2.exists()) {
            decompressedFile2.delete();
        }

        assertEquals(expected, actual);
    }
}
