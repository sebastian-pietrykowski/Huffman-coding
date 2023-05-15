package pl.edu.pw.ee.compression.tree;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.edu.pw.ee.HuffmanTree;

public class UnitedHuffmanTreeBuilderTest {

    private String pathToTestDir;

    @Before
    public void setUp() {
        String absolutePathOfWorkingDir
                = Paths.get("").toAbsolutePath().toString();

        pathToTestDir = absolutePathOfWorkingDir
                + "/tests data/Compression/UnitedHuffmanTreeBuilder";
    }

    @Test
    public void build_WorkCorrectly_FileToReadIsEmpty() {
        // given
        String fileToReadPath = pathToTestDir + "/Empty file.txt";
        File fileToRead = new File(fileToReadPath);
        UnitedHuffmanTreeBuilder instance = new UnitedHuffmanTreeBuilder(
                fileToRead);

        // when
        HuffmanTree result = null;
        try {
            result = instance.build();
        } catch (IOException ex) {
            Logger.getLogger(UnitedHuffmanTreeBuilderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // then
        HuffmanTree expResult = null;
        assertEquals(expResult, result);
    }

    @Test
    public void build_WorkCorrectly_FileToReadContains1Character() {
        // given
        String fileToReadPath = pathToTestDir + "/One letter text.txt";
        File fileToRead = new File(fileToReadPath);
        UnitedHuffmanTreeBuilder instance = new UnitedHuffmanTreeBuilder(
                fileToRead);

        // when
        HuffmanTree result = null;
        try {
            result = instance.build();
        } catch (IOException ex) {
            Logger.getLogger(UnitedHuffmanTreeBuilderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // then
        HuffmanTree expResult = new HuffmanTree(1, null, null,
                new HuffmanTree(1, 'd'));
        assertEquals(expResult, result);
    }

    @Test
    public void build_WorkCorrectly_FileToReadContainManyCharacters() {
        // given
        String fileToReadPath = pathToTestDir
                + "/Simple text - many characters.txt";
        File fileToRead = new File(fileToReadPath);
        UnitedHuffmanTreeBuilder instance = new UnitedHuffmanTreeBuilder(
                fileToRead);

        // when
        HuffmanTree result = null;
        try {
            result = instance.build();
        } catch (IOException ex) {
            Logger.getLogger(UnitedHuffmanTreeBuilderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // then
        HuffmanTree expResult = buildExpTreeForFileWith2Lines();
        assertEquals(expResult, result);
    }

    // tree containing letter, digit and new line characters with proper prefix code
    // ',' - > 000, ' ' -> 001, 'r' -> 01, 'b' - > 10, '1' -> 11
    private HuffmanTree buildExpTreeForFileWith2Lines() {
        HuffmanTree root = new HuffmanTree(15);

        HuffmanTree lChild = new HuffmanTree(6);
        root.setLeftChild(lChild);
        HuffmanTree llChild = new HuffmanTree(3);
        lChild.setLeftChild(llChild);
        llChild.setLeftChild(new HuffmanTree(1, ','));
        llChild.setRightChild(new HuffmanTree(2, ' '));

        lChild.setRightChild(new HuffmanTree(3, 'r'));

        HuffmanTree rChild = new HuffmanTree(9);
        root.setRightChild(rChild);
        rChild.setLeftChild(new HuffmanTree(4, 'b'));
        rChild.setRightChild(new HuffmanTree(5, '1'));

        return root;
    }

}
