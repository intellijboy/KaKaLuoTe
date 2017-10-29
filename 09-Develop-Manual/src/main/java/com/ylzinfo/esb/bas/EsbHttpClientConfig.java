package com.ylzinfo.esb.bas;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javax.crypto.Cipher;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import com.ylzinfo.esb.message.MessageType;

/**
 * ESB客户端配置
 * Copyright ylzinfo Corporation. All rights reserved.
 * @author:  <a href="xmlvrl@163.com">LvRongLin</a>
 * History:  2011-12-15 Created.
 * Version: 3.0
 */

public class EsbHttpClientConfig {
	private static boolean iIsInitialed = false ;  //是否已经完成初始化
	private static ResourceBundle iResourceBundle = null;
	private static String connProtocol = "http";
	private static String esbUrl= "";             //ESB访问URL /YlzinfoESB/esbproxy
	private static String keyStoreFile = "";    //客户端访问ESB服务器证书
	private static String keyStorePassword="";  //客户端访问ESB服务器证书密码
	private static String keyStoreType = "JKS"; //客户端访问ESB服务器证书类型
	private static String trustStoreFile = "";    //ESB服务器根证书信任证书
	private static String trustStorePassword="";  //ESB服务器根证书信任证书密码
	private static String trustStoreType = "JKS"; //ESB服务器根证书信任证书类型
	private static String certPassword=""; //客户端证书私钥加密密码
	private static String certFileType="" ; //keys/client.jks
	private static SSLSocketFactory iSSLSocketFactory = null;
	private static String certEncryptPassword ="";//客户端证书别名
	private static String certFileAlias ="";//客户端证书别名
	private static PrivateKey privK  = null;//客户端证书私钥
	private static PublicKey  publick =null;//客户端证书公钥
	private static Certificate     iTrustCertificate = null; //客户端信任证书
	public EsbHttpClientConfig(){	  
	}  
	private static void bundle() throws EsbException //绑定客户端初始化信息
	{
		if(!iIsInitialed){
			try{
				iResourceBundle = ResourceBundle.getBundle("ylzinfoMQ"); //资源文件
			}
			catch(Exception e){
				iResourceBundle = null;
				iIsInitialed=true;
				return;
				//throw new EsbException("ESB客户端配置文件ylzinfoMQ.properties找不到.");
			}
			try{
				esbUrl=iResourceBundle.getString("ESBURL").trim(); 
			} catch(Exception e){
				throw new EsbException("ESB客户端配置文件ylzinfoMQ.properties：没有声明ESBURL属性");
			}	 
			try{
				connProtocol=esbUrl.substring(0,5);
				connProtocol=connProtocol.equalsIgnoreCase("https")?"https":"http"; 
				if(connProtocol.equalsIgnoreCase("https")){
					trustStoreFile =iResourceBundle.getString("TrustStoreFile").trim();  
					trustStorePassword=iResourceBundle.getString("TrustStorePassword").trim();
					byte[] trust_pass_new = Decrypt.xansi_pwd(IConstants.ENCRYPT_KEY.getBytes(), trustStorePassword.getBytes());
					if(trust_pass_new==null){
						throw new EsbException("ESB服务器根证书信任证书文件TrustStorePassword密码有误!");
					}
					trustStorePassword=new String(trust_pass_new);
					trustStoreType=iResourceBundle.getString("TrustStoreType").trim();
				
					keyStoreFile =iResourceBundle.getString("KeyStoreFile").trim();  
					keyStorePassword=iResourceBundle.getString("KeyStorePassword").trim();
					byte[] key_pass_new = Decrypt.xansi_pwd(IConstants.ENCRYPT_KEY.getBytes(), keyStorePassword.getBytes());
					if(key_pass_new==null){
						throw new EsbException("客户端访问ESB服务器证书KeyStorePassword密码有误!");
					}
					keyStorePassword=new String(key_pass_new);
					keyStoreType=iResourceBundle.getString("KeyStoreType").trim();
					initSSL();
				}
			} catch(Exception e){
				throw new EsbException("ESB客户端配置文件ylzinfoMQ.properties：没有声明ConnectionProtocol属性");
			}
			try{
				certPassword=iResourceBundle.getString("CertPassword").trim();
				certEncryptPassword=certPassword;
				byte[] pass_new = Decrypt.xansi_pwd(IConstants.ENCRYPT_KEY.getBytes(), certPassword.getBytes());
				if(pass_new==null){
					throw new EsbException("客户端证书CertPassword私钥密码不正确!");
				}
				certPassword=new String(pass_new);
			} catch(Exception e){
				throw new EsbException("ESB客户端配置文件ylzinfoMQ.properties：没有声明CertPassword属性");
			}
			try{
				certFileType=iResourceBundle.getString("CertFileType").trim(); 
			} catch(Exception e){
				throw new EsbException("ESB客户端配置文件ylzinfoMQ.properties：没有声明CertFileType属性");
			}
			String  userCrtFile = null ;
			try{
				userCrtFile=iResourceBundle.getString("CertFile").trim(); 	
			} 
			catch(Exception e){
				throw new EsbException("ESB客户端配置文件ylzinfoMQ.properties：没有声明UserCrtFile属性");
			} 
			iTrustCertificate =  getCertificate(userCrtFile);
			iIsInitialed=true;
		}
	}

	/**
	 * 获取通讯协议
	 * @return
	 * @throws EsbException
	 */
	public static String getConnProtocol() throws EsbException{ 
		bundle();
		return connProtocol;
	}
	public static String getEsbUrl() throws EsbException {
		bundle();
		return esbUrl;
	}
	
	  public static SSLSocketFactory getSSLSocketFactory()
	    throws EsbException
	  {
	    bundle();
	    return iSSLSocketFactory;
	  }

	  public static String getCertFileAlias()throws EsbException {
	    bundle();
		return certFileAlias;
	 }
  
	  
	public static String getCertEncryptPassword() throws EsbException {
	    bundle();
		return certEncryptPassword;
	}

	private static void initSSL() throws EsbException
  {
	    try
	    {
	      SSLContext tSSLContext = SSLContext.getInstance("TLS");
	      TrustManagerFactory tTrustManagerFactory = TrustManagerFactory.getInstance("SunX509");
	      KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
	      KeyStore kKeyStore = KeyStore.getInstance(keyStoreType);
	      KeyStore tKeyStore = KeyStore.getInstance(trustStoreType);
	      //数字证书解析
		  InputStream kin=EsbHttpClientConfig.class.getResourceAsStream(keyStoreFile);
		  InputStream tin=EsbHttpClientConfig.class.getResourceAsStream(trustStoreFile);
		  if(kin==null){
				throw new EsbException("客户端访问ESB服务器证书:"+keyStoreFile+" 找不到");	
			}
		  if(tin==null){
				throw new EsbException("ESB服务器根证书信任证书:"+trustStoreFile+" 找不到");	
			}
		  kKeyStore.load(kin, keyStorePassword.toCharArray());
		  kin.close();
	      tKeyStore.load(tin, trustStorePassword.toCharArray());
	      tin.close();
	      tTrustManagerFactory.init(tKeyStore);
	      kmf.init(kKeyStore, keyStorePassword.toCharArray());
	      tSSLContext.init(kmf.getKeyManagers(), tTrustManagerFactory.getTrustManagers(), null);
	      iSSLSocketFactory = tSSLContext.getSocketFactory();
	    }
	    catch (Exception e) {
	      throw new EsbException("客户端初始化访问ESB服务器HTTPS出错:"+e.getMessage());
	    }
	  
	  }
	
	//根据证书路径获取信任客户端证书
	private static Certificate getCertificate(String userCrtFile) throws EsbException{
		Certificate cert = null;
		//数字证书解析
		InputStream in=EsbHttpClientConfig.class.getResourceAsStream(userCrtFile);
		if(in==null){
			throw new EsbException("ESB客户端密钥文件:"+userCrtFile+" 找不到");	
		}
		try {
			KeyStore ks = KeyStore.getInstance(certFileType);
			ks.load(in, certPassword.toCharArray());
			in.close();
			Enumeration<String> en = ks.aliases();
			while (en.hasMoreElements())
			{
				certFileAlias = (String) en.nextElement();
				cert = ks.getCertificate(certFileAlias);
		        X509Certificate t=(X509Certificate) cert;
		        Date endDate=t.getNotAfter();
		        if(endDate.before(new Date())){
		        	throw new EsbException("ESB客户端访问证书无效。原因：证书"+userCrtFile+"已过期失效，请重新申请！");
		        }
				privK=(PrivateKey) ks.getKey(certFileAlias,certPassword.toCharArray());
				publick=cert.getPublicKey();
			}
		} catch (Exception e) {
			throw new EsbException("ESB客户端证书格式有误,原因如下:"+e.getMessage());	
		}       
		return cert;
	}
	
    /**
     * 消息签名
     * @param msgType
     * @param param
     * @param paramValue
     * @return
     * @throws EsbException
     */
    public  static String[]  signMessage(MessageType msgType,String[] param,String[] paramValue) throws EsbException{   	
    	String[] signAndType=new String[2];
    	try{
        	String signatureData = null;   //数据签名
        	StringBuffer signSourceData=new StringBuffer();
        	for(int i=0;i<param.length;i++){
        		signSourceData.append(param[i])
        		              .append("=")
        		              .append(paramValue[i]).append("&");
        	}
        	if(msgType == MessageType.MT_SIGN_TIMP){ //签名、签名+加密
        		//使用私鈅签名
        		Signature sig=Signature.getInstance("MD5WithRSA");
        		sig.initSign(privK);
        		sig.update(signSourceData.toString().getBytes());
        		byte[] signature=sig.sign();
        		signatureData=StringUtils.encodeHex(signature);
        		signAndType[0]=signatureData;
        		signAndType[1]=IConstants.SECURITY_POLICY_SIGN; 
        	}
//        	else if(msgType == MessageType.MT_SECURITY){ //加密
//        		//获得一个私鈅加密类Cipher，ECB是加密方式，PKCS5Padding是填充方法
//        		Cipher cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");
//        		cipher.init(Cipher.ENCRYPT_MODE,privK);
//        		byte[] cipherText=cipher.doFinal(signSourceData.toString().getBytes());
//        		signatureData=StringUtils.encodeHex(cipherText);
//        		signAndType[0]=signatureData;
//        		signAndType[1]=IConstants.SECURITY_POLICY_ENCRYPT; 
//        	}
    	}catch(Exception e)
    	{
    		throw new EsbException("数字签名失败,原因如下:"+e.getMessage());
    	}	
		return signAndType;
    }
    
    /**
     * 消息解密
     * @param responseMsg
     * @return
     * @throws EsbException
     */
    public  static void  decodeMessage(String responseMsg) throws EsbException{   	
    	try{
    		byte[] decodeByteMsg =StringUtils.decodeHex(responseMsg);
    		//获得一个私鈅加密类Cipher，ECB是加密方式，PKCS5Padding是填充方法
    		Cipher cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");
    		cipher.init(Cipher.DECRYPT_MODE,publick);
    		byte[] newDecodeByteMsg=cipher.doFinal(decodeByteMsg);
    		responseMsg=new String(newDecodeByteMsg,"UTF8");
    	}catch(Exception e)
    	{
    		throw new EsbException("返回消息不合法，消息解密失败,原因如下:"+e.getMessage());
    	}	
    }
}
