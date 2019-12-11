package com.creditkarma.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;


public class DbConnect {
	Connection con = null;

	private static final String STR1_DB_SCHEMA = "jdbc:oracle:thin:@Qr0111L.test.xxx.com:3300/XX05107.CREKAR.COM";
	private static final String STR2_DB_SCHEMA = "jdbc:oracle:thin:@Qr2222L.test.xxx.com:5500/XX7140.CREKAR.COM";
	private static final String STR3_DB_SCHEMA = "jdbc:oracle:thin:@Qr3333L.test.xxx.com:9981/XX9151.CREKAR.COM";
	private static final String STR4_DB_SCHEMA = "jdbc:oracle:thin:@Qr4444L.test.xxx.com:1234/XX0935.CREKAR.COM";
	private static final String STR1_USER_ID = "ABCD";
	private static final String STR2_USER_ID = "LMNO";
	private static final String STR3_USER_ID = "PQRS";
	private static final String STR4_USER_ID = "TUVW";
	private static final String STR1_PWD = "xxxxxxxx";
	private static final String STR2_PWD = "yyyyyyyy";
	private static final String STR3_PWD = "zzzzzzzz";
	private static final String STR4_PWD = "xyzzzzzz";
	private static final String QUERY_FETCH_CARDS = "Select CARD_NBR as cardNbr FROM cardtable where CARD_TYP='L' ORDER BY CARD_MODE";

	public void connectToSpecificDb(String cardId) throws SQLException, ClassNotFoundException {
		switch (cardId) {
		case "VISA":
			con = DriverManager.getConnection(STR1_DB_SCHEMA, STR1_USER_ID, STR1_PWD);
			break;
		case "MASTERCARD":
			con = DriverManager.getConnection(STR2_DB_SCHEMA, STR2_USER_ID, STR2_PWD);
			break;
		case "DISCOVER":
			con = DriverManager.getConnection(STR3_DB_SCHEMA, STR3_USER_ID, STR3_PWD);
			break;
		case "CHASE":
			con = DriverManager.getConnection(STR4_DB_SCHEMA, STR4_USER_ID, STR4_PWD);
			break;
		default:
			Assert.fail("This card type do not exist. Failing test scenario.");
			break;
		}
	}

	public List<String> getActualCardTypeValues(String cardId) {
		List<String> cardCodes = new ArrayList<String>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			int i = 0;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(QUERY_FETCH_CARDS);
			while (rs.next()) {
				cardCodes.add(i, rs.getString(1));
			}
			con.close();
		} catch (Exception e) {
			System.out.println("There was an error/exception" + e);
		}
		return cardCodes;
	}
}