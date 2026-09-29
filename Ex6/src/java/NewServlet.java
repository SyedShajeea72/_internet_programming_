package com.exam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ExamServlet")
public class NewServlet extends HttpServlet {

    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/online_exam";

    private static final String DB_USER = "root";

    private static final String DB_PASSWORD = "root";

    private Connection getConnection() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(
                DB_URL,
                DB_USER,
                DB_PASSWORD
        );
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");

        PrintWriter out =
                response.getWriter();

        String action =
                request.getParameter("action");

        try {

            if ("register".equals(action)) {

                register(request, out);

            } else if ("login".equals(action)) {

                login(request, out);

            } else if ("quiz".equals(action)) {

                quiz(request, out);

            } else {

                out.print("Invalid action");
            }

        } catch (Exception e) {

            e.printStackTrace();

            out.print("Database error: " + e.getMessage());
        }
    }


    // =========================
    // REGISTER
    // =========================

    private void register(
            HttpServletRequest request,
            PrintWriter out)
            throws Exception {

        String name =
                request.getParameter("name");

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");

        String confirmPassword =
                request.getParameter(
                        "confirmPassword"
                );


        if (name == null ||
            email == null ||
            password == null ||
            confirmPassword == null) {

            out.print("All fields are required");

            return;
        }


        if (!password.equals(confirmPassword)) {

            out.print(
                    "Passwords do not match"
            );

            return;
        }


        Connection con =
                getConnection();


        String checkSQL =
                "SELECT id FROM users WHERE email=?";


        PreparedStatement check =
                con.prepareStatement(checkSQL);

        check.setString(1, email);


        ResultSet rs =
                check.executeQuery();


        if (rs.next()) {

            out.print(
                    "Email already registered"
            );

            rs.close();
            check.close();
            con.close();

            return;
        }


        rs.close();
        check.close();


        String sql =
                "INSERT INTO users " +
                "(name,email,password) " +
                "VALUES(?,?,?)";


        PreparedStatement ps =
                con.prepareStatement(sql);


        ps.setString(1, name);

        ps.setString(2, email);

        ps.setString(3, password);


        int result =
                ps.executeUpdate();


        if (result > 0) {

            out.print(
                    "Registration successful"
            );

        } else {

            out.print(
                    "Registration failed"
            );
        }


        ps.close();

        con.close();
    }


    // =========================
    // LOGIN
    // =========================

    private void login(
            HttpServletRequest request,
            PrintWriter out)
            throws Exception {

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");


        String sql =
                "SELECT id,name FROM users " +
                "WHERE email=? AND password=?";


        Connection con =
                getConnection();


        PreparedStatement ps =
                con.prepareStatement(sql);


        ps.setString(1, email);

        ps.setString(2, password);


        ResultSet rs =
                ps.executeQuery();


        if (rs.next()) {

            int userId =
                    rs.getInt("id");

            String name =
                    rs.getString("name");


            HttpSession session =
                    request.getSession();


            session.setAttribute(
                    "userId",
                    userId
            );


            session.setAttribute(
                    "name",
                    name
            );


            session.setAttribute(
                    "email",
                    email
            );


            out.print(
                    "Login successful"
            );

        } else {

            out.print(
                    "Invalid email or password"
            );
        }


        rs.close();

        ps.close();

        con.close();
    }


    // =========================
    // QUIZ
    // =========================

    private void quiz(
            HttpServletRequest request,
            PrintWriter out)
            throws Exception {


        HttpSession session =
                request.getSession(false);


        if (session == null ||
            session.getAttribute("userId") == null) {

            out.print(
                    "Please login first"
            );

            return;
        }


        int userId =
                (Integer)
                session.getAttribute(
                        "userId"
                );


        String[] correctAnswers = {

            "a",
            "b",
            "c",
            "a",
            "b",
            "c",
            "a",
            "b",
            "c",
            "b"

        };


        int score = 0;


        for (int i = 0; i < 10; i++) {

            String question =
                    "q" + (i + 1);


            String answer =
                    request.getParameter(
                            question
                    );


            if (answer != null &&
                answer.equals(
                        correctAnswers[i]
                )) {

                score++;
            }
        }


        Connection con =
                getConnection();


        String sql =
                "INSERT INTO results " +
                "(user_id,score,total) " +
                "VALUES(?,?,?)";


        PreparedStatement ps =
                con.prepareStatement(sql);


        ps.setInt(1, userId);

        ps.setInt(2, score);

        ps.setInt(3, 10);


        ps.executeUpdate();


        ps.close();

        con.close();


        out.print(
                "Your Score: " +
                score +
                " / 10"
        );
    }


    // =========================
    // LOGOUT
    // =========================

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action =
                request.getParameter("action");


        if ("logout".equals(action)) {

            HttpSession session =
                    request.getSession(false);


            if (session != null) {

                session.invalidate();
            }


            response.getWriter().print(
                    "Logout successful"
            );

        } else {

            response.getWriter().print(
                    "Online Exam Servlet Running"
            );
        }
    }
}
