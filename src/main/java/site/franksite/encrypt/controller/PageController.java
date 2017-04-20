package site.franksite.encrypt.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import site.franksite.encrpt.rsaencrypt.KeyManager;
import site.franksite.encrpt.rsaencrypt.RSAKeyGenerator;
import site.franksite.encrpt.rsaencrypt.RSAValidator;

@Controller
public class PageController {

	@GetMapping("/")
	public String indexPage(Model mdl, HttpSession session) {
		
		RSAKeyGenerator generator = new RSAKeyGenerator();
		byte[] privateKeyEncoded = generator.getPrivateKeyEncoded();
		byte[] publicKeyEncoded = generator.getPublicKeyEncoded();
		
		KeyManager keyMan = new RSAValidator();
		RSAPublicKey rsaPubKey = (RSAPublicKey) keyMan.restorePublicKey(Base64.decodeBase64(publicKeyEncoded));
		BigInteger publicExponent = rsaPubKey.getPublicExponent();
		BigInteger modulus = rsaPubKey.getModulus();
		
		Map<String, String> keyPair = new HashMap<String, String>();
		
		Object obj = session.getAttribute("keys"); // 原始数据
		if (null != obj) {
			// 移除原始session
			session.removeAttribute("keys");
		}
		
		session.setAttribute("keys", keyPair);
		try {
			String pubKeyStr = new String(publicKeyEncoded, "utf-8");
			String priKeyStr = new String(privateKeyEncoded, "utf-8");
			
			System.out.println(pubKeyStr);
			System.out.println(priKeyStr);
			
			keyPair.put(pubKeyStr, priKeyStr);
			mdl.addAttribute("pubKey", pubKeyStr);
			mdl.addAttribute("modulus", modulus.toString(16));
			mdl.addAttribute("pubExep", publicExponent.toString(16));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		return "login";
	}
	
}
