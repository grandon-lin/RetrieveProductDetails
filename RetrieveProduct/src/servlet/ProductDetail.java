package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.DBConnection;

/**
 * Servlet implementation class ProductDetail
 */
@WebServlet("/ProductDetail")
public class ProductDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public String GET_PRODUCT_DETAIL_QUERY = "select * from eproduct where ID = (?)";

    /**
     * Default constructor. 
     */
    public ProductDetail() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			
			InputStream in = getServletContext().getResourceAsStream("/WEB-INF/config.properties");
			Properties props = new Properties();
			props.load(in);
			
			DBConnection conn = new DBConnection(props.getProperty("url"), props.getProperty("userid"), props.getProperty("password"));
			PreparedStatement stmt = conn.getConnection().prepareStatement(GET_PRODUCT_DETAIL_QUERY);
			String id = request.getParameter("id");
			stmt.setString(1, id);
			ResultSet rst = stmt.executeQuery();
			while(rst.next())
			{
				out.println(rst.getString("id") + " " + rst.getString("name") + " " + rst.getString("price"));
			}
			
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
