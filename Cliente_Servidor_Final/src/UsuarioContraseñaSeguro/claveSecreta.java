package UsuarioContraseñaSeguro;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class claveSecreta {

	    public static void main(String[] args) {

	        String archivoClave = "archivoImportante.key";
	        try {
	            KeyGenerator kg = KeyGenerator.getInstance("AES");
	            /*
	             * AES (128) DES (56) DESede(168 HmacSHA1 HmacSHA256
	             */
	            kg.init(128);
	            SecretKey clave = kg.generateKey();
	            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivoClave));
	            out.writeObject(clave);
	            out.close();
	            System.out.println("Fichero clave secreta creado");
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	
}
