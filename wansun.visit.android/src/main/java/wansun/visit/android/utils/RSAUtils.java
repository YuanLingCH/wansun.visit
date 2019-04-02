package wansun.visit.android.utils;


import android.util.Base64;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * RSA非对称加密工具
 * 
 * @author nasico
 *
 */
public class RSAUtils {

    /**
     * 公钥加密
     */
    public static String encryptByPublicKey(String data, String key)
            throws GeneralSecurityException
    {
        byte[] keyBytes = Base64.decode(key, Base64.DEFAULT);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] mi = cipher.doFinal(data.getBytes());

        return Base64.encodeToString(mi, Base64.DEFAULT);
    }



	public  static String encrykey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCjnarkIhYCnJwPY5ETeVAc6tX/3f4ytm30WX4/DKuQzqYF1RKP305tTc7PPWEjkUKKD8iLKOgwS96ghgbO3eXCXQRjNrReHjtNoqyFINZW7dp/Xw0Aq9HXPNJzGCQbaWAZe3FqMUQhvcj+G5jeyGB7nagAHszsq8WJ3iTSY+HUgQIDAQAB";









	}
