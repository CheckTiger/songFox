package cn.sxh.utils.encryption;

import android.os.Build;
import android.security.keystore.KeyProperties;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @package-name: cn.sxh.songfox.util
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/5 0005 : 10 :15
 * @project-name: songFox
 */
public class Encryptor {

    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";

    private byte[] encryption;
    private byte[] iv;

    public Encryptor() {
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public byte[] encryptText(final String alias, final String textToEncrypt)
            throws NoSuchAlgorithmException,NoSuchPaddingException, InvalidKeyException, IOException,
            InvalidAlgorithmParameterException, BadPaddingException,
            IllegalBlockSizeException {

        SecureRandom rng = new SecureRandom();

        byte[] aesKeyData = new byte[128 / Byte.SIZE];
        rng.nextBytes(aesKeyData);
        SecretKey aesKey = new SecretKeySpec(aesKeyData, "AES");

        Cipher aesCBC = Cipher.getInstance(TRANSFORMATION);
        byte[] iv = new byte[aesCBC.getBlockSize()];
        rng.nextBytes(iv);
        aesCBC.init(Cipher.ENCRYPT_MODE, aesKey, new IvParameterSpec(iv));
        return (encryption = aesCBC.doFinal(textToEncrypt.getBytes("UTF-8")));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    private SecretKey getSecretKey(final String alias) throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidAlgorithmParameterException {

        final KeyGenerator keyGenerator = KeyGenerator
                .getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE);

        keyGenerator.init(128);

        return keyGenerator.generateKey();
    }

    public byte[] getEncryption() {
        return encryption;
    }

    public byte[] getIv() {
        return iv;
    }
}
