import java.sql.*;
public class JDBCDemo {

	public static void main(String[] args) throws Exception {
		batchdemo();
	}
	//read record method
	public static void readRecords() throws Exception{
		//we are now going to read the data from the table
		//the first is to connect with database
		//connection-interface,con-reference variable
		//we cannot instantiate the interface 
		String url = "jdbc:mysql://localhost:3306/jdbcdemo";
		String userName = "root";
		String passWord = "swetha";
		String query = "select * from employee";
		
		//this is an object
		Connection con = DriverManager.getConnection(url,userName,passWord);  
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query); // this is going to return result set,we are creating a object for resultSet
        
        while(rs.next()) {
        System.out.println("Id is "+rs.getInt(1));
        System.out.println("Name is "+rs.getString(2));
        System.out.println("Salary is "+rs.getInt(3));
        } 
        con.close();
	}
	
	public static void insertRecords() throws Exception{
		//we are now going to read the data from the table
		//the first is to connect with database
		//connection-interface,con-reference variable
		//we cannot instantiate the interface 
		String url = "jdbc:mysql://localhost:3306/jdbcdemo";
		String userName = "root";
		String passWord = "swetha";
		
		String query = "insert into employee values(4,'sasi',80000)";
		
		//this is an object
		Connection con = DriverManager.getConnection(url,userName,passWord);  
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query); // this is going to return result set,we are creating a object for resultSet
        
        System.out.println("Number of rows affected: "+rows);
        con.close();
	}
	
	public static void insertVar() throws Exception{
		//we are now going to read the data from the table
		//the first is to connect with database
		//connection-interface,con-reference variable
		//we cannot instantiate the interface 
		String url = "jdbc:mysql://localhost:3306/jdbcdemo";
		String userName = "root";
		String passWord = "swetha";
		
		int id = 5;
		String name = "Rithikswe";
		int salary = 30000;
		
		String query = "insert into employee values("+ id+", '" +name+ "' ," +salary+");";
		
		//this is an object
		Connection con = DriverManager.getConnection(url,userName,passWord);  
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query); // this is going to return result set,we are creating a object for resultSet
        
        System.out.println("Number of rows affected: "+rows);
        con.close();
	}
	//prepare Statement
	public static void insertUsingPst() throws Exception{
		String url = "jdbc:mysql://localhost:3306/jdbcdemo";
		String userName = "root";
		String passWord = "swetha";
		
		int id = 6;
		String name = "Paru";
		int salary = 30000;
		
		String query = "insert into employee values(?,?,?)";
		
		//this is an object
		Connection con = DriverManager.getConnection(url,userName,passWord);  
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);
        pst.setString(2, name);
        pst.setInt(3, salary);
        int rows = pst.executeUpdate();
        
        System.out.print("Number of rows affected: " +rows);
        con.close();
	}
	public static void delete() throws Exception{
		
		String url = "jdbc:mysql://localhost:3306/jdbcdemo";
		String userName = "root";
		String passWord = "swetha";
		
		int id = 5;
		
		String query = "delete from employee where emp_id = " +id;
		
		//this is an object
		Connection con = DriverManager.getConnection(url,userName,passWord);  
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query); // this is going to return result set,we are creating a object for resultSet
        
        System.out.println("Number of rows affected: "+rows);
        con.close();
	}
	
public static void update() throws Exception{
		
		String url = "jdbc:mysql://localhost:3306/jdbcdemo";
		String userName = "root";
		String passWord = "swetha";
		
		int id = 5;
		
		String query = "update employee set salary = 150000 where emp_id=1";
		
		//this is an object
		Connection con = DriverManager.getConnection(url,userName,passWord);  
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query); // this is going to return result set,we are creating a object for resultSet
        
        System.out.println("Number of rows affected: "+rows);
        con.close();
	}

//Stored procedure
public static void sp() throws Exception{
	
	String url = "jdbc:mysql://localhost:3306/jdbcdemo";
	String userName = "root";
	String passWord = "swetha";
	
	//this is an object
	Connection con = DriverManager.getConnection(url,userName,passWord);  
    CallableStatement cst = con.prepareCall("{call GetEmp()}"); 
    ResultSet rs = cst.executeQuery();
    
    while(rs.next()) {
    	System.out.println("Id is "+rs.getInt(1));
    	System.out.println("Name is "+rs.getString(2));
    	System.out.println("Salary is "+rs.getInt(3));
    }
    con.close();
}

public static void sp2() throws Exception{
	
	String url = "jdbc:mysql://localhost:3306/jdbcdemo";
	String userName = "root";
	String passWord = "swetha";
	
	int id = 3;
	//this is an object
	Connection con = DriverManager.getConnection(url,userName,passWord);  
    CallableStatement cst = con.prepareCall("{call GetEmpId(?)}"); 
    cst.setInt(1, id);
    ResultSet rs = cst.executeQuery();
    
    rs.next();
    	System.out.println("Id is "+rs.getInt(1));
    	System.out.println("Name is "+rs.getString(2));
    	System.out.println("Salary is "+rs.getInt(3));
    con.close();
}

public static void sp3() throws Exception{
	
	String url = "jdbc:mysql://localhost:3306/jdbcdemo";
	String userName = "root";
	String passWord = "swetha";
	
	int id = 3;
	//this is an object
	Connection con = DriverManager.getConnection(url,userName,passWord);  
    CallableStatement cst = con.prepareCall("{call GetNameById(?,?)}"); 
    cst.setInt(1, id);
    //2 is giving because the name is returned in 2nd ?
    cst.registerOutParameter(2, Types.VARCHAR);
    cst.executeUpdate();
    System.out.print(cst.getString(2));
  
    con.close();
}

//commit vs autocommit
//if i have problem in 2nd query then, both the query should not be executed 
public static void commitdemo() throws Exception{
	
	String url = "jdbc:mysql://localhost:3306/jdbcdemo";
	String userName = "root";
	String passWord = "swetha";
	
	String query1 = "update employee set salary = 550000 where emp_id=1";
	String query2 = "update employee set salary = 550000 where emp_id=2";
	//this is an object
	Connection con = DriverManager.getConnection(url,userName,passWord);
	//so we set the autoCommit as false 
	con.setAutoCommit(false);
	
    Statement st = con.createStatement();
    int rows1 = st.executeUpdate(query1);
    System.out.println("Number of rows affacted: "+rows1);
    int rows2 = st.executeUpdate(query2);
    System.out.println("Number of rows affacted: "+rows2);
    
    if(rows1 > 0 && rows2>0)
    	con.commit();
    con.close();
}

//Batch processing

public static void batchdemo() throws Exception{
	
	String url = "jdbc:mysql://localhost:3306/jdbcdemo";
	String userName = "root";
	String passWord = "swetha";
	
	String query1 = "update employee set salary = 55555 where emp_id=1";
	String query2 = "update employee set salary = 55555 where emp_id=2";
	String query3 = "update employee set salary = 55555 where emp_id=3";
	String query4 = "update employee set salary = 55555 where emp_id=4";
	//this is an object
	Connection con = DriverManager.getConnection(url,userName,passWord);
	con.setAutoCommit(false);
	Statement st = con.createStatement();
	st.addBatch(query1);
	st.addBatch(query2);
	st.addBatch(query3);
	st.addBatch(query4);
	
	int[] arr = st.executeBatch();
	for(int i:arr) {
		if (i > 0) {
			continue;
        }else {
        	con.rollback();
        }
	}
	con.commit();
    con.close();
}
}
