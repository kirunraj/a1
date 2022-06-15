import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;


public class login extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	throws ServletException,IOException
	{
		final String DB_Driver = "com.mysql.cj.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost:3306/test";
		final String USER = "root";
		final String PASS = "password";
		System.out.println("hello");
		try{
			Class.forName(DB_Driver);
			Connection conct=DriverManager.getConnection(DB_URL,USER,PASS);
            String user=req.getParameter("user");
			System.out.println("User: " + user);
			Statement stm=conct.createStatement();
			String sql="select user,password from temp where user=\'"+user+"\';";
			ResultSet rs=stm.executeQuery(sql);
			PrintWriter pw=res.getWriter();

			if(rs.next())
			{
				if(rs.getString("user").equals(user) && rs.getString("password").equals(req.getParameter("pass"))){
					RequestDispatcher rd = req.getRequestDispatcher("adminside.html");
            		rd.forward(req, res);
				}
				else{
					System.out.println("Password mismatch");
            		res.sendRedirect("admin.html");
				}
			}
			else{
				pw.println("Password mismatch");
				res.sendRedirect("admin.html");
			}
		}
		
		catch(Exception e){
			System.out.println(e);	
		}
	}
}