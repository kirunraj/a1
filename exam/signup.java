import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class signup extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	throws ServletException,IOException{
		final String JDBC_Driver="com.mysql.cj.jdbc.Driver";
		final String DB_URL="jdbc:mysql://localhost:3306/test";
		final String USER="root";
		final String PASS="password";
        PrintWriter out=res.getWriter();
        res.setContentType("text/html");
		try{
			Class.forName(JDBC_Driver);
			Connection conct=DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stm=conct.createStatement();
			String sql="insert into temp(user,password) values(";
			sql=sql+"'"+req.getParameter("user") + "','" + req.getParameter("pass")+"');";
			System.out.println(sql);
			stm.execute(sql);
            out.print("<h1>Inserted</h1>");
			// RequestDispatcher rd = req.getRequestDispatcher("show");
            // rd.forward(req, res);
			res.sendRedirect("index.html");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}