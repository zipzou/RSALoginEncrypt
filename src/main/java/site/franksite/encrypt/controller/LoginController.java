package site.franksite.encrypt.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import site.franksite.encrpt.rsaencrypt.RSAEncrypt;
import site.franksite.encrpt.rsaencrypt.RSAValidator;
import site.franksite.encrypt.entity.ResultEntity;

@Controller
public class LoginController {

	@PostMapping("/login")
	public @ResponseBody ResultEntity login(@RequestParam("username") String username, 
			@RequestParam("password") String passwordBased64, HttpSession session, @RequestParam("pubKey") String pubKey) {
		
		ResultEntity result = new ResultEntity();
		
		Object keys = session.getAttribute("keys");
		
		if (null == keys) {
			result.setStatus(false);
			result.setReason("该公钥已经失效！");
			result.setData(pubKey);
		} else {
			@SuppressWarnings("unchecked")
			Map<String, String> keyMap = (Map<String, String>) keys;
			
			String priKey = keyMap.get(pubKey);
			
			RSAEncrypt validator = new RSAValidator();
			byte[] dencrypt = validator.dencrypt(Base64.decodeBase64(priKey), Base64.decodeBase64(passwordBased64));
			try {
				System.out.println(new String(dencrypt, "utf-8"));

				if (new String(dencrypt, "utf-8").equals("1234")) {
					result.setStatus(true);
					result.setData(username);
				} else {
					result.setStatus(false);
					result.setReason("密码错误！");
					result.setData(username);
				}
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		session.removeAttribute("keys"); // 移除session
		
		return result;
		
	}
	
}
