package ua.nure.vasilchenko.SummaryTask4.exception;

import java.util.ResourceBundle;

/**
 * Holder for messages of exceptions.
 * 
 * @author S. Vasilchenko
 *
 */
public class Messages {

	private Messages() {
		throw new IllegalStateException("Utility class");
	}

	private static final String RES_STR = "resources";
	public static ResourceBundle res = ResourceBundle.getBundle(RES_STR);


	public static final String ERR_CANNOT_OBTAIN_CONNECTION = res.getString("ERR_CANNOT_OBTAIN_CONNECTION");
	public static final String ERR_CANNOT_FIND_ALL_USERS = res.getString("ERR_CANNOT_FIND_ALL_USERS");
	public static final String ERR_CANNOT_FIND_ALL_CARDS = res.getString("ERR_CANNOT_FIND_ALL_CARDS");
	public static final String ERR_CANNOT_FIND_ALL_PAYMENTS = res.getString("ERR_CANNOT_FIND_ALL_PAYMENTS");
	public static final String ERR_CANNOT_OBTAIN_USER_BY_ID = res.getString("ERR_CANNOT_OBTAIN_USER_BY_ID");
	public static final String ERR_CANNOT_OBTAIN_CARD_BY_ID = res.getString("ERR_CANNOT_OBTAIN_CARD_BY_ID");
	public static final String ERR_CANNOT_OBTAIN_CARD_BY_NUMBER = res.getString("ERR_CANNOT_OBTAIN_CARD_BY_NUMBER");
	public static final String ERR_CANNOT_OBTAIN_PAYMENT_BY_ID = res.getString("ERR_CANNOT_OBTAIN_PAYMENT_BY_ID");
	public static final String ERR_CANNOT_OBTAIN_USER_BY_LOGIN = res.getString("ERR_CANNOT_OBTAIN_USER_BY_LOGIN");
	public static final String ERR_CANNOT_COUNT_REQUESTS = res.getString("ERR_CANNOT_COUNT_REQUESTS");
	public static final String ERR_CANNOT_UPDATE_PAYMENT = res.getString("ERR_CANNOT_UPDATE_PAYMENT");
	public static final String ERR_CANNOT_UPDATE_USER = res.getString("ERR_CANNOT_UPDATE_USER");
	public static final String ERR_CANNOT_UPDATE_CARD = res.getString("ERR_CANNOT_UPDATE_CARD");
	public static final String ERR_CANNOT_DELETE_PAYMENT = res.getString("ERR_CANNOT_DELETE_PAYMENT");
	public static final String ERR_CANNOT_CLOSE_CONNECTION = res.getString("ERR_CANNOT_CLOSE_CONNECTION");
	public static final String ERR_CANNOT_CLOSE_RESULTSET = res.getString("ERR_CANNOT_CLOSE_RESULTSET");
	public static final String ERR_CANNOT_CLOSE_STATEMENT = res.getString("ERR_CANNOT_CLOSE_STATEMENT");
	public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = res.getString("ERR_CANNOT_OBTAIN_DATA_SOURCE");
	public static final String ERR_CANNOT_INSERT_USER = res.getString("ERR_CANNOT_INSERT_USER");
	public static final String ERR_CANNOT_INSERT_CARD = res.getString("ERR_CANNOT_INSERT_CARD");
	public static final String ERR_CANNOT_INSERT_PAYMENT = res.getString("ERR_CANNOT_INSERT_PAYMENT");
	public static final String EVERYTHING = "Hello! It`s custom tag!";

//	public static final String YOUR_PASSWORD_DOES_NOT_MATCH = res.getString("YOUR_PASSWORD_DOES_NOT_MATCH");
//	public static final String PAYMENT_DONT_CREATED = res.getString("PAYMENT_DONT_CREATED");
//	public static final String FIELDS_CANNOT_BE_EMPTY = res.getString("FIELDS_CANNOT_BE_EMPTY");
//	public static final String YOUR_CARD_WAS_BLOCKED = res.getString("YOUR_CARD_WAS_BLOCKED");
//	public static final String CARD_DESTINATION_USER_WAS_BLOCKED = res.getString("CARD_DESTINATION_USER_WAS_BLOCKED");
//	public static final String INSUFFISIENT_FUNDS_IN_THE_ACCOUNT = res.getString("INSUFFISIENT_FUNDS_IN_THE_ACCOUNT");
//	public static final String NO_SUCH_USER_WITH_THIS_LOGIN = res.getString("NO_SUCH_USER_WITH_THIS_LOGIN");
//	public static final String SUM_HIGHER_THAN_MAX = res.getString("SUM_HIGHER_THAN_MAX");
//	public static final String CARD_DESTINATION_IS_NOT_VALID = res.getString("CARD_DESTINATION_IS_NOT_VALID");
//	public static final String SUM_HIGHER_THAN_MONEY = res.getString("SUM_HIGHER_THAN_MONEY");
//	public static final String DESTINATION_CARD_DOES_NOTE_EXISTS = res.getString("DESTINATION_CARD_DOES_NOTE_EXISTS");
//	public static final String CARD_USER_WAS_BLOCKED = res.getString("CARD_USER_WAS_BLOCKED");
//	public static final String CARD_WITH_THIS_NAME_ALREADY_EXISTS = res.getString("CARD_WITH_THIS_NAME_ALREADY_EXISTS");
//	public static final String ERROR = res.getString("ERROR");
//	public static final String LOGINPASSWORD_CANNOT_BE_EMPTY = res.getString("LOGINPASSWORD_CANNOT_BE_EMPTY");
//	public static final String CANNOT_FIND_USER_WITH_SUCH_LOGIN = res.getString("CANNOT_FIND_USER_WITH_SUCH_LOGIN");
//	public static final String YOUR_ACCOUNT_WAS_BLOCKED = res.getString("YOUR_ACCOUNT_WAS_BLOCKED");
//	public static final String THIS_USER_ALREADY_EXTIST = res.getString("THIS_USER_ALREADY_EXTIST");

	public static final String YOUR_PASSWORD_DOES_NOT_MATCH = "YOUR_PASSWORD_DOES_NOT_MATCH";
	public static final String PAYMENT_DONT_CREATED = "PAYMENT_DONT_CREATED";
	public static final String FIELDS_CANNOT_BE_EMPTY = "FIELDS_CANNOT_BE_EMPTY";
	public static final String YOUR_CARD_WAS_BLOCKED = "YOUR_CARD_WAS_BLOCKED";
	public static final String CARD_DESTINATION_USER_WAS_BLOCKED = "CARD_DESTINATION_USER_WAS_BLOCKED";
	public static final String INSUFFISIENT_FUNDS_IN_THE_ACCOUNT = "INSUFFISIENT_FUNDS_IN_THE_ACCOUNT";
	public static final String NO_SUCH_USER_WITH_THIS_LOGIN = "NO_SUCH_USER_WITH_THIS_LOGIN";
	public static final String SUM_HIGHER_THAN_MAX = "SUM_HIGHER_THAN_MAX";
	public static final String CARD_DESTINATION_IS_NOT_VALID = "CARD_DESTINATION_IS_NOT_VALID";
	public static final String SUM_HIGHER_THAN_MONEY = "SUM_HIGHER_THAN_MONEY";
	public static final String DESTINATION_CARD_DOES_NOTE_EXISTS = "DESTINATION_CARD_DOES_NOTE_EXISTS";
	public static final String CARD_USER_WAS_BLOCKED = "CARD_USER_WAS_BLOCKED";
	public static final String CARD_WITH_THIS_NAME_ALREADY_EXISTS = "CARD_WITH_THIS_NAME_ALREADY_EXISTS";
	public static final String ERROR = "ERROR";
	public static final String LOGINPASSWORD_CANNOT_BE_EMPTY = "LOGINPASSWORD_CANNOT_BE_EMPTY";
	public static final String CANNOT_FIND_USER_WITH_SUCH_LOGIN = "CANNOT_FIND_USER_WITH_SUCH_LOGIN";
	public static final String YOUR_ACCOUNT_WAS_BLOCKED = "YOUR_ACCOUNT_WAS_BLOCKED";
	public static final String THIS_USER_ALREADY_EXTIST = "THIS_USER_ALREADY_EXTIST";


	
}