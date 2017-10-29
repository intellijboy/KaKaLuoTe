package com.ylzinfo.esb.bas;
/**
 * Copyright ylzinfo Corporation. All rights reserved.
 * @author:  <a href="xmlvrl@163.com">LvRongLin</a>
 * History:  2011-12-15 Created.
 * Version: 3.0
 */

public class IConstants {

	public static String ENCRYPT_KEY ="2009200920102010"; //DES加密密钥串

	public static final String SIGN_PASSWORD ="signPassword"; //数字证书密码
	
	public static final String USER_CRT_FILE ="userCrtFile";  //数字证书路径
	
	public static final String SECURITY_POLICY_SIGN="000";    //数字签名策略
	
	public static final String SECURITY_POLICY_ENCRYPT="001";    //数字加密策略
	
	public static final int serverStopError=100;//所有端服务器均停止运行
 	  
	public static final int esbReturnError=200; //ESB返回数据出错
 	 
	public static final int esbConnTimeoutError=300; //客户端与远端服务器等待连接超时
 	 
	public static final int configFileError=400;     //客户端ylzmq.properties配置文件出错
	
	public static final int esbRequestError=500;     //ESB请求数据格式出错
	
	public static final String ENVELOP_END = "</soap:Envelope>";

	public static final String BODY_END = "</soap:Body>";

	public static final String BODY_START = "<soap:Body>";

	public static final String BUSINESS_START="<out:business xmlns:out=\"http://www.ylzinfo.com/\">";
	
	public static final String BUSINESS_END="</out:business>";
	
	public static final String HEAD_END = "</out:system></soap:Header>";

	public static final String HEAD_START = "<soap:Header><out:system xmlns:out=\"http://www.ylzinfo.com/\">";

	public static final String ENVELOP_START = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">";

	public static final String XML_PI = "<?xml version=\"1.0\" encoding=\"GBK\"?>";
	
	public static final String XML_PI_UTF_8= "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
	
	public static final String  Single_Row= "<result showtype=\"1\" />";
	
	public static final String  Show_Type_Result= "</result>";
	
	public static final String  Multilateral_Row= "<result showtype=\"2\" />";
	
	public static final String  Structs_START = "<resultset name=\"structs\">";
	public static final String  Structs_END = "</resultset>";
	
	public static final String  Retrieve_START = "<resultset name=\"retrieve\">";
	public static final String  Retrieve_END = "</resultset>";
	
	public static final String  Message_START = "<resultset name=\"retrieve\" information=\"";
	public static final String  Message_R= "\" >";
	public static final String  Message_End = "</resultset>";
    public static final String  Single= "1";
	
	public static final String  Multilateral= "2";
	
	public static final String  FAULT_START="<soap:Fault>";
	public static final String  FAULT_END="</soap:Fault>";
	
	public static final String  FAULT_CODE_START="<faultcode>";
	public static final String  FAULT_CODE_END="</faultcode>";
	
	public static final String  FAULT_STR_START="<faultstring>";
	public static final String  FAULT_STR_END="</faultstring>";
	
	public static final String ERROR_MSG_START="<error msg=\"";
	public static final String ERROR_MSG_END="\" />";
}
