package pl.edu.pw.ee.files_manipulation;

import java.io.File;

public class FileValidator {

    private final File file;
    private final String fileName;

    public FileValidator(File file) {
        this.file = file;
        fileName = file == null ? null : file.getName();
    }

    public void validateIfFileIsNotNull() {
        if (file == null) {
            throw new IllegalArgumentException(
                    "File cannot be null: " + fileName + ".");
        }
    }

    public void validateFileForWriting() {
        validateIfFileIsNotNull();
        validateIfFileExists();
        if (!file.canWrite()) {
            throw new IllegalArgumentException(
                    "Cannot write to file: " + fileName + ".");
        }
    }

    public void validateFileForReading() {
        validateIfFileIsNotNull();
        validateIfFileExists();
        if (!file.canRead()) {
            throw new IllegalArgumentException(
                    "Cannot read from file: " + fileName + ".");
        }
    }

    public void validateIfFileExists() {
        if (!file.exists()) {
            throw new IllegalArgumentException(
                    "File doesn't exist: " + fileName + ".");
        }
    }

    public void validateIfFileIsDirectory() {
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(
                    "File is not directory: " + fileName + ".");
        }
    }
}
