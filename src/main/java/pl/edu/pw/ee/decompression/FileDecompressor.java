package pl.edu.pw.ee.decompression;

import pl.edu.pw.ee.decompression.converters.BytesToBinaryTextConverter;
import pl.edu.pw.ee.decompression.converters.BinaryToCharsTextConverter;
import pl.edu.pw.ee.decompression.tree.HuffmanTreeFileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.edu.pw.ee.HuffmanTree;
import pl.edu.pw.ee.files_manipulation.FileNameManipulator;
import pl.edu.pw.ee.files_manipulation.FileValidator;
import pl.edu.pw.ee.files_manipulation.FilesToWorkFinder;
import pl.edu.pw.ee.files_manipulation.PairOfFilesToBeDecompressed;
import pl.edu.pw.ee.files_manipulation.ResultFileCreator;

public class FileDecompressor {

    private int sumOfCharsAfterDecompression = 0;

    public void decompress(String pathToRootDir) throws FileNotFoundException {
        validatePathToRootDir(pathToRootDir);
        File fileFromPath = new File(pathToRootDir);

        FileValidator fileValidator = new FileValidator(fileFromPath);
        fileValidator.validateIfFileIsNotNull();
        fileValidator.validateIfFileExists();
        fileValidator.validateIfFileIsDirectory();

        FilesToWorkFinder filesToWorkFinder = new FilesToWorkFinder();
        List<PairOfFilesToBeDecompressed> filesToDecompress
                = filesToWorkFinder.findFilesToDecompress(pathToRootDir);

        for (PairOfFilesToBeDecompressed pair : filesToDecompress) {
            try {
                File decompressedFile = convertTreeFileNameToResultFileName(
                        pair.getTreeFile());
                decompressFile(pair.getTreeFile(), pair.getCompressedTextFile(),
                        decompressedFile);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(FileDecompressor.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
    }

    private void decompressFile(File treeFile, File compressedTextFile,
            File resultFile) throws IOException, FileNotFoundException,
            ClassNotFoundException {

        CompressedTextFileReader reader = new CompressedTextFileReader(
                compressedTextFile);
        byte[] bytes;
        int nOfActualBits;
        try {
            bytes = reader.read();
            nOfActualBits = reader.getNOfActualBits();
        } catch (IOException e) {
            resultFile.delete();
            return;
        }

        HuffmanTree tree;
        try {
            tree = new HuffmanTreeFileReader(treeFile).read();
        } catch (IOException | ClassNotFoundException e) {
            resultFile.delete();
            return;
        }

        decompressCompressedFileAndTreeAndWriteToFile(bytes, nOfActualBits,
                tree, resultFile);
    }

    private void decompressCompressedFileAndTreeAndWriteToFile(
            byte[] bytes, int nOfActualBits, HuffmanTree tree, File resultFile)
            throws IOException {

        String binaryCompressedText
                = new BytesToBinaryTextConverter(bytes).convert();
        BinaryToCharsTextConverter binaryToCharsTextConverter
                = new BinaryToCharsTextConverter(tree, nOfActualBits,
                        binaryCompressedText);

        String decodedText = binaryToCharsTextConverter.convert();
        int nOfCharsInDecompressedFile = binaryToCharsTextConverter
                .getNOfCharsInDecompressedFile();

        sumOfCharsAfterDecompression += nOfCharsInDecompressedFile;

        DecompressedTextFileWriter writer
                = new DecompressedTextFileWriter(decodedText);
        writer.write(resultFile);
    }

    private File convertTreeFileNameToResultFileName(File treeFile) {
        FileNameManipulator fileNameManipulator = new FileNameManipulator();
        File sourceFile = fileNameManipulator.removePostfixBeforeExtension(
                treeFile, "-tree");
        ResultFileCreator resultFileCreator = new ResultFileCreator(sourceFile);

        return resultFileCreator.createFileForDecompressedText();
    }

    public int getSumOfCharsAfterDecompression() {
        return sumOfCharsAfterDecompression;
    }

    private void validatePathToRootDir(String pathToRootDir) {
        if (pathToRootDir == null) {
            throw new IllegalArgumentException("Argument pathToRootDir is null.");
        }
        FileValidator validator = new FileValidator(new File(pathToRootDir));
        validator.validateIfFileIsNotNull();
        validator.validateIfFileExists();
        validator.validateIfFileIsDirectory();
    }
}
