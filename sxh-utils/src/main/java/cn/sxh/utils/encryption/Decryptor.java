package cn.sxh.utils.encryption;

import android.os.Build;
import android.util.Base64;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @package-name: cn.sxh.songfox.util
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/5 0005 : 10 :05
 * @project-name: songFox
 */
public class Decryptor {
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";

    private KeyStore keyStore;

    public Decryptor() throws CertificateException, NoSuchAlgorithmException, KeyStoreException,
            IOException {
        initKeyStore();
    }

    private void initKeyStore() throws KeyStoreException, CertificateException,
            NoSuchAlgorithmException, IOException {
        keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
        keyStore.load(null);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String decryptData(final String alias, final byte[] encryptedData, final byte[] encryptionIv)
            throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException,
            NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IOException,
            BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        SecureRandom rng = new SecureRandom();
        byte[] aesKeyData = new byte[128 / Byte.SIZE];
        SecretKey aesKey = new SecretKeySpec(aesKeyData, "AES");

        final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        byte[] iv = new byte[cipher.getBlockSize()];
        rng.nextBytes(aesKeyData);
        cipher.init(Cipher.ENCRYPT_MODE, aesKey, new IvParameterSpec(iv));
        Base64.encodeToString(cipher.doFinal(encryptedData), Base64.DEFAULT);
        return Base64.encodeToString(cipher.doFinal(encryptedData), Base64.DEFAULT);
    }

    private SecretKey getSecretKey(final String alias) throws NoSuchAlgorithmException,
            UnrecoverableEntryException, KeyStoreException {
        return ((KeyStore.SecretKeyEntry) keyStore.getEntry(alias, null)).getSecretKey();
    }
}
