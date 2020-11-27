package cn.sxh.utils.encryption;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @package-name: cn.sxh.songfox.util
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/6/28 0028 : 11 :15
 * @project-name: songFox
 */
@TargetApi(Build.VERSION_CODES.O)
public class MD5Utils {

    public static String encrypt(String data,String key){
        if(TextUtils.isEmpty(data)||TextUtils.isEmpty(key)){
            Log.e("加密失败","参数和密钥不允许为空");
            return null;
        }
        String strs = null;
        try {
            byte[] bytes = doAES(Cipher.ENCRYPT_MODE,data.getBytes(),key.getBytes());
            // base64编码字节
            strs = new String(bytes,"utf-8");
        } catch (UnsupportedEncodingException e) {
            Log.e("加密失败，errormsg={}",e.getMessage());
        }
        return strs;
    }


    public static String decrypt(String data, String key){
        if(TextUtils.isEmpty(data)||TextUtils.isEmpty(key)){
            Log.e("解密失败","参数和密钥不允许为空");
            return null;
        }
        String strs = null;
        try {
            byte[] src = Base64.getDecoder().decode(data);
            byte[] bytes = doAES(Cipher.DECRYPT_MODE,src,key.getBytes());
            strs = new String(bytes,"utf-8");
        } catch (UnsupportedEncodingException e) {
            Log.e("解密失败，errormsg={}",e.getMessage());
        }
        return strs;

    }

    public  static  byte[]  doAES(int mode,byte[] data,byte[] key){
        try{
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            kgen.init(128, new SecureRandom(key));
            //3.产生原始对称密钥
            SecretKey secretKey = kgen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] enCodeFormat = secretKey.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat,"AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(mode, keySpec);// 初始化
            return cipher.doFinal(data);
        }catch (Exception e){
            Log.e("加解密失败，errormsg={}",e.getMessage());
        }
        return  null;
    }



}
