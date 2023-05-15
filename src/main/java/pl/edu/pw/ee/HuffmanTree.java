package pl.edu.pw.ee;

import java.io.Serializable;

public class HuffmanTree implements Comparable<HuffmanTree>, Serializable {

    private final int occurence;
    private final Character character;
    private HuffmanTree leftChild, rightChild;

    public HuffmanTree(int occurence) {
        this(occurence, null, null, null);
    }

    public HuffmanTree(int occurence, Character character) {
        this(occurence, character, null, null);
        validateOccurence(occurence);
    }

    public HuffmanTree(int occurence, Character character, HuffmanTree leftChild,
            HuffmanTree rightChild) {
        this.occurence = occurence;
        this.character = character;
        setLeftChild(leftChild);
        setRightChild(rightChild);
    }

    public int getOccurence() {
        return occurence;
    }

    public Character getCharacter() {
        return character;
    }

    public HuffmanTree getLeftChild() {
        return leftChild;
    }

    public HuffmanTree getRightChild() {
        return rightChild;
    }

    public final void setLeftChild(HuffmanTree leftChild) {
        validateOccurenceOfChildAndParent(leftChild);
        this.leftChild = leftChild;
    }

    public final void setRightChild(HuffmanTree rightChild) {
        validateOccurenceOfChildAndParent(rightChild);
        this.rightChild = rightChild;
    }

    @Override
    public int compareTo(HuffmanTree o) {
        if (occurence == o.getOccurence()) {
            if (character == null & o.getCharacter() == null) {
                return 0;
            }
            if (character == null) {
                return -1;
            }
            if (o.getCharacter() == null) {
                return 1;
            }
            return Character.compare(character, o.character);
        }
        return Integer.compare(occurence, o.getOccurence());
    }

    public boolean isLeaf() {
        return this.leftChild == null && this.rightChild == null;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (!(that instanceof HuffmanTree)) {
            return false;
        }

        return equals((HuffmanTree) that);
    }

    public boolean equals(HuffmanTree thatTree) {
        if (occurence != thatTree.getOccurence()) {
            return false;
        }
        if (character == null && thatTree.getCharacter() == null) {
            if (isLeaf() && thatTree.isLeaf()) {
                return true;
            }

        } else {
            if (isOnlyOneCharacterNull(character, thatTree.getCharacter())) {
                return false;
            }
            if (!character.equals(thatTree.getCharacter())) {
                return false;
            }
        }

        return areSubtreesEqual(leftChild, thatTree.getLeftChild())
                && areSubtreesEqual(rightChild, thatTree.getRightChild());
    }

    private boolean areSubtreesEqual(HuffmanTree tree1, HuffmanTree tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        } else if (isOnlyOneTreeNull(tree1, tree2)) {
            return false;
        } else {
            return tree1.equals(tree2);
        }
    }

    private boolean isOnlyOneTreeNull(HuffmanTree tree1, HuffmanTree tree2) {
        return (tree1 == null && tree2 != null)
                || (tree1 != null && tree2 == null);
    }

    private boolean isOnlyOneCharacterNull(Character character1, Character character2) {
        return (character1 == null && character2 != null)
                || (character1 != null && character2 == null);
    }

    private void validateOccurence(int occurence) {
        if (occurence < 1) {
            throw new IllegalArgumentException(
                    "Occurence cannot be smaller than 1.");
        }
    }

    private void validateOccurenceOfChildAndParent(HuffmanTree childTree) {
        if (childTree != null && occurence < childTree.getOccurence()) {
            throw new IllegalArgumentException(
                    "Child cannot have larger occurence than parent.");
        }
    }
}
