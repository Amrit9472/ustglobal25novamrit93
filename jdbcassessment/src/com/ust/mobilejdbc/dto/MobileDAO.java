package com.ust.mobilejdbc.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ust.mobilejdbc.dao.ContactInfo;

public class MobileDAO implements IMobileDAO{
	private static String url = "jdbc:mysql://localhost:3306/ust_web_db?user=root&password=root";
	private static String driver = "com.mysql.jdbc.Driver";
	private static String select_sql = "select * from contact";
	private static String insert_sql = "insert into contact values(?,?,?)";
	private static String search_sql = "select * from contact where name = ?";
	private static String delete_sql = "delete  from contact where name = ?";
	private static String edit_sql = "update contact set mobileno = ?,group_name=? where name=?";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	@Override
	public int addContact(ContactInfo info) {
		// TODO Auto-generated method stub
		try {
			Class.forName(driver);
			try(Connection conn = DriverManager.getConnection(url);
					PreparedStatement pstmt = conn.prepareStatement(insert_sql)) {
				pstmt.setString(1, info.getName());
				pstmt.setString(2, info.getNumber());
				pstmt.setString(3, info.getGroup());
				
				int count = pstmt.executeUpdate();
				return count;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
			
		}
		
	}
	@Override
	public List<ContactInfo> getConatct() {
		try {
			Class.forName(driver);
			try(Connection conn = DriverManager.getConnection(url);
					PreparedStatement pstmt = conn.prepareStatement(select_sql);
					ResultSet rs  = pstmt.executeQuery()) {
				List<ContactInfo> ls = new ArrayList<ContactInfo>();
				ContactInfo info = new ContactInfo();
				while(rs.next()) {
					String name = rs.getString("name");
					info.setName(name);	
					String number = rs.getString("mobileno");
					info.setNumber(number);
					String group = rs.getString("group_name");
					info.setGroup(group);
					ls.add(info);
				}
				return ls;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public ContactInfo searchContact(String name) {
		try {
			Class.forName(driver);
			try(Connection conn = DriverManager.getConnection(url);
					PreparedStatement pstmt = conn.prepareStatement(search_sql)){
				ContactInfo info = new ContactInfo();
				pstmt.setString(1, name);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					info.setName(rs.getString("name"));
					info.setNumber(rs.getString("mobileno"));
					info.setGroup(rs.getString("group_name"));
				}
				return info;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int deleteContact(String name) {
		try {
			Class.forName(driver);
			
				try(Connection conn = DriverManager.getConnection(url);
						PreparedStatement pstmt = conn.prepareStatement(delete_sql)){
					pstmt.setString(1, name);
					int count = pstmt.executeUpdate();
					return count;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
	}
	@Override
	public int editContact(ContactInfo info,String name) {
		try {
			Class.forName(driver);
			try(Connection conn = DriverManager.getConnection(url);
					PreparedStatement pstmt = conn.prepareStatement(edit_sql)){
				pstmt.setString(1, info.getNumber());
				pstmt.setString(2, info.getGroup());
				pstmt.setString(3, name);
				
				int count = pstmt.executeUpdate();
				return count;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	
}
