package com.qa.jdbcconnector.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qa.jdbcconnector.models.Customer;

import java.sql.DriverManager;

public class CustomerDAO {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/qatraining";
	private static  String USERNAME = "";
	private static  String PASSWORD = "";

	public static String getUSERNAME() {
		return USERNAME;
	}

	public static void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public static String getPASSWORD() {
		return PASSWORD;
	}

	public static void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public static String getUrl() {
		return URL;
	}

	public static String getUsername() {
		return USERNAME;
	}

	public static String getPassword() {
		return PASSWORD;
	}

	public static Connection connection;
	public static PreparedStatement statement;

	public CustomerDAO() {
		super();
	}

	public Customer linkToCustomer(ResultSet r) throws SQLException {
		Customer customer = new Customer();

		customer.setId(r.getInt(1));
		customer.setName(r.getString(2));
		customer.setEmail(r.getString(3));
		customer.setAddress(r.getString(4));
		return customer;

	}

	public void readAll() throws SQLException {
		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		statement = connection.prepareStatement("SELECT * FROM customers");

		ResultSet result = statement.executeQuery();

		List<Customer> customers = new ArrayList<>();

		while (result.next()) {
			customers.add(linkToCustomer(result));
		}

		for (Customer c : customers) {
			System.out.println(c);
		}

	}

	public void create(String name, String email, String address) throws SQLException {
		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		statement = connection.prepareStatement("INSERT INTO customers(name, email, address) " + "VALUES (?, ?, ?)");
		statement.setString(1, name);
		statement.setString(2, email);
		statement.setString(3, address);

		statement.execute();

	}

	public void update(int id, Customer customer) throws SQLException {
		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		statement = connection.prepareStatement("UPDATE customers SET name = ?, email = ?, address = ? WHERE customer_id = ?");
		statement.setString(1, customer.getName());
		statement.setString(2, customer.getEmail());
		statement.setString(3, customer.getAddress());
		statement.setInt(4, id);

		statement.execute();
	}

	public void delete(int id) throws SQLException {
		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		statement = connection.prepareStatement("DELETE FROM customers WHERE customer_id = ?");
		statement.setInt(1, id);
		
		statement.execute();
	}

	public void closeResources() throws SQLException {
		this.connection.close();
		this.statement.close();
	}

}
