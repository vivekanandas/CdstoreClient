package com.ottawau.cdstore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ottawau.cdstore.rest.ProcessOrder;

@WebServlet("/processCDOrder")
public class ProcessOrderController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("InputName");
		System.out.println("Name entered by user =" +username);
		// Call the rest service from controller
		ProcessOrder po = new ProcessOrder();
		System.out.println("Response Received =" + po.submitProcess3(username));
		resp.sendRedirect(req.getContextPath() + "/index.jsp");
	}
	

}
