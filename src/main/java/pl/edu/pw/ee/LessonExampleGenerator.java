package pl.edu.pw.ee;

public class LessonExampleGenerator {
    public static void main(String[] args) {
        Huffman huffman = new Huffman();
        huffman.huffman("lesson example", true);
        huffman.huffman("lesson example", false);
    }
}
