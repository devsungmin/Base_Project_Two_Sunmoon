package Server;

import java.sql.*;

public class OracleJDBCTest{
	boolean Isly=false;
	String pid=null;
	String mac=null;
	String cl=null;
	String no=null;
	String cls=null;
	String nos=null;
	Connection con =null; 
    Statement stmt =null; 
    ResultSet rs =null;
    String TABLE_NAME ="MEMBER";
    String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:1521:xe";
    String user = "sys as sysdba";
    String password = "@@mbc42825";
    public void Qwer(){
    	  try { 
    		  Class.forName("oracle.jdbc.driver.OracleDriver"); 
    		  System.out.println("OracleDriver의 로딩이 정상적으로 이뤄졌습니다."); 
    		  con = DriverManager.getConnection(url, user, password); 
    		  System.out.println("데이터베이스의 연결에 성공하였습니다."); 
    		  stmt = con.createStatement(); 
         
    		  if(rs!=null) rs.close(); 
    		  if(stmt!=null) stmt.close(); 
    		  if(con!=null) con.close();
    		  //연결 해제
    	  }catch(ClassNotFoundException cnfe){ 
         System.out.println("oracle.jdbc.driver.OracleDriver를 찾을 수 없습니다."); 
    	  }catch(Exception e){ 
    		  System.out.println(e.toString()); 
    	  }finally{ 
        	System.out.println("성공!!"); 
    	  } 
   }
      public void insertDB(int Id,String Pw,String birthday,String Tel) {
	
    	  String sql_insert = "INSERT INTO MEMBER VALUES(" + Id +",'" + Pw + "','"+birthday+"','" + Tel + "', 'Y')";
  
    	  try {//다시연결
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  con = DriverManager.getConnection(url, user, password);
    		  stmt = con.createStatement(); 
    		  stmt.executeUpdate(sql_insert);
    	  } catch (SQLException e) {
    		  e.printStackTrace();
    	  }catch (ClassNotFoundException e) {
    		  e.printStackTrace();
    	  }
      }
      public boolean selectDB(int Id,String Pw) {
    	  String sql_select = "SELECT ID FROM MEMBER WHERE (ID="+Id+" AND "+"PASSWORD='"+Pw+"')";
    	  try {//다시연결
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  con = DriverManager.getConnection(url, user, password);
    		  stmt = con.createStatement(); 
    		  ResultSet rs = stmt.executeQuery(sql_select);
    		  rs.next();
    			  String data = rs.getString("id");
    			  System.out.println(data);
    			  if(Id==Integer.parseInt(data)) {
    				  Isly=true;
    			  }
    			  else
    				  Isly=false;
    			  
    	  } catch (SQLException e) {
    		  e.printStackTrace();
    	  }catch (ClassNotFoundException e) {
    		  e.printStackTrace();
    	  }
    	  if(Isly==true) {
			  return true;
		  }
		  else
			  return false;
		
      }
      public boolean idcheckDB(int Id) {
    	  String sql_select = "SELECT ID FROM MEMBER WHERE (ID="+Id+")";
    	  try {//다시연결
    		  	Class.forName("oracle.jdbc.driver.OracleDriver");
    		  	con = DriverManager.getConnection(url, user, password);
    		  	stmt = con.createStatement(); 
    		  	ResultSet rs = stmt.executeQuery(sql_select);
    		  	
    		  	rs.next();
    		  	String data = rs.getString("id");
    		  	System.out.println(data);
    			 if(Id==Integer.parseInt(data)) {
    			  Isly=true;
    			 }
    			 else
    				 Isly=false;
    			  
    	  } catch (SQLException e) {
    		  e.printStackTrace();
    		  return true;
    	  }catch (ClassNotFoundException e) {
    		  e.printStackTrace();
    		  return true;
    	  }
    	  if(Isly==true) {
			  return false;
		  }
		  else
			  return true;
      }
      public String sixmacDB(int no,int clss) {
    	  String sql_select = "SELECT MAC FROM MAC WHERE (NO="+no+" AND CLASS="+clss+")";
    	  try {//다시연결
    		  	Class.forName("oracle.jdbc.driver.OracleDriver");
    		  	con = DriverManager.getConnection(url, user, password);
    		  	stmt = con.createStatement(); 
    		  	ResultSet rs = stmt.executeQuery(sql_select);
    		  	rs.next();
    		  	mac = rs.getString("mac");
    		  	System.out.println("db:"+mac);
    			return mac;
    			  
    	  } catch (SQLException e) {
    		  e.printStackTrace();
    	  }catch (ClassNotFoundException e) {
    		  e.printStackTrace();
    		  
    	  }
    	  return mac;
    	  
      }
      public String ninemacDB(int no,int clss) {
    	  String sql_select = "SELECT MAC FROM MAC WHERE (NO="+no+" AND CLASS="+clss+")";
    	  try {//다시연결
    		  	Class.forName("oracle.jdbc.driver.OracleDriver");
    		  	con = DriverManager.getConnection(url, user, password);
    		  	stmt = con.createStatement(); 
    		   	ResultSet rs = stmt.executeQuery(sql_select);
    		  	rs.next();
    		  	mac = rs.getString("mac");
    		  	System.out.println("db:"+mac);
    			return mac;
    			  
    	  } catch (SQLException e) {
    		  e.printStackTrace();
    	  }catch (ClassNotFoundException e) {
    		  e.printStackTrace();
    		  
    	  }
    	  return mac;
    	  
      }
      public String tenmacDB(int no,int clss) {
    	  
    	  String sql_select = "SELECT MAC FROM MAC WHERE (NO="+no+" AND CLASS="+clss+")";
    	  try {//다시연결
    		  	Class.forName("oracle.jdbc.driver.OracleDriver");
    		  	con = DriverManager.getConnection(url, user, password);
    		  	stmt = con.createStatement(); 
    		  	ResultSet rs = stmt.executeQuery(sql_select);
    		  	rs.next();
    		  	mac = rs.getString("mac");
    		  	System.out.println("db:"+mac);
    			return mac;
    			  
    	  } catch (SQLException e) {
    		  e.printStackTrace();
    	  }catch (ClassNotFoundException e) {
    		  e.printStackTrace();
    		  
    	  }
    	  return mac;
    	  
      }
      public String ctsixmacDB(int no,int clss) {
    	  String sql_select = "SELECT MAC FROM MAC WHERE (NO="+no+" AND CLASS="+clss+")";
    	  try {//다시연결
    		  	Class.forName("oracle.jdbc.driver.OracleDriver");
    		  	con = DriverManager.getConnection(url, user, password);
    		  	stmt = con.createStatement();
    		  	ResultSet rs = stmt.executeQuery(sql_select);
    		  	rs.next();
    		  	mac = rs.getString("mac");
    		  	System.out.println("db:"+mac);
    			return mac;
    			  
    	  } catch (SQLException e) {
    		  e.printStackTrace();
    	  }catch (ClassNotFoundException e) {
    		  e.printStackTrace();
    		  
    	  }
    	  return mac;
    	  
      }
      public String pwselect(int id,String Phone,String Birth) {
    	  String sql_select = "SELECT ID FROM MEMBER WHERE (BIRTH='"+Birth+"' AND ID="+id+"AND PHONE='"+Phone+"')";
    	  try {//다시연결
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  con = DriverManager.getConnection(url, user, password);
    		  stmt = con.createStatement(); 
 		  	
  		  	ResultSet rs = stmt.executeQuery(sql_select);
  		  	rs.next();
  		  	pid = rs.getString("id");
  		  	return pid;
    			  
    	  } catch (SQLException e) {
    		  e.printStackTrace();
    	  }catch (ClassNotFoundException e) {
    		  e.printStackTrace();
    	  }
    	  return pid;
		
      }
      public void updateDB(int id,String Pw) {
    		
    	  String sql_update = "UPDATE MEMBER SET PASSWORD='"+Pw+"' WHERE (ID="+id+")";
    	  
    	  System.out.println(sql_update);
    	  try {//다시연결
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  con = DriverManager.getConnection(url, user, password);
    		  stmt = con.createStatement(); 
    		  stmt.executeUpdate(sql_update);
    		  
    	  } catch (SQLException e) {
    		  e.printStackTrace();
    	  }catch (ClassNotFoundException e) {
    		  e.printStackTrace();
    	  }
    	  

      }
      public String viewNDB(String mac1) {
    	  String sql_selno= "SELECT NO FROM MAC WHERE (MAC='"+mac1+"')";
    	   
    	  try {//다시연결
  		  	Class.forName("oracle.jdbc.driver.OracleDriver");
  		  	con = DriverManager.getConnection(url, user, password);
  		  	stmt = con.createStatement();
 
  		  	ResultSet rs = stmt.executeQuery(sql_selno);
  		  	if(rs.next()) {}
  		  	no = rs.getString("no");
  		  	System.out.println("no :"+no);

  			return no;
  			  
  	  } catch (SQLException e) {
  		  e.printStackTrace();
  	  }catch (ClassNotFoundException e) {
  		  e.printStackTrace();
  	  }
  	  return no;
    	  
      }
      public String viewCDB(String mac1) {
    	  String sql_selcl= "SELECT CLASS FROM MAC WHERE MAC='"+mac1+"'";
    	  try {//다시연결
  		  	Class.forName("oracle.jdbc.driver.OracleDriver");
  		  	con = DriverManager.getConnection(url, user, password);
  		  	stmt = con.createStatement();
  		  	ResultSet rs = stmt.executeQuery(sql_selcl);
  		  	if(rs.next()) {}
  		  	cl = rs.getString("class");
  		  	System.out.println("cl :"+cl);
  			return cl;
  			  
  	  } catch (SQLException e) {
  		  e.printStackTrace();
  	  }catch (ClassNotFoundException e) {
  		  e.printStackTrace();
  		  
  	  }
  	  return cl;
    	  
      }
      public String loginNDB(String mac4) {
    	  String sql_selnos= "SELECT NO FROM MAC WHERE (MAC='"+mac4+"')";
    	   
    	  try {//다시연결
  		  	Class.forName("oracle.jdbc.driver.OracleDriver");
  		  	con = DriverManager.getConnection(url, user, password);
  		  	stmt = con.createStatement();
 
  		  	ResultSet rs = stmt.executeQuery(sql_selnos);
  		  	if(rs.next()) {}
  		  	nos = rs.getString("no");
  		  	System.out.println("nos :"+nos);

  			return nos;
  			  
  	  } catch (SQLException e) {
  		  e.printStackTrace();
  	  }catch (ClassNotFoundException e) {
  		  e.printStackTrace();
  		  
  	  }
  	  return nos;
      }
      public String loginCDB(String mac5) {
    	  String sql_selcls= "SELECT CLASS FROM MAC WHERE MAC='"+mac5+"'";
    	  try {//다시연결
  		  	Class.forName("oracle.jdbc.driver.OracleDriver");
  		  	con = DriverManager.getConnection(url, user, password);
  		  	stmt = con.createStatement();
  		  	ResultSet rs = stmt.executeQuery(sql_selcls);
  		  	if(rs.next()) {}
  		  	cls = rs.getString("class");
  		  	System.out.println("cls :"+cls);
  			return cls;
  			  
  	  } catch (SQLException e) {
  		  e.printStackTrace();
  	  }catch (ClassNotFoundException e) {
  		  e.printStackTrace();
  		  
  	  }
  	  return cls;
    	  
      }
      public void updateLoginYDB(int id) {
  		
    	  String sql_updatey = "UPDATE MEMBER SET LOGIN='N' WHERE (ID="+id+")";
  
    	  try {//다시연결
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  con = DriverManager.getConnection(url, user, password);
    		  stmt = con.createStatement(); 
    		  stmt.executeUpdate(sql_updatey);
    	  } catch (SQLException e) {
    		  e.printStackTrace();
    	  }catch (ClassNotFoundException e) {
    		  e.printStackTrace();
    	  }
      }
      public void updateLoginNDB(int id) {
    		
    	  String sql_updaten = "UPDATE MEMBER SET LOGIN='Y' WHERE (ID="+id+")";
  
    	  try {//다시연결
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  con = DriverManager.getConnection(url, user, password);
    		  stmt = con.createStatement(); 
    		  stmt.executeUpdate(sql_updaten);
    	  } catch (SQLException e) {
    		  e.printStackTrace();
    	  }catch (ClassNotFoundException e) {
    		  e.printStackTrace();
    	  }
      }
      public void updateLoginDB() {
    		
    	  String sql_updatea = "UPDATE MEMBER SET LOGIN='Y'";
  
    	  try {//다시연결
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  con = DriverManager.getConnection(url, user, password);
    		  stmt = con.createStatement(); 
    		  stmt.executeUpdate(sql_updatea);
    		  
    	  } catch (SQLException e) {
    		  e.printStackTrace();
    	  }catch (ClassNotFoundException e) {
    		  e.printStackTrace();
    	  }
      }
      public boolean selectlycheck(int id) {
    	  String sql_select = "SELECT LOGIN FROM MEMBER WHERE (ID="+id+")";
    	  try {//다시연결
    		  	Class.forName("oracle.jdbc.driver.OracleDriver");
    		  	con = DriverManager.getConnection(url, user, password);
    		  	stmt = con.createStatement(); 
    		  	ResultSet rs = stmt.executeQuery(sql_select);
    		  	
    		  	rs.next();
    		  	String data = rs.getString("login");
    		  	System.out.println(data);
    			 if(data.charAt(0)=='Y') {
    			  Isly=true;
    			 }
    			 else
    				 Isly=false;
    			  
    	  } catch (SQLException e) {
    		  e.printStackTrace();
    		  return false;
    	  }catch (ClassNotFoundException e) {
    		  e.printStackTrace();
    		  return false;
    	  }
    	  if(Isly==true) {
			  return true;
		  }
		  else
			  return false;
      }
    public static void main(String[] args) {
    
    }
}