package cn.itsource.cms.util;

/**  
* @Title: AjaxResult.java
* @Package:cn.itsource.cms.util
* @Description:(封装AjaxResult返回参数)
* @author:Joi
* @date:2020年6月6日
* @version:V1.0  
*/
public class AjaxResult {
	private Boolean success = true;
	private String msg;
	public AjaxResult() {
	}
	public AjaxResult(Boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
