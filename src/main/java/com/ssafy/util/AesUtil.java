package com.ssafy.util;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {
	
	private static String secretKey = "SECRET_KEY_FOR_AES_256";
	
	// AES-256 암호화를 위한 키 생성
    private static SecretKeySpec getKey() throws Exception {
        byte[] key = secretKey.getBytes("UTF-8");
        // AES는 256비트(32바이트) 키를 사용하므로, 키 길이를 맞춰줍니다.
        byte[] keyBytes = new byte[32];
        System.arraycopy(key, 0, keyBytes, 0, Math.min(key.length, keyBytes.length));
        return new SecretKeySpec(keyBytes, "AES");
    }

    // AES 암호화 메소드
    public static String encrypt(String strToEncrypt) {
        try {
            SecretKeySpec secretKeySpec = getKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // AES 복호화 메소드
    public static String decrypt(String strToDecrypt) {
        try {
        	SecretKeySpec secretKeySpec = getKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
