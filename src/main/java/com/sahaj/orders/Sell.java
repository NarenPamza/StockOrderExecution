package com.sahaj.orders;

/**
 * Sell Class hold the Values of Sell Stock. This class extends the
 * {@link Order} Order Class.
 * 
 * @author Naren
 *
 */
public class Sell extends Order
{
	public static final String SELL = "Sell";
	
	public Sell()
	{
		super(SELL);
	}

	/**
	 * Set the Remaining Quantity Value
	 * 
	 * @param remainingQuantity
	 *            Remaining Quantity of Stock
	 */
	@Override
	public void setRemainingQuantity(int remainingQuantity)
	{
		this.remainingQuantity = remainingQuantity;

		// If Remaining Quantity value is Greater than 0
		if (remainingQuantity > 0)
		{
			this.setStatus(Status.OPEN);
		}
		else
		{
			// If Remaining Quantity value is equal to 0, then Set Status as
			// Closed
			this.setStatus(Status.CLOSED);
		}
	}
}
