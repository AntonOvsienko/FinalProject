package com.ua.web;

import com.ua.ConnectionPool;
import com.ua.Utils.Constant;
import com.ua.command.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // (0) session setup
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        System.out.println("session==>" + session);
        session.setMaxInactiveInterval(30);

        // (1) get command name

        if (req.getParameter("command") != null) {
            session.setAttribute("command", req.getParameter("command"));
        }
        String commandName = (String) session.getAttribute("command");
        System.out.println("commandName ==> " + commandName);

        // (2) get command
        Command command = CommandContainer.getCommand(commandName);
        System.out.println("command ==> " + command);

        String address = "errorMessage/error.jsp";
        ConnectionPool.getInstance();
        // (3) do command
        try {
            address = command.execute(req, resp, ConnectionPool.getConnection());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // (4) go to address
        resp.sendRedirect(address);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        System.out.println("session ==> " + session);
        session.setMaxInactiveInterval(-1);
        String address = Constant.URL_ERROR_PAGE;
        if (req.getParameter("command") != null) {
            session.setAttribute("command", req.getParameter("command"));
        }
        System.out.println("address => " + req.getParameter("address"));
        String commandName = (String) session.getAttribute("command");
        System.out.println("commandName ==> " + commandName);

        // (2) get command
        Command command = CommandContainer.getCommand(commandName);
        System.out.println("command ==> " + command);

        try {
            address = command.execute(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("address ==> " + address);
        req.getRequestDispatcher(address).forward(req, resp);
    }
}
