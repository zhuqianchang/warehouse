package indi.zqc.warehouse.util.encode;


import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;

/**
 * Title : Base64Code.java
 * Package : indi.zqc.warehouse.util.encode
 * Description : BASE64编码、解码
 * Create on : 2018/2/4 14:34
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public abstract class Base64Code {

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");


    private static final Base64Delegate delegate;

    static {
        delegate = new CommonsCodecBase64Delegate();
    }

    /**
     * 对给定的字节流进行编码.
     */
    public static byte[] encode(byte[] src) {
        return delegate.encode(src);
    }

    /**
     * 将编码结果转换成“UTF-8”的字符串.
     */
    public static String encodeToString(byte[] src) {
        if (src == null) {
            return null;
        }
        if (src.length == 0) {
            return "";
        }
        return new String(delegate.encode(src), DEFAULT_CHARSET);
    }

    /**
     * 对给定的字节流进行解码.
     */
    public static byte[] decode(byte[] src) {
        return delegate.decode(src);
    }

    /**
     * 对UTF-8字符串进行解码.
     */
    public static byte[] decodeFromString(String src) {
        if (src == null) {
            return null;
        }
        if (src.length() == 0) {
            return new byte[0];
        }
        return delegate.decode(src.getBytes(DEFAULT_CHARSET));
    }


    private interface Base64Delegate {

        byte[] encode(byte[] src);

        byte[] decode(byte[] src);
    }

    private static class CommonsCodecBase64Delegate implements Base64Delegate {

        private final Base64 base64 = new Base64();

        public byte[] encode(byte[] src) {
            return this.base64.encode(src);
        }

        public byte[] decode(byte[] src) {
            return this.base64.decode(src);
        }
    }

}
