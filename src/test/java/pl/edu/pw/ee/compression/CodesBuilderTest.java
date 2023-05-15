package pl.edu.pw.ee.compression;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.HuffmanTree;
import static org.junit.Assert.*;

public class CodesBuilderTest {

    private HuffmanTree abcdTree, niemanieTree;

    @Before
    public void setUp() {
        createAbcdTree();
        createNiemanieTree();
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_ThrowException_ArgumentHuffmanTreeIsNull() {
        // given
        HuffmanTree tree = null;

        // when
        new CodesBuilder(tree);

        // then
        assert false;
    }

    @Test
    public void buildCodesForCompressing_WorkCorrectly_AbcdTree() {
        // given
        CodesBuilder instance = new CodesBuilder(abcdTree);

        // when
        Map<Character, String> result = instance.buildCodesForCompressing();

        // then
        Map<Character, String> expResult = new HashMap<>();
        expResult.put('A', "000");
        expResult.put('B', "001");
        expResult.put('C', "01");
        expResult.put('D', "1");

        assertEquals(expResult, result);
    }

    @Test
    public void buildCodesForCompressing_WorkCorrectly_NiemanieTree() {
        // given
        CodesBuilder instance = new CodesBuilder(niemanieTree);

        // when
        Map<Character, String> result = instance.buildCodesForCompressing();

        // then
        Map<Character, String> expResult = new HashMap<>();
        expResult.put('n', "11");
        expResult.put('i', "10");
        expResult.put('e', "01");
        expResult.put('m', "001");
        expResult.put('a', "000");

        assertEquals(expResult, result);
    }

    // A -> 000, B -> 001, C -> 01, D -> 1
    private void createAbcdTree() {
        abcdTree = new HuffmanTree(10);
        HuffmanTree rChild = new HuffmanTree(4, 'D');
        abcdTree.setRightChild(rChild);
        HuffmanTree lChild = new HuffmanTree(6);
        abcdTree.setLeftChild(lChild);
        HuffmanTree lrChild = new HuffmanTree(3, 'C');
        lChild.setRightChild(lrChild);
        HuffmanTree llChild = new HuffmanTree(3);
        lChild.setLeftChild(llChild);
        HuffmanTree llrChild = new HuffmanTree(2, 'B');
        llChild.setRightChild(llrChild);
        HuffmanTree lllChild = new HuffmanTree(1, 'A');
        llChild.setLeftChild(lllChild);
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
