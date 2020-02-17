package com.fosustu.omas.utils;

public enum LuoErrorCode {

    NULL_OBJ("LUO001","对象为空"),
    ERROR_ADD_USER("LUO002","添加用户失败"),
    UNKNOWN_ERROR("LUO999","系统繁忙，请稍后再试...."),
	UNSEARCH_APARTMENT("PS0001","无法匹配科室，请确认您的科室和病症"),
	UNSEARCH_DOCTORLIST("PS0002","没有查询到合适的医生，请明确您要查询的科室或症状");

    private LuoErrorCode(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}
    
    private String value;
    private String desc;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
    public String toString() {
        return "[" + this.value + "]" + this.desc;
    }

	
}