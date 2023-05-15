package pl.edu.pw.ee.compression;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import pl.edu.pw.ee.files_manipulation.FileValidator;

public class CompressedTextFileWriter {

    private final byte[] compressedText;
    private final int nOfActualBits;

    public CompressedTextFileWriter(byte[] compressedText, int nOfActualBits) {
        validateBytesArray(compressedText);
        validateNOfActualBits(nOfActualBits);
        this.compressedText = compressedText;
        this.nOfActualBits = nOfActualBits;
    }

    public void write(File fileToWrite) throws FileNotFoundException, IOException {
        new FileValidator(fileToWrite).validateFileForWriting();

        try ( DataOutputStream outputStream = new DataOutputStream(
                new FileOutputStream(fileToWrite))) {
            outputStream.writeInt(nOfActualBits);
            outputStream.write(compressedText);
        }
    }

    private void validateBytesArray(byte[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array of bytes is null.");
        }
    }

    private void validateNOfActualBits(int nOfActualBits) {
        if (nOfActualBits <= 0) {
            throw new IllegalArgumentException(
                    "Number of actual bits cannot be smaller or equal to 0.");
        }
    }

}
