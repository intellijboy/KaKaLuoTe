package com.ylzinfo.esb.client;

import com.ylzinfo.esb.bas.EsbResponse;
/**
 * Copyright ylzinfo Corporation. All rights reserved.
 * @author:  <a href="xmlvrl@163.com">LvRongLin</a>
 * History:  2011-12-16 Created.
 * Version: 3.0
 */

public class Test 
{
	
	public static void main(String[] args) throws Exception 
	{
		XMLRequest xmlRequest = new XMLRequest();
		xmlRequest.setEsbUrl("http://127.0.0.1:7001/YlzinfoESB/esbproxy"); //ESB服务器路径
		xmlRequest.setEsbUserPwd(new String[]{"esb", "8E000FDB54B7FD93"}); //ESB访问用户及密码
		xmlRequest.setSvid("ylz.info.test.sysfunctionquery");//服务ID
		String[] v_param={"orderno","rows","cpage"};//访问参数
		String[] v_value={"30","5","1"};//访问参数值
		xmlRequest.setParam(v_param);
		xmlRequest.setParamValue(v_value);
		System.out.println("开始测试");
		String ls_key = ClientKeyHelper.initKey(xmlRequest);
		//System.out.println(ls_key);
		EsbResponse esbRsp = xmlRequest.postXmlRequest(ls_key);//没加密就用此方法发送SOAP数据
		String ls_return = esbRsp.getResponseData(); //接收返回的信息
		System.out.println("返回信息:" + ls_return);
		System.out.println("结束测试");
			 
	} 
}
