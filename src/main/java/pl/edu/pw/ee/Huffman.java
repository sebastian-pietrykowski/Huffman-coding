package pl.edu.pw.ee;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.edu.pw.ee.decompression.FileDecompressor;
import pl.edu.pw.ee.compression.FileCompressor;

public class Huffman {

    /*
        pathToRootDir - path to directory containing files to compress or
            decompress
        compress - true for compression, false for decompression
    
        If directory contains more than one file to compress or decompress,
        all of them will be handled.
        
        - File to be compressed doesn't have any special requirements.
        - File with Huffman tree has postfix "-tree".
        - File with compressed text has postfix "-compressed".
        - File containing decompressed text has postfix "-decompressed".
    
        Returns:
        - for compression: sum of bits in files contatinig compressed text after
            compression without trees, using bytes for writing,
        - for decompression: sum of characters in decompressed files.
    */
    public int huffman(String pathToRootDir, boolean compress) {
        return compress ? compress(pathToRootDir) : decompress(pathToRootDir);
    }

    private int compress(String pathToRootDir) {
        try {
            FileCompressor fileCompressor = new FileCompressor();
            fileCompressor.compress(pathToRootDir);

            return fileCompressor.getSumOfBitsAfterCompression();
        } catch (IOException ex) {
            Logger.getLogger(Huffman.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    private int decompress(String pathToRootDir) {
        try {
            FileDecompressor fileDecompressor = new FileDecompressor();
            fileDecompressor.decompress(pathToRootDir);

            return fileDecompressor.getSumOfCharsAfterDecompression();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Huffman.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }
}
