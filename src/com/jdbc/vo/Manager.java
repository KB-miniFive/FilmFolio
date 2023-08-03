package com.jdbc.vo;

public class Manager extends User{
	private String dept;
	
	public Manager() {}

	public Manager(String userId, int admin, String dept) {
		super(userId, admin);
		this.dept = dept;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Manager [dept=" + dept + "]"+super.toString();
	}
	

}
