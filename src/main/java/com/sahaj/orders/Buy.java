package com.sahaj.orders;

/**
 * Buy Class hold the Values of Buy Stock. This class extends the {@link Order}
 * Class.
 * 
 * @author Naren
 *
 */
public class Buy extends Order
{
	public static final String BUY = "Buy";

	/**
	 * Default Constructor
	 */
	public Buy()
	{
		// Invoke the Parent Constructor to set the Side value
		super(BUY);
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
			// Set the Status as Open
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
