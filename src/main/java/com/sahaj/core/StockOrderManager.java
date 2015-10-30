package com.sahaj.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sahaj.exception.StockOrderException;
import com.sahaj.orders.Buy;
import com.sahaj.orders.Order;
import com.sahaj.orders.Order.Status;
import com.sahaj.orders.Sell;

/**
 * Stock Order Manager is a Singleton class.Stock Order Manager instance will be
 * created only once in the application.The instance can be accessed by
 * getInstance method. Stock Order Manager create the instance for Order based
 * on the input value of side. If Side value is Buy, the instance of {@link Buy}
 * Buy will be created. if the value of side is Sell, then the instance of
 * {@link Sell} sell will be created. Stock Order maintain an Output Order List
 * to print the Outputs. Stock Order has Map which store Map Key as Company Name
 * and Map Value as List of Company's Order.
 * 
 * @author Naren
 *
 */
public class StockOrderManager
{
	// private static variable
	private static StockOrderManager stockOrderManager;

	// Order Map store key as Company name and value as List of Company's {@link
	// Order}.
	private Map<String, List<Order>> orders = new HashMap<String, List<Order>>();

	// Output Orders List
	private List<Order> orderOutputList = new ArrayList<Order>();

	/**
	 * Private Constructor. So that class cannot be initialized and can be
	 * accessed only by getInstance method.
	 */
	private StockOrderManager()
	{
	}

	/**
	 * Get Instance method to return the Instance of StockOrderManager
	 * 
	 * @return instance of StockOrderManager
	 */
	public static StockOrderManager getInstance()
	{
		/*
		 * if StockOrderManager instance is null,then create the instance of
		 * StockOrderManager.
		 */
		if (stockOrderManager == null)
		{
			// Instance will be created only first time of this method calls.
			stockOrderManager = new StockOrderManager();
		}
		return stockOrderManager;
	}

	/**
	 * Create Order {@link Order} and set the value of the Order variables based
	 * on the input values.
	 * 
	 * @param side
	 *            Buy or Sell
	 * @param companyName
	 *            Company Name
	 * @param quantity
	 *            Number of Stocks
	 */
	public boolean createOrder(String side, String companyName, int quantity)
	{
		try
		{
			Order order = null;
			// IF Buy is equal to Side value
			if (Buy.BUY.equalsIgnoreCase(side))
			{
				// Create instance of {@link Buy} Buy
				order = new Buy();
			}
			else if (Sell.SELL.equalsIgnoreCase(side))
			{
				// create instance of {@link Sell} Sell
				order = new Sell();
			}

			order.setCompanyName(companyName);
			order.setQuantity(quantity);

			// add the Order
			addOrder(companyName, order);
		}
		catch (Exception exception)
		{
			new StockOrderException("Exception while creating the Stock Order",
					exception);
		}
		return true;
	}

	/**
	 * Add Order method get the List of order for the Current Company name, and
	 * add the new order instance to the list of order. It also add the order to
	 * Output Order List. This method calculate the Remaining Quantity of the
	 * Stock for the company and set the value as Remaining Quantity. The Buy
	 * Stock is executed against the Sell Stock and the remaining quantity is
	 * calculated. Based on the remaining quantity value the status for the
	 * order is updated.
	 * 
	 * @param companyName
	 *            Company Name
	 * @param order
	 *            Order Instance {@link Order}
	 */
	private void addOrder(String companyName, Order order)
	{
		// Get the List of Order for the Company Name
		List<Order> orderList = orders.get(companyName);

		// Boolean to Store if the Company have any Open Order or not
		boolean isOpenOrder = false;

		// If Order List is null, then create the instance for the List.
		if (orderList == null)
		{
			orderList = new ArrayList<Order>();
			order.setRemainingQuantity(order.getQuantity());
		}
		else
		{
			/*
			 * Iterate the List of Orders in reverse order to get the Open Order
			 * easily.
			 */
			for (int i = orderList.size() - 1; i >= 0; i--)
			{
				Order order2 = orderList.get(i);
				int remainingQuantity = 0;

				/*
				 * Check if the Order Status is Open, if it is closed, then it
				 * has 0 remaining quantity
				 */
				if (order2.getStatus() != Status.CLOSED)
				{
					// Open Order exists
					isOpenOrder = true;

					/*
					 * Check if the newly created order and the open order from
					 * the list is from same side, if both order are from same
					 * side, then we need to do sum of the remaining quantity
					 */
					if ((order2 instanceof Buy && order instanceof Buy)
							|| (order2 instanceof Sell && order instanceof Sell))
					{
						remainingQuantity = order2.getRemainingQuantity()
								+ order.getQuantity();

						// Update the Remaining Quantity to newly created Order
						order.setRemainingQuantity(Math.abs(remainingQuantity));

						/*
						 * Update the Remaining Quantity value as 0 to old order
						 * from list, Since the values of Remaining Quantity is
						 * updated in newly created ORder
						 */
						order2.setRemainingQuantity(0);
					}
					else
					{
						/*
						 * This else block executed, when the newly created
						 * order and the open order from the list if from
						 * Different Side, then we need to find the difference
						 * of the remaining quantity.
						 */
						remainingQuantity = order2.getRemainingQuantity()
								- order.getQuantity();

						/*
						 * Since the order are from different side, we need to
						 * update Remaining Quantity to the Order which has the
						 * Remaining Quantity value greater than the comparing
						 * order. For example, if Buy Order has 10 remaining
						 * quantity and Sell order has 5 remaining quantity,
						 * then there are 5 remaining quantity of buy here.So we
						 * need to update Buy order remaining quantity as 5.
						 */
						if (order2.getRemainingQuantity() > order.getQuantity())
						{
							order2.setRemainingQuantity(Math
									.abs(remainingQuantity));
							order.setRemainingQuantity(0);
						}
						else
						{
							order.setRemainingQuantity(Math
									.abs(remainingQuantity));
							order2.setRemainingQuantity(0);
						}
					}

					/*
					 * Break the for loop, once we get the last open order from
					 * the list, no need to iterate the whole list.
					 */
					break;
				}
			}
		}

		/*
		 * If all the order for the company are closed and there is no open
		 * Order, then add the quantity of this order as Remaining Quantity.
		 */
		if (!isOpenOrder)
		{
			/*
			 * If there is no open Order in the List, then add the Order's
			 * Quantity value as Remaining Quantity
			 */
			order.setRemainingQuantity(order.getQuantity());
		}

		// add the Order to the List
		orderList.add(order);

		// Put the Company Name as Key and List of Order as value
		orders.put(companyName, orderList);

		// List to Hold the Output Values
		orderOutputList.add(order);
	}

	/**
	 * Get the List of Order for the Company
	 * 
	 * @param companyName
	 *            Company Name
	 * @return List of Order {@link Order}
	 */
	public List<Order> getOrder(String companyName)
	{
		return orders.get(companyName);
	}

	/**
	 * Print the Output Values. Print the List of Orders
	 */
	public void printOrderOutput()
	{
		for (Order order : orderOutputList)
		{
			System.out.println(order.toString());
		}

		if (orderOutputList.size() >= 1)
		{
			System.out
					.println("====================--END OF OUTPUT--=========================");
		}
	}
}
