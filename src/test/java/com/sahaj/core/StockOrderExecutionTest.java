package com.sahaj.core;

import com.sahaj.exception.StockOrderException;

import junit.framework.Assert;
import junit.framework.TestCase;

public class StockOrderExecutionTest extends TestCase
{
	private String[] input = { "Buy", "ABC", "10" };

	private String[] input1 = { "Sell", "ABC", "10" };

	private String[] input2 = { "Wrong", "ABC", "10" };

	public void testIsValidInputs() throws NumberFormatException,
			StockOrderException
	{
		Assert.assertEquals(
				true,
				StockOrderExecution.isValidInputs(input[0], input[1],
						Integer.parseInt(input[2])));
		Assert.assertEquals(
				true,
				StockOrderExecution.isValidInputs(input1[0], input1[1],
						Integer.parseInt(input1[2])));

		try
		{
			Assert.assertEquals(false, StockOrderExecution.isValidInputs(
					input2[0], input2[1], Integer.parseInt(input2[2])));
			fail("StockOrderException excepted");
		}
		catch (StockOrderException exception)
		{

		}
	}

	public void testIsValidInputLength() throws StockOrderException
	{
		Assert.assertEquals(true, StockOrderExecution.isValidInputLength(input));
	}
}
