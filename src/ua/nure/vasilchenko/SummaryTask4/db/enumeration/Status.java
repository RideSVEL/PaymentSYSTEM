package ua.nure.vasilchenko.SummaryTask4.db.enumeration;


/**
 * Status entity.
 * 
 * @author S.Vasilchenko
 * 
 */
public enum Status {
	PREPARED, PAID;

//	public static Status getStatus(Payment payment) {
//		int statusId = payment.getStatusId();
//		return Status.values()[statusId];
//	}

	public String getName() {
		return name().toLowerCase();
	}
}