package com.yz.work.common.response;


public final class ResponseCode {

	public static final Integer OK = 0;
	public static final Integer SUCCESS = 200; //成功
	public static final Integer BAD_REQUEST = 400;
	public static final Integer RESOURCE_UNFOUND = 404;//资源不存在
	public static final Integer HTTP_METHOD_NOT_ALLOWED = 405;//http方法不对
	public static final Integer HTTP_MEDIA_TYPE_UNSUPPORTED = 415;//media-type不支持 
	public static final Integer	SYSTEM_ERROR = 500; //系统异常  
	public static final Integer VALIDATION_ERROR = 600;//检验异常
	public static final Integer AUTHENTICATION_FAIL = 601;//认证失败
	public static final Integer STATUS_ERROR = 602;//状态验证失败
	
	public static final Integer Entity_NOT_EXIST = 604;
	public static final Integer Entity_ALREADY_EXIST = 605;
	
	public static final Integer SERVICE_NOT_IMPLEMENT=700; //服务未实现
	public static final Integer SERVICE_INVOKE_ERROR = 701; //服务调用失败
	
	public static final Integer PARAMETER_MISSING=800; //参数缺失
	public static final Integer PARAMETER_TYPE=801;  //参数类型不匹配
	public static final Integer CONCURRENCY_FAIL = 1002;//并发失败e
	public static final Integer OPERATION_FAIL = 900;
	public static final Integer OPERATION_NOT_SUPPORT=901;
	public static final Integer CUSTOM_ERROR=9999;
}
