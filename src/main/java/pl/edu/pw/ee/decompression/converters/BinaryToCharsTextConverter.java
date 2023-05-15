package pl.edu.pw.ee.decompression.converters;

import pl.edu.pw.ee.HuffmanTree;

public class BinaryToCharsTextConverter {

    private final HuffmanTree tree;
    private final int nOfActualBits;
    private final String binaryText;
    private int nOfCharsInDecompressedFile = 0;

    public BinaryToCharsTextConverter(HuffmanTree tree, int nOfActualBits,
            String binaryText) {
        validateTree(tree);
        validateBinaryText(binaryText);
        validateNOfActualBits(nOfActualBits);

        this.tree = tree;
        this.nOfActualBits = nOfActualBits;
        this.binaryText = binaryText;
    }

    public String convert() {
        StringBuilder decodedTextBuilder = new StringBuilder();
        int currentPos = 0;
        while (currentPos < nOfActualBits) {
            DecodedCharacter decodedCharacter = decodeCharacter(
                    binaryText, currentPos);
            decodedTextBuilder.append(decodedCharacter.getCharacter());
            currentPos = decodedCharacter.getEnd() + 1;
        }
        String result = decodedTextBuilder.toString();
        this.nOfCharsInDecompressedFile = result.length();

        return result;
    }

    public int getNOfCharsInDecompressedFile() {
        return nOfCharsInDecompressedFile;
    }

    private DecodedCharacter decodeCharacter(String binaryText, int start) {
        HuffmanTree currentTree = this.tree;
        char side;
        int currentPosition = start;
        while (!currentTree.isLeaf() && currentPosition < nOfActualBits) {
            side = binaryText.charAt(currentPosition);
            switch (side) {
                case '0':
                    currentTree = currentTree.getLeftChild();
                    currentPosition++;
                    break;
                case '1':
                    currentTree = currentTree.getRightChild();
                    currentPosition++;
                    break;
                default:
                    throw new IllegalStateException(
                            "Error in converting character from binary.");
            }
        }
        if (currentTree.getCharacter() == null) {
            throw new IllegalStateException(
                    "Error in converting from binary to chars.");
        }
        char character = currentTree.getCharacter();
        int end = currentPosition - 1;

        return new DecodedCharacter(character, start, end);
    }

    private void validateTree(HuffmanTree tree) {
        if (tree == null) {
            throw new IllegalArgumentException("Huffman tree is null.");
        }
    }

    private void validateBinaryText(String binaryText) {
        if (binaryText == null) {
            throw new IllegalArgumentException("String binaryText is null.");
        }
        if (binaryText == null) {
            throw new IllegalArgumentException(""
                    + "Argument String binaryText is null.");
        }
    }

    private void validateNOfActualBits(int nOfActualBits) {
        if (nOfActualBits < 1) {
            throw new IllegalArgumentException(
                    "Argument nOfActualBits cannot be smaller than 1.");
        }
    }

    private class DecodedCharacter {

        private final char character;
        private final int start;
        private final int end; //inclusive

        public DecodedCharacter(char character, int start, int end) {
            this.character = character;
            this.start = start;
            this.end = end;
        }

        public char getCharacter() {
            return character;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }
}
