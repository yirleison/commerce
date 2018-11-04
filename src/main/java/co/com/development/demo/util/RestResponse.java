package co.com.development.demo.util;

public class RestResponse {
	
	private Integer resposneCode;
	private String message;
	
	public RestResponse(Integer resposneCode){
		super();
		this.resposneCode = resposneCode;
	}
	
	public RestResponse(Integer resposneCode, String message){
		super();
		this.resposneCode = resposneCode;
		this.message = message;
	}
	
	public int getResposneCode() {
		return resposneCode;
	}
	public void setResposneCode(int resposneCode) {
		this.resposneCode = resposneCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
		
}
