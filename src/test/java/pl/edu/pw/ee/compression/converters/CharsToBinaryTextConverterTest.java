package pl.edu.pw.ee.compression.converters;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

public class CharsToBinaryTextConverterTest {

    @Test(expected = IllegalArgumentException.class)
    public void constructor_ThrowException_EncodingMapIsNull() {
        // given
        Map<Character, String> encoding = null;
        char[] text = "fedew".toCharArray();

        // when
        new CharsToBinaryTextConverter(encoding, text);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_ThrowException_CharsArrayIsNull() {
        // given
        Map<Character, String> encoding = new HashMap<>();
        encoding.put('A', "000");
        encoding.put('B', "001");
        encoding.put('C', "01");
        encoding.put('D', "1");
        char[] text = null;

        // when
        new CharsToBinaryTextConverter(encoding, text);

        // then
        assert false;
    }

    @Test
    public void convert_WorkCorrectly_EachCharacterOccursOnce() {
        // given
        Map<Character, String> encoding = new HashMap<>();
        encoding.put('A', "000");
        encoding.put('B', "001");
        encoding.put('C', "01");
        encoding.put('D', "1");
        char[] text = "ABCD".toCharArray();
        CharsToBinaryTextConverter converter
                = new CharsToBinaryTextConverter(encoding, text);

        // when
        String result = converter.convert();

        // then
        String expResult = "000001011";
        assertEquals(expResult, result);
    }

    @Test
    public void convert_WorkCorrectly_EachCharacterOccursAtLeastOnce() {
        // given
        Map<Character, String> encoding = new HashMap<>();
        encoding.put('n', "11");
        encoding.put('i', "10");
        encoding.put('e', "01");
        encoding.put('m', "001");
        encoding.put('a', "000");
        char[] text = "niemanie".toCharArray();
        CharsToBinaryTextConverter converter
                = new CharsToBinaryTextConverter(encoding, text);

        // when
        String result = converter.convert();

        // then
        String expResult = "111001001000111001";
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalStateException.class)
    public void convert_ThrowException_CharacterInTextDoesntHaveItsOwnCodeInEncodingMap() {
        // given
        Map<Character, String> encoding = new HashMap<>();
        encoding.put('n', "11");
        encoding.put('i', "10");
        encoding.put('e', "01");
        encoding.put('a', "000");
        char[] text = "niemanie".toCharArray();
        CharsToBinaryTextConverter converter
                = new CharsToBinaryTextConverter(encoding, text);

        // when
        converter.convert();

        // then
        assert false;
    }

    @Test
    public void getNOfBitsAfterCompression_EachCharacterOccursOnce() {
        // given
        Map<Character, String> encoding = new HashMap<>();
        encoding.put('A', "000");
        encoding.put('B', "001");
        encoding.put('C', "01");
        encoding.put('D', "1");
        char[] text = "ABCD".toCharArray();
        CharsToBinaryTextConverter converter
                = new CharsToBinaryTextConverter(encoding, text);

        // when
        converter.convert();
        int result = converter.getNOfActualBits();

        // then
        int expResult = 9; // 000001011
        assertEquals(expResult, result);
    }

    @Test
    public void getNOfBitsAfterCompression_EachCharacterOccursAtLeastOnce() {
        // given
        Map<Character, String> encoding = new HashMap<>();
        encoding.put('n', "11");
        encoding.put('i', "10");
        encoding.put('e', "01");
        encoding.put('m', "001");
        encoding.put('a', "000");
        char[] text = "niemanie".toCharArray();
        CharsToBinaryTextConverter converter
                = new CharsToBinaryTextConverter(encoding, text);

        // when
        converter.convert();
        int result = converter.getNOfActualBits();

        // then
        int expResult = 18; // 111001001000111001
        assertEquals(expResult, result);
    }
}
