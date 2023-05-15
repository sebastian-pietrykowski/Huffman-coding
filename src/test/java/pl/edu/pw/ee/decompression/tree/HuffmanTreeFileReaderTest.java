package pl.edu.pw.ee.decompression.tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.edu.pw.ee.HuffmanTree;
import pl.edu.pw.ee.TestUtils;

public class HuffmanTreeFileReaderTest {

    private File testFile;

    @Before
    public void setUp() throws IOException {
        testFile = new File("reading tree.alk");

        TestUtils.validateIfFileExistsForTesting(testFile);

        testFile.createNewFile();
    }

    @After
    public void tearDown() {
        testFile.delete();
    }

    @Test(expected = IllegalArgumentException.class)
    public void read_ThrowException_FileToReadIsNull() {
        // given
        File file = null;

        // when
        new HuffmanTreeFileReader(file);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void read_ThrowException_FileToDoesntExist() {
        // given
        File file = new File("tests data/gadad.4qar");

        // when
        new HuffmanTreeFileReader(file);

        // then
        assert false;
    }

    @Test
    public void read_ReadCorrectly_TreeIsMultilevel() {
        // given
        HuffmanTree tree = createHuffmanTreeForTests();
        File file = testFile;
        try ( ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(file))) {
            oos.writeObject(tree);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HuffmanTreeFileReaderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HuffmanTreeFileReaderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        HuffmanTreeFileReader instance = new HuffmanTreeFileReader(file);

        // when
        HuffmanTree readTree = null;
        try {
            readTree = instance.read();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(HuffmanTreeFileReaderTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        // then
        assertEquals(tree, readTree);
    }

    private HuffmanTree createHuffmanTreeForTests() {
        HuffmanTree root = new HuffmanTree(5);
        root.setRightChild(new HuffmanTree(3, 'A'));
        HuffmanTree leftChild = new HuffmanTree(2);
        root.setLeftChild(leftChild);
        leftChild.setLeftChild(new HuffmanTree(1, 'C'));
        leftChild.setRightChild(new HuffmanTree(1, 'E'));

        return root;
    }

}
