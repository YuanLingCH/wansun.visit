package wansun.visit.android.utils;


import android.util.Base64;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;


/**
 * RSA非对称加密工具
 * 
 * @author nasico
 *
 */
public class RSAUtils {




	public static String encrypt(String data, String publicKey) {
		// base64编码的公钥
		try {
			String decoded = Base64.encodeToString(publicKey.getBytes(), Base64.NO_WRAP );
			byte[] bytes = decoded .getBytes();
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
			RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(keySpec);

			//RSAPublicKey key=KeyFactory.getInstance("RSA").generatePublic(bytes)
			// RSA加密
	       Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			String outStr = Base64.encodeToString(cipher.doFinal(data.getBytes("UTF-8")), Base64.NO_WRAP);
			logUtils.d("加密数据outStr"+outStr );
			return outStr;
		} catch (Exception e) {
			logUtils.d("加密数据Exception"+e.toString() );
			return null;
		}
	}



	public  static String encrykey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCjnarkIhYCnJwPY5ETeVAc6tX/3f4ytm30WX4/DKuQzqYF1RKP305tTc7PPWEjkUKKD8iLKOgwS96ghgbO3eXCXQRjNrReHjtNoqyFINZW7dp/Xw0Aq9HXPNJzGCQbaWAZe3FqMUQhvcj+G5jeyGB7nagAHszsq8WJ3iTSY+HUgQIDAQAB";









	}
