package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.model.ProductMaster;

public class ProductMasterDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public ProductMasterDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

	public List<ProductMaster> getAllProducts() throws ClassNotFoundException, SQLException {
		String sql = "select * from Product_Info";
		this.connect();

		Statement stmt = this.jdbcConnection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		// map it to model
		List<ProductMaster> products = new ArrayList<ProductMaster>();
		while (rs.next()) {
			ProductMaster product = new ProductMaster(
					rs.getInt("Id"), 
					rs.getString("ProductName"),
					rs.getInt("Cost"), 
					rs.getString("ProductDescription"));

			products.add(product);
		}

		rs.close();
		stmt.close();
		this.disconnect();

		return products;
	}
	
	public boolean addNewProduct(String pname, String pdesc, String pcost) throws ClassNotFoundException, SQLException {
		String sql = "insert into Product_Info (ProductName, Cost, ProductDescription) values(?,?,?)";
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		pstmt.setString(1, pname);
		pstmt.setString(2, pcost);
		pstmt.setString(3, pdesc);
		
		boolean added = pstmt.executeUpdate() > 0;
		
		pstmt.close();
		this.disconnect();
		return added;
	}

	public boolean updateProduct(int pid, String pname, String pdesc, String pcost) throws ClassNotFoundException, SQLException {
		// String sql = "update productmaster set (productName,cost,productDescription) values(?,?,?) WHERE id = ?";
		String sql = "update Product_Info set ProductName = ?, Cost = ?, ProductDescription = ? WHERE Id = ?";
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		pstmt.setString(1, pname);
		pstmt.setString(2, pcost);
		pstmt.setString(3, pdesc);
		pstmt.setInt(4, pid);
		
		boolean updated = pstmt.executeUpdate() > 0;
		
		pstmt.close();
		this.disconnect();
		return updated;
	}

	public ProductMaster getRequiredProductDetail(String pid) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "select * from Product_Info where Id =" + pid;
		this.connect();

		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		try {
			rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ProductMaster product = new ProductMaster(
				rs.getInt("Id"), 
				rs.getString("ProductName"),
				rs.getInt("Cost"), 
				rs.getString("ProductDescription"));
		
		pstmt.close();
		this.disconnect();
		return product;
	}

	public boolean deleteProduct(String pid) throws SQLException {
		String sql = "DELETE FROM Product_Info WHERE Id = " + pid;
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
				
		boolean added = pstmt.executeUpdate() > 0;
		
		pstmt.close();
		this.disconnect();
		return added;
	}
	
}