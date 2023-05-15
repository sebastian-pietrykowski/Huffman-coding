package pl.edu.pw.ee.compression;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import pl.edu.pw.ee.files_manipulation.FileValidator;

public class FileReaderToChars {

    private final File fileToRead;

    public FileReaderToChars(File fileToRead) {
        new FileValidator(fileToRead).validateFileForReading();
        this.fileToRead = fileToRead;
    }

    public char[] readChars() throws UnsupportedEncodingException,
            FileNotFoundException, IOException {

        StringBuilder sb = new StringBuilder();
        try ( BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileToRead), "UTF-8"))) {
            int characterInt;
            while ((characterInt = reader.read()) != -1) {
                char character = (char) characterInt;
                sb.append(character);
            }
        }

        return sb.toString().toCharArray();
    }
}
