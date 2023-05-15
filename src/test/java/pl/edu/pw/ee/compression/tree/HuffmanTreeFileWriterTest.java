package pl.edu.pw.ee.compression.tree;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.edu.pw.ee.HuffmanTree;
import pl.edu.pw.ee.TestUtils;

public class HuffmanTreeFileWriterTest {

    private File testFile;

    @Before
    public void setUp() throws IOException {
        testFile = new File("writing huffman tree.txt");

        TestUtils.validateIfFileExistsForTesting(testFile);

        testFile.createNewFile();
    }

    @After
    public void tearDown() {
        testFile.delete();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrite_ThrowException_ArgumentHuffmanTreeIsNull() {
        // given
        HuffmanTree tree = null;

        // when
        new HuffmanTreeFileWriter(tree);

        // then
        assert false;
    }

    @Test
    public void testWrite_CorrectlyWriteToFile_TreeContains1Character() throws Exception {
        HuffmanTree tree = new HuffmanTree(5, 'a');
        HuffmanTreeFileWriter instance = new HuffmanTreeFileWriter(tree);

        // when
        instance.write(testFile);

        // then
        HuffmanTree readTree;
        try ( ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(testFile))) {
            readTree = (HuffmanTree) inputStream.readObject();
        }
        assertEquals(tree, readTree);
    }

    @Test
    public void testWrite_CorrectlyWriteToFile_TreeIsMultilevel() throws Exception {
        HuffmanTree node = new HuffmanTree(30);
        node.setRightChild(new HuffmanTree(16, 'e'));
        HuffmanTree leftChild = new HuffmanTree(14);
        node.setLeftChild(leftChild);
        leftChild.setLeftChild(new HuffmanTree(5, 'a'));
        leftChild.setRightChild(new HuffmanTree(9, 'b'));

        HuffmanTreeFileWriter instance = new HuffmanTreeFileWriter(node);

        // when
        instance.write(testFile);

        // then
        HuffmanTree readTree;
        try ( ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(testFile))) {
            readTree = (HuffmanTree) inputStream.readObject();
        }
        assertEquals(node, readTree);
    }

    @Test
    public void testWrite_CorrectlyWriteToFile_TreeContainsCharacterWhiteCharacters() throws Exception {
        HuffmanTree node = new HuffmanTree(30);
        node.setRightChild(new HuffmanTree(16, '\t'));
        HuffmanTree leftChild = new HuffmanTree(14);
        node.setLeftChild(leftChild);
        leftChild.setLeftChild(new HuffmanTree(5, 'a'));
        leftChild.setRightChild(new HuffmanTree(9, '\n'));

        HuffmanTreeFileWriter instance = new HuffmanTreeFileWriter(node);

        // when
        instance.write(testFile);

        // then
        HuffmanTree readTree;
        try ( ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(testFile))) {
            readTree = (HuffmanTree) inputStream.readObject();
        }
        assertEquals(node, readTree);
    }

}
