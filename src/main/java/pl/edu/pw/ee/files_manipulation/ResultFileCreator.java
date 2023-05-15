package pl.edu.pw.ee.files_manipulation;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResultFileCreator {

    private final File baseFile;

    public ResultFileCreator(File baseFile) {
        new FileValidator(baseFile).validateIfFileIsNotNull();
        this.baseFile = baseFile;
    }

    public File createFileForTree() {
        return createFileWithPostfix("-tree");
    }

    public File createFileForCompressedText() {
        return createFileWithPostfix("-compressed");
    }

    public File createFileForDecompressedText() {
        return createFileWithPostfix("-decompressed");
    }

    private File createFileWithPostfix(String postfix) {
        FileNameManipulator fileNameManipulator = new FileNameManipulator();
        File newFile = fileNameManipulator.addPostfixBeforeExtension(
                baseFile, postfix);
        try {
            newFile.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(ResultFileCreator.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        return newFile;
    }
}
