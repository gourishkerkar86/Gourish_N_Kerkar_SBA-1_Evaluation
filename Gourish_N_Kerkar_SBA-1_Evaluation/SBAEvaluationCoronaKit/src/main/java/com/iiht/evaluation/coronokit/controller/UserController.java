package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.coronokit.dao.KitDao;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;
import com.iiht.evaluation.coronokit.model.ProductMaster;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KitDao kitDAO;
	private ProductMasterDao productMasterDao;
	private HttpSession custSession;
	private List<String> productsToAdd = new ArrayList<String>();

	public void setKitDAO(KitDao kitDAO) {
		this.kitDAO = kitDAO;
	}

	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");

		this.kitDAO = new KitDao(jdbcURL, jdbcUsername, jdbcPassword);
		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);

	}


	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		String viewName = "";
		try {
			switch (action) {
			case "newuser":
				viewName = showNewUserForm(request, response);
				break;
			case "insertuser":
				viewName = insertNewUser(request, response);
				break;
			case "showproducts":
				viewName = showAllProducts(request, response);
				break;
			case "addnewitem":
				viewName = addNewItemToKit(request, response);
				break;
			case "deleteitem":
				viewName = deleteItemFromKit(request, response);
				break;
			case "showkit":
				viewName = showKitDetails(request, response);
				break;
			case "placeorder":
				viewName = showPlaceOrderForm(request, response);
				break;
			case "saveorder":
				viewName = saveOrderForDelivery(request, response);
				break;
			case "ordersummary":
				viewName = showOrderSummary(request, response);
				break;
			default:
				viewName = "notfound.jsp";
				break;
			}
		} catch (Exception ex) {

			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);
	}


	private String showOrderSummary(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub

		custSession = request.getSession();
		int validateOrder = Integer.parseInt(custSession.getAttribute("validateOrder").toString());
		
		if (validateOrder == -1) {
			
			CoronaKit orderSummary = this.kitDAO.getLastOrderSummary();
			request.setAttribute("OrderSummary", orderSummary);

			return "ordersummary.jsp";
		}	

		CoronaKit newUserRegDetails = (CoronaKit) custSession.getAttribute("coronaKit");
		newUserRegDetails.setDeliveryAddress(request.getParameter("address"));

		Integer orderTotal = (Integer)custSession.getAttribute("OrderTotal");

		if (this.kitDAO.addNewOrder(newUserRegDetails, orderTotal)) {
			custSession.setAttribute("validateOrder", -1);
			
			CoronaKit orderSummary = this.kitDAO.getLastOrderSummary();
			request.setAttribute("OrderSummary", orderSummary);

			return "ordersummary.jsp";
		}
		else
			return "errorPage.jsp";
	}

	private String saveOrderForDelivery(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String showPlaceOrderForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		custSession = request.getSession();
		custSession.setAttribute("validateOrder", 0);
		
		return "placeorder.jsp";
	}

	private String showKitDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub

		custSession = request.getSession();
		List<KitDetail> addedProducts = new ArrayList<KitDetail>();
		int totalAmount = 0;
		
		if(custSession.getAttribute("productsAddedtoCart") == null) {
			
			custSession.setAttribute("validateOrder", -1);
			return "user?action=showproducts";
			
		}

		Map<String, Integer> productsAddedtoCart = (Map<String, Integer>) custSession.getAttribute("productsAddedtoCart");
		
		for (String pid : productsAddedtoCart.keySet()) {
			int productAmountTotal = 0;
			ProductMaster product = this.productMasterDao.getRequiredProductDetail(pid);
			KitDetail kitDetail = new KitDetail();

			productAmountTotal = product.getCost() * productsAddedtoCart.get(pid);
			totalAmount += productAmountTotal;
			
			kitDetail.setProductId(product.getProductId());
			kitDetail.setProductName(product.getProductName());
			kitDetail.setAmount(product.getCost());
			kitDetail.setQuantity(productsAddedtoCart.get(pid));

			addedProducts.add(kitDetail);
		}

		// Put data into request object (to share with view page)
		custSession.setAttribute("KitDetailList", addedProducts);
		custSession.setAttribute("OrderTotal", totalAmount);

		return "showkit.jsp";
	}

	private String deleteItemFromKit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String addNewItemToKit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		custSession = request.getSession();
		custSession.setAttribute("productsToAdd", productsToAdd);

		String productId = request.getParameter("pid");
		
		custSession.getAttribute("productsAddedtoCart");

		Map<String, Integer> productsAddedtoCart = (Map<String, Integer>) custSession.getAttribute("productsAddedtoCart");

		if (productsAddedtoCart == null) {
			productsAddedtoCart = new HashMap<>();
			custSession.setAttribute("productsAddedtoCart", productsAddedtoCart);
		}

		if (productsAddedtoCart.keySet().contains(productId)) {
			productsAddedtoCart.replace(productId, productsAddedtoCart.get(productId) + 1);
		} else {
			productsAddedtoCart.put(productId, 1);
		}
		
		custSession.setAttribute("validateOrder", 0);
				
		return "user?action=showproducts";
	}

	private String showAllProducts(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// need to retrieve records from DB
		List<ProductMaster> products = this.productMasterDao.getAllProducts();

		// Put data into request object (to share with view page)
		request.setAttribute("products", products);

		return "showproductstoadd.jsp";
	}

	private String insertNewUser(HttpServletRequest request, HttpServletResponse response) {

		custSession = request.getSession();
		custSession.removeAttribute("KitDetailList");
		custSession.removeAttribute("coronaKit");
		custSession.removeAttribute("productsToAdd");
		custSession.removeAttribute("productsAddedtoCart");
		custSession.removeAttribute("validateOrder");

		CoronaKit newUserRegDetails = new CoronaKit();
		newUserRegDetails.setPersonName(request.getParameter("pname"));
		newUserRegDetails.setEmail(request.getParameter("pemail"));
		newUserRegDetails.setContactNumber(request.getParameter("pcontact"));

		custSession.setAttribute("coronaKit", newUserRegDetails);

		return "user?action=showproducts";
	}

	private String showNewUserForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "newuser.jsp";
	}
}