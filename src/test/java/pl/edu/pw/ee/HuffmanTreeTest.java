package pl.edu.pw.ee;

import org.junit.Test;
import static org.junit.Assert.*;

public class HuffmanTreeTest {

    @Test
    public void getOccurence_WorkCorrectly_TreeIs1Node() {
        // given
        int occurence = 5;
        HuffmanTree instance = new HuffmanTree(occurence);

        // when
        int resOccurence = instance.getOccurence();

        assertEquals(occurence, resOccurence);
    }

    @Test
    public void getCharacter_WorkCorrectly_CharacterIsNull() {
        // given
        Character character = null;
        int occurence = 2;
        HuffmanTree instance = new HuffmanTree(occurence, character);

        // when
        Character result = instance.getCharacter();

        // then
        Character expResult = null;

        assertEquals(expResult, result);
    }

    @Test
    public void getCharacter_WorkCorrectly_CharacterIsNotNull() {
        // given
        Character character = 'a';
        int occurence = 3;
        HuffmanTree instance = new HuffmanTree(occurence, character);

        // when
        Character result = instance.getCharacter();

        // then
        Character expResult = 'a';

        assertEquals(expResult, result);
    }

    @Test
    public void getLeftChild_WorkCorrectly_LeftChildIsNull() {
        // given
        Character character = 'E';
        int occurence = 10;
        HuffmanTree instance = new HuffmanTree(occurence, character);

        // when
        HuffmanTree result = instance.getLeftChild();

        // then
        HuffmanTree expResult = null;
        assertEquals(expResult, result);
    }

    @Test
    public void getRightChild_WorkCorrectly_LeftChildIsNotNull() {
        // given
        Character character = 'E';
        int occurence = 10;
        HuffmanTree leftChild = new HuffmanTree(3, 'c');
        HuffmanTree rightChild = new HuffmanTree(1, 'o');
        HuffmanTree instance = new HuffmanTree(occurence, character,
                leftChild, rightChild);

        // when
        HuffmanTree result = instance.getRightChild();

        // then
        HuffmanTree expResult = rightChild;
        assertEquals(expResult, result);
    }

    @Test
    public void setLeftChild_WorkCorrectly_LeftChildIsNull() {
        // given
        Character character = ' ';
        int occurence = 4;
        HuffmanTree instance = new HuffmanTree(occurence, character);
        HuffmanTree leftChild = null;

        // when
        instance.setLeftChild(leftChild);

        // then
        assertEquals(leftChild, instance.getLeftChild());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setLeftChild_ThrowException_OccurenceOfChildIsGreaterThanParents() {
        // given
        Character character = ' ';
        int occurence = 4;
        HuffmanTree instance = new HuffmanTree(occurence, character);
        Character childCharacter = '.';
        int childOccurence = 15;
        HuffmanTree leftChild = new HuffmanTree(childOccurence, childCharacter);

        // when
        instance.setLeftChild(leftChild);

        // then
        assert false;
    }

    @Test
    public void setRightChild_WorkCorrectly_RightChildIsNotNull() {
        // given
        Character character = '1';
        int occurence = 5;
        HuffmanTree instance = new HuffmanTree(occurence, character);
        Character childCharacter = '\\';
        int childOccurence = 3;
        HuffmanTree rightChild = new HuffmanTree(childOccurence, childCharacter);

        // when
        instance.setRightChild(rightChild);

        // then
        assertEquals(rightChild, instance.getRightChild());
    }

    @Test
    public void compareTo_WorkCorrectly_OccurencesAreEqual() {
        // given
        HuffmanTree firstTree = new HuffmanTree(
                4, null, new HuffmanTree(1, 'A'), new HuffmanTree(3, 'R'));
        HuffmanTree secondTree = new HuffmanTree(4, null);

        // when
        int result = firstTree.compareTo(secondTree);

        // then
        int expResult = 0;

        assertEquals(expResult, result);
    }

    @Test
    public void compare_WorkCorrectly_OccurencesArentEqual() {
        // given
        HuffmanTree firstTree = new HuffmanTree(
                12, null, new HuffmanTree(1, 'A'), new HuffmanTree(3, 'R'));
        HuffmanTree secondTree = new HuffmanTree(
                4, null, null, new HuffmanTree(3, 'R'));

        // when
        int result = firstTree.compareTo(secondTree);

        // then
        int expResultGreaterThan = 0;
        assertTrue(result > expResultGreaterThan);
    }

    @Test
    public void isLeaf_ReturnTrue_TreeIsLeaf() {
        // given
        int occurence = 3;
        HuffmanTree instance = new HuffmanTree(occurence);

        // when
        boolean result = instance.isLeaf();

        // then
        boolean expResult = true;
        assertEquals(expResult, result);
    }

    @Test
    public void isLeaf_ReturnFalse_TreeIsNotLeaf() {
        // given
        int occurence = 3;
        Character character = 'a';
        HuffmanTree instance = new HuffmanTree(occurence, character);
        int childOccurence = 2;
        HuffmanTree child = new HuffmanTree(childOccurence);
        instance.setRightChild(child);

        // when
        boolean result = instance.isLeaf();

        // then
        boolean expResult = false;
        assertEquals(expResult, result);
    }

    @Test
    public void equals_Object_ReturnFalse_ObjectIsNotTree() {
        // given
        Object that = (Object) 3;
        int occurence = 1;
        Character character = 'd';
        HuffmanTree instance = new HuffmanTree(occurence, character);

        // when
        boolean result = instance.equals(that);

        // then
        boolean expResult = false;
        assertEquals(expResult, result);
    }

    @Test
    public void equals_Object_ReturnTrue_ObjectIsEqualTree() {
        // given
        int occurence = 1;
        Character character = 'd';
        Object that = (Object) new HuffmanTree(occurence, character);
        HuffmanTree instance = new HuffmanTree(occurence, character);

        // when
        boolean result = instance.equals(that);

        // then
        boolean expResult = true;
        assertEquals(expResult, result);
    }

    @Test
    public void equals_HuffmanTree_ReturnTrue_BothTreesAreEqual() {
        // given
        HuffmanTree firstTree = new HuffmanTree(
                4, null, new HuffmanTree(1, 'A'), new HuffmanTree(3, 'R'));
        HuffmanTree secondTree = new HuffmanTree(
                4, null, new HuffmanTree(1, 'A'), new HuffmanTree(3, 'R'));

        // when
        boolean result = firstTree.equals(secondTree);

        // then
        boolean expResult = true;

        assertEquals(expResult, result);
    }

    @Test
    public void equals_HuffmanTree_ReturnFalse_BothTreesAreNotEqual() {
        // given
        HuffmanTree firstTree = new HuffmanTree(
                4, null, new HuffmanTree(1, 'A'), new HuffmanTree(3, 'R'));
        HuffmanTree secondTree = new HuffmanTree(
                4, null, null, new HuffmanTree(3, 'R'));

        // when
        boolean result = firstTree.equals(secondTree);

        // then
        boolean expResult = false;

        assertEquals(expResult, result);
    }

}
