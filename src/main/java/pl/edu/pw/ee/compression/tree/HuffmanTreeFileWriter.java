package pl.edu.pw.ee.compression.tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import pl.edu.pw.ee.HuffmanTree;

public class HuffmanTreeFileWriter {

    private final HuffmanTree tree;

    public HuffmanTreeFileWriter(HuffmanTree tree) {
        validateTree(tree);
        this.tree = tree;
    }

    public void write(File fileToWrite) throws FileNotFoundException,
            IOException {

        try ( ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(fileToWrite))) {
            outputStream.writeObject(tree);
        }
    }

    private void validateTree(HuffmanTree tree) {
        if (tree == null) {
            throw new IllegalArgumentException("Tree is null.");
        }
    }
}
