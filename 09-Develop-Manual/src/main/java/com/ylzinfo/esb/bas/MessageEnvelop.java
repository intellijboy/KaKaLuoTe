package com.ylzinfo.esb.bas;


/**
 * Copyright ylzinfo Corporation. All rights reserved.
 * @author:    LvRongLin
 * History:  2009-10-29 Created.
 * Version: 1.0
 */

public class MessageEnvelop {

	
	public  String createMsgFault(String errorMsg){
		//构建标准Soap输出规范
	    StringBuffer reXML = new StringBuffer(IConstants.XML_PI);
    	reXML.append(IConstants.ENVELOP_START) //信封
    	     .append(IConstants.HEAD_START)    //soap:header
    	     .append(IConstants.HEAD_END)      
    	     .append(IConstants.BODY_START);    //soap:body
    	reXML.append(IConstants.FAULT_START)   //soap:fault
             .append(IConstants.FAULT_CODE_START) //faultcode
             .append("500")   //500：client端出错;600:Esb端出错;700:service端出错
             .append(IConstants.FAULT_CODE_END)
             .append(IConstants.FAULT_STR_START) //faultstring
             .append(IConstants.ERROR_MSG_START) //error msg
             .append(errorMsg)
             .append(IConstants.ERROR_MSG_END)
             .append(IConstants.FAULT_STR_END)
             .append(IConstants.FAULT_END);
    	reXML.append(IConstants.BODY_END)
 	         .append(IConstants.ENVELOP_END);
	    return reXML.toString();
		
	}
	
	
	public  String createMsgFault(String errorMsg,int errorCode){
		//构建标准Soap输出规范
	    StringBuffer reXML = new StringBuffer(IConstants.XML_PI);
    	reXML.append(IConstants.ENVELOP_START) //信封
    	     .append(IConstants.HEAD_START)    //soap:header
    	     .append(IConstants.HEAD_END)      
    	     .append(IConstants.BODY_START);    //soap:body
    	reXML.append(IConstants.FAULT_START)   //soap:fault
             .append(IConstants.FAULT_CODE_START) //faultcode
             .append("500-").append(errorCode)    //500：client端出错;600:Esb端出错;700:service端出错
             .append(IConstants.FAULT_CODE_END)
             .append(IConstants.FAULT_STR_START) //faultstring
             .append(IConstants.ERROR_MSG_START) //error msg
             .append(errorMsg)
             .append(IConstants.ERROR_MSG_END)
             .append(IConstants.FAULT_STR_END)
             .append(IConstants.FAULT_END);
    	reXML.append(IConstants.BODY_END)
 	         .append(IConstants.ENVELOP_END);
	    return reXML.toString();
	}

}


 