package com.ua.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

public class ConfirmNurseAppointmentCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, Connection con) throws SQLException {
        HttpSession session = req.getSession();
        System.out.println("session ==> " + session);
        Statement st = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String URL = "controller?command=viewNurse";
        String[] checkbox = req.getParameterValues("appointment");
        try {
            con.setAutoCommit(false);
            StringBuilder nameSurname = new StringBuilder();
            int id = (int) session.getAttribute("keyLogin");
            String findName = "SELECT * FROM nurse WHERE login_password_id=" + id;
            st = con.createStatement();
            rs = st.executeQuery(findName);
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                nameSurname.append(name).append(" ").append(surname);
            }
            String findRole = "SELECT * FROM login_password WHERE id=" + id;
            st = con.createStatement();
            rs = st.executeQuery(findRole);
            while (rs.next()) {
                String role = rs.getString("role");
                if (role.equals("doctor")) {
                    nameSurname.append("(").append("доктор").append(")");
                }
                if (role.equals("nurse")) {
                    nameSurname.append("(").append("медсестра").append(")");
                }
            }
            for (int i = 0; i < checkbox.length; i++) {
                String path = "UPDATE doctor_appointment SET complete='true'" +
                        ", name_staff_complete='" + nameSurname.toString() + "'" +
                        " WHERE id=" + checkbox[i];
                System.out.println(path);
                ps = con.prepareStatement(path);
                ps.execute();
            }
            con.commit();
        } catch (SQLException throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        System.out.println(URL);
        return URL;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        return null;
    }
}
