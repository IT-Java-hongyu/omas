package com.fosustu.omas.utils;

public class OmasException extends RuntimeException{

	private static final long serialVersionUID = 1L;
    public OmasException(Object Obj) {
    	
        super(Obj.toString());
    }
}
