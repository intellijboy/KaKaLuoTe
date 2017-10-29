package com.ylzinfo.esb.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.ylzinfo.esb.bas.EsbResponse; 
/**
 * Copyright ylzinfo Corporation. All rights reserved.
 * @author:  <a href="xmlvrl@163.com">LvRongLin</a>
 * History:  2013-2-27 Created.
 * Version: 3.0
 */

public class TestYLZESB {
	
private static String esbUrl="http://127.0.0.1:8090/YlzinfoESB/esbproxy";

public static String getBase64Data(byte[] buff){

	//照片数据查询
	BASE64Encoder encoder = new BASE64Encoder();
	String data=encoder.encode(buff);
	return data;
}
public static byte[] getBlob(String ls_base64) throws IOException{

	//照片数据查询
	BASE64Decoder decoder = new BASE64Decoder();
	byte[] buff = decoder.decodeBuffer(ls_base64);
	return buff;
}
//public static void abc(String ls_base64){
//	String infoMsg="更新成功!"; //反馈信息
//	Connection conn=null;
//	Statement st=null;
//	ResultSet rs =null;
//	try {
//		byte[] base64Data=new BASE64Decoder().decodeBuffer(ls_base64);
//		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
//		conn = DriverManager.getConnection(url,"esb4","esb4"); 
//		//conn= jdbctemplate.getDataSource().getConnection();
//		conn.setAutoCommit(false);
//		st = conn.createStatement();
//		//插入一个空对象    
//		st.executeUpdate("update TEST_BLOB_1 set acol =empty_blob()  " );
//		//用for update方式锁定数据行   
//		rs = st.executeQuery("select acol from  TEST_BLOB_1  "); 
//		if (rs.next())
//		{   
//			  //使用oracle.sql.BLOB类
//			  oracle.sql.BLOB dataBlob = (oracle.sql.BLOB) rs.getBlob("acol"); 			  
//			  //到数据库的输出流   
//			  OutputStream outStream = dataBlob.getBinaryOutputStream();
//			  outStream.write(base64Data);
//			  outStream.flush();   
//			  outStream.close();  
//		}
//		conn.commit();
//	}
//	catch (Exception e) {
//		
//	}
//}
//public static void main(String[] args) 
// {
//		XMLRequest xmlRequest = new XMLRequest();
//     	xmlRequest.setEsbUrl(esbUrl); //ESB服务器路径
//       	xmlRequest.setEsbUserPwd(new String[]{"esb","8E000FDB54B7FD93"});//ESB访问用户及密码
//    	String[] v_param={"aac002","key","termid"};//访问参数
//    	String[] v_value={"350212197109065519","aab00111","aab0022"};//访问参数值
//    	xmlRequest.setSvid("testqueryblob");//服务ID
//    	xmlRequest.setMsgType(MessageType.MT_SIGN_TIMP);
//    	xmlRequest.setParam(v_param);
//    	xmlRequest.setParamValue(v_value);
//    	try {
//			String ls_key = ClientKeyHelper.initKey(xmlRequest);
//			EsbResponse esbRsp = xmlRequest.postXmlRequest(ls_key);//发送封装SOAP数据
//	        System.out.println(esbRsp.getResponseData());//返回响应SOAP数据
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////    	xmlRequest.setParam(v_param);
////    	xmlRequest.setParamValue(v_value);
////    	xmlRequest.setReqType("1");
////    	xmlRequest.setTypeData("1401021941071012");
//    	
// }
//public static void main(String[] args) throws Exception 
//{
//       XMLRequest xmlRequest = new XMLRequest();
//	    xmlRequest.setEsbUrl("http://localhost:8081/YlzinfoESB/esbproxy"); //ESB服务器路径
//	    xmlRequest.setEsbUserPwd(new String[]{"esb","8E000FDB54B7FD93"}); //ESB访问用户及密码	      
//	    xmlRequest.setSvid("testquery");//服务ID	 
//	    xmlRequest.setMsgType(MessageType.MT_SIGN_TIMP);
//    	String[] v_param={"areacodebegin"};//访问参数
//    	String[] v_value={"111"};//访问参数值
//	    xmlRequest.setParam(v_param);
//	    xmlRequest.setParamValue(v_value);
//	    System.out.println("开始测试");	
//	    //String ls_key = ClientKeyHelper.initKey(xmlRequest);
//	    //System.out.println(ls_key);	
//	    EsbResponse esbRsp = xmlRequest.postXmlRequest();//没加密就用此方法发送SOAP数据
//	    String ls_return= esbRsp.getResponseData(); //接收返回的信息 
////	    int ii_begin = ls_return.indexOf("acol=\"")+"acol=\"".length();
////	    int ii_end = ls_return.indexOf("\"",ii_begin+1);
////	    ls_return = ls_return.substring(ii_begin, ii_end);
////	    abc(ls_return);
////	    byte[] buff =getBlob(ls_return);
//		System.out.println("返回信息:"+ls_return);	 		 
//		System.out.println("结束测试");			 
//} 

//public static void main(String[] args) throws Exception 
//{
//	XMLRequest request = new XMLRequest();		
//	request.setGzip(true);
//	request.setSvid("gov.xm.xx.queryZx_gahjxx_rs"); //服务名 
//	//request.setEsbUrl("http://172.18.200.200:9010/esb/esbproxy"); //访问地址  
//	request.setEsbUrl("http://222.76.243.149:9010/esb/esbproxy"); 	
//	request.setEsbUserPwd(new String[]{"webNew","D5F8DE72CCAC4765EC4D7F953612B439F2E57172DA3F2FDF"}); //ESB访问用户及密码 
//    String[] v_param={"arg0"};//访问参数    	 
//    String[] v_value={"350203201503184341"};//访问参数值
//    request.setParam(v_param);
//    request.setParamValue(v_value);
//	String ls_key = ClientKeyHelper.initKey(request);
//	EsbResponse res = request.postXmlRequest(ls_key); 
//	String result = res.getResponseData();
//	System.out.println(result);
////	ls_key="30819f300d06092a864886f70d010101050003818d0030818902818100911d432ca3607b267d2f743bd6d0b57ac4c4741bb14382bb9996b366a95ec9fd3a03c5a23caf0df501e2d3ca0532cda095c4b65a8aa6cb8e9f87e7eda9e8f7b0e844032714db682d56833fbd6e013b1ec28539b8e1be5f00daf65303ad31fb136f9295ae0ff110a5bde4ddb20b8812a841790fdd001f55a069c1e430ec4b5d510203010001";
////	result = ServiceEncryptUtil.getServiceEncryptSoap(result, ls_key);
////	System.out.println(result);
//// 		 
////			ls_key=ClientKeyHelper.getEncryptFromSoap(result,request);
////	 
////	List	list = ServiceEncryptUtil.getSoapHeadAndBody(result);
////	result = list.get(0) +ServiceEncryptUtil.decryptByAes(list.get(1).toString(), ls_key)+list.get(2).toString();
//// 		
////	System.out.println(result);
//	
//		 
//} 

//public static void main(String[] args) throws Exception 
//{
//	XMLRequest request = new XMLRequest();		
//	request.setGzip(true);
//	request.setSvid("queryFs"); //服务名  
//	request.setEsbUrl("http://127.0.0.1:8080/YlzinfoESB/esbproxy"); 	
//	request.setEsbUserPwd(new String[]{"esb","8E000FDB54B7FD93"}); //ESB访问用户及密码 
//    String[] v_param={"name"};//访问参数    	 
//    String[] v_value={"张三"};//访问参数值
//    request.setParam(v_param);
//    request.setParamValue(v_value); 
//	EsbResponse res = request.postXmlRequest(); 
//	String result = res.getResponseData();
//	System.out.println(result); 
//		 
//} 

//public static void main(String[] args) throws Exception 
//{
//	XMLRequest request = new XMLRequest();		
//	request.setGzip(true);
//	request.setSvid("testchaxun2"); //服务名  
//	
//	//request.setOtherMode("JSONMODE");
//	//request.setEsbUrl("https://127.0.0.1:8080/YlzinfoESB/esbproxy"); 	
//	request.setEsbUserPwd(new String[]{"esb","8E000FDB54B7FD93"}); //ESB访问用户及密码 
//	String ls_param="logid0,userid";
//	String ls_value="16563908,esb";
//	String[] v_param=ls_param.split(",");//访问参数    	  
//    String[] v_value=ls_value.split(",");
////    String[] v_param={"logid0","userid"};//访问参数    	  
////    String[] v_value={"16563908","esb"};
//    request.setParam(v_param);
//    request.setParamValue(v_value);  
//    
//	EsbResponse res = request.postXmlRequest(); 
//	String result = res.getResponseData();
////	System.out.println(result); 
////	result = FormatConversionUtil.xmlToJson(result);
////	System.out.println(result); 
////	result = FormatConversionUtil.jsonToXml(result);
//	System.out.println(result); 
//		 
//} 

//public static void main(String[] args) throws Exception {	
//	System.out.println("开始测试");		
//	XMLRequest request = new XMLRequest();		
//	request.setGzip(true);
//	request.setOtherMode("JSONMODE");
//	request.setSvid("yourservicename"); //写入测试服务名  
//	request.setEsbUrl("http://127.0.0.1:8080/YlzinfoESB/esbproxy");
//	request.setEsbUserPwd(new String[]{"esb","8E000FDB54B7FD93"}); //ESB访问用户及密码 
//	request.setParam(new String[]{"bcc001"});
//	request.setParamValue(new String[]{"0909"});
//	 
//	ArrayList paramList = new ArrayList();
//	ArrayList paramArr = new ArrayList();
//	ArrayList acol = new ArrayList();
//	HashMap aCol = new HashMap();
//	aCol.put("aab034", "140502");
//	aCol.put("aab301", "1405");
//	aCol.put("akb020", "1000017");
//	aCol.put("aka120", "");
//	aCol.put("skc057", "");
//	aCol.put("skc058", "");
//	aCol.put("skc099", "");
//	aCol.put("skc516", "");
//	aCol.put("aae011", "csyy");
//	aCol.put("xae011", "csyy1");
//	paramArr.add("list01");
//	paramArr.add(aCol); 		
//	aCol = new HashMap();
//	aCol.put("aab034", "aaa");
//	aCol.put("aab301", "bbb");
//	aCol.put("akb020", "ccc");
//	aCol.put("aka120", "");
//	aCol.put("skc057", "");
//	aCol.put("skc058", "");
//	aCol.put("skc099", "");
//	aCol.put("skc516", "");
//	aCol.put("aae011", "eee");
//	aCol.put("xae011", "fff"); 
//	paramArr.add(aCol); 		
//	paramList.add(paramArr);
//	request.setParamList(paramList); 
//	EsbResponse res = request.postXmlRequest(); 
//	String ls_return= res.getResponseData(); 
//	System.out.println(ls_return);	 
//	System.out.println("结束测试");		
//}

public static void main(String[] args) throws Exception 
{
	XMLRequest request = new XMLRequest();		
	request.setGzip(false);
	request.setSvid("gov.xm.si.mz.sp_gjjzx_sftxry"); //服务名 
	//request.setEsbUrl("http://172.18.200.200:9010/esb/esbproxy"); //访问地址  	
	request.setEsbUrl("http://172.18.1.150:9010/esb/esbproxy"); //访问地址  	
	request.setEsbUserPwd(new String[]{"xmgjjzx","AB0BE9895891A272CE94F3C82823ECB1"}); //ESB访问用户及密码 
//	request.setEsbUrl("http://172.18.200.153:9010/esb/esbproxy");
//	request.setEsbUserPwd(new String[]{"esb","985984FA6B44CE2751075D37FF59EECD"});
    String[] v_param={"pi_aac002"};//访问参数    	 
    String[] v_value={"350502198402031026"};//访问参数值
    //String[] v_value={"350502198402031026"};//访问参数值
    request.setParam(v_param);
    request.setParamValue(v_value);
	//String ls_key = "xmsb12333pabx";
    String ls_key=ClientKeyHelper.initKey(request);
	EsbResponse res = request.postXmlRequest(ls_key); 
	String result = res.getResponseData();
	System.out.println(result);
 
		 
} 
 
//public static void main(String[] args) throws Exception 
//{
//       XMLRequest xmlRequest = new XMLRequest();
//	    xmlRequest.setEsbUrl("http://127.0.0.1:8080/YlzinfoESB/esbproxy"); //ESB服务器路径
//	    xmlRequest.setEsbUserPwd(new String[]{"esb","8E000FDB54B7FD93"}); //ESB访问用户及密码
//	    xmlRequest.setOtherMode("JSONMODE");
//	    List rowInfo = new ArrayList();
//	    Map<String,String> map1 =new HashMap<String,String>();
//	    map1.put("id", "003");
//	    map1.put("name", "moujl2");
//	    Map<String,String> map2 =new HashMap<String,String>();
//	    map2.put("id", "004");
//	    map2.put("name", "lanlanjiejie2");		    
//	    rowInfo.add(map1);
//	    rowInfo.add(map2); 
//	    xmlRequest.setRowInfo(rowInfo);
//	    xmlRequest.setSvid("testpiliang2");//服务ID
//	    System.out.println("开始测试");		 
//	    EsbResponse esbRsp = xmlRequest.postXmlRequest();//发送封装SOAP数据
//	    String ls_return= esbRsp.getResponseData(); //接收返回的信息
//		System.out.println("返回信息:"+ls_return);	 		 
//		System.out.println("结束测试");			 
//}


//public static void main(String[] args) throws Exception {	 	
//		XMLRequest request = new XMLRequest();		
//		request.setGzip(true);
//		request.setSvid("testpiliang");  
//		request.setEsbUrl("http://127.0.0.1:8080/YlzinfoESB/esbproxy");
//		request.setEsbUserPwd(new String[]{"esb","8E000FDB54B7FD93"});  
////		request.setParam(new String[]{"bcc001"});
////		request.setParamValue(new String[]{"0909"});
//		 
//		ArrayList paramList = new ArrayList();
//		ArrayList paramArr = new ArrayList();
//		ArrayList acol = new ArrayList();
//		HashMap aCol = new HashMap();
//		aCol.put("aab034", "140502");
//		aCol.put("aab301", "1405");
//		aCol.put("akb020", "1000017");
//		aCol.put("aka120", "");
//		aCol.put("skc057", "");
//		aCol.put("skc058", "");
//		aCol.put("skc099", "");
//		aCol.put("skc516", "");
//		aCol.put("aae011", "csyy");
//		aCol.put("xae011", "csyy1");
//		paramArr.add("renyuanxinxi");
//		paramArr.add(aCol); 		
//		paramList.add(paramArr);
//		request.setParamList(paramList); 
//		EsbResponse res = request.postXmlRequest(); 
//		String ls_return= res.getResponseData(); 
//		System.out.println(ls_return);	  	
//} 
 
//public static void main(String[] args) throws Exception 
//{
//	XMLRequest xmlRequest = new XMLRequest();
//    xmlRequest.setEsbUrl("http://127.0.0.1:8081/YlzinfoESB/esbproxy"); //ESB服务器路径
//    xmlRequest.setEsbUserPwd(new String[]{"esb","8E000FDB54B7FD93"}); //ESB访问用户及密码	      
//    xmlRequest.setSvid("testqueryblob");//服务ID	  
//    xmlRequest.setParam(new String[]{"usernm"});
//    xmlRequest.setParamValue(new String[]{"haodan110"});
//    System.out.println("开始测试");	
//    String ls_key = ClientKeyHelper.initKey(xmlRequest);
//    //System.out.println(ls_key);	
//    EsbResponse esbRsp = xmlRequest.postXmlRequest(ls_key);//没加密就用此方法发送SOAP数据
//    String ls_return= esbRsp.getResponseData(); //接收返回的信息 
//	System.out.println("返回信息:"+ls_return);	 		 
//	System.out.println("结束测试");			 
//} 
public static void testYLZCBPLogin(){
	
 }
}
