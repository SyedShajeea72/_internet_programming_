<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html>

<head>

    <title>Registration Successful</title>

    <style>

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #667eea, #764ba2);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .success-card {
            width: 550px;
            max-width: 92%;
            background: white;
            border-radius: 18px;
            padding: 40px;
            text-align: center;
            box-shadow: 0 15px 40px rgba(0,0,0,0.25);
        }

        .success-icon {
            width: 75px;
            height: 75px;
            margin: 0 auto 20px;
            border-radius: 50%;
            background: #22c55e;
            color: white;
            font-size: 42px;
            line-height: 75px;
            font-weight: bold;
        }

        h1 {
            color: #222;
            margin: 10px 0;
        }

        .message {
            color: #777;
            margin-bottom: 30px;
        }

        .details {
            text-align: left;
            background: #f8f8ff;
            border-radius: 12px;
            padding: 20px;
        }

        .detail-row {
            display: flex;
            justify-content: space-between;
            padding: 13px 5px;
            border-bottom: 1px solid #e5e5e5;
        }

        .detail-row:last-child {
            border-bottom: none;
        }

        .label {
            font-weight: bold;
            color: #555;
        }

        .value {
            color: #333;
            text-align: right;
        }

        .button {
            display: inline-block;
            margin-top: 25px;
            padding: 12px 25px;
            background: linear-gradient(90deg, #4f46e5, #7c3aed);
            color: white;
            text-decoration: none;
            border-radius: 8px;
            font-weight: bold;
        }

        .button:hover {
            opacity: 0.9;
        }

    </style>

</head>

<body>

<%

    String username = request.getParameter("username");
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    String qualification = request.getParameter("qualification");
    String jobrole = request.getParameter("jobrole");
    String experience = request.getParameter("experience");

%>


<div class="success-card">

    <div class="success-icon">
        ✓
    </div>


    <h1>Registration Successful!</h1>

    <p class="message">
        Welcome to CareerHub, <strong><%= name %></strong>!
        Your job seeker registration has been completed successfully.
    </p>


    <div class="details">

        <div class="detail-row">

            <span class="label">
                Full Name
            </span>

            <span class="value">
                <%= name %>
            </span>

        </div>


        <div class="detail-row">

            <span class="label">
                Username
            </span>

            <span class="value">
                <%= username %>
            </span>

        </div>


        <div class="detail-row">

            <span class="label">
                Email
            </span>

            <span class="value">
                <%= email %>
            </span>

        </div>


        <div class="detail-row">

            <span class="label">
                Phone
            </span>

            <span class="value">
                <%= phone %>
            </span>

        </div>


        <div class="detail-row">

            <span class="label">
                Qualification
            </span>

            <span class="value">
                <%= qualification %>
            </span>

        </div>


        <div class="detail-row">

            <span class="label">
                Job Role
            </span>

            <span class="value">
                <%= jobrole %>
            </span>

        </div>


        <div class="detail-row">

            <span class="label">
                Experience
            </span>

            <span class="value">
                <%= experience %>
            </span>

        </div>

    </div>


    <a href="register.html" class="button">
        Register Another User
    </a>

</div>


</body>

</html>