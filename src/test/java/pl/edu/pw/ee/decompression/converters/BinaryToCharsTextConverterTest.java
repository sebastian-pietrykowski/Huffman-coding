package pl.edu.pw.ee.decompression.converters;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import pl.edu.pw.ee.HuffmanTree;

public class BinaryToCharsTextConverterTest {

    private HuffmanTree niemanieTree;

    @Before
    public void setUp() {
        createNiemanieTree();
    }

    @Test
    public void convert_WorkCorrectly_ConvertingBinarySequenceWithGhostBits() {
        // given
        HuffmanTree tree = niemanieTree;
        String binaryMessage = "11" + "10" + "01" + "001" + "000" + "11"
                + "10" + "01" + "000000";
        int nOfActualBits = 18;
        BinaryToCharsTextConverter instance = new BinaryToCharsTextConverter(
                tree, nOfActualBits, binaryMessage);

        // when
        String result = instance.convert();

        // then
        String expResult = "niemanie";
        assertEquals(expResult, result);
    }

    @Test
    public void convert_WorkCorrectly_ConvertingBinarySequenceWithoutGhostBits() {
        // given
        HuffmanTree tree = niemanieTree;
        String binaryMessage = "11" + "10" + "01" + "001" + "000" + "11"
                + "10" + "01" + "001" + "000";
        int nOfActualBits = 24;
        BinaryToCharsTextConverter instance = new BinaryToCharsTextConverter(
                tree, nOfActualBits, binaryMessage);

        // when
        String result = instance.convert();

        // then
        String expResult = "niemaniema";
        assertEquals(expResult, result);
    }

    @Test
    public void getNOfCharsInDecompressedFile_WorkCorrectly_ConvertingBinarySequenceWithGhostBits() {
        // given
        HuffmanTree tree = niemanieTree;
        String binaryMessage = "11" + "10" + "01" + "001" + "000" + "11"
                + "10" + "01" + "000000";
        int nOfActualBits = 18;
        BinaryToCharsTextConverter instance = new BinaryToCharsTextConverter(
                tree, nOfActualBits, binaryMessage);

        // when
        instance.convert();
        int result = instance.getNOfCharsInDecompressedFile();

        // then
        int expResult = "niemanie".length();
        assertEquals(expResult, result);
    }

    @Test
    public void getNOfCharsInDecompressedFile_WorkCorrectly_ConvertingBinarySequenceWithoutGhostBits() {
        // given
        HuffmanTree tree = niemanieTree;
        String binaryMessage = "11" + "10" + "01" + "001" + "000" + "11"
                + "10" + "01" + "001" + "000";
        int nOfActualBits = 24;
        BinaryToCharsTextConverter instance = new BinaryToCharsTextConverter(
                tree, nOfActualBits, binaryMessage);

        // when
        instance.convert();
        int result = instance.getNOfCharsInDecompressedFile();

        // then
        int expResult = "niemaniema".length();
        assertEquals(expResult, result);
    }

    // n -> 11, i -> 10, e -> 01, m -> 001, a -> 000
    private void createNiemanieTree() {
        niemanieTree = new HuffmanTree(8);
        HuffmanTree rChild = new HuffmanTree(4);
        niemanieTree.setRightChild(rChild);
        HuffmanTree rrChild = new HuffmanTree(2, 'n');
        rChild.setRightChild(rrChild);
        HuffmanTree rlChild = new HuffmanTree(2, 'i');
        rChild.setLeftChild(rlChild);
        HuffmanTree lChild = new HuffmanTree(4);
        niemanieTree.setLeftChild(lChild);
        HuffmanTree lrChild = new HuffmanTree(2, 'e');
        lChild.setRightChild(lrChild);
        HuffmanTree llChild = new HuffmanTree(2);
        lChild.setLeftChild(llChild);
        HuffmanTree llrChild = new HuffmanTree(1, 'm');
        llChild.setRightChild(llrChild);
        HuffmanTree lllChild = new HuffmanTree(1, 'a');
        llChild.setLeftChild(lllChild);
    }

}
