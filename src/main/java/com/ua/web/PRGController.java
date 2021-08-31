package com.ua.web;

import com.ua.ConnectionPool;
import com.ua.command.Command;
import com.ua.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/prg_controller")
public class PRGController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        // (3) do command
        try {
            address = command.execute(req, resp, ConnectionPool.getInstance().getConnection());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // (4) go to address
        String path = req.getContextPath() + "/" + address;
        System.out.println(path);
        resp.sendRedirect(path);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        System.out.println("session ==> " + session);
        session.setMaxInactiveInterval(30);
        String address = "errorMessage/error.jsp";
        if (req.getParameter("command") != null) {
            session.setAttribute("command", req.getParameter("command"));
        }
        String commandName = (String) session.getAttribute("command");
        System.out.println("commandName ==> " + commandName);

        // (2) get command
        Command command = CommandContainer.getCommand(commandName);
        System.out.println("command ==> " + command);

        address = command.execute(req, resp);
        System.out.println("address ==> " + address);
        req.getRequestDispatcher(address).forward(req, resp);
    }

}
