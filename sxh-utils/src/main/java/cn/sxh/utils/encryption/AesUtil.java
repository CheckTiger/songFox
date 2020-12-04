package cn.sxh.utils.encryption;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * aes工具
 *
 * @author xujianhui
 * @since 2019-07-09 17:09
 */
public class AesUtil {


	private static final String SEED = "hexin";
	private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
	private static final String KEY_ALGORITHM = "AES";
	private static Key key;
	private static IvParameterSpec ivParameterSpec;
	private static byte[] byteKey = new byte[]{126, 61, 10, 25, -97, -95, -37, -12, -33, 40, -89, 15, -19, -3, 4, 83};

	static {
		key = new SecretKeySpec(byteKey, KEY_ALGORITHM);
		ivParameterSpec = new IvParameterSpec(byteKey);
	}

	/**
	 * 加密文件
	 *
	 * @param source
	 * @param encrypt
	 * @return
	 */
	public static File encrypt(File source, File encrypt) {
		InputStream sourceStream = null;
		CipherOutputStream encryptStream = null;
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
			sourceStream = new FileInputStream(source);
			encryptStream = new CipherOutputStream(new FileOutputStream(encrypt), cipher);
			byte[] buffer = new byte[1024];
			int count;
			while ((count = sourceStream.read(buffer)) >= 0) {
				encryptStream.write(buffer, 0, count);
			}
			encryptStream.flush();
		} catch (Exception e) {
		} finally {
			if (encryptStream != null) {
				try {
					encryptStream.close();
				} catch (IOException e) {
				}
			}
			if (sourceStream != null) {
				try {
					sourceStream.close();
				} catch (IOException e) {
				}
			}
		}
		return encrypt;
	}


	public static void decrypt(File encrypt, File target) {
		CipherInputStream encryptStream = null;
		FileOutputStream targetStream = null;
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
			targetStream = new FileOutputStream(target);
			encryptStream = new CipherInputStream(new FileInputStream(encrypt), cipher);
			byte[] buffer = new byte[1024];
			int count;
			while ((count = encryptStream.read(buffer)) >= 0) {
				targetStream.write(buffer, 0, count);
			}
			targetStream.flush();
		} catch (Exception e) {
		} finally {
			if (targetStream != null) {
				try {
					targetStream.close();
				} catch (IOException e) {
				}
			}
			if (encryptStream != null) {
				try {
					encryptStream.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static byte[] generateKey(int length) throws Exception {
		KeyGenerator generator = KeyGenerator.getInstance(KEY_ALGORITHM);
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(SEED.getBytes());
		generator.init(length, secureRandom);
		SecretKey key = generator.generateKey();
		byte[] bytesKey = key.getEncoded();
		return bytesKey;
	}

	public static void main(String[] args) throws Exception {
		/*System.out.println(generateKey(128));
		System.out.println(generateKey(192));
		System.out.println(generateKey(256));*/
		// encrypt(new File("d:/index.html"), new File("d:/index1.html"));
		decrypt(new File("d:/new.js"), new File("d:/new2.js"));
	}
}
