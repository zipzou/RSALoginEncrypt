package site.franksite.encrypt;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import site.franksite.encrpt.rsaencrypt.KeyManager;
import site.franksite.encrpt.rsaencrypt.RSAEncrypt;
import site.franksite.encrpt.rsaencrypt.RSAValidator;

public class EncryptTest {

	private static final String pubKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/h5K+Yr5VRtCyeg3g85xbi91K4hcXehTtkPOZaC19SGyrNGzq8WmvGLbbvcU7NcpJ0I/cdg0sAcbcPlt/Q/RofmYgpD0gCKz2ZIpgcAszndY0DjKhOGbNPnoIRO9ln3giPqCIfdzF8MUCgIGVLWK1ha7STUwdAFZkbz45X4KVkwIDAQAB";
	
	private static final String priKeyStr = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAL+Hkr5ivlVG0LJ6DeDznFuL3UriFxd6FO2Q85loLX1IbKs0bOrxaa8Yttu9xTs1yknQj9x2DSwBxtw+W39D9Gh+ZiCkPSAIrPZkimBwCzOd1jQOMqE4Zs0+eghE72WfeCI+oIh93MXwxQKAgZUtYrWFrtJNTB0AVmRvPjlfgpWTAgMBAAECgYB/W6xVkx2TYRqHg6fU7MkNCu0kzUCjqNdfvaJuYRrWkyFHmv3OIKew+hMl/YO4QqV3/gqbg5hwqeHNNF3tO4e6CcJqMMPjDWcnklNMmcZyl6BaBHcJHhEoOKZ8IR8ZDTbzy1fy3ZT12ge6BjYxvdEGsOWxbs7+7qzPzk0ZuG1uMQJBAOFp3UzBXnSPobij31ztJYVfENSMLoHnamzrZPl3yL8rgV5yaU3tS+h+6Ea0Zh3Rcn36x2UP5TMGwcA0IOhMzOkCQQDZhLCuQOnaB8QZlndRHw0yRBpeITyt/WhXdbDNZUiftFu7fwFLlNOR30Pboj4I2TbY2M73w7xcCWeCpzG48ZEbAkBnQp3T+YkRkHKfKMK7yQ81J7WICKeNbrt8JeFdvpfBq2ZaI8NFpXzuhqRVL3LQGhB+0ZDiJQPz+hZru/WPNQ9xAkAx6HUVdkVxxkhjSpfT4BkKCIA4Ss3+ad9P+ev4JJ4WLq7BhXGJovssRBrwwPgU1an09UH/rUSBpZK/cpdBBQQzAkBmlkxVnotMR5Nx+5hxIyBnNwxK7yamkFzd1epLX4gjKMv/gaW4D25UqD57vLemtzkUgY/X+y4J1zVOEWjYkB0T";
	
	private static final String pwd = "1234";
	
	private static final String modulus = "bf8792be62be5546d0b27a0de0f39c5b8bdd4ae217177a14ed90f399682d7d486cab346ceaf169af18b6dbbdc53b35ca49d08fdc760d2c01c6dc3e5b7f43f4687e6620a43d2008acf6648a60700b339dd6340e32a13866cd3e7a0844ef659f78223ea0887ddcc5f0c5028081952d62b585aed24d4c1d0056646f3e395f829593";
	
	@Test
	public void testEncrypt() {
		
		RSAEncrypt encrypt = new RSAValidator();
		try {
			
			byte[] decodeBase64 = Base64.decodeBase64(pubKeyStr.getBytes("utf-8"));
			
			
			
			System.out.println(decodeBase64.length);
			for (byte b : decodeBase64) {
				System.out.print(Integer.toHexString(b));
			}
			
			BigInteger keyHex = new BigInteger(decodeBase64);
			System.out.println(keyHex);
			
			byte[] byteEncrypt = encrypt.encrypt(Base64.decodeBase64(pubKeyStr.getBytes("utf-8")), pwd.getBytes());
			BigInteger bigInt = Base64.decodeInteger(decodeBase64);
			System.out.println(Base64.encodeBase64String(byteEncrypt));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
