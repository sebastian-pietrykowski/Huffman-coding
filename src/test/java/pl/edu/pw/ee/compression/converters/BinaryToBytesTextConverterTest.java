package pl.edu.pw.ee.compression.converters;

import java.util.Collections;
import org.junit.Test;
import static org.junit.Assert.*;

public class BinaryToBytesTextConverterTest {

    public static final int N_OF_BITS_IN_BYTE = 8;

    @Test(expected = IllegalArgumentException.class)
    public void constructor_ThrowException_ArgumentIsNull() {
        // given
        String binarySequence = null;

        // when
        new BinaryToBytesTextConverter(binarySequence);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_ThrowException_BinarySequenceContainsForbiddenCharacters() {
        // given
        String binarySequence = "00111001" + "12345678" + "10010";

        // when
        new BinaryToBytesTextConverter(binarySequence);

        // then
        assert false;
    }

    @Test
    public void convert_CorrectlyConvert_NOfBitsIsDivisibleBy8WithRemainder0() {
        // given
        int nOfBytes = 3;
        String[] binaryBytes = {"01100110", "01111111", "00010101"};
        String binarySequence = String.join("", binaryBytes);
        BinaryToBytesTextConverter converter
                = new BinaryToBytesTextConverter(binarySequence);

        // when
        byte[] resultConvertedData = converter.convert();

        // then
        byte[] expectedConvertedData = new byte[nOfBytes];
        for (int i = 0; i < nOfBytes; i++) {
            expectedConvertedData[i] = (byte) (int) Integer.valueOf(binaryBytes[i], 2);
        }
        assertArrayEquals(expectedConvertedData, resultConvertedData);
    }

    @Test
    public void convert_CorrectlyConvert_NOfBitsIsDivisibleBy8WithRemainder1() {
        // given
        int nOfBytes = 4;
        int remainderFromDividing = 1;
        int nOfEmptyBitsToFill = N_OF_BITS_IN_BYTE - remainderFromDividing;
        String[] binaryBytes = {"00101110", "01001111", "10101010", "1"};
        String binarySequence = String.join("", binaryBytes);
        BinaryToBytesTextConverter converter
                = new BinaryToBytesTextConverter(binarySequence);

        // when
        byte[] resultConvertedData = converter.convert();

        // then
        byte[] expectedConvertedData = new byte[nOfBytes];
        for (int i = 0; i < nOfBytes - 1; i++) {
            expectedConvertedData[i] = (byte) (int) Integer.valueOf(
                    binaryBytes[i], 2);
        }
        String lastByteS = binaryBytes[nOfBytes - 1]
                + String.join("", Collections.nCopies(nOfEmptyBitsToFill, "0"));
        expectedConvertedData[nOfBytes - 1] = (byte) (int) Integer.valueOf(
                lastByteS, 2);
        assertArrayEquals(expectedConvertedData, resultConvertedData);
    }

    @Test
    public void convert_CorrectlyConvert_NOfBitsIsDivisibleBy8WithRemainder4() {
        // given
        int nOfBytes = 4;
        int remainderFromDividing = 4;
        int nOfEmptyBitsToFill = N_OF_BITS_IN_BYTE - remainderFromDividing;
        String[] binaryBytes = {"01110010", "01001111", "10001110", "1000"};
        String binarySequence = String.join("", binaryBytes);
        BinaryToBytesTextConverter converter
                = new BinaryToBytesTextConverter(binarySequence);

        // when
        byte[] resultConvertedData = converter.convert();

        // then
        byte[] expectedConvertedData = new byte[nOfBytes];
        for (int i = 0; i < nOfBytes - 1; i++) {
            expectedConvertedData[i] = (byte) (int) Integer.valueOf(
                    binaryBytes[i], 2);
        }
        String lastByteS = binaryBytes[nOfBytes - 1]
                + String.join("", Collections.nCopies(nOfEmptyBitsToFill, "0"));
        expectedConvertedData[nOfBytes - 1] = (byte) (int) Integer.valueOf(
                lastByteS, 2);
        assertArrayEquals(expectedConvertedData, resultConvertedData);
    }

    @Test
    public void convert_CorrectlyConvert_NOfBitsIsDivisibleBy8WithRemainder7() {
        // given
        int nOfBytes = 4;
        int remainderFromDividing = 7;
        int nOfEmptyBitsToFill = N_OF_BITS_IN_BYTE - remainderFromDividing;
        String[] binaryBytes = {"11110100", "01100111", "10100110", "1011001"};
        String binarySequence = String.join("", binaryBytes);
        BinaryToBytesTextConverter converter
                = new BinaryToBytesTextConverter(binarySequence);

        // when
        byte[] resultConvertedData = converter.convert();

        // then
        byte[] expectedConvertedData = new byte[nOfBytes];
        for (int i = 0; i < nOfBytes - 1; i++) {
            expectedConvertedData[i] = (byte) (int) Integer.valueOf(
                    binaryBytes[i], 2);
        }
        String lastByteS = binaryBytes[nOfBytes - 1]
                + String.join("", Collections.nCopies(nOfEmptyBitsToFill, "0"));
        expectedConvertedData[nOfBytes - 1] = (byte) (int) Integer.valueOf(
                lastByteS, 2);
        assertArrayEquals(expectedConvertedData, resultConvertedData);
    }

    @Test
    public void getNOfBitsInCompressedFile_CorrectlyConvert_LengthOfBinarySequenceIsDivisableBy8() {
        // given
        String[] binaryBytes = {"11110100", "01100111", "10100110", "1011001"};
        String binarySequence = String.join("", binaryBytes);
        BinaryToBytesTextConverter converter
                = new BinaryToBytesTextConverter(binarySequence);

        // when
        converter.convert();
        int result = converter.getNOfBitsInCompressedFile();

        // then
        int expResult = binaryBytes.length * N_OF_BITS_IN_BYTE;
        assertEquals(expResult, result);
    }

    @Test
    public void getNOfBitsInCompressedFile_CorrectlyConvert_LengthOfBinarySequenceIsNotDivisableBy8() {
        // given
        String[] binaryBytes = {"11110100", "01100111", "10100110", "101"};
        String binarySequence = String.join("", binaryBytes);
        BinaryToBytesTextConverter converter
                = new BinaryToBytesTextConverter(binarySequence);

        // when
        converter.convert();
        int result = converter.getNOfBitsInCompressedFile();

        // then
        int expResult = binaryBytes.length * N_OF_BITS_IN_BYTE;
        assertEquals(expResult, result);
    }
}
