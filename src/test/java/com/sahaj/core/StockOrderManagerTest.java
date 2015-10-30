package com.sahaj.core;

import junit.framework.Assert;
import junit.framework.TestCase;

public class StockOrderManagerTest extends TestCase
{
	private StockOrderManager stockOrderManager = StockOrderManager
			.getInstance();

	private String[] input = { "Buy", "ABC", "10" };

	private String[] input1 = { "Sell", "XYZ", "15" };

	private String[] input2 = { "Sell", "ABC", "13" };

	private String[] input3 = { "Buy", "XYZ", "10" };

	private String[] input4 = { "Buy", "XYZ", "8" };

	public void testGetInstance()
	{
		assertNotNull(stockOrderManager);
	}

	public void testCreateOrder()
	{
		Assert.assertEquals(
				true,
				stockOrderManager.createOrder(input[0], input[1],
						Integer.parseInt(input[2])));

		Assert.assertEquals(
				true,
				stockOrderManager.createOrder(input1[0], input1[1],
						Integer.parseInt(input1[2])));

		Assert.assertEquals(
				true,
				stockOrderManager.createOrder(input2[0], input2[1],
						Integer.parseInt(input2[2])));

		Assert.assertEquals(
				true,
				stockOrderManager.createOrder(input3[0], input3[1],
						Integer.parseInt(input3[2])));

		Assert.assertEquals(
				true,
				stockOrderManager.createOrder(input4[0], input4[1],
						Integer.parseInt(input4[2])));
	}

	public void testPrintOrderOutput()
	{
		stockOrderManager.printOrderOutput();
	}

}
