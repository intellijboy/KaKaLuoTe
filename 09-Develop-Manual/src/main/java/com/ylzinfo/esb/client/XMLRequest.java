package com.ylzinfo.esb.client;


import com.ylzinfo.esb.bas.EsbException;
import com.ylzinfo.esb.bas.EsbHttpClientConfig;
import com.ylzinfo.esb.bas.EsbResponse;
import com.ylzinfo.esb.bas.MessageEnvelop;
import com.ylzinfo.esb.bas.ServiceEncryptUtil;
import com.ylzinfo.esb.message.MessageType;
import com.ylzinfo.esb.util.FormatConversionUtil;
import com.ylzinfo.esb.util.GZipUtil;
import com.ylzinfo.esb.util.HttpClientUtils; 

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.XMLFormatter;
import java.util.zip.GZIPOutputStream;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

public class XMLRequest
{
  private String svid;
  private boolean isGzip;
  
  
private String[] param;
  private String[] paramValue;
  private String router;
  private String esbUrl;
  private String[] esbUserPwd;
  private ArrayList paramList;
  private List rowInfo;
  private MessageType msgType;    //消息类型
  public static String FDCJM="101"; //非对称加密 add by moujl 20150612
  public static String DCJM="001"; //对称加密 add by moujl 20150612
  protected String encryptKey;	//客户端传过来的被非对称加密过的随机对称加密密匙 add by moujl 20150612
  private String encryptType="DCJM";//加密方式 默认为对称加密 add by moujl 20150612 
  private String otherMode="SOAP";
  
  public XMLRequest(){
		this.msgType=MessageType.MT_COMMON; //普通消息类型
		//this.msgType=MessageType.MT_SECURITY;
		//this.msgType=MessageType.MT_SECURITY_SIGN_TIMP;
		//this.msgType=MessageType.MT_SIGN_TIMP;
	}
  public MessageType getMsgType() {
		return msgType;
	}

	public void setMsgType(MessageType msgType) {
		this.msgType = msgType;
	}
  public String getSvid()
  {
    return this.svid; }

  public void setSvid(String svid) {
    this.svid = svid; }

  public String[] getParam() {
    return this.param; }

  public void setParam(String[] param) {
    this.param = param; }

  public String[] getParamValue() {
    return this.paramValue; }

  public void setParamValue(String[] paramValue) {
    this.paramValue = paramValue;
  }

  public String getEsbUrl()
  {
    return this.esbUrl; }

  public void setEsbUrl(String esbUrl) {
    this.esbUrl = esbUrl; }

  public String[] getEsbUserPwd() {
    return this.esbUserPwd; }

  public void setEsbUserPwd(String[] esbUserPwd) {
    this.esbUserPwd = esbUserPwd; }

  public boolean isGzip() {
    return this.isGzip; }

  public void setGzip(boolean isGzip) {
    this.isGzip = isGzip; }

  public ArrayList getParamList() {
    return this.paramList; }

  public void setParamList(ArrayList paramList) {
    this.paramList = paramList; }

  public List getRowInfo() {
    return this.rowInfo; }

  public void setRowInfo(List rowInfo) {
    this.rowInfo = rowInfo;
  }

  public String getRouter() {
    return this.router; }

  public void setRouter(String router) {
    this.router = router;
  }

  
  public String getEncryptKey() {
	  return encryptKey;
	}
	
	public void setEncryptKey(String encryptKey) {
		this.encryptKey = encryptKey;
	}
	
public String getEncryptType() {
		return encryptType;
	}

	public void setEncryptType(String encryptType) {
		
		this.encryptType = encryptType;
	}
	public String getOtherMode() {
		return this.otherMode;
	}
	public void setOtherMode(String otherMode) {
		this.otherMode = otherMode;
	}

public EsbResponse postXmlRequest() {
    MessageEnvelop msgEnvelop;
    EsbResponse esbResponse = new EsbResponse();
    try {
      checkRequest();
     // Object soapMsg = genRequestMessage(this.esbUserPwd[0], this.esbUserPwd[1]);
      Object soapMsg = genRequestMessage(EsbHttpClientConfig.getCertFileAlias(),EsbHttpClientConfig.getCertEncryptPassword());      
      //soapMsg = "<soap:Envelope><soap:Header><in:system xmlns:in=\"http://www.ylzinfo.com/\"><para usr=\"esba\"/><para pwd=\"F6F7943A85129B08\"/><para sid=\"mytest\"/></in:system></soap:Header><soap:Body><in:business xmlns:in=\"http://www.ylzinfo.com/\"><para bcc001=\"123\"/></in:business></soap:Body></soap:Envelope>";
      //System.out.println(soapMsg);
     // soapMsg = XmlFormat.formatXml(soapMsg.toString());
      System.out.println(soapMsg);
      if (this.otherMode.equals("JSONMODE"))
      {
    	  soapMsg = FormatConversionUtil.soapToJson(soapMsg.toString());
      }
      System.out.println(soapMsg);
//      System.out.println(soapMsg);
//      soapMsg = FormatConversionUtil.jsonToSoap(soapMsg.toString());
//      System.out.println(soapMsg);
      String responseData = sendSoapMessage(soapMsg);
      esbResponse.setResponseData(responseData);
    }
    catch (EsbException e) {
      e.printStackTrace();
      msgEnvelop = new MessageEnvelop();
      esbResponse.setResponseData(msgEnvelop.createMsgFault(e.getMessage(), e.getErrorCode()));
      e.printStackTrace();
    }
    catch (Exception e) {
      e.printStackTrace();
      msgEnvelop = new MessageEnvelop();
      esbResponse.setResponseData(msgEnvelop.createMsgFault("发送SOAP信息失败,原因如下:" + e.getMessage() + "访问被拒绝!"));
      e.printStackTrace();
    }
    return esbResponse;
  }
   
  public EsbResponse postXmlRequest(String ls_key) {
	    MessageEnvelop msgEnvelop;
	    EsbResponse esbResponse = new EsbResponse();
	    try {
	        checkRequest();
	        //Object soapMsg = genRequestMessage(this.esbUserPwd[0], this.esbUserPwd[1]);
	        Object soapMsg = genRequestMessage(EsbHttpClientConfig.getCertFileAlias(),EsbHttpClientConfig.getCertEncryptPassword());  
	        //System.out.println(soapMsg);
	        /////////add by moujl 20150507 begin///////////////////
			List list = ServiceEncryptUtil.getSoapHeadAndBody(soapMsg.toString());
			soapMsg = list.get(0) +ServiceEncryptUtil.encryptByAes(list.get(1).toString(), ls_key)+list.get(2).toString();
			//System.out.println(soapMsg);
			if (this.otherMode.equals("JSONMODE"))
		    {
				soapMsg = FormatConversionUtil.soapToJson(soapMsg.toString());
		    }
			/////////add by moujl 20150507 end///////////////////
	        String responseData = sendSoapMessage(soapMsg);
	        /////////add by moujl 20150507 begin///////////////////
			if (responseData.toLowerCase().indexOf("<soap:body>")<0)
			{								
				if (encryptType.equals(FDCJM))
				{
					ls_key=ClientKeyHelper.getEncryptFromSoap(responseData,this);
				}
				list = ServiceEncryptUtil.getSoapHeadAndBody(responseData);
				responseData = list.get(0) +ServiceEncryptUtil.decryptByAes(list.get(1).toString(), ls_key)+list.get(2).toString();
			}			
			/////////add by moujl 20150507 end///////////////////		
	        esbResponse.setResponseData(responseData);
	    }
	    catch (EsbException e) {
	      e.printStackTrace();
	      msgEnvelop = new MessageEnvelop();
	      esbResponse.setResponseData(msgEnvelop.createMsgFault(e.getMessage(), e.getErrorCode()));
	      e.printStackTrace();
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      msgEnvelop = new MessageEnvelop();
	      esbResponse.setResponseData(msgEnvelop.createMsgFault("发送SOAP信息失败,原因如下:" + e.getMessage() + "访问被拒绝!"));
	      e.printStackTrace();
	    }
	    return esbResponse;
	  }

  protected void checkRequest() throws EsbException
  {
    if (this.svid == null)
      throw new EsbException(500, "HttpRequest 服务ID不能为空");

    if (((((this.param != null) ? 1 : 0) & ((this.paramValue != null) ? 1 : 0)) != 0) && (this.param.length != this.paramValue.length))
      throw new EsbException(500, "HttpRequest 参数名、参数值没有匹配.");
    if (this.esbUrl == null)
    {
     
        this.esbUrl = EsbHttpClientConfig.getEsbUrl(); 
        if (this.esbUrl == null)
        	throw new EsbException(500, "HttpRequest ESB服务器URL不能为空.");
    }
      
    if (this.esbUserPwd == null)
      throw new EsbException(500, "HttpRequest ESB访问用户、密码不能为空.");
  }

  protected Object genRequestMessage(String alias, String password)
    throws EsbException
  {
    if (this.esbUserPwd != null) {
      alias = this.esbUserPwd[0];
      password = this.esbUserPwd[1];
    }
    StringBuffer sbXML = new StringBuffer();
    sbXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"><soap:Header><in:system xmlns:in=\"http://www.ylzinfo.com/\">");
//    sbXML.append("  ");/////////////////
    sbXML.append("<para usr=\"");
    sbXML.append(alias);
    sbXML.append("\"/><para pwd=\"");
    sbXML.append(password);
    sbXML.append("\"/><para sid=\"");
    sbXML.append(this.svid);
    if (this.encryptKey != null) {//add by moujl 20150612
    	 sbXML.append("\"/><para encryptkey=\"");
    	sbXML.append(encryptKey);	
    }   
    sbXML.append("\"/>");    
    if (this.router != null) {
      sbXML.append("<para router=\"");
      sbXML.append(this.router);
      sbXML.append("\"/>");
    }
    String signature=null;
	String signtype=null;
	if(msgType!=MessageType.MT_COMMON){ //采用安全策略
		String[] signAndType=EsbHttpClientConfig.signMessage(msgType, param, paramValue);
		signature=signAndType[0];
		signtype=signAndType[1];
	}
	//数据签名
	if(signature!=null){
		sbXML.append("<para signature=\"").append(signature).append("\"/>");
	}if(signtype!=null){
		sbXML.append("<para signaturetype=\"").append(signtype).append("\"/>");
	}
    sbXML.append("</in:system></soap:Header>");
//    sbXML.append("\r");////////////////////
    
    sbXML.append("<soap:Body><in:business xmlns:in=\"http://www.ylzinfo.com/\">");
//    sbXML.append("\n");///////////////////////
    if(param!=null)
      getParamValue(this.param, this.paramValue, sbXML);
    if (this.paramList != null)
      appendParamList(this.paramList, sbXML);
    if (this.rowInfo != null)
      buildRowInfo(this.rowInfo, sbXML);
    sbXML.append("</in:business></soap:Body></soap:Envelope>");
    return sbXML.toString();
  }

  protected Object genRequestMessage2(String alias, String password)
		    throws EsbException
	{
	  if (this.esbUserPwd != null) {
	      alias = this.esbUserPwd[0];
	      password = this.esbUserPwd[1];
	    }
	    StringBuffer sbXML = new StringBuffer();
	    sbXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"><soap:Header><in:system xmlns:in=\"http://www.ylzinfo.com/\">");
//	    sbXML.append("  ");/////////////////
	    sbXML.append("<para usr=\"");
	    sbXML.append(alias);
	    sbXML.append("\"/><para pwd=\"");
	    sbXML.append(password);
	    sbXML.append("\"/><para sid=\"");
	    sbXML.append(this.svid);
	    if (this.encryptKey != null) {//add by moujl 20150612
	    	 sbXML.append("\"/><para encryptkey=\"");
	    	sbXML.append(encryptKey);	
	    }   
	    sbXML.append("\"/>");    
	    if (this.router != null) {
	      sbXML.append("<para router=\"");
	      sbXML.append(this.router);
	      sbXML.append("\"/>");
	    }
	    String signature=null;
		String signtype=null;
		if(msgType!=MessageType.MT_COMMON){ //采用安全策略
			String[] signAndType=EsbHttpClientConfig.signMessage(msgType, param, paramValue);
			signature=signAndType[0];
			signtype=signAndType[1];
		}
		//数据签名
		if(signature!=null){
			sbXML.append("<para signature=\"").append(signature).append("\"/>");
		}if(signtype!=null){
			sbXML.append("<para signaturetype=\"").append(signtype).append("\"/>");
		}
		
	    sbXML.append("</in:system></soap:Header>");
//	    sbXML.append("\r");////////////////////
	    
	    sbXML.append("<soap:Body><in:business xmlns:in=\"http://www.ylzinfo.com/\">");
//	    sbXML.append("\n");///////////////////////
	    if(param!=null)
	      getParamValue(this.param, this.paramValue, sbXML);
	    if (this.paramList != null)
	      appendParamList(this.paramList, sbXML);
	    if (this.rowInfo != null)
	      buildRowInfo(this.rowInfo, sbXML);
	    sbXML.append("</in:business></soap:Body></soap:Envelope>");
	    return sbXML.toString();
	}
  
  private void appendParamList(ArrayList<ArrayList> paramList, StringBuffer sbXML)
  {
    String key = null;
    for (Iterator localIterator1 = paramList.iterator(); localIterator1.hasNext(); ) { ArrayList paramArr = (ArrayList)localIterator1.next();
      key = (String)paramArr.get(0);
      sbXML.append("<paralist name=\"").append(key).append("\">");
      for (int i = 1; i < paramArr.size(); ++i) {
        HashMap map = (HashMap)paramArr.get(i);
        sbXML.append("<row ");
        for (Iterator keySet = map.keySet().iterator(); keySet.hasNext(); ) {
          key = (String)keySet.next();
          sbXML.append(key).append
            ("=\"");
          if (map.get(key) != null)
            sbXML.append((String)map.get(key));
          sbXML.append("\" ");
        }
        sbXML.append("/>");
      }
      sbXML.append("</paralist>");
    }
  }

  private void buildRowInfo(List rowInfo, StringBuffer sbXML)
  {
    if (rowInfo != null) {
      HashMap map = null;
      String key = null;
      for (Iterator it = rowInfo.iterator(); it.hasNext(); ) {
        map = (HashMap)it.next();
        sbXML.append("<row ");
        for (Iterator keySet = map.keySet().iterator(); keySet.hasNext(); ) {
          key = (String)keySet.next();
          sbXML.append(key).append
            ("=\"");
          if (map.get(key) != null)
            sbXML.append(map.get(key));
          sbXML.append("\"  ");
        }
        sbXML.append("/>");
      }
    }
  }

  private void getParamValue(String[] param, String[] paramValue, StringBuffer pvXml)
  {
    if ((param != null) && (paramValue != null))
      for (int i = 0; i < param.length; ++i)
        if ((param[i] != null) && (paramValue[i] != null))
        {
          pvXml.append("<para ").append(param[i]).append("=\"");
          setFilter(paramValue[i], pvXml);
          pvXml.append("\"/>");
        }
  }

  private void setFilter(String value, StringBuffer sb)
  {
    for (int i = 0; i < value.length(); ++i)
      switch (value.charAt(i))
      {
      case '&':
        sb.append("&amp;");
        break;
      case '<':
        sb.append("&lt;");
        break;
      case '>':
        sb.append("&gt;");
        break;
      case '"':
        sb.append("&quot;");
        break;
      case '\'':
        sb.append("&apos;");
        break;
      default:
        sb.append(value.charAt(i));
      }
  }

  private String sendSoapMessage(Object soapMsg)
    throws Exception
  {
    PostMethod method = null;
    HttpClient httpClient = null;
    if(EsbHttpClientConfig.getConnProtocol().equalsIgnoreCase("https"))
	{
	      HttpsURLConnection.setDefaultSSLSocketFactory(EsbHttpClientConfig.getSSLSocketFactory() );
	      HttpsURLConnection.setDefaultHostnameVerifier(hnv);
	}
    if (this.esbUrl==null||this.esbUrl.equals(""))
    	this.esbUrl = EsbHttpClientConfig.getEsbUrl(); 
    URL targetURL = new URL(this.esbUrl);
    int port = targetURL.getPort();
    String protocol = targetURL.getProtocol();
    if (port == -1)
      if ("http".equals(protocol))
        port = 80;
      else if ("https".equals(protocol))
        port = 443;
    
    try
    {
      String str1;
      method = new PostMethod();
//      soapMsg = FormatConversionUtil.xmlToJson(soapMsg.toString()); //add by moujl 20160218
//      System.out.println(soapMsg);
//      String temp = FormatConversionUtil.jsonToXml(soapMsg.toString());
//      System.out.println(temp);
      byte[] content = soapMsg.toString().getBytes("GBK");
      
      if (this.isGzip) {
        method.setRequestHeader("Accept-Encoding", "gzip");
        
        ByteArrayInputStream bis = new ByteArrayInputStream(content);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gos = new GZIPOutputStream(bos);
        for (int c = bis.read(); c != -1; c = bis.read())
          gos.write(c);

        gos.flush();
        gos.close();
        content = bos.toByteArray();
      }
     
      RequestEntity reqEntity = new ByteArrayRequestEntity(content, "text/xml; charset=GBK");
      method.setRequestEntity(reqEntity);
      method.setRequestHeader("Connection", "close");
      method.setPath(targetURL.getPath());
      method.setQueryString(targetURL.getQuery());
      method.setContentChunked(true);
      if (this.otherMode.equals("JSONMODE"))
    	  method.setRequestHeader("OtherMode", "JSONMODE"); //add by moujl 20160218
      
      HostConfiguration config = new HostConfiguration();
      config.setHost(targetURL.getHost(), port, targetURL.getProtocol());
      httpClient = HttpClientUtils.getHttpClient();
      httpClient.executeMethod(config, method, null);  
      
      if (method.getResponseHeader("Content-Encoding") != null) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZipUtil.decompress(method.getResponseBodyAsStream(), baos);
        baos.flush();
        baos.close();
        if (this.otherMode.equals("JSONMODE")) //add by moujl 20161115
        {
        	String ls_response = baos.toString("GBK");
        	//System.out.println(ls_response);
        	return FormatConversionUtil.jsonToSoap(ls_response);
        } 
        return baos.toString("GBK");
      }
      if (this.otherMode.equals("JSONMODE")) //add by moujl 20161115
      {
      	String ls_response = method.getResponseBodyAsString();
      	//System.out.println(ls_response);
      	return FormatConversionUtil.jsonToSoap(ls_response);
      } 
      return method.getResponseBodyAsString();
    }
    finally {
      if (method != null)
      {
        method.releaseConnection();
        //////因为上面有 method.setRequestHeader("Connection", "close"); 所以此句应该不用加httpClient.getHttpConnectionManager().closeIdleConnections(0); 
      }
    }
  }
  /**
	 * 处理ESB服务器响应返回数据
	 */
	protected Object handleResult(Object responseData) throws EsbException {
		// TODO Auto-generated method stub
		if(msgType!=MessageType.MT_COMMON){
			EsbHttpClientConfig.decodeMessage((String)responseData);
		}
		return responseData;
	}
	 private HostnameVerifier hnv = new HostnameVerifier() {
         public boolean verify(String hostname,SSLSession session) {
             return true;
         }
      }; 
}