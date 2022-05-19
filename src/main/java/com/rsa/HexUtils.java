package com.rsa;

import java.security.SecureRandom;
import java.util.Arrays;

/**
 * 16 Hex String operate class
 *
 */
public class HexUtils {
    /**
     * HEX_DIGITS
     */
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F'};

    /**
     * Transform integer array to byte
     *
     * @param source the source need to be transformed
     * @param length the length of byte array
     * @return b the length of byte array b
     */
    public static byte[] longToBytes(long source, int length) {
        byte[] b = new byte[length];
        for (int i = 0; i < length; i++) {
            b[i] = (byte) (source >> 8 * (length - i - 1) & 0xFF);
        }
        return b;
    }


    /**
     * bytes turn to HexString
     *
     * @param bytes  byte[]
     * @param length hexString length
     * @return bytes
     */
    private static String bytes2HexString(byte[] bytes, int length) {
        if (bytes == null) {
            return null;
        }
        int len = length;
        if (len <= 0) {
            return null;
        }
        char[] ret = new char[len << 1];
        for (int i = 0, j = 0; i < len; i++) {
            ret[j++] = HEX_DIGITS[bytes[i] >>> 4 & 0x0f];
            ret[j++] = HEX_DIGITS[bytes[i] & 0x0f];
        }
        return new String(ret);
    }

    /**
     * bytes turn to HexString
     *
     * @param bytes byte[]
     * @return bytes
     */
    public static String bytes2HexString(byte[] bytes) {
        return bytes != null && bytes.length > 0 ? bytes2HexString(bytes, bytes.length) : "data is null";
    }

    /**
     * æŠŠå��è¿›åˆ¶æ•°å­—è½¬æ�¢æˆ�è¶³ä½�çš„å��å…­è¿›åˆ¶å­—ç¬¦ä¸²,å¹¶è¡¥å…¨ç©ºä½�
     *
     * @param num long number
     * @return hex string
     */
    public static String decimal2fitHex(long num) {
        String hex = Long.toHexString(num).toUpperCase();
        if (hex.length() % 2 != 0) {
            return "0" + hex;
        }
        return hex.toUpperCase();
    }


    /**
     * æŠŠå��å…­è¿›åˆ¶è¡¨ç¤ºçš„å­—èŠ‚æ•°ç»„å­—ç¬¦ä¸²ï¼Œè½¬æ�¢æˆ�å��å…­è¿›åˆ¶å­—èŠ‚æ•°ç»„
     *
     * @param hex hex string
     * @return the result of hex string turn to byte[]
     */
    public static byte[] hexStr2bytes(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] resultChar = hex.toUpperCase().toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (hexChar2byte(resultChar[pos]) << 4 | hexChar2byte(resultChar[pos + 1]));
        }
        return result;
    }

    /**
     * æŠŠ16è¿›åˆ¶å­—ç¬¦[0123456789abcde]ï¼ˆå�«å¤§å°�å†™ï¼‰è½¬æˆ�å��è¿›åˆ¶
     *
     * @param hexChar hexChar
     * @return int 10è¿›åˆ¶ç»“æžœ
     */
    private static int hexChar2byte(char hexChar) {
        switch (hexChar) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case 'a':
            case 'A':
                return 10;
            case 'b':
            case 'B':
                return 11;
            case 'c':
            case 'C':
                return 12;
            case 'd':
            case 'D':
                return 13;
            case 'e':
            case 'E':
                return 14;
            case 'f':
            case 'F':
                return 15;
            default:
                return -1;
        }
    }

    /**
     * concatenate many byte[]
     *
     * @param arrays number of  byte[]
     * @return many  byte arrays
     */
    public static byte[] concatenate(byte[]... arrays) {
        int length = 0;
        for (byte[] array : arrays) {
            length += array.length;
        }
        byte[] newArray = new byte[length];
        int n = 0;
        for (byte[] array : arrays) {
            System.arraycopy(array, 0, newArray, n, array.length);
            n += array.length;
        }
        return newArray;
    }

    /**
     * Returns byte representation of integer as little-endian
     *
     * @param value  Value to convert
     * @param length Length of integer value (1, 2 or 4 bytes).
     * @return Byte array representation of integer as little-endian
     */
    public static byte[] intToLittleEndDian(int value, int length) {
        byte[] result = new byte[length];
        int index;
        for (int i = 0; i < length; i++) {
            index = length - i - 1;
            result[i] = (byte) (value >> (8 * index) & 0xFF);
        }
        return result;
    }

    /**
     * Return integer from its little-endian byte array representation
     *
     * @param value Little-endian byte array
     * @return Integer from its little-endian representation
     */
    public static int littleEndianToInt(byte[] value) {
        int result = 0;
        for (int i = 0; i < value.length; i++) {
            result += ((value[i] & 0xFF) << 8 * i);
        }
        return result;
    }


    /**
     * little int data  turn to byte[]
     *
     * @param intValue int data
     * @param length   turn to byte[] length
     * @return byte[] the result
     */
    public static byte[] littleIntToByte(int intValue, int length) {
        byte[] byteResult = new byte[length];
        if (length == 1) {
            byteResult[0] = (byte) (0xff & intValue);
        } else if (length == 2) {
            byteResult[0] = (byte) (0xff & intValue);
            byteResult[1] = (byte) ((0xff00 & intValue) >> 8);
        } else {
            byteResult[0] = (byte) (0xff & intValue);
            byteResult[1] = (byte) ((0xff00 & intValue) >> 8);
            byteResult[2] = (byte) ((0xff0000 & intValue) >> 16);
            byteResult[3] = (byte) ((0xff000000 & intValue) >> 24);
        }
        return byteResult;
    }


    /**
     * Get the specified location length of length
     *
     * @param packet byte[] src
     * @return length
     */
    public static int getDataLength(final byte[] packet) {
        byte[] bytesLengthBody = Arrays.copyOfRange(packet, 2, 3);
        return littleEndianToInt(bytesLengthBody);
    }


    /**
     * å��å…­è¿›åˆ¶è½¬æ�¢å­—ç¬¦ä¸²
     *
     * @param hexStr str Byteå­—ç¬¦ä¸²(Byteä¹‹é—´æ— åˆ†éš”ç¬¦ å¦‚:[616C6B])
     * @return String å¯¹åº”çš„å­—ç¬¦ä¸²
     */
    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexChars = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;

        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexChars[2 * i]) * 16;
            n += str.indexOf(hexChars[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    /**
     * å��è½¬byte []
     *
     * @param bytes éœ€è¦�å��è½¬çš„byte[]
     */
    public static void reserveByte(byte[] bytes) {
        for (int start = 0, end = bytes.length - 1; start < end; start++, end--) {
            byte temp = bytes[end];
            bytes[end] = bytes[start];
            bytes[start] = temp;
        }
    }

    /**
     * turn lots of byte[] to a byte[]
     *
     * @param values lots of byte[]
     * @return a byte[]
     */
    public static byte[] byteMergerAll(byte[]... values) {
        int length_byte = 0;
        for (byte[] value : values) {
            length_byte += value.length;
        }
        byte[] all_byte = new byte[length_byte];
        int countLength = 0;
        for (byte[] b : values) {
            System.arraycopy(b, 0, all_byte, countLength, b.length);
            countLength += b.length;
        }
        return all_byte;
    }


    /**
     * short turn to byte[]
     *
     * @param value short value
     * @return the result of short value turns to byte[]
     */
    public static byte[] short2ByteNew(short value) {
        byte high = (byte) (0x00FF & (value >> 8));//å®šä¹‰ç¬¬ä¸€ä¸ªbyte
        byte low = (byte) (0x00FF & value);//å®šä¹‰ç¬¬äºŒä¸ªbyte
        byte[] bytes = new byte[2];
        bytes[0] = low;
        bytes[1] = high;
        return bytes;
    }

    /**
     * String to hex to byte[]
     *
     * @param valueString the string
     * @return byte[]
     */
    public static byte[] getAsByteArray(String valueString) {
        //Log.d(TAG, "getAsByteArray in:" + in);
        byte[] bArray = new byte[valueString.length() / 2];
        for (int i = 0; i < (valueString.length() / 2); i++) {
            //if(hexStr.charAt(i) == ' ') continue;
            byte firstNibble = Byte.parseByte(valueString.substring(2 * i, 2 * i + 1), 16); // [x,y)
            byte secondNibble = Byte.parseByte(valueString.substring(2 * i + 1, 2 * i + 2), 16);
            int finalByte = (secondNibble) | (firstNibble << 4); // bit-operations only with numbers, not bytes.
            bArray[i] = (byte) finalByte;
        }
        //Log.d(TAG, "getAsByteArray bArray:" + bArray.toString() + " , length = " + bArray.length);
        return bArray;
    }

    /**
     * get a random
     *
     * @param dataSize the random size
     * @return random byte[]
     */
    public static byte[] generateRandom(int dataSize) {
        SecureRandom random = new SecureRandom();
        byte[] data = new byte[dataSize];
        random.nextBytes(data);
        return data;
    }

    /**
     * hex string to byte[]
     *
     * @param hexString hex String
     * @return byte[]
     * @throws IllegalArgumentException exception IllegalArgumentExceptiono
     */
    public static byte[] hexStringToByteArray(String hexString) throws IllegalArgumentException {
        int len = hexString.length();
        if (len % 2 == 1) {
            throw new IllegalArgumentException("Hex string must have even number of characters");
        } else {
            byte[] data = new byte[len / 2];

            for (int i = 0; i < len; i += 2) {
                data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
            }

            return data;
        }
    }


    /**
     * å°†å­—èŠ‚æ•°ç»„è½¬æ�¢ä¸ºå��å…­è¿›åˆ¶å­—ç¬¦æ•°ç»„
     *
     * @param data     byte[]
     * @param toDigits ç”¨äºŽæŽ§åˆ¶è¾“å‡ºçš„char[]
     * @return å��å…­è¿›åˆ¶char[]
     */
    protected static char[] encodeHex(byte[] data, char[] toDigits) {
        int l = data.length;
        char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return out;
    }


    /**
     * å°†å��å…­è¿›åˆ¶å­—ç¬¦ä¸²è½¬æ�¢ä¸ºå­—èŠ‚æ•°ç»„
     *
     * @param data
     * @return
     */
    public static byte[] decodeHex(String data) {
        if (data == null) {
            return new byte[0];
        }
        return decodeHex(data.toCharArray());
    }

    /**
     * å°†å��å…­è¿›åˆ¶å­—ç¬¦æ•°ç»„è½¬æ�¢ä¸ºå­—èŠ‚æ•°ç»„
     *
     * @param data å��å…­è¿›åˆ¶char[]
     * @return byte[]
     * @throws RuntimeException å¦‚æžœæº�å��å…­è¿›åˆ¶å­—ç¬¦æ•°ç»„æ˜¯ä¸€ä¸ªå¥‡æ€ªçš„é•¿åº¦ï¼Œå°†æŠ›å‡ºè¿�è¡Œæ—¶å¼‚å¸¸
     */
    public static byte[] decodeHex(char[] data) {

        int len = data.length;

        if ((len & 0x01) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }

        byte[] out = new byte[len >> 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }

        return out;
    }

    /**
     * å°†å��å…­è¿›åˆ¶å­—ç¬¦è½¬æ�¢æˆ�ä¸€ä¸ªæ•´æ•°
     *
     * @param ch    å��å…­è¿›åˆ¶char
     * @param index å��å…­è¿›åˆ¶å­—ç¬¦åœ¨å­—ç¬¦æ•°ç»„ä¸­çš„ä½�ç½®
     * @return ä¸€ä¸ªæ•´æ•°
     * @throws RuntimeException å½“chä¸�æ˜¯ä¸€ä¸ªå�ˆæ³•çš„å��å…­è¿›åˆ¶å­—ç¬¦æ—¶ï¼ŒæŠ›å‡ºè¿�è¡Œæ—¶å¼‚å¸¸
     */
    protected static int toDigit(char ch, int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }

    /**
     * æˆªå�–å­—èŠ‚æ•°ç»„
     *
     * @param src   byte []  æ•°ç»„æº�  è¿™é‡Œå¡«16è¿›åˆ¶çš„ æ•°ç»„
     * @param begin èµ·å§‹ä½�ç½® æº�æ•°ç»„çš„èµ·å§‹ä½�ç½®ã€‚0ä½�ç½®æœ‰æ•ˆ
     * @param count æˆªå�–é•¿åº¦
     * @return
     */
    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        System.arraycopy(src, begin, bs, 0, count);  // bs ç›®çš„æ•°ç»„  0 æˆªå�–å�Žå­˜æ”¾çš„æ•°å€¼èµ·å§‹ä½�ç½®ã€‚0ä½�ç½®æœ‰æ•ˆ
        return bs;
    }

    /**
     * intè½¬byteæ•°ç»„
     *
     * @param bb
     * @param x
     * @param index ç¬¬å‡ ä½�å¼€å§‹
     * @param flag  æ ‡è¯†é«˜ä½Žä½�é¡ºåº�ï¼Œé«˜ä½�åœ¨å‰�ä¸ºtrueï¼Œä½Žä½�åœ¨å‰�ä¸ºfalse
     */
    public static void intToByte(byte[] bb, int x, int index, boolean flag) {
        if (flag) {
            bb[index + 0] = (byte) (x >> 24);
            bb[index + 1] = (byte) (x >> 16);
            bb[index + 2] = (byte) (x >> 8);
            bb[index + 3] = (byte) (x >> 0);
        } else {
            bb[index + 3] = (byte) (x >> 24);
            bb[index + 2] = (byte) (x >> 16);
            bb[index + 1] = (byte) (x >> 8);
            bb[index + 0] = (byte) (x >> 0);
        }
    }

    /**
     * byteæ•°ç»„è½¬int
     *
     * @param bb
     * @param index ç¬¬å‡ ä½�å¼€å§‹
     * @param flag  æ ‡è¯†é«˜ä½Žä½�é¡ºåº�ï¼Œé«˜ä½�åœ¨å‰�ä¸ºtrueï¼Œä½Žä½�åœ¨å‰�ä¸ºfalse
     * @return
     */
    public static int byteToInt(byte[] bb, int index, boolean flag) {
        if (flag) {
            return (int) ((((bb[index + 0] & 0xff) << 24)
                    | ((bb[index + 1] & 0xff) << 16)
                    | ((bb[index + 2] & 0xff) << 8)
                    | ((bb[index + 3] & 0xff) << 0)));
        } else {
            return (int) ((((bb[index + 3] & 0xff) << 24)
                    | ((bb[index + 2] & 0xff) << 16)
                    | ((bb[index + 1] & 0xff) << 8)
                    | ((bb[index + 0] & 0xff) << 0)));
        }
    }


    /**
     * å­—èŠ‚æ•°ç»„é€†åº�
     *
     * @param data
     * @return
     */
    public static byte[] reverse(byte[] data) {
        byte[] reverseData = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            reverseData[i] = data[data.length - 1 - i];
        }
        return reverseData;
    }

    /**
     * è“�ç‰™ä¼ è¾“ 16è¿›åˆ¶ é«˜ä½Žä½� è¯»æ•°çš„ è½¬æ�¢
     *
     * @param data  æˆªå�–æ•°æ�®æº�ï¼Œå­—èŠ‚æ•°ç»„
     * @param index æˆªå�–æ•°æ�®å¼€å§‹ä½�ç½®
     * @param count æˆªå�–æ•°æ�®é•¿åº¦ï¼Œå�ªèƒ½ä¸º2ã€�4ã€�8ä¸ªå­—èŠ‚
     * @param flag  æ ‡è¯†é«˜ä½Žä½�é¡ºåº�ï¼Œé«˜ä½�åœ¨å‰�ä¸ºtrueï¼Œä½Žä½�åœ¨å‰�ä¸ºfalse
     * @return
     */
    public static long byteToLong(byte[] data, int index, int count, boolean flag) {
        long lg = 0;
        if (flag) {
            switch (count) {
                case 2:
                    lg = ((((long) data[index + 0] & 0xff) << 8)
                            | (((long) data[index + 1] & 0xff) << 0));
                    break;

                case 4:
                    lg = ((((long) data[index + 0] & 0xff) << 24)
                            | (((long) data[index + 1] & 0xff) << 16)
                            | (((long) data[index + 2] & 0xff) << 8)
                            | (((long) data[index + 3] & 0xff) << 0));
                    break;

                case 8:
                    lg = ((((long) data[index + 0] & 0xff) << 56)
                            | (((long) data[index + 1] & 0xff) << 48)
                            | (((long) data[index + 2] & 0xff) << 40)
                            | (((long) data[index + 3] & 0xff) << 32)
                            | (((long) data[index + 4] & 0xff) << 24)
                            | (((long) data[index + 5] & 0xff) << 16)
                            | (((long) data[index + 6] & 0xff) << 8)
                            | (((long) data[index + 7] & 0xff) << 0));
                    break;
            }
            return lg;
        } else {
            switch (count) {
                case 2:
                    lg = ((((long) data[index + 1] & 0xff) << 8)
                            | (((long) data[index + 0] & 0xff) << 0));
                    break;
                case 4:
                    lg = ((((long) data[index + 3] & 0xff) << 24)
                            | (((long) data[index + 2] & 0xff) << 16)
                            | (((long) data[index + 1] & 0xff) << 8)
                            | (((long) data[index + 0] & 0xff) << 0));
                    break;
                case 8:
                    lg = ((((long) data[index + 7] & 0xff) << 56)
                            | (((long) data[index + 6] & 0xff) << 48)
                            | (((long) data[index + 5] & 0xff) << 40)
                            | (((long) data[index + 4] & 0xff) << 32)
                            | (((long) data[index + 3] & 0xff) << 24)
                            | (((long) data[index + 2] & 0xff) << 16)
                            | (((long) data[index + 1] & 0xff) << 8)
                            | (((long) data[index + 0] & 0xff) << 0));
                    break;
            }
            return lg;
        }
    }

}
