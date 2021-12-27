package com.ua.command.get;

import com.ua.ConnectionPool;
import com.ua.Utils.CloseLink;
import com.ua.Utils.Constant;
import com.ua.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;


public class LoginCommand implements Command {

    private static final Logger log = LogManager.getLogger(LoginCommand.class.getName());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, Connection con) {
        //Checking for a login in the database and assigning it a role
        HttpSession session = req.getSession();
        String address = "";
        if (req.getParameter("globalLogin") != null) {
            session.setAttribute("globalLogin", req.getParameter("globalLogin"));
        }
        if (req.getParameter("password") != null) {
            session.setAttribute("globalPassword", req.getParameter("password"));
        }
        String login = (String) session.getAttribute("globalLogin");
        String password = (String) session.getAttribute("globalPassword");
        String role;
        int keyLogin = 0;
        PreparedStatement ps;
        try {
            System.out.println("con ==> " + con);
            ps = con.prepareStatement(Constant.SQL_SELECT_LOGIN_PASSWORD_WHERE_LOGIN_AND_PASSWORD);
            int k = 1;
            ps.setString(k++, login);
            ps.setString(k++, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                role = rs.getString("role");
                System.out.println("role=" + role);
                session.setAttribute("globalRole", role);
                keyLogin = rs.getInt("id");
                session.setAttribute("keyLogin", keyLogin);
                if (role.equals("ROLE_ADMIN")) {
                    session.setAttribute("finalAddress", "users/doctorList.jsp");
                    address = Constant.URL_CONTROLLER_VIEW_STAFF;
                    session.setAttribute("error", "");
                } else if (role.equals("ROLE_DOCTOR")) {
                    session.setAttribute("finalAddress", "users/doctor.jsp");
                    address = Constant.URL_CONTROLLER_VIEW_CASERECORD;
                } else if (role.equals("ROLE_NURSE")) {
                    session.setAttribute("finalAddress", "page?page=1&pageSize=6");
                    address = Constant.URL_CONTROLLER_VIEW_CASERECORD;
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException throwables) {
            log.error("command Login not executed" + con, throwables);
            session.setAttribute("errorMessage", 1);
            return Constant.URL_ERROR_PAGE;
        } finally {
            CloseLink.close(con);
        }
        return address;
    }


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        return execute(req, resp, ConnectionPool.getConnection());
    }
}
