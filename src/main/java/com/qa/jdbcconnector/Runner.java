package com.qa.jdbcconnector;

import java.sql.SQLException;
import java.util.Scanner;

import com.qa.jdbcconnector.dao.CustomerDAO;
import com.qa.jdbcconnector.models.Customer;

public class Runner {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String username, password;
		System.out.println("Please insert username below:-");
		username = scan.nextLine();
		System.out.println("Please insert password below:-");
		password = scan.nextLine();
		scan.close();
		CustomerDAO.setUSERNAME(username);
		CustomerDAO.setPASSWORD(password);
		createCustomer("Chris", "chris@gmail.com", "Las Vegas");
		getAllCustomers();
		updateCustomers(3, new Customer("Siriti", "sk@gmail.com", "Tokyo"));
//		deleteCustomer(4);
		getAllCustomers();
	}

	private static void deleteCustomer(int i) {
		CustomerDAO customerTable = new CustomerDAO();

		try {

			customerTable.delete(i);
			customerTable.closeResources();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void getAllCustomers() {
		CustomerDAO customerTable = new CustomerDAO();

		try {

			customerTable.readAll();
			customerTable.closeResources();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void updateCustomers(int i, Customer c) {
		CustomerDAO customerTable = new CustomerDAO();

		try {

			customerTable.update(i, c);
			customerTable.closeResources();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void createCustomer(String name, String email, String address) {
		CustomerDAO customerTable = new CustomerDAO();

		try {

			customerTable.create(name, email, address);
			customerTable.closeResources();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
