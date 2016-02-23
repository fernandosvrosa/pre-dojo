package br.com.gameresult.util;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.StringUtils;

public class FileUtil {
	
	private static final String BASE_64_REGEX_PATTERN =
		      "^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$";
	
	public static boolean isValideBase64Content(final String fileContent) {
	    return matches(fileContent, BASE_64_REGEX_PATTERN);
	  }
	
	public static boolean matches(final String content, final String regex) {

	    if (StringUtils.isBlank(content) || StringUtils.isBlank(regex)) {
	      return false;
	    }
	    return content.matches(regex);
	  }
	
	
	public static byte[] convert(final String base64content) {
	    return DatatypeConverter.parseBase64Binary(base64content);
	  }

}
