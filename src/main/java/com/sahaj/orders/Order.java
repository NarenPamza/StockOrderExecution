package com.sahaj.orders;

/**
 * Order class is an Abstract class which cannot be initialized and can only be
 * extends. This class Implements Quantity Interface. Order has the company
 * name, side, quantity , status, remaining quantity as variables.
 * 
 * @author Naren
 *
 */
public abstract class Order implements Quantity
{
	private String side;

	private String companyName;

	private int quantity;

	protected int remainingQuantity;

	private Status status;

	/**
	 * Return the Status Value
	 * 
	 * @return
	 */
	public Status getStatus()
	{
		return status;
	}

	/**
	 * Set the Status
	 * 
	 * @param status
	 */
	public void setStatus(Status status)
	{
		this.status = status;
	}

	/**
	 * Parameterized Constructor
	 * 
	 * @param side
	 *            Side Name
	 */
	public Order(String side)
	{
		this.side = side;
	}

	/**
	 * Return the Company Name
	 * 
	 * @return
	 */
	public String getCompanyName()
	{
		return companyName;
	}

	/**
	 * Set the Company Name
	 * 
	 * @param companyName
	 */
	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	/**
	 * Return the Quantity of the Stock
	 * 
	 * @Return quantity
	 */
	public int getQuantity()
	{
		return quantity;
	}

	/**
	 * Set the Quantity of the Stock
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	/**
	 * Return the Remaining Quantity
	 * 
	 * @return Remaining Quantity of the Stock
	 */
	public int getRemainingQuantity()
	{
		return remainingQuantity;
	}

	/**
	 * Enum to have status.Status will be as Open or Closed.
	 * 
	 * @author Naren
	 *
	 */
	public static enum Status
	{
		OPEN("Open"), CLOSED("Closed");

		private String statusCode;

		private Status(String code)
		{
			statusCode = code;
		}

		/**
		 * Return the Status Code
		 * 
		 */
		public String getStatusCode()
		{
			return statusCode;
		}
	}

	/**
	 * To String method converts the Object to String (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder aBuilder = new StringBuilder();
		aBuilder.append("Side: " + side)
				.append(", CompanyName: " + companyName)
				.append(", Quantity: " + quantity)
				.append(", RemainingQty: " + remainingQuantity)
				.append(", Status: " + status.getStatusCode());
		return aBuilder.toString();
	}

	/**
	 * Return the Side Value
	 * 
	 * @return side value
	 */
	public String getSide()
	{
		return side;
	}
}
