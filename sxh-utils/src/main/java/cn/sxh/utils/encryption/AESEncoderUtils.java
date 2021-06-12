package cn.sxh.utils.encryption;

import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.GCMParameterSpec;
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

    /**
     * @package-name: cn.sxh.songfox.util
     * @auther:snowFox
     * @Email:snowTigersong@gmail.com
     * @time: 2019/7/5 0005 : 09 :50
     * @project-name: songFox
     */

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static class AndroidKeyStoreUtils {

        private static final String TRANSFORMATION = "AES/GCM/NoPadding";
        private static final String ANDROID_KEY_STORE = "AndroidKeyStore";

        private KeyStore keyStore;
        private static AndroidKeyStoreUtils instance;
        private static byte[] encryption;
        private static byte[] iv;

        private AndroidKeyStoreUtils()throws CertificateException, NoSuchAlgorithmException, KeyStoreException,
                IOException {
            initKeyStore();
        }

        public synchronized static AndroidKeyStoreUtils getInstance()throws CertificateException, NoSuchAlgorithmException, KeyStoreException,
                IOException {
            if (instance == null) {
                instance = new AndroidKeyStoreUtils();
            }
            return instance;
        }

        /**
         * 加密处理
         * @param alias
         * @param textToEncrypt
         * @return
         */
        public byte[] encryptText(final String alias, final String textToEncrypt)
                throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IOException,
                InvalidAlgorithmParameterException, BadPaddingException,
                IllegalBlockSizeException {

            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(alias));

            iv = cipher.getIV();

            return (encryption = cipher.doFinal(textToEncrypt.getBytes("UTF-8")));
        }

        private SecretKey getSecretKey(final String alias) throws NoSuchAlgorithmException,
                NoSuchProviderException, InvalidAlgorithmParameterException {

            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE);
            keyGenerator.init(new KeyGenParameterSpec.Builder(alias,
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .build());

            return keyGenerator.generateKey();
        }

        private void initKeyStore() throws KeyStoreException, CertificateException,
                NoSuchAlgorithmException, IOException {
            keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
            keyStore.load(null);
        }


        /**
         * 解密处理
         * @param alias
         * @param encryptedData
         * @param encryptionIv
         * @return
         */
        public String decryptData(final String alias, final byte[] encryptedData, final byte[] encryptionIv)
                throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException,
                NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IOException,
                BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            final GCMParameterSpec spec = new GCMParameterSpec(128, encryptionIv);
            cipher.init(Cipher.DECRYPT_MODE, getDecryptSecretKey(alias), spec);

            return new String(cipher.doFinal(encryptedData), "UTF-8");
        }

        private SecretKey getDecryptSecretKey(final String alias) throws NoSuchAlgorithmException,
                UnrecoverableEntryException, KeyStoreException {
            return ((KeyStore.SecretKeyEntry) keyStore.getEntry(alias, null)).getSecretKey();
        }

    }
}




