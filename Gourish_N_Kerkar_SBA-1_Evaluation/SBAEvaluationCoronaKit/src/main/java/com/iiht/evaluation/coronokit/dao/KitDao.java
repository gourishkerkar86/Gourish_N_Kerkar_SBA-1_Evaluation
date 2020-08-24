package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;
import com.iiht.evaluation.coronokit.model.ProductMaster;

public class KitDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public KitDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				// Class.forName("com.mysql.jdbc.Driver");
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	// add DAO methods as per requirements

	public boolean addNewOrder(CoronaKit newUserRegDetails, int orderTotal) throws SQLException {

		String sql = "insert into Customer_Order_Info (CustName, CustEmail, CustPhone, CustAddress, OrderDate, TotalAmount) values(?,?,?,?,?)";
		this.connect();

		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		pstmt.setString(1, newUserRegDetails.getPersonName());
		pstmt.setString(2, newUserRegDetails.getEmail());
		pstmt.setString(3, newUserRegDetails.getContactNumber());
		pstmt.setString(4, newUserRegDetails.getDeliveryAddress());
		pstmt.setInt(5, orderTotal);

		boolean added = pstmt.executeUpdate() > 0;

		pstmt.close();
		this.disconnect();
		return added;

	}

	public CoronaKit getLastOrderSummary() throws SQLException {

		String sql = "select * from Customer_Order_Info where OrderId = (SELECT max(OrderId) FROM Customer_Order_Info)";
		this.connect();

		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		try {
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		CoronaKit coronaKit = new CoronaKit(
				rs.getInt("OrderId"),
				rs.getString("CustName"),
				rs.getString("CustEmail"),
				rs.getString("CustPhone"),
				rs.getInt("TotalAmount"),
				rs.getString("Address"),
				rs.getString("OrderDate"),
				true);

		pstmt.close();
		this.disconnect();
		return coronaKit;
	}

}