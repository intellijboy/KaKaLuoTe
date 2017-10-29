package com.ylzinfo.esb.client;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.ylzinfo.esb.bas.ServiceEncryptUtil;

/* Copyright ylzinfo Corporation. All rights reserved.
 * @author:  moujl 
 * History: 2015-6-17 Created.
 * Version: 1.0
 * info:此类为key生成辅助类，是为了简化客户端调用代码而建立
 */
public  class ClientKeyHelper {
	/**
	 * 初始化非对称密匙
	 * @param ls_type
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	public static String initKey(XMLRequest req) throws Exception  
	{ 
		Map map = ServiceEncryptUtil.getKeyMessageFromXmlStr(req.getSvid()); 
		Object ls_algorithm = map.get("algorithm");
		if (ls_algorithm==null) throw new Exception("没有服务("+req.getSvid()+")相关的加密配置");
		String ls_type = ls_algorithm.toString();
		req.setEncryptType(ls_type);
		if (ls_type.equals(XMLRequest.FDCJM))
		{			 
			List list = ServiceEncryptUtil.generateKey("AES");
			String ls_key = list.get(0).toString();			
			String ls_temp = ServiceEncryptUtil.encryptByRSA(ls_key,map.get("publickeyserver").toString());
			req.setEncryptKey(ls_temp); 
			return ls_key;
		}
		if (ls_type.equals(XMLRequest.DCJM))
		{			 
			String ls_key =  map.get("encryptkey").toString();
			return ls_key;
		}
		return null;
		
	}
	
	/**
	 * 获取服务端返回的包头中的对称密匙
	 * @param ls_soapxml
	 * @param req
	 * @return
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws Base64DecodingException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
 
	 */
	public static String getEncryptFromSoap(String ls_soapxml,XMLRequest req) throws ParserConfigurationException, SAXException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, Base64DecodingException  
	{
		List list =ServiceEncryptUtil.getSoapHeadAndBody(ls_soapxml);
		String ls_head = list.get(0).toString(); 
		String ls_keyWord = "encryptkey=\"";
		int ii_begin = ls_head.indexOf(ls_keyWord)+ls_keyWord.length();
		int ii_end = ls_head.indexOf("\"", ii_begin);
        String ls_source = ls_head.substring(ii_begin, ii_end);      
        Map map = ServiceEncryptUtil.getKeyMessageFromXmlStr(req.getSvid()); 
		String ls_decryptKey = ServiceEncryptUtil.decryptByRSA(ls_source,map.get("privatekeyclient").toString());
		return ls_decryptKey;
	}
}
