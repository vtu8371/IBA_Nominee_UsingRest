package com.cg.iba.exception;

public class LowBalanceException extends Exception {

	private static final long serialVersionUID = 1L;

	public LowBalanceException(String msg) {
		super(msg);
	}

}
