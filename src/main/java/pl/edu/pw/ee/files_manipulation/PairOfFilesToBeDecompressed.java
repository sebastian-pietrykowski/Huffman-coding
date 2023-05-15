package pl.edu.pw.ee.files_manipulation;

import java.io.File;

public class PairOfFilesToBeDecompressed {

    private final File treeFile;
    private final File compressedTextFile;

    public PairOfFilesToBeDecompressed(File treeFile, File compressedTextFile) {
        this.treeFile = treeFile;
        this.compressedTextFile = compressedTextFile;
    }

    public File getTreeFile() {
        return treeFile;
    }

    public File getCompressedTextFile() {
        return compressedTextFile;
    }

    @Override
    public String toString() {
        return "tree file: " + treeFile + ", compressedTextFile: "
                + compressedTextFile;
    }
}
