package com.jdbc.exception;

// 중복되었는지 확인
public class DuplicateIDException extends Exception {
	public DuplicateIDException() {
		this("This is DuplicateIDException...");
	}

	public DuplicateIDException(String message) {
		super(message);
	}
}
