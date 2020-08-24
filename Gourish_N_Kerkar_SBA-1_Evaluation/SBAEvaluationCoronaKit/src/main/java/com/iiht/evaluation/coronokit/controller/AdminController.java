package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.ProductMaster;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductMasterDao productMasterDao;
	private HttpSession adminSession;

	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");
		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String viewName = "";
		try {
			switch (action) {
			case "login":
				viewName = adminLogin(request, response);
				break;
			case "newproduct":
				viewName = showNewProductForm(request, response);
				break;
			case "insertproduct":
				viewName = insertProduct(request, response);
				break;
			case "deleteproduct":
				viewName = deleteProduct(request, response);
				break;
			case "deleteproductconfirm":
				viewName = deleteProductConfirm(request, response);
				break;
			case "editproduct":
				viewName = showEditProductForm(request, response);
				break;
			case "updateproduct":
				viewName = updateProduct(request, response);
				break;
			case "list":
				viewName = listAllProducts(request, response);
				break;
			case "logout":
				viewName = adminLogout(request, response);
				break;
			default:
				viewName = "notfound.jsp";
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		if(viewName==null || viewName.equals("")) {
			response.sendError(response.SC_NOT_FOUND);
		}
		else {
		RequestDispatcher dispatch = request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);
		}
	}

	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		adminSession = request.getSession();
		adminSession.invalidate();
		return "index.jsp";
	}

	private String listAllProducts(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// need to retrieve records from DB
		List<ProductMaster> products = this.productMasterDao.getAllProducts();

		// Put data into request object (to share with view page)
		request.setAttribute("products", products);
		
		return "admin?action=list";
	}

	private String updateProduct(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {

		// TODO Auto-generated method stub
		adminSession = request.getSession();
		
		int pid = Integer.parseInt(adminSession.getAttribute("pid").toString());
		
		String pname = request.getParameter("pname");
		String pdesc = request.getParameter("pdesc");
		String pcost = request.getParameter("pcost");

		if (pid == -1) {
			return "admin?action=list";
		}
			
		if (this.productMasterDao.updateProduct(pid, pname, pdesc, pcost)) {
			adminSession.setAttribute("pid", -1);
			return "admin?action=list";
		}
		else
			return "errorPage.jsp";
	}

	private String showEditProductForm(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		String pid = request.getParameter("pid");
		adminSession = request.getSession();
		adminSession.setAttribute("pid", pid);

		// need to retrieve record from DB
		ProductMaster product = this.productMasterDao.getRequiredProductDetail(pid);
		// Put data into request object (to share with view page)
		request.setAttribute("product", product);

		return "editproduct.jsp";
	}

	private String deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		String pid = request.getParameter("pid");
		adminSession = request.getSession();
		adminSession.setAttribute("pid", pid);

		// need to retrieve record from DB
		ProductMaster product = this.productMasterDao.getRequiredProductDetail(pid);
		// Put data into request object (to share with view page)
		request.setAttribute("product", product);
		
		return "deleteproduct.jsp";

	}
	
	private String deleteProductConfirm(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		adminSession = request.getSession();
		int pid = Integer.parseInt(adminSession.getAttribute("pid").toString());
		
		if (pid == -1) {
			return "admin?action=list";
		}
		
		if (this.productMasterDao.deleteProduct(Integer.toString(pid))){
			adminSession.setAttribute("pid", -1);
			return "admin?action=list";
		}
		else
			return "errorPage.jsp";

	}

	private String insertProduct(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		String pname = request.getParameter("pname");
		String pdesc = request.getParameter("pdesc");
		String pcost = request.getParameter("pcost");
	
		adminSession = request.getSession();
		int pid = Integer.parseInt(adminSession.getAttribute("pid").toString());
		
		if (pid == -1) {
			return "admin?action=list";
		}
		
		if (this.productMasterDao.addNewProduct(pname, pdesc, pcost)) {
			adminSession.setAttribute("pid", -1);
			return "admin?action=list";
		}
		else
			return "errorPage.jsp";

	}

	private String showNewProductForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		adminSession.setAttribute("pid", 0);
		
		return "newproduct.jsp";
	}

	private String adminLogin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String adminId = request.getParameter("loginid");
		String adminPassword = request.getParameter("password");
		
		if(adminId==null || adminPassword==null) {
			return "index.jsp";
		}

		if (adminId.equals("admin") && adminPassword.equals("admin")) {
		//adminSession = request.getSession();
			adminSession.setAttribute("adminId", adminId);
			return "admin?action=list";
		} else {
			return "errorPage.jsp";
		}
	}

}