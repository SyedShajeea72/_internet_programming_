package com.shopping.controller;

import com.shopping.dao.OrderDAO;
import com.shopping.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

@WebServlet("/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        try {

            // Get values from JSP form
            String customerName = request.getParameter("customerName");
            String productName = request.getParameter("productName");

            int quantity = Integer.parseInt(
                    request.getParameter("quantity")
            );

            BigDecimal price = new BigDecimal(
                    request.getParameter("price")
            );

            Date orderDate = Date.valueOf(
                    request.getParameter("orderDate")
            );

            String address = request.getParameter("address");

            // Calculate total amount
            BigDecimal totalAmount = price.multiply(
                    BigDecimal.valueOf(quantity)
            );

            // Create Order object
            Order order = new Order(
                    customerName,
                    productName,
                    quantity,
                    price,
                    totalAmount,
                    orderDate,
                    address
            );

            // Create DAO object
            OrderDAO dao = new OrderDAO();

            // Insert order into database
            boolean result = dao.addOrder(order);

            if (result) {

                // Successfully inserted
                response.sendRedirect("viewOrders.jsp");

            } else {

                // Insert failed
                response.setContentType("text/html");

                response.getWriter().println(
                        "<h2>Failed to add order.</h2>"
                );

                response.getWriter().println(
                        "<p>Database insertion failed.</p>"
                );
            }

        } catch (Exception e) {

            // Display the actual error
            response.setContentType("text/html");

            response.getWriter().println(
                    "<h2>Error while adding order</h2>"
            );

            response.getWriter().println("<pre>");

            e.printStackTrace(
                    response.getWriter()
            );

            response.getWriter().println("</pre>");
        }
    }
}