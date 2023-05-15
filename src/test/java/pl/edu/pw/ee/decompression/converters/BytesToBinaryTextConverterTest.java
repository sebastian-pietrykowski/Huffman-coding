package pl.edu.pw.ee.decompression.converters;

import org.junit.Test;
import static org.junit.Assert.*;

public class BytesToBinaryTextConverterTest {

    @Test(expected = IllegalArgumentException.class)
    public void convert_ThrowException_BytesArrayIsNull() {
        // given
        byte[] bytes = null;

        // when
        new BytesToBinaryTextConverter(bytes);

        // then
        assert false;
    }

    @Test
    public void convert_ConvertCorrectly_SequenceIs0ByteLong() {
        // given
        byte[] bytes = {};
        BytesToBinaryTextConverter instance = new BytesToBinaryTextConverter(
                bytes);

        // when
        String result = instance.convert();

        // then
        String expResult = "";
        assertEquals(expResult, result);
    }

    @Test
    public void convert_ConvertCorrectly_SequenceIs1ByteLong() {
        // given
        byte[] bytes = {33};
        BytesToBinaryTextConverter instance = new BytesToBinaryTextConverter(
                bytes);

        // when
        String result = instance.convert();

        // then
        String expResult = "00100001";
        assertEquals(expResult, result);
    }

    @Test
    public void convert_ConvertCorrectly_SequenceIs3ByteLong() {
        // given
        byte[] bytes = {-104, 0, 70};
        BytesToBinaryTextConverter instance = new BytesToBinaryTextConverter(
                bytes);

        // when
        String result = instance.convert();

        // then
        String expResult = "10011000" + "00000000" + "01000110";
        assertEquals(expResult, result);
    }

}
