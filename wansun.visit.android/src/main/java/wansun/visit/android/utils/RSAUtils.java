package com.wansun.datahouse.visit.api.util.security;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import com.wansun.datahouse.visit.api.util.api.sign.ApiSignature;

/**
 * RSA非对称加密工具
 * 
 * @author nasico
 *
 */
public class RSAUtils {

	/**
	 * 随机生成密钥对
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static void genKeyPair() throws NoSuchAlgorithmException {
		// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		// 初始化密钥对生成器，密钥大小为96-1024位
		keyPairGen.initialize(1024, new SecureRandom());
		// 生成一个密钥对，保存在keyPair中
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); // 得到私钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); // 得到公钥
		String publicKeyString = new String(Base64.getEncoder().encode(publicKey.getEncoded()));
		// 得到私钥字符串
		String privateKeyString = new String(Base64.getEncoder().encode(privateKey.getEncoded()));

		System.out.println("公钥：" + publicKeyString);
		System.out.println("私钥：" + privateKeyString);
	}

	/**
	 * @param data
	 * @param publicKey
	 * @return
	 */
	public static String encrypt(String data, String publicKey) {
		// base64编码的公钥
		try {
			byte[] decoded = Base64.getDecoder().decode(publicKey);
			RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
					.generatePublic(new X509EncodedKeySpec(decoded));
			// RSA加密
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			String outStr = Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
			return outStr;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @param data
	 * @param privateKey
	 * @return
	 */
	public static String decrypt(String data, String privateKey) {
		// 64位解码加密后的字符串
		try {
			byte[] inputByte =  Base64.getDecoder().decode(data.getBytes("UTF-8"));
			// base64编码的私钥
			byte[] decoded =  Base64.getDecoder().decode(privateKey);
			RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
					.generatePrivate(new PKCS8EncodedKeySpec(decoded));
			// RSA解密
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, priKey);
			String outStr = new String(cipher.doFinal(inputByte));
			return outStr;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		try {
			//RSAUtils.genKeyPair();
			String data = "key=123&aasd1=2321321";
			String signStr = RSAUtils.encrypt(data , ApiSignature.PUBLICKEY);
			System.out.println(signStr);
			System.out.println(RSAUtils.decrypt(signStr, ApiSignature.PRIVETEKEY));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
