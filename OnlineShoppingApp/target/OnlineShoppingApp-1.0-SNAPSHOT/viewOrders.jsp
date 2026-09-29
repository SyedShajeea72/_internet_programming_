<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="com.shopping.model.Order" %>
<%@ page import="com.shopping.dao.OrderDAO" %>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Order Details</title>

   <style>

    * {
        box-sizing: border-box;
    }

    body {
        margin: 0;
        font-family: "Segoe UI", Arial, sans-serif;
        background: #eef2f7;
        color: #263238;
    }

    .container {
        width: 92%;
        max-width: 1150px;
        margin: 45px auto;
        background: #ffffff;
        border-radius: 12px;
        padding: 35px;
        box-shadow: 0 5px 20px rgba(0, 0, 0, 0.08);
    }

    .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 25px;
    }

    h1 {
        margin: 0;
        font-size: 28px;
        color: #1e3a5f;
    }

    .subtitle {
        margin-top: 8px;
        color: #78909c;
        font-size: 14px;
    }

    .table-wrapper {
        overflow-x: auto;
        border: 1px solid #e0e6ed;
        border-radius: 8px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        min-width: 850px;
    }

    th {
        background: #1e3a5f;
        color: white;
        padding: 15px 12px;
        font-size: 14px;
        text-align: left;
        letter-spacing: 0.3px;
    }

    td {
        padding: 14px 12px;
        border-bottom: 1px solid #e8edf2;
        font-size: 14px;
    }

    tbody tr:hover {
        background: #f5f8fb;
    }

    tbody tr:last-child td {
        border-bottom: none;
    }

    .order-id {
        font-weight: bold;
        color: #1e3a5f;
    }

    .price {
        font-weight: 600;
        color: #37474f;
    }

    .total {
        font-weight: bold;
        color: #00897b;
    }

    .quantity {
        text-align: center;
    }

    .btn-area {
        margin-top: 25px;
        text-align: right;
    }

    .btn {
        display: inline-block;
        background: #00897b;
        color: white;
        text-decoration: none;
        padding: 12px 22px;
        border-radius: 7px;
        font-size: 14px;
        font-weight: 600;
        transition: 0.2s;
    }

    .btn:hover {
        background: #00695c;
    }

    .empty {
        text-align: center;
        padding: 30px;
        color: #78909c;
    }

    @media (max-width: 700px) {

        .container {
            width: 96%;
            padding: 20px;
            margin: 20px auto;
        }

        .header {
            display: block;
        }

        h1 {
            font-size: 24px;
        }

        .btn-area {
            text-align: center;
        }
    }

</style>

</head>

<body>

<div class="container">

    <h1>🛒 Order Details</h1>

    <p class="subtitle">All Customer Orders</p>

    <table>

        <tr>
            <th>Order ID</th>
            <th>Customer</th>
            <th>Product</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Total</th>
            <th>Date</th>
            <th>Address</th>
        </tr>

        <%
            OrderDAO dao = new OrderDAO();
            List<Order> orders = dao.getAllOrders();

            for (Order order : orders) {
        %>

        <tr>

            <td>
                <%= order.getOrderId() %>
            </td>

            <td>
                <%= order.getCustomerName() %>
            </td>

            <td>
                <%= order.getProductName() %>
            </td>

            <td>
                <%= order.getQuantity() %>
            </td>

            <td class="price">
                ₹<%= order.getPrice() %>
            </td>

            <td class="total">
                ₹<%= order.getTotalAmount() %>
            </td>

            <td>
                <%= order.getOrderDate() %>
            </td>

            <td>
                <%= order.getAddress() %>
            </td>

        </tr>

        <%
            }
        %>

    </table>

    <div class="btn-container">

        <a href="orderForm.jsp" class="btn">
            ➕ Place New Order
        </a>

    </div>

</div>

</body>
</html>