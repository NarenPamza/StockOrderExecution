/**
 * 
 */
package com.sahaj.exception;

/**
 * @author Admin
 *
 */
public class StockOrderException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message = null;

	public StockOrderException()
	{
		super();
	}

	public StockOrderException(String errorMessage)
	{
		super(errorMessage);
		message = errorMessage;
	}

	public StockOrderException(String errorMessage, Throwable throwable)
	{
		super(errorMessage, throwable);
		message = errorMessage;
	}

	@Override
	public String toString()
	{
		return message;
	}

	@Override
	public String getMessage()
	{
		return message;
	}
}
