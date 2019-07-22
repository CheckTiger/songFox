package cn.sxh.songfox.util;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Base64;
import android.util.Log;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * @package-name: cn.sxh.songfox.util
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/4 0004 : 16 :55
 * @project-name: songFox
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class AESEncoderUtils {

    public static final String keyStr = "YWJjZGVmZ2hpLM3sbW5vcc5yckR1dnd4";
    private static byte[] key = Base64.decode(keyStr.getBytes(), Base64.URL_SAFE);
    private static byte[] keyiv = { 1, 2, 3, 4, 5, 6, 7, 8 };
    /**
     * ECB加密,不要IV
     * @param data 明文
     * @return Base64编码的密文
     */
    public static byte[] des3EncodeECB(byte[] data)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }

    /**
     * ECB解密,不要IV
     * @param data Base64编码的密文
     * @return 明文
     */
    public static byte[] ees3DecodeECB(byte[] data)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
        Log.i("des", "decode init before");
        cipher.init(Cipher.DECRYPT_MODE, deskey);
        Log.i("des", "decode init after" + new String(data,"UTF-8"));
        byte[] bOut = cipher.doFinal(data);
        Log.i("des", "decode doFinal after");
        return bOut;

    }

    /**
     * CBC加密
     * @param data 明文
     * @return Base64编码的密文
     */
    public static byte[] des3EncodeCBC( byte[] data)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(keyiv);
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }

    /**
     * CBC解密
     * @param data Base64编码的密文
     * @return 明文
     */
    public static byte[] des3DecodeCBC(byte[] data)
            throws Exception {

        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(keyiv);
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(data);
        return bOut;

    }
}




