package pl.edu.pw.ee.decompression;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import pl.edu.pw.ee.files_manipulation.FileValidator;

public class DecompressedTextFileWriter {

    private final String decompressedText;

    public DecompressedTextFileWriter(String decompressedText) {
        validateString(decompressedText);
        this.decompressedText = decompressedText;
    }

    public void write(File fileToWrite) throws IOException {
        new FileValidator(fileToWrite).validateFileForWriting();

        try ( BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileToWrite), StandardCharsets.UTF_8))) {
            writer.write(decompressedText);
        }
    }

    private void validateString(String s) {
        if (s == null) {
            throw new IllegalArgumentException("String cannot be null.");
        }
    }
}
