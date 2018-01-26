package indi.zqc.warehouse.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Title : EncryptAlgorithm.java
 * Package : indi.zqc.warehouse.util
 * Description : 加密工具
 * Create on : 2018/1/25 16:49
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class EncryptAlgorithm {

    private static String MD5 = "md5";

    private static String SHA = "sha";

    private static final int BUFFER_SIZE = 3;

    private static final int BUFFER_COMPARE = 4;

    private static final int BUFFER_FLOAT = 16;

    private static final String DECODE = "ISO-8859-1";

    private EncryptAlgorithm() {

    }

    /**
     * MD5加密
     *
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String hexMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return toHex(encryptMD5(str.getBytes(DECODE)));
    }

    /**
     * SHA加密
     *
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String hexSHA(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return toHex(encryptSHA(str.getBytes(DECODE)));
    }


    /**
     * MD5加密
     *
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static byte[] encryptMD5(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance(MD5);
        md5.update(data);
        return md5.digest();
    }

    /**
     * SHA加密
     *
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static byte[] encryptSHA(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance(SHA);
        sha.update(data);
        return sha.digest();
    }


    private static String toHex(byte[] buffer) {
        StringBuilder sb = new StringBuilder(buffer.length * BUFFER_SIZE);
        for (int i = 0; i < buffer.length; i++) {
            sb.append(Character.forDigit((buffer[i] & 0xf0) >> BUFFER_COMPARE, BUFFER_FLOAT));
            sb.append(Character.forDigit(buffer[i] & 0x0f, BUFFER_FLOAT));
        }
        return sb.toString();
    }

    /**
     * BASE64加密
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getBASE64(String str) throws UnsupportedEncodingException {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        return Base64.encodeBase64String(str.getBytes(DECODE));
    }

    /**
     * BASE64解密
     *
     * @param str
     * @return
     */
    public static String getFromBASE64(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        return new String(Base64.decodeBase64(str));
    }
}