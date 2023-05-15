package pl.edu.pw.ee.decompression;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import pl.edu.pw.ee.files_manipulation.FileValidator;

public class CompressedTextFileReader {

    public static final int N_OF_BITS_IN_BYTE = 8;

    private final File fileToRead;
    private int nOfActualBits = 0;
    private int nOfBytes = 0;

    public CompressedTextFileReader(File fileToRead) {
        new FileValidator(fileToRead).validateFileForReading();
        this.fileToRead = fileToRead;
    }

    public byte[] read() throws FileNotFoundException, IOException {
        byte[] readBytes;
        try ( DataInputStream inputStream = new DataInputStream(
                new FileInputStream(fileToRead))) {
            nOfActualBits = inputStream.readInt();
            readBytes = inputStream.readAllBytes();
        }
        nOfBytes = readBytes.length;

        if (nOfBytes * N_OF_BITS_IN_BYTE < nOfActualBits) {
            throw new IllegalStateException(
                    "File is corrupted. Some parts are missing.");
        }

        return readBytes;
    }

    public int getNOfActualBits() {
        return nOfActualBits;
    }

    public int getNOfBytes() {
        return nOfBytes;
    }
}
