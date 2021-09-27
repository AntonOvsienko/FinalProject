package com.ua.command.update;

import com.ua.Utils.Constant;
import com.ua.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UpdateDoctorCommand implements Command {
    private static final Logger log = Logger.getLogger(UpdateDoctorCommand.class.getName());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, Connection con) {
        HttpSession session = req.getSession();
        System.out.println("session ==> " + session);
        String address=req.getParameter("address");
        if (session.getAttribute("changeProfile")==null){
            session.setAttribute("changeProfile","true");
            return address;
        }
        session.setAttribute("successfully", null);
        try {
            con.setAutoCommit(false);
            insertLogin(con, req);
            updateLogin(con, req);
            con.commit();
        } catch (SQLException throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
            session.setAttribute("error", "Ошибка ввода");
            return Constant.URL_NEW_LOGIN;
        } finally {
            try {
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        session.setAttribute("checkLogin", null);
        session.setAttribute("successfully", "Логин создан");
        session.setAttribute("error", null);
        return Constant.URL_NEW_LOGIN;
    }

    private void insertLogin(Connection con, HttpServletRequest req) throws SQLException {
        HttpSession session = req.getSession();
        PreparedStatement preparedStatement1;
        PreparedStatement preparedStatement2;
        PreparedStatement preparedStatement3;
        ResultSet rs2;
        String login = req.getParameter("newLogin");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        preparedStatement1 = con.prepareStatement(Constant.SQL_NEW_LOGIN_INSERT_LOGIN);
        preparedStatement1.setString(1, login);
        preparedStatement1.setString(2, password);
        preparedStatement1.setString(3, role);
        session.setAttribute("role", role);
        preparedStatement1.executeUpdate();
        preparedStatement2 = con.prepareStatement(Constant.SQL_SELECT_LOGIN_PASSWORD_WHERE_LOGIN);
        int k = 1;
        preparedStatement2.setString(k++, login);
        rs2 = preparedStatement2.executeQuery();
        int id = 0;
        while (rs2.next()) {
            id = rs2.getInt("id");
        }
        String path = "INSERT INTO " + role + " (login_password_id) VALUES (?)";
        preparedStatement3 = con.prepareStatement(path);
        preparedStatement3.setInt(1, id);
        preparedStatement3.executeUpdate();
        session.setAttribute("idLoginPassword", id);
        preparedStatement1.close();
        preparedStatement2.close();
        preparedStatement3.close();
        rs2.close();
    }

    private void updateLogin(Connection con, HttpServletRequest req) throws SQLException {
        HttpSession session = req.getSession();
        PreparedStatement preparedStatement;
        int k;
        String update = "";
        String role = (String) session.getAttribute("role");
        if (role.equals("doctor")) {
            update = Constant.SQL_NEW_LOGIN_UPDATE_DOCTOR;
        } else {
            update = Constant.SQL_NEW_LOGIN_UPDATE_NURSE;
        }
        System.out.println(update);
        preparedStatement = con.prepareStatement(update);
        k = 1;
        System.out.println("name => " + req.getParameter("name"));
        System.out.println("surname => " + req.getParameter("surname"));
        System.out.println("telephone => " + req.getParameter("telephone"));
        System.out.println("passport => " + req.getParameter("passport"));
        System.out.println("idLoginPassword => " + (int) session.getAttribute("idLoginPassword"));
        preparedStatement.setString(k++, req.getParameter("name"));
        preparedStatement.setString(k++, req.getParameter("surname"));
        preparedStatement.setString(k++, req.getParameter("telephone"));
        preparedStatement.setString(k++, req.getParameter("passport"));
        if (role.equals("doctor")) {
            preparedStatement.setString(k++, req.getParameter("department"));
        }
        preparedStatement.setInt(k++, (int) session.getAttribute("idLoginPassword"));
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "users/newLogin.jsp";
    }
}
