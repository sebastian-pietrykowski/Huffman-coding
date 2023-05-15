package pl.edu.pw.ee.compression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.edu.pw.ee.HuffmanTree;

public class CodesBuilder {

    private final HuffmanTree huffmanTree;
    private Map<Character, String> codes;

    public CodesBuilder(HuffmanTree huffmanTree) {
        validateHuffmanTree(huffmanTree);
        this.huffmanTree = huffmanTree;
    }

    public Map<Character, String> buildCodesForCompressing() {
        codes = new HashMap<>();
        List<Character> currentCode = new ArrayList<>();
        buildCodes(huffmanTree, currentCode);

        return codes;
    }

    private void buildCodes(HuffmanTree tree, List<Character> currentCode) {
        if (tree == null) {
            return;
        }
        if (tree.isLeaf()) {
            StringBuilder sb = new StringBuilder();
            for (Character sign : currentCode) {
                sb.append(sign);
            }
            String currentCodeString = sb.toString();
            codes.put(tree.getCharacter(), currentCodeString);
            return;
        }

        currentCode.add('0');
        buildCodes(tree.getLeftChild(), currentCode);

        currentCode.set(currentCode.size() - 1, '1');
        buildCodes(tree.getRightChild(), currentCode);

        currentCode.remove(currentCode.size() - 1);
    }

    private void validateHuffmanTree(HuffmanTree huffmanTree) {
        if (huffmanTree == null) {
            throw new IllegalArgumentException("Huffman tree cannot be null.");
        }
    }
}
