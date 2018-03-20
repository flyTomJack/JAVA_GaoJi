package com.atguigu.java.expert;

public enum Country {
	A(1,"齐国"),B(2,"楚国"),C(3,"燕国"),D(4,"韩国"),E(5,"赵国"),F(6,"魏国");
	private Integer retCode;
	private String retMsg;
	
	private Country(Integer retCode, String retMsg) {
		this.retCode = retCode;
		this.retMsg = retMsg;
	}
	public int getRetCode() {
		return retCode;
	}
	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	public static String forEachCountry(int i) {
		for (Country country : values()) {
			if(country.getRetCode() == i) {
				return country.getRetMsg();
			}
		}
		return null;
	}
}
