package com.app.votingsystem.exception;

public class VotingSystemException extends RuntimeException{
	
	public VotingSystemException(String errorMessage)  {
		super(errorMessage);
	}

	public VotingSystemException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }
}
