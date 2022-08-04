package emp;
import java.util.*;  
import java.sql.*; 
import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class EmpDao {
	public static final String driverClass = "com.mysql.jdbc.Driver";
	public static final String connectionUrl = "jdbc:mysql://192.168.18.245:3306/javadb_167";
	public static final String username = "javadb_167";
	public static final String password = "ben#u62000";

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driverClass);
			con = DriverManager.getConnection(connectionUrl);
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;

	}

	public static int save(Emp e) {
		int status = 0;
		try {
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=(PreparedStatement)con.prepareStatement("insert into Employee_details ( name,password,email,country) values (?,?,?,?)");
			ps.setString(1, e.getName());
			ps.setString(2, e.getPassword());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getCountry());

			status = ps.executeUpdate();

			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}public static int update(Emp e) {
		int status =0;
		try {
			Connection con=EmpDao.getConnection();
				PreparedStatement ps=(PreparedStatement)con.prepareStatement("update Employee_details set name=?,password=?,email=?,country=? where id=?");
				ps.setString(1,e.getName());
				ps.setString(2,e.getPassword());
				ps.setString(3,e.getEmail());
				ps.setString(4,e.getCountry());
				
				status=ps.executeUpdate();
				
				con.close();
				
		}catch(Exception ep) {ep.printStackTrace();
		
		}return status;
	}public static int delete(int id) {
		int status=0;
		try {
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=(PreparedStatement) con.prepareStatement("delete form Employee_details where id=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			
			con.close();
		}catch (Exception ew) {ew.printStackTrace();
		
			
		}return status;
	}public static Emp getEmployeebyid(int id) {
		Emp e=new Emp();
		 
		try {
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from Employee_details where id=?");
			ps.setInt(1, id);
			ResultSet rs=(ResultSet) ps.executeQuery();
			if(rs.next()) {
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setCountry(rs.getString(5));
			}con.close();
			
		}catch (Exception eq) {eq.printStackTrace();
		
			
		}return e;
	}public static List<Emp> getAllEmployees(){  
     List<Emp> list=new ArrayList<Emp>();
     
     try {
    	 Connection con=EmpDao.getConnection();
    	 PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from Employee_details");
    	 ResultSet rs=(ResultSet) ps.executeQuery();
    	 while(rs.next()){
    		 Emp e=new Emp();
    		 e.setId(rs.getInt(1));
    		 e.setName(rs.getString(2));
    		 e.setPassword(rs.getString(3));
    		 e.setEmail(rs.getString(4));
    		 e.setCountry(rs.getString(5));
    		 list.add(e);
    	 }con.close();
    		 
    	 }catch (Exception ea) {ea.printStackTrace();
    	 
		}return list;
    			 
     }

}

















