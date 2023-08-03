package com.jdbc.exception;
// 없는지 확인
public class RecordNotFoundException extends Exception{
	public RecordNotFoundException() {
		this("This is RecordNotFoundException...");
	}
	
	public RecordNotFoundException(String message) {
		super(message);
	}
}
