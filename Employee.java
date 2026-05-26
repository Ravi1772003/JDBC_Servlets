package com.kodnest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.io.IOException;
@WebServlet("/Employee")
public class Employee extends HttpServlet {
	//String dpath="jdbc:mysql://localhost:3306/feb";
	//String url="com.mysql.cj.jdbc.Driver";
	//String name="root";
	//String pass="ravi1234";
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;
	String sql="select * from Employee";
	PrintWriter out=null;
		public void init() {
		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");

		        con = DriverManager.getConnection(
		                "jdbc:mysql://localhost:3306/feb",
		                "root",
		                "ravi1234");

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	
	public void service(HttpServletRequest req, HttpServletResponse res) {
        try {
        	out=res.getWriter();
        	Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from employee");
            out.println("<html><body>");
            out.println("<h2>Employee Data</h2>");

            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Salary</th>");
            out.println("</tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getDouble("salary") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void destroy() {
        try {
        	con.close();
        	stmt.close();
        	rs.close();
        	out.close();	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
