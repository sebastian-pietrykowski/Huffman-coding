package pl.edu.pw.ee.compression;

import pl.edu.pw.ee.compression.converters.CharsToBinaryTextConverter;
import pl.edu.pw.ee.compression.converters.BinaryToBytesTextConverter;
import pl.edu.pw.ee.compression.tree.HuffmanTreeFileWriter;
import pl.edu.pw.ee.compression.tree.UnitedHuffmanTreeBuilder;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import pl.edu.pw.ee.HuffmanTree;
import pl.edu.pw.ee.files_manipulation.FileValidator;
import pl.edu.pw.ee.files_manipulation.FilesToWorkFinder;
import pl.edu.pw.ee.files_manipulation.ResultFileCreator;

public class FileCompressor {

    private int sumOfBitsAfterCompression = 0;

    public void compress(String pathToRootDir) throws IOException {
        validatePathToRootDir(pathToRootDir);
        File fileFromPath = new File(pathToRootDir);

        FileValidator fileValidator = new FileValidator(fileFromPath);
        fileValidator.validateIfFileIsNotNull();
        fileValidator.validateIfFileExists();
        fileValidator.validateIfFileIsDirectory();

        FilesToWorkFinder filesToWorkFinder = new FilesToWorkFinder();
        List<File> filesToCompress
                = filesToWorkFinder.findFilesToCompress(pathToRootDir);
        for (File file : filesToCompress) {
            compressFile(file);
        }
    }

    public int getSumOfBitsAfterCompression() {
        return sumOfBitsAfterCompression;
    }

    private void compressFile(File fileToCompress) throws IOException {
        UnitedHuffmanTreeBuilder unitedTreeBuilder
                = new UnitedHuffmanTreeBuilder(fileToCompress);
        HuffmanTree unitedTree = unitedTreeBuilder.build();
        if (unitedTree == null) {
            return;
        }

        CodesBuilder codesBuilder = new CodesBuilder(unitedTree);
        Map<Character, String> codes = codesBuilder.buildCodesForCompressing();

        ResultFileCreator resultFileCreator
                = new ResultFileCreator(fileToCompress);
        writeTreeToFile(unitedTree, resultFileCreator);

        char[] charsText = new FileReaderToChars(fileToCompress).readChars();
        CharsToBinaryTextConverter charsToBinaryTextConverter
                = new CharsToBinaryTextConverter(codes, charsText);
        String binaryCompressedText = charsToBinaryTextConverter.convert();
        int nOfActualBits = charsToBinaryTextConverter.getNOfActualBits();

        BinaryToBytesTextConverter binaryToBytesTextConverter
                = new BinaryToBytesTextConverter(binaryCompressedText);
        byte[] bytesCompressedText = binaryToBytesTextConverter.convert();
        int nOfBitsInCompressedFile
                = binaryToBytesTextConverter.getNOfBitsInCompressedFile();

        writeCompressedTextToFile(bytesCompressedText, nOfActualBits,
                resultFileCreator);

        sumOfBitsAfterCompression += nOfBitsInCompressedFile;
    }

    private void writeTreeToFile(HuffmanTree unitedTree,
            ResultFileCreator resultFileCreator) throws IOException {

        HuffmanTreeFileWriter treeFileWriter
                = new HuffmanTreeFileWriter(unitedTree);
        File fileToWriteTree = resultFileCreator.createFileForTree();
        treeFileWriter.write(fileToWriteTree);
    }

    private void writeCompressedTextToFile(byte[] bytesCompressedText,
            int nOfActualBits, ResultFileCreator resultFileCreator)
            throws IOException {

        CompressedTextFileWriter codesFileWriter = new CompressedTextFileWriter(
                bytesCompressedText, nOfActualBits);
        File fileToWriteCompressedText = resultFileCreator
                .createFileForCompressedText();
        codesFileWriter.write(fileToWriteCompressedText);
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
