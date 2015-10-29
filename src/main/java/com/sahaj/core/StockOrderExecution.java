package com.sahaj.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.sahaj.orders.Buy;
import com.sahaj.orders.Sell;
import com.sahaj.exception.StockOrderException;
import com.sahaj.core.StockOrderManager;
import com.sahaj.Utils.Utility;

/**
 * Stock Order Execution is the entry point for this Application. This class get
 * the Inputs from the user and do validation for the inputs. Using Stock Order
 * Manager ,it will create Order Instance from the Input Values. This class
 * using {@link StockOrderManager} Stock Order Manager also print the outputs
 * based on the input values.
 * 
 * @author Naren
 *
 */
public class StockOrderExecution
{
	/**
	 * Main Method. Entry Point of the Application. Get the User Input from
	 * Console and create Order Instance and Print the Outputs for the Input
	 * value.
	 * 
	 * @param args
	 *            Input Arguments
	 * @throws StockOrderException
	 */
	public static void main(String[] args) throws StockOrderException
	{
		System.out.println("Welcome to Stock Order Execution");

		/*
		 * Creating bufferedReader instance using try with Resource will close
		 * the BufferedReader, Once it complete processing.No need to close the
		 * BufferedReader explicitly.
		 */
		try (@SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(System.in)))
		{
			System.out
					.println("Enter the Input in the following order in a single line, seperated by ',' and press enter twice: ");
			System.out.println("Side,Company,Quantity");
			String input = null;

			/*
			 * Read the Input from the Line
			 */
			while ((input = bufferedReader.readLine()) != null)
			{
				/*
				 * Check if the Line is not Empty
				 */
				if (!input.isEmpty())
				{
					/*
					 * Split the Input with the delimiter ','
					 */
					String[] inputs = input.split(",");

					if (!isValidInputLength(inputs))
					{
						throw new StockOrderException(
								"Invalid Input: Input Values are not equal to 3");
					}

					String side = inputs[0].trim();
					String company = inputs[1].trim();
					int quantity = 0;
					try
					{
						quantity = Integer.parseInt(inputs[2].trim());
					}
					catch (NumberFormatException numberFormatException)
					{
						throw new StockOrderException(
								"Invalid Input: Quantity value is not an Integer value",
								numberFormatException);
					}

					/*
					 * Validate the Inputs
					 */
					if (isValidInputs(side, company, quantity))
					{
						/*
						 * Create the Order Instance
						 */
						StockOrderManager.getInstance().createOrder(
								side.trim(), company.trim(), quantity);
					}
				}
				else
				{
					/*
					 * Print the Order Output
					 */
					StockOrderManager.getInstance().printOrderOutput();
				}
			}
		}
		catch (Exception exception)
		{
			throw new StockOrderException(
					"Exception while executing the Stock Order Execution",
					exception);
		}
	}

	/**
	 * Validate the Input. Side value should be either Buy or Sale. Company name
	 * should not be empty. Quantity value should be greater than 0.
	 * 
	 * @param side
	 *            value of side
	 * @param company
	 *            name of the company
	 * @param quantity
	 *            number of stocks
	 * @return
	 * @throws StockOrderException
	 */
	public static boolean isValidInputs(String side, String company,
			int quantity) throws StockOrderException
	{
		/*
		 * Verify whether Side or Company input is null or empty
		 */
		if (Utility.isStringNullOrEmpty(side)
				|| Utility.isStringNullOrEmpty(company))
		{
			throw new StockOrderException(
					"Invalid Input: Input Value of Side or Company is Empty");
		}

		/*
		 * Verify whether Side value is equal to Buy or Side
		 */
		if (!(Sell.SELL.equalsIgnoreCase(side) || Buy.BUY
				.equalsIgnoreCase(side)))
		{
			throw new StockOrderException(
					"Invalid Input: Input Value of Side should be 'Buy' or 'Sell'");
		}

		/*
		 * Verify whether Quantity value is less than or equal to 0
		 */
		if (quantity <= 0)
		{
			throw new StockOrderException(
					"Invalid Input: Input Value of Quantity is Less than or equal to O");
		}

		// Return true,if all the three inputs are valid
		return true;
	}

	/**
	 * Validate whether three Inputs are entered by the user. if not, then throw
	 * Exception
	 * 
	 * @param inputs
	 *            Inputs
	 * @return boolean true if input is of length 3,else throw exception
	 * @throws StockOrderException
	 */
	public static boolean isValidInputLength(String[] inputs)
			throws StockOrderException
	{
		if (inputs.length != 3)
		{
			System.out.println("Invalid Input: Input Values are Invalid");
			System.out.println("Usage: Side,Company,Quantity");
			System.out.println("Example: Buy,ABC,10");
			throw new StockOrderException(
					"Invalid Input: Input Values are not equal to 3");
		}

		// Return true, if length of input is equal to 3
		return true;
	}
}
