package pl.edu.pw.ee.files_manipulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FilesToWorkFinder {

    public List<File> findFilesToCompress(String pathToRootDir) throws FileNotFoundException {
        validateString(pathToRootDir);
        validateIfFileFromPathIsDir(pathToRootDir);

        List<File> readableFiles = findReadableFiles(pathToRootDir);
        List<File> filesToCompress = new ArrayList<>();
        FilesToWorkFinder filesToWorkFinder = new FilesToWorkFinder();
        for (File file : readableFiles) {
            if (filesToWorkFinder.validateFileForCompression(file)) {
                filesToCompress.add(file);
            }
        }

        return filesToCompress;
    }

    public List<PairOfFilesToBeDecompressed> findFilesToDecompress(
            String pathToRootDir) throws FileNotFoundException {

        validateString(pathToRootDir);
        validateIfFileFromPathIsDir(pathToRootDir);

        List<File> readableFiles = findReadableFiles(pathToRootDir);
        List<File> potentialFiles = validatePostfixesOfFilesForDecompression(
                readableFiles);

        List<String> potentialFilesPaths = new ArrayList<>();
        for (File potentialFile : potentialFiles) {
            potentialFilesPaths.add(potentialFile.getAbsolutePath());
        }
        List<PairOfFilesToBeDecompressed> filesToDecompress = new ArrayList<>();

        while (!potentialFilesPaths.isEmpty()) {
            File file = new File(potentialFilesPaths.get(0));
            potentialFilesPaths.remove(0);
            PairOfFilesToBeDecompressed pair
                    = findCorrespondingFileToDecompress(file);
            if (pair != null) {
                potentialFilesPaths.remove(pair.getCompressedTextFile()
                        .getAbsolutePath());
                potentialFilesPaths.remove(pair.getTreeFile().getAbsolutePath());
                filesToDecompress.add(pair);
            }
        }

        return filesToDecompress;
    }

    public PairOfFilesToBeDecompressed findFilesToDecompress(File sourceFile) {
        new FileValidator(sourceFile).validateIfFileIsNotNull();

        FileNameManipulator manipulator = new FileNameManipulator();
        File treeFile = manipulator.addPostfixBeforeExtension(
                sourceFile, "-tree");
        File compressedTextFile = manipulator.addPostfixBeforeExtension(
                sourceFile, "-compressed");

        if (treeFile.exists() && compressedTextFile.exists()) {
            return new PairOfFilesToBeDecompressed(treeFile, compressedTextFile);
        }

        return null;
    }

    private List<File> findReadableFiles(String pathToRootDir) {
        File directory = new File(pathToRootDir);
        File[] filesInDir = directory.listFiles();

        List<File> readableFiles = new ArrayList<>();
        for (File file : filesInDir) {
            if (file.canRead() && !file.isDirectory()) {
                readableFiles.add(file);
            }
        }

        return readableFiles;
    }

    private boolean validateFileForCompression(File file) {
        String fileName = file.getName();
        FileNameManipulator manipulator = new FileNameManipulator();
        fileName = manipulator.removeExtension(fileName);

        return !manipulator.endsWith(fileName, "-tree")
                && !manipulator.endsWith(fileName, "-compressed");
    }

    private List<File> validatePostfixesOfFilesForDecompression(List<File> files) {
        List<File> resultFiles = new ArrayList<>();
        for (File file : files) {
            String fileName = file.getName();
            FileNameManipulator manipulator = new FileNameManipulator();
            fileName = manipulator.removeExtension(fileName);
            if (fileName.endsWith("-tree") || fileName.endsWith("-compressed")) {
                resultFiles.add(file);
            }
        }

        return resultFiles;
    }

    private PairOfFilesToBeDecompressed findCorrespondingFileToDecompress(
            File singleFile) {
        singleFile = singleFile.getAbsoluteFile();

        String singleFileName = singleFile.getName();
        FileNameManipulator manipulator = new FileNameManipulator();

        String extension = manipulator.getExtension(singleFileName);
        String singleFileNameNoExt = manipulator.removeExtension(singleFileName);
        String parentPath = singleFile.getParentFile().getAbsolutePath();

        if (singleFileNameNoExt.endsWith("-tree")) {
            String compressedTextFileName = parentPath + "/"
                    + manipulator.removePostfix(singleFileNameNoExt, "-tree")
                    + "-compressed" + extension;
            File compressedTextFile = new File(compressedTextFileName);
            if (compressedTextFile.exists()) {
                return new PairOfFilesToBeDecompressed(singleFile,
                        compressedTextFile);
            }
        }

        if (singleFileNameNoExt.endsWith("-compressed")) {
            String treeFileName = parentPath + "/"
                    + manipulator.removePostfix(singleFileNameNoExt,
                            "-compressed")
                    + "-tree" + extension;
            File treeFile = new File(treeFileName);
            if (treeFile.exists()) {
                return new PairOfFilesToBeDecompressed(treeFile, singleFile);
            }
        }

        return null;
    }

    private void validateString(String s) {
        if (s == null) {
            throw new IllegalArgumentException("String is null");
        }
    }

    private void validateIfFileFromPathIsDir(String filePath) {
        File file = new File(filePath);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("File is not directory");
        }
    }
}
