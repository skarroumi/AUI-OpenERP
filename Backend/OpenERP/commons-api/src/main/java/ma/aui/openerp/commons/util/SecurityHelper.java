package ma.aui.openerp.commons.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class SecurityHelper 
{
	
	public String generateMD5 (String str) throws Exception {
						
	byte[] uniqueKey = str.getBytes();
	byte[] hash = null;
	
	hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
	StringBuffer hashString = new StringBuffer();
	
	for (int i = 0; i < hash.length; ++i) {
	String hex = Integer.toHexString(hash[i]);
	if (hex.length() == 1) {
	hashString.append("0");
	hashString.append(hex.charAt(hex.length() - 1));
	}
	else 
		hashString.append(hex.substring(hex.length() - 2));
	}
	
	return hashString.toString();

	}

}
