package com.ylzinfo.esb.bas;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.ConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

 
 
 
 
/**
 * Copyright ylzinfo Corporation. All rights reserved.
 * @author moujl
 * History: 2015-5-17 Created.
 * version: 1.1
 * info:加解密工具类。在esbos,ylzinfoesb,esbclient-http,esbsdk这四个工程里的均有此类，而且除了所在包位置不同外，所有的代码均一致，以后如果加密模块没在变动，可考虑将其弄成jar包调用，避免代码冗余。
 */
public  class ServiceEncryptUtil {
 
 
    
    /**
	 * AES加密 
	 * @param ls_source
	 * @param ls_key
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException 
	 */
    
    public static String encryptByAes(String ls_source, String ls_key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {                          
    	ls_source= java.net.URLEncoder.encode(ls_source.toString(), "UTF-8");
    	KeyGenerator kgen = KeyGenerator.getInstance("AES");  
    	SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(ls_key.getBytes());
        kgen.init(128, secureRandom);
        SecretKey secretKey = kgen.generateKey();   
        byte[] enCodeFormat = secretKey.getEncoded(); 
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");  
        Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
        cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化   
        byte[] result = cipher.doFinal(ls_source.getBytes());  
        String ls_result = new String(Base64.encodeBase64(result));
        //return new String(Base64.encode(result)); // 加密             
        return ls_result;
    }   
    
   
      
    /**
     * AES解密
     * @param ls_source
     * @param ls_key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws Base64DecodingException
     * @throws UnsupportedEncodingException 
     */
     public static String decryptByAes(String ls_source, String ls_key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException,  UnsupportedEncodingException {      
          KeyGenerator kgen = KeyGenerator.getInstance("AES"); 
          SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
  		  secureRandom.setSeed(ls_key.getBytes());
          kgen.init(128, secureRandom);   
          SecretKey secretKey = kgen.generateKey(); 
          byte[] enCodeFormat = secretKey.getEncoded();
          SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");   
          Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
          cipher.init(Cipher.DECRYPT_MODE, key);// 初始化   
          byte[] result = cipher.doFinal(Base64.decodeBase64(ls_source.getBytes()));
          String ls_result= java.net.URLDecoder.decode(new String(result), "UTF-8");
          return ls_result;
              
     }  
     
     
     /**
 	 * RSA加密
 	 * @param ls_source
     * @param ls_key
     * @throws NoSuchPaddingException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeySpecException 
     * @throws ConfigurationException 
     * @throws InvalidKeyException 
     * @throws UnsupportedEncodingException 
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
 	 * @throws Exception
 	 */
     public static String  encryptByRSA(String ls_source, String ls_key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
  		Cipher cipher=Cipher.getInstance("RSA");  
  		RSAPublicKey publicKey = (RSAPublicKey)getKeyObjFromKeyStr(ls_key, 1); 	 
  		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
  		byte[] results=cipher.doFinal(ls_source.getBytes("UTF-8"));		
  		return new String(Base64.encodeBase64(results),"UTF-8");
  	}
     
     
     /**
      * RSA解密
      * @param ls_source
      * @param ls_key
      * @return
     * @throws NoSuchPaddingException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeySpecException 
     * @throws ConfigurationException 
     * @throws InvalidKeyException 
     * @throws UnsupportedEncodingException 
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
     * @throws Base64DecodingException 
      */
      public static String decryptByRSA(String ls_source, String ls_key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException  {      
    	  
    	  Cipher cipher=Cipher.getInstance("RSA");    	  
    	  RSAPrivateKey privateKey = (RSAPrivateKey)getKeyObjFromKeyStr(ls_key, 0);	 
    	  cipher.init(Cipher.DECRYPT_MODE, privateKey);   		 
    	  byte[] results=cipher.doFinal(Base64.decodeBase64(ls_source.getBytes()));   	
    	  return new String(results);     
    	 
      }  
     
      /**
       * 从密匙字符串获取RSA非对称加密的密匙
       * @param ls_keyStr
       * @param type
       * @return
       * @throws NoSuchAlgorithmException
       * @throws InvalidKeySpecException
       */
     public static Key getKeyObjFromKeyStr(String ls_keyStr, int type) throws NoSuchAlgorithmException, InvalidKeySpecException   {   
    	 KeyFactory keyFactory = KeyFactory.getInstance("RSA");      
         if (type == 0) {   
        	 // privateKey
             PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(   
                     toBytes(ls_keyStr));   
             PrivateKey privateKey = keyFactory.generatePrivate(priPKCS8);   
             return privateKey;   
   
         } else {   
             // publicKey    
             X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(   
                     toBytes(ls_keyStr));   
             PublicKey publicKey = keyFactory.generatePublic(bobPubKeySpec);   
             return publicKey;   
         }   
     }   
 	
     /**
      * 字符串转化成字节
      * @param s
      * @return
      */
     public static final byte[] toBytes(String s) {   
         byte[] bytes;   
         bytes = new byte[s.length() / 2];   
         for (int i = 0; i < bytes.length; i++) {   
             bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2),   
                     16);   
         }   
         return bytes;   
     } 
     
     
  
     /** 
      * 将包头和包身以及包尾分出来 
      * @param ls_soapxml
      * @return
      */
    public static List getSoapHeadAndBody(String ls_soapxml)  
 	{
 		List returnList = new ArrayList(); 
 		int ii_begin=ls_soapxml.toLowerCase().indexOf("<soap:body>");
 		if(ii_begin>0) //有body这标签
 		{
 			int ii_end=ls_soapxml.toLowerCase().indexOf("</soap:body>")+("</soap:body>").length();
 	 		returnList.add(ls_soapxml.substring(0, ii_begin));
 	 		returnList.add(ls_soapxml.substring(ii_begin,ii_end));
 	 		returnList.add(ls_soapxml.substring(ii_end,ls_soapxml.length())); 
 		}
 		else
 		{
 			ii_begin=ls_soapxml.toLowerCase().indexOf("</soap:header>")+("</soap:header>").length();
 			int ii_end=ls_soapxml.toLowerCase().indexOf("</soap:envelope>");
 			returnList.add(ls_soapxml.substring(0, ii_begin));
 	 		returnList.add(ls_soapxml.substring(ii_begin,ii_end));
 	 		returnList.add(ls_soapxml.substring(ii_end,ls_soapxml.length())); 
 		}
 		
 		return returnList;
 	} 
    
    
    /**
     * 生成密匙
     * @param ls_type
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static List generateKey(String ls_type) throws NoSuchAlgorithmException
    {
    	List listReturn = new ArrayList();
    	if(ls_type.equals("RSA"))
    	{
    		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");   
            keyPairGen.initialize(1024, new SecureRandom());     
            KeyPair keyPair = keyPairGen.generateKeyPair();   
            PublicKey pubkey = keyPair.getPublic();   
            PrivateKey prikey = keyPair.getPrivate();   
            
            listReturn.add(toHexString(pubkey.getEncoded()));
            listReturn.add(toHexString(prikey.getEncoded()));
    	} 
    	if (ls_type.equals("AES"))
    	{
    		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom;
            secureRandom = new SecureRandom();             
            keyGenerator.init(128, secureRandom); 
            SecretKey secretKey = keyGenerator.generateKey(); 
            listReturn.add(Base64.encodeBase64(secretKey.getEncoded()));
    	} 
    	
    	return listReturn;
    	
    }
    
   
    /**
     * 将字节流转化成16进制字符串  
     * @param b
     * @return
     */
    private static String toHexString(byte[] b) {   
        StringBuilder sb = new StringBuilder(b.length * 2);   
        for (int i = 0; i < b.length; i++) {   
            sb.append(HEXCHAR[(b[i] & 0xf0) >>> 4]);   
            sb.append(HEXCHAR[b[i] & 0x0f]);   
        }   
        return sb.toString();   
    }   
  
    private static char[] HEXCHAR = { '0', '1', '2', '3', '4', '5', '6', '7',   
        '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };  
 
    
    /**
     * 根据服务id从xml字符串获取密匙
     * @param ls_serviceid
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Map getKeyMessageFromXmlStr(String ls_serviceid) throws ParserConfigurationException, SAXException, IOException
    {
    	 TreeMap returnMap = new TreeMap();
    	 //得到DOM解析器的工厂实例
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         //从DOM工厂中获得DOM解析器
         DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
         //把要解析的xml文档读入DOM解析器
         Document doc = dbBuilder.parse(ServiceEncryptUtil.class.getResourceAsStream("/keyStore.xml"));
         //得到文档名称为Student的元素的节点列表
         NodeList nList = doc.getElementsByTagName("akey"); 
         //遍历该集合，显示结合中的元素及其子元素的名字
         for(int i = 0; i< nList.getLength() ; i ++){
             Element node = (Element)nList.item(i);
             if(node.getAttribute("serviceid").equals(ls_serviceid))
             {         	 
            	 returnMap.put("algorithm", node.getElementsByTagName("algorithm").item(0).getFirstChild().getNodeValue());
            	 returnMap.put("encryptkey", node.getElementsByTagName("encryptkey").item(0).getFirstChild().getNodeValue());
            	 returnMap.put("publickeyserver", node.getElementsByTagName("publickeyserver").item(0).getFirstChild().getNodeValue());
            	 returnMap.put("privatekeyclient", node.getElementsByTagName("privatekeyclient").item(0).getFirstChild().getNodeValue());
            	 return returnMap;
             }
          }
         nList = doc.getElementsByTagName("defaultkey"); 
         //遍历该集合，显示结合中的元素及其子元素的名字      
         Element node = (Element)nList.item(0);  
         if (node!=null)
         {
	         returnMap.put("algorithm", node.getElementsByTagName("algorithm").item(0).getFirstChild().getNodeValue());
	         returnMap.put("encryptkey", node.getElementsByTagName("encryptkey").item(0).getFirstChild().getNodeValue());
	         returnMap.put("publickeyserver", node.getElementsByTagName("publickeyserver").item(0).getFirstChild().getNodeValue());
	         returnMap.put("privatekeyclient", node.getElementsByTagName("privatekeyclient").item(0).getFirstChild().getNodeValue());      
         }
    	 return returnMap; 
    }
    
     
    
	
    /**
     * 对将返回给客户端的xml进行封装加密
     * @param xml
     * @param clientPublicKeyStr
     * @throws NoSuchAlgorithmException 
     * @throws Base64DecodingException 
     * @throws UnsupportedEncodingException 
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
     * @throws InvalidKeySpecException 
     * @throws NoSuchPaddingException 
     * @throws InvalidKeyException 
     */
//    public static String getServiceEncryptSoap(String ls_soap,String clientPublicKeyStr) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
//    {
//    	List listTemp = generateKey("AES");
//    	String ls_key=listTemp.get(0).toString();    	
//    	String ls_encryptKey = encryptByRSA(ls_key,clientPublicKeyStr);
//    	List list =getSoapHeadAndBody(ls_soap);
//    	String ls_encryptBody = encryptByAes(list.get(1).toString(),ls_key);//temp
//    	String ls_head = list.get(0).toString();
//    	int ii_begin = ls_soap.toLowerCase().indexOf("</out:system>");
//    	StringBuffer ls_resultBuf = new StringBuffer();
//    	ls_resultBuf.append(ls_head.substring(0, ii_begin));
//    	ls_resultBuf.append("<para encryptkey=\"");
//    	ls_resultBuf.append(ls_encryptKey);
//    	ls_resultBuf.append("\"/>");
//    	ls_resultBuf.append(ls_head.substring(ii_begin,ls_head.length())); 
//    	ls_resultBuf.append(ls_encryptBody); 
//    	ls_resultBuf.append(list.get(2).toString());
//    	return ls_resultBuf.toString();
//    }
 
    public static String getServiceEncryptSoap(String ls_soap,String clientPublicKeyStr) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
    {
    	List listTemp = generateKey("AES");
    	String ls_key=listTemp.get(0).toString();    	
    	String ls_encryptKey = encryptByRSA(ls_key,clientPublicKeyStr);
    	List list =getSoapHeadAndBody(ls_soap);
    	String ls_encryptBody = encryptByAes(list.get(1).toString(),ls_key);//temp
    	String ls_head = list.get(0).toString();
    	int ii_begin = ls_soap.toLowerCase().indexOf("</out:system>");
    	if(ii_begin<0)
    	{
    		ii_begin = ls_soap.toLowerCase().indexOf("</soap:header>");
    	}
    	StringBuffer ls_resultBuf = new StringBuffer();
    	ls_resultBuf.append(ls_head.substring(0, ii_begin));
    	ls_resultBuf.append("<para encryptkey=\"");
    	ls_resultBuf.append(ls_encryptKey);
    	ls_resultBuf.append("\"/>");
    	ls_resultBuf.append(ls_head.substring(ii_begin,ls_head.length())); 
    	ls_resultBuf.append(ls_encryptBody); 
    	ls_resultBuf.append(list.get(2).toString());
    	return ls_resultBuf.toString();
    }
    
    /**
     * 将非对称加密密匙信息构建成xml字符串
     * @param list
     * @return
     */
    public static String createXmlStrFromKeyMessage(List list)
    {
    	StringBuffer result = new StringBuffer();   	 
    	result.append("<keys>"); 
    	for  (int i=0;i<list.size();i++)
    	{
    		result.append("<akey serviceid=\"");
    		Map aKey = (Map)list.get(i);
    		result.append(aKey.get("serviceid"));
    		result.append("\">");
    		result.append("<algorithm>");
    		result.append(aKey.get("algorithm"));
    		result.append("</algorithm>");
    		result.append("<encryptkey>");
    		result.append(aKey.get("encryptkey"));
    		result.append("</encryptkey>");
    		result.append("<publickeyserver>");
    		result.append(aKey.get("publickeyserver"));
    		result.append("</publickeyserver>");
    		result.append("<privatekeyclient>");
    		result.append(aKey.get("privatekeyclient"));
    		result.append("</privatekeyclient>");
    		result.append("</akey>");
    		
    	}
    	result.append("</keys>");  
    	return result.toString();
    }
    
    public static String createXmlStrFromMessage(List listDefaultKey,List listServiceKey)
    {
    	StringBuffer result = new StringBuffer();  
    	result.append("<root>");    	
    	result.append(createXmlStrFromDefaultMessage(listDefaultKey));
    	result.append(createXmlStrFromKeyMessage(listServiceKey));
    	result.append("</root>");
    	return result.toString();
    }
    
    public static String createXmlStrFromDefaultMessage(List list)
    {
    	StringBuffer result = new StringBuffer();   	     
		result.append("<defaultkey>");
		Map aKey = (Map)list.get(0); 
		result.append("<algorithm>");
		result.append(aKey.get("algorithm"));
		result.append("</algorithm>");
		result.append("<encryptkey>");
		result.append(aKey.get("encryptkey"));
		result.append("</encryptkey>");
		result.append("<publickeyserver>");
		result.append(aKey.get("publickeyserver"));
		result.append("</publickeyserver>");
		result.append("<privatekeyclient>");
		result.append(aKey.get("privatekeyclient"));
		result.append("</privatekeyclient>");
		result.append("</defaultkey>");    		  
    	return result.toString();
    }
    
    public static void main(String[] args)   {   
    	try {
//    		String ls_return = ServiceEncryptUtil.encryptByRSA("我测非对称加密中文","30819f300d06092a864886f70d010101050003818d00308189028181008423859afa84559518b43c84729f72cc57c9309bffd46b9b9119f841d9a418245b0070caca3adba064ab6abba06d0f7b53fc02c55605f3adb40f7334d97ab74e5cdf47bf95c87f9bcf5220d828fdbc4c876bb1b3584308ea2a306f867a10ea2b4ba25e8dc300b0ccd07abdc1fc3e73c40c3e7237bb247a0802cd1969ce6a17eb0203010001");
//    		ls_return = ServiceEncryptUtil.decryptByRSA(ls_return, "30820276020100300d06092a864886f70d0101010500048202603082025c020100028181008423859afa84559518b43c84729f72cc57c9309bffd46b9b9119f841d9a418245b0070caca3adba064ab6abba06d0f7b53fc02c55605f3adb40f7334d97ab74e5cdf47bf95c87f9bcf5220d828fdbc4c876bb1b3584308ea2a306f867a10ea2b4ba25e8dc300b0ccd07abdc1fc3e73c40c3e7237bb247a0802cd1969ce6a17eb02030100010281806fc1ab49fbfed8731545bab93a4868cb32a3d21cde2e46247880425b4f3b5dbfc1c7899db99436145d902ce12b399212786177e6766eea3d4b4095eb68d4451b22487364a2c2200bf16243a94e4d9ef7716788badd4e3558bce5731619dd77aafcadb4d3d09eadd548014b1af769ea4fcea05dc991da9291f6bd76be948a0a41024100d903519b0fc4e239d1419f8c358a028adf5a23dc93575131fe793f01e96e775165d9dbf286ee3b1ef30e3569118c82e1f0d9fe6c4551d60a35a00e3b342a6f090241009be0bd16b1d7564d5993983e6fe693403918f3d33b3064ae89050f2c70077a67b4a359e674af47163ca5ceb25bb7adb3c98cc7b25631a70e419142b28cd5585302406cccae2838a2b3ecdacdb4c8ddbac085916f26d59a85476ef22f2578b6e8ac278211d4a4e623203ee70ab39a66b2f4978462369d5fafa4c8df93cc2ec07b99c90240487c7df1ebc9836c7cd751d8d71896292b641b1e834bc1c61819ae30aff80a47e78c7ee5d8251a1d911e59bc3f426f1848059b67a70577f5fd2f9ba0843541d9024100c3043df004f58d580fd3efa121f0057a4c700f02196411bfa31f72534a979a67987067da85c9e0fb8254380f15b88c46f35df7625fe1f1b014b63358390162c2");
//    		System.out.println(ls_return);
    		//ServiceEncryptUtil.encryptASoap("<?xml version=\"1.0\" encoding=\"UTF-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"><soap:Header><in:system xmlns:in=\"http://www.ylzinfo.com/\"><para usr=\"esba\"/><para pwd=\"F6F7943A85129B08\"/><para sid=\"abcd\"/></in:system></soap:Header><soap:Body><in:business xmlns:in=\"http://www.ylzinfo.com/\"></in:business></soap:Body></soap:Envelope>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    }   
  
 
	
}
