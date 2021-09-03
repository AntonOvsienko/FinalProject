package com.ua.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

public class SortPatientListCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, Connection con) throws SQLException {
        return null;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String sort = req.getParameter("sort2");
        session.setAttribute("sort2", sort);
        System.out.println("sort =>" + sort);
        if (sort.equals("sortByName")) {
            session.setAttribute("patients", session.getAttribute("patientsByName"));
        }
        if (sort.equals("sortByBirthday")) {
            session.setAttribute("patients", session.getAttribute("patientsByBirthday"));
        }

        return "users/doctorList.jsp";
    }
}