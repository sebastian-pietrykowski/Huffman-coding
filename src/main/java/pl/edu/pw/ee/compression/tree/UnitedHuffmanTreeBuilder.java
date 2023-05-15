package pl.edu.pw.ee.compression.tree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import pl.edu.pw.ee.HuffmanTree;
import pl.edu.pw.ee.files_manipulation.FileValidator;

public class UnitedHuffmanTreeBuilder {

    private final File fileToRead;

    public UnitedHuffmanTreeBuilder(File fileToRead) {
        new FileValidator(fileToRead).validateFileForReading();
        this.fileToRead = fileToRead;
    }

    private Map<Character, Integer> countOccurencesOfCharacters()
            throws FileNotFoundException, IOException {

        Map<Character, Integer> occurencesOfCharacters = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileToRead), "UTF-8"));

        int characterInt;
        while ((characterInt = reader.read()) != -1) {
            Character character = (char) characterInt;
            int occurenceOfCharacter
                    = occurencesOfCharacters.getOrDefault(character, 0);
            occurencesOfCharacters.put(character, ++occurenceOfCharacter);
        }

        return occurencesOfCharacters;
    }

    public HuffmanTree build() throws IOException {
        Map<Character, Integer> occurencesOfCharacters
                = countOccurencesOfCharacters();
        if (occurencesOfCharacters.isEmpty()) {
            return null;
        }

        PriorityQueue<HuffmanTree> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry
                : occurencesOfCharacters.entrySet()) {
            Character character = entry.getKey();
            int occurence = entry.getValue();
            HuffmanTree tree = new HuffmanTree(occurence, character);
            pq.add(tree);
        }

        if (pq.size() == 1) {
            HuffmanTree oneNode = pq.poll();
            HuffmanTree root = new HuffmanTree(oneNode.getOccurence());
            root.setRightChild(oneNode);
            return root;
        }

        while (pq.size() > 1) {
            HuffmanTree tree1 = pq.poll();
            HuffmanTree tree2 = pq.poll();

            Character character = null;
            int occurence = tree1.getOccurence() + tree2.getOccurence();
            HuffmanTree mergedTree = new HuffmanTree(
                    occurence, character, tree1, tree2);
            pq.add(mergedTree);
        }

        return pq.poll();
    }
}
