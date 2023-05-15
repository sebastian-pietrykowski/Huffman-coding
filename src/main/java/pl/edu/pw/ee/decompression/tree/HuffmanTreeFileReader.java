package pl.edu.pw.ee.decompression.tree;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import pl.edu.pw.ee.HuffmanTree;
import pl.edu.pw.ee.files_manipulation.FileValidator;

public class HuffmanTreeFileReader {

    private final File fileToRead;

    public HuffmanTreeFileReader(File fileToRead) {
        new FileValidator(fileToRead).validateFileForReading();
        this.fileToRead = fileToRead;
    }

    public HuffmanTree read() throws FileNotFoundException, IOException,
            ClassNotFoundException {

        HuffmanTree tree;
        try ( ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(fileToRead))) {
            tree = (HuffmanTree) inputStream.readObject();
        }

        return tree;
    }
}
