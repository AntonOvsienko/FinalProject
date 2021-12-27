//package com.ua;
//
//import com.ua.Utils.Constant;
//import com.ua.command.Command;
//import com.ua.command.CommandContainer;
//import org.junit.*;
//import org.mockito.*;
//import org.mockito.Mockito;
//
//import static org.mockito.Mockito.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.sql.*;
//import java.util.regex.Matcher;
//
//
//public class TestGetController {
//
//    private HttpServletRequest req;
//    private HttpServletResponse resp;
//    private HttpSession session;
//    private PreparedStatement ps;
//    private ResultSet rs;
//    private ResultSet rs1;
//    private ResultSet rs2;
//    private ResultSet rs3;
//    private Connection con;
//    private Statement st;
//    private Matcher matcher;
//
//    @Before
//    public void init() {
//        st = Mockito.mock(Statement.class);
//        rs = Mockito.mock(ResultSet.class);
//        rs1 = Mockito.mock(ResultSet.class);
//        rs2 = Mockito.mock(ResultSet.class);
//        rs3 = Mockito.mock(ResultSet.class);
//        ps = Mockito.mock(PreparedStatement.class);
//        resp = Mockito.mock(HttpServletResponse.class);
//        req = Mockito.mock(HttpServletRequest.class);
//        session = Mockito.mock(HttpSession.class);
//        con = mock(Connection.class);
//        matcher = mock(Matcher.class);
//        Mockito.when((req.getSession())).thenReturn(session);
//    }
//
//
//    @Test
//    public void еnterLoginAdminAndPasswordIsTrueShouldTrue() throws SQLException {
//        Mockito.when(req.getParameter("login"))
//                .thenReturn("admin");
//        Mockito.when(req.getParameter("password"))
//                .thenReturn("12345");
//        Mockito.when(ps.executeQuery())
//                .thenReturn(rs);
//        Mockito.when(con.prepareStatement("SELECT * FROM login_password WHERE login=? AND password=?"))
//                .thenReturn(ps);
//        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
//        Mockito.when(rs.getString("role")).thenReturn("administrator");
//        Mockito.when(rs.getInt("id")).thenReturn(1);
//        Mockito.when(con.createStatement()).thenReturn(st);
//        Mockito.when(st.executeQuery("SELECT * FROM administrator WHERE login_password_id=1")).thenReturn(rs2);
//        Mockito.when(rs2.next()).thenReturn(true).thenReturn(false);
//        Mockito.when(rs2.getString("name")).thenReturn("admin");
//        Mockito.when(rs2.getString("surname")).thenReturn("admin");
//        Mockito.when(rs2.getString("passport")).thenReturn("admin");
//        Mockito.when(rs2.getString("telephone")).thenReturn("admin");
//        Mockito.when(rs2.getString("id")).thenReturn("3");
//        try (MockedStatic<ConnectionPool> utilMock = Mockito.mockStatic(ConnectionPool.class)) {
//            utilMock.when(ConnectionPool::getConnection)
//                    .thenReturn(con);
//
//            Command command = CommandContainer.getCommand("login");
//            String address = command.execute(req, resp, con);
//            Assert.assertEquals(address, "controller?command=viewStaff");
//        }
//    }
//
//    @Test
//    public void еnterLoginAdminAndPasswordIsFalseShouldTrue() throws SQLException {
//        Mockito.when(req.getParameter("login"))
//                .thenReturn("admin");
//        Mockito.when(req.getParameter("password"))
//                .thenReturn("10");
//        Mockito.when(ps.executeQuery())
//                .thenReturn(rs);
//        Mockito.when(con.prepareStatement("SELECT * FROM login_password WHERE login=? AND password=?"))
//                .thenReturn(ps);
//        Mockito.when(rs.next()).thenReturn(false);
//        try (MockedStatic<ConnectionPool> utilMock = Mockito.mockStatic(ConnectionPool.class)) {
//            utilMock.when(ConnectionPool::getConnection)
//                    .thenReturn(con);
//
//            Command command = CommandContainer.getCommand("login");
//            String address = command.execute(req, resp, con);
//            Assert.assertEquals(address, "errorMessage/error.jsp");
//        }
//    }
//
//    @Test
//    public void еnterLoginDoctorAndPasswordIsTrueShouldTrue() throws SQLException {
//        Mockito.when(req.getParameter("login"))
//                .thenReturn("doctor1");
//        Mockito.when(req.getParameter("password"))
//                .thenReturn("12345");
//        Mockito.when(ps.executeQuery())
//                .thenReturn(rs);
//        Mockito.when(con.prepareStatement("SELECT * FROM login_password WHERE login=? AND password=?"))
//                .thenReturn(ps);
//        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
//        Mockito.when(rs.getString("role")).thenReturn("doctor");
//        Mockito.when(rs.getInt("id")).thenReturn(26);
//        Mockito.when(con.createStatement()).thenReturn(st);
//        Mockito.when(st.executeQuery("SELECT * FROM doctor WHERE login_password_id=26")).thenReturn(rs2);
//        try (MockedStatic<ConnectionPool> utilMock = Mockito.mockStatic(ConnectionPool.class)) {
//            utilMock.when(ConnectionPool::getConnection)
//                    .thenReturn(con);
//
//            Command command = CommandContainer.getCommand("login");
//            String address = command.execute(req, resp, con);
//            Assert.assertEquals(address, "controller?command=viewCaseRecord");
//        }
//    }
//
//    @Test
//    public void еnterLoginNurseAndPasswordIsTrueShouldTrue() throws SQLException {
//        Mockito.when(req.getParameter("login"))
//                .thenReturn("nurse1");
//        Mockito.when(req.getParameter("password"))
//                .thenReturn("12345");
//        Mockito.when(ps.executeQuery())
//                .thenReturn(rs);
//        Mockito.when(con.prepareStatement("SELECT * FROM login_password WHERE login=? AND password=?"))
//                .thenReturn(ps);
//        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
//        Mockito.when(rs.getString("role")).thenReturn("nurse");
//        Mockito.when(rs.getInt("id")).thenReturn(42);
//        Mockito.when(con.createStatement()).thenReturn(st);
//        Mockito.when(st.executeQuery("SELECT * FROM nurse WHERE login_password_id=42")).thenReturn(rs2);
//        try (MockedStatic<ConnectionPool> utilMock = Mockito.mockStatic(ConnectionPool.class)) {
//            utilMock.when(ConnectionPool::getConnection)
//                    .thenReturn(con);
//
//            Command command = CommandContainer.getCommand("login");
//            String address = command.execute(req, resp, con);
//            Assert.assertEquals(address, "controller?command=viewCaseRecord");
//        }
//    }
//
//    @Test
//    public void checkNewLoginAndPasswordShouldLoginTaken() throws SQLException {
//        Mockito.when(req.getParameter("login"))
//                .thenReturn("doctor10");
//        Mockito.when(req.getParameter("password"))
//                .thenReturn("12345");
//        Mockito.when(req.getParameter("password_repeat"))
//                .thenReturn("12345");
//        Mockito.when(req.getParameter("role"))
//                .thenReturn("doctor");
//        Mockito.when(ps.executeQuery())
//                .thenReturn(rs);
//        Mockito.when(con.prepareStatement("SELECT * FROM login_password WHERE login=?"))
//                .thenReturn(ps);
//        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
//        try (MockedStatic<ConnectionPool> utilMock = Mockito.mockStatic(ConnectionPool.class)) {
//            utilMock.when(ConnectionPool::getConnection)
//                    .thenReturn(con);
//
//            Command command = CommandContainer.getCommand("checkNewLogin");
//            String address = command.execute(req, resp, con);
//            Assert.assertEquals(address, "users/newLogin.jsp");
//        }
//    }
//
//    @Test
//    public void checkNewLoginAndPasswordWrongRepeatPassword() throws SQLException {
//        Mockito.when(req.getParameter("login"))
//                .thenReturn("doctor10");
//        Mockito.when(req.getParameter("password"))
//                .thenReturn("12345");
//        Mockito.when(req.getParameter("password_repeat"))
//                .thenReturn("123456");
//        Mockito.when(req.getParameter("role"))
//                .thenReturn("doctor");
//        Mockito.when(ps.executeQuery())
//                .thenReturn(rs);
//        Mockito.when(con.prepareStatement("SELECT * FROM login_password WHERE login=?"))
//                .thenReturn(ps);
//        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
//        try (MockedStatic<ConnectionPool> utilMock = Mockito.mockStatic(ConnectionPool.class)) {
//            utilMock.when(ConnectionPool::getConnection)
//                    .thenReturn(con);
//
//            Command command = CommandContainer.getCommand("checkNewLogin");
//            String address = command.execute(req, resp, con);
//            Assert.assertEquals(address, "users/newLogin.jsp");
//        }
//    }
//
//    @Test
//    public void checkNewLoginAndPasswordShouldTrue() throws SQLException {
//        Mockito.when(req.getParameter("login"))
//                .thenReturn("doctor10");
//        Mockito.when(req.getParameter("password"))
//                .thenReturn("12345");
//        Mockito.when(req.getParameter("password_repeat"))
//                .thenReturn("12345");
//        Mockito.when(req.getParameter("role"))
//                .thenReturn("doctor");
//        Mockito.when(ps.executeQuery())
//                .thenReturn(rs);
//        Mockito.when(con.prepareStatement("SELECT * FROM login_password WHERE login=?"))
//                .thenReturn(ps);
//        Mockito.when(rs.next()).thenReturn(false);
//        try (MockedStatic<ConnectionPool> utilMock = Mockito.mockStatic(ConnectionPool.class)) {
//            utilMock.when(ConnectionPool::getConnection)
//                    .thenReturn(con);
//
//            Command command = CommandContainer.getCommand("checkNewLogin");
//            String address = command.execute(req, resp, con);
//            Assert.assertEquals(address, "users/newLogin.jsp");
//        }
//    }
//
//    @Test
//    public void takeDoctorAppointmentListShouldTrue() throws SQLException {
//        Mockito.when(req.getParameter("caseRecordId"))
//                .thenReturn("5");
//        Mockito.when(session.getAttribute("caseRecordId"))
//                .thenReturn("5");
//        Mockito.when(con.prepareStatement("SELECT * FROM patient_has_case_records WHERE id=?"))
//                .thenReturn(ps);
//        Mockito.when(con.prepareStatement("SELECT * FROM doctor WHERE id=?"))
//                .thenReturn(ps);
//        Mockito.when(con.prepareStatement("SELECT * FROM patient WHERE id=?"))
//                .thenReturn(ps);
//        Mockito.when(con.prepareStatement("SELECT * FROM doctor_appointment WHERE case_record_id=?"))
//                .thenReturn(ps);
//        Mockito.when(con.prepareStatement("SELECT * FROM patient_has_case_records WHERE doctor_id=?"))
//                .thenReturn(ps);
//        Mockito.when(con.prepareStatement("SELECT * FROM case_record WHERE id=?"))
//                .thenReturn(ps);
//        Mockito.when(con.prepareStatement("SELECT * FROM patient_has_case_records"))
//                .thenReturn(ps);
//        Mockito.when(con.prepareStatement("SELECT * FROM patient_has_case_records WHERE patient_id=?"))
//                .thenReturn(ps);
//        Mockito.when(ps.executeQuery()).thenReturn(rs).thenReturn(rs2).thenReturn(rs2).thenReturn(rs2);
//
//        Mockito.when(ps.executeQuery())
//                .thenReturn(rs);
//        Mockito.when(rs.next()).thenReturn(true)
//                .thenReturn(false)
//                .thenReturn(true)
//                .thenReturn(false);
//        Mockito.when(rs2.next()).thenReturn(true)
//                .thenReturn(true)
//                .thenReturn(true)
//                .thenReturn(true)
//                .thenReturn(false)
//                .thenReturn(true)
//                .thenReturn(false)
//                .thenReturn(true)
//                .thenReturn(false)
//                .thenReturn(true)
//                .thenReturn(false)
//                .thenReturn(true)
//                .thenReturn(false)
//                .thenReturn(true)
//                .thenReturn(false)
//                .thenReturn(true)
//                .thenReturn(false);
//
//
//        Mockito.when(con.createStatement()).thenReturn(st);
//        Mockito.when(st.executeQuery("SELECT * FROM patient_has_case_records WHERE doctor_id=?"))
//                .thenReturn(rs1);
//        Mockito.when(st.executeQuery("SELECT * FROM patient WHERE id=?"))
//                .thenReturn(rs2);
//        Mockito.when(st.executeQuery("SELECT * FROM doctor WHERE id=?"))
//                .thenReturn(rs2);
//        Mockito.when(st.executeQuery("SELECT * FROM case_record WHERE id=?"))
//                .thenReturn(rs2);
//        Mockito.when(rs1.next()).thenReturn(true)
//                .thenReturn(false);
//        Mockito.when(rs.getDate("birthday")).thenReturn(Date.valueOf("2000-12-20"));
//        Mockito.when(rs.getInt("doctor_id")).thenReturn(359);
//        Mockito.when(rs.getInt("patient_id")).thenReturn(23);
//        Mockito.when(rs.getInt("case_record_id")).thenReturn(12);
//        try (MockedStatic<ConnectionPool> utilMock = Mockito.mockStatic(ConnectionPool.class)) {
//            utilMock.when(ConnectionPool::getConnection)
//                    .thenReturn(con);
//
//            Command command = CommandContainer.getCommand("doctorAppointment");
//            String address = command.execute(req, resp, con);
//            Assert.assertEquals(address, "controller?command=viewCaseRecord");
//        }
//    }
//
//    @Test
//    public void getArchiveListShouldTrue() throws SQLException {
//        Mockito.when(req.getParameter("caseRecordId"))
//                .thenReturn("5");
//
//        Mockito.when(con.prepareStatement("SELECT * FROM archive"))
//                .thenReturn(ps);
//        Mockito.when(con.prepareStatement("SELECT * FROM doctor_appointment_archive WHERE case_record_archive_id=?"))
//                .thenReturn(ps);
//        Mockito.when(con.prepareStatement("SELECT * FROM doctor_archive WHERE id=?"))
//                .thenReturn(ps);
//        Mockito.when(ps.executeQuery()).thenReturn(rs);
//        Mockito.when(ps.executeQuery()).thenReturn(rs3);
//        Mockito.when(rs.next()).thenReturn(true)
//                .thenReturn(false);
//        Mockito.when(rs.getInt("id")).thenReturn(10);
//        Mockito.when(rs2.getInt("id")).thenReturn(10);
//        Mockito.when(rs2.getInt("doctor_id")).thenReturn(20);
//        Mockito.when(rs.getString("name")).thenReturn("Василий");
//        Mockito.when(rs.getString("surname")).thenReturn("Олегович");
//        Mockito.when(rs.getString("passport")).thenReturn("АН18278472");
//        Mockito.when(rs.getString("telephone")).thenReturn("380507205911");
//        Mockito.when(rs2.getString("initial_diagnosis")).thenReturn("Перелом");
//        Mockito.when(rs2.getString("final_diagnosis")).thenReturn("Перелом со смещением");
//        Mockito.when(rs3.getString("type")).thenReturn("Приём лекарств");
//        Mockito.when(rs3.getString("description")).thenReturn("");
//        Mockito.when(rs3.getString("name_staff_complete")).thenReturn("Вася Петров(доктор)");
//        Mockito.when(matcher.find()).thenReturn(true);
//        Mockito.when(matcher.group(1)).thenReturn("1980");
//        Mockito.when(matcher.group(2)).thenReturn("09");
//        Mockito.when(matcher.group(3)).thenReturn("19");
//        Mockito.when(con.prepareStatement("SELECT * FROM case_record_archive WHERE archive_id=?"))
//                .thenReturn(ps);
//        Mockito.when(rs.getDate("birthday")).thenReturn(Date.valueOf("1980-09-19"));
//
//
//        Mockito.when(ps.executeQuery()).thenReturn(rs).thenReturn(rs2).thenReturn(rs2).thenReturn(rs2);
//        Mockito.when(rs3.next()).thenReturn(true)
//                .thenReturn(false);
//        Mockito.when(rs2.next()).thenReturn(true)
//                .thenReturn(false);
//        Mockito.when(session.getAttribute("finalAddress")).thenReturn("users/patients.jps");
//
//        try (MockedStatic<ConnectionPool> utilMock = Mockito.mockStatic(ConnectionPool.class)) {
//            utilMock.when(ConnectionPool::getConnection)
//                    .thenReturn(con);
//
//            Command command = CommandContainer.getCommand("archivePatient");
//            String address = command.execute(req, resp, con);
//            Assert.assertEquals(address, "users/patients.jps");
//        }
//    }
//
//    @Test
//    public void getPatientListShouldTrue() throws SQLException {
//        Mockito.when(req.getParameter("caseRecordId"))
//                .thenReturn("5");
//        Mockito.when(ps.executeQuery())
//                .thenReturn(rs);
//        Mockito.when(rs.next())
//                .thenReturn(true)
//                .thenReturn(false)
//                .thenReturn(true)
//                .thenReturn(false)
//                .thenReturn(true)
//                .thenReturn(false);
//        Mockito.when(con.prepareStatement("SELECT * FROM patient_has_case_records WHERE patient_id=?")).
//                thenReturn(ps);
//        Mockito.when(con.prepareStatement("SELECT * FROM patient WHERE id=?")).
//                thenReturn(ps);
//        Mockito.when(con.prepareStatement("SELECT * FROM case_record WHERE id=?")).
//                thenReturn(ps);
//        Mockito.when(con.prepareStatement("SELECT * FROM doctor WHERE id=?")).
//                thenReturn(ps);
//        Mockito.when(con.prepareStatement("SELECT * FROM patient")).
//                thenReturn(ps);
//        Mockito.when(con.prepareStatement("SELECT * FROM archive"))
//                .thenReturn(ps);
//        Mockito.when(con.prepareStatement("SELECT * FROM doctor_appointment_archive WHERE case_record_archive_id=?"))
//                .thenReturn(ps);
//        Mockito.when(con.prepareStatement("SELECT * FROM doctor_archive WHERE id=?"))
//                .thenReturn(ps);
//        Mockito.when(ps.executeQuery()).thenReturn(rs);
//        Mockito.when(ps.executeQuery()).thenReturn(rs3);
//
//        Mockito.when(rs.getInt("id")).thenReturn(10);
//        Mockito.when(rs2.getInt("id")).thenReturn(10);
//        Mockito.when(rs2.getInt("doctor_id")).thenReturn(20);
//        Mockito.when(rs.getString("name")).thenReturn("Василий");
//        Mockito.when(rs.getString("surname")).thenReturn("Олегович");
//        Mockito.when(rs.getString("passport")).thenReturn("АН18278472");
//        Mockito.when(rs.getString("telephone")).thenReturn("380507205911");
//        Mockito.when(rs2.getString("initial_diagnosis")).thenReturn("Перелом");
//        Mockito.when(rs2.getString("final_diagnosis")).thenReturn("Перелом со смещением");
//        Mockito.when(rs3.getString("type")).thenReturn("Приём лекарств");
//        Mockito.when(rs3.getString("description")).thenReturn("");
//        Mockito.when(rs3.getString("name_staff_complete")).thenReturn("Вася Петров(доктор)");
//        Mockito.when(matcher.find()).thenReturn(true);
//        Mockito.when(matcher.group(1)).thenReturn("1980");
//        Mockito.when(matcher.group(2)).thenReturn("09");
//        Mockito.when(matcher.group(3)).thenReturn("19");
//        Mockito.when(con.prepareStatement("SELECT * FROM case_record_archive WHERE archive_id=?"))
//                .thenReturn(ps);
//        Mockito.when(rs.getDate("birthday")).thenReturn(Date.valueOf("1980-09-19"));
//        Mockito.when(ps.executeQuery()).thenReturn(rs).thenReturn(rs2).thenReturn(rs2).thenReturn(rs2);
//        Mockito.when(rs3.next()).thenReturn(true)
//                .thenReturn(false);
//        Mockito.when(rs2.next()).thenReturn(true)
//                .thenReturn(false);
//        Mockito.when(session.getAttribute("finalAddress")).thenReturn("users/patients.jps");
//
//        try (MockedStatic<ConnectionPool> utilMock = Mockito.mockStatic(ConnectionPool.class)) {
//            utilMock.when(ConnectionPool::getConnection)
//                    .thenReturn(con);
//
//            Command command = CommandContainer.getCommand("viewPatient");
//            String address = command.execute(req, resp, con);
//            Assert.assertEquals(address, "controller?command=viewCaseRecord");
//        }
//    }
//
//    @Test
//    public void getCaseRecordListShouldTrue() throws SQLException {
//        Mockito.when(ps.executeQuery())
//                .thenReturn(rs);
//        Mockito.when(con.prepareStatement("SELECT * FROM patient_has_case_records"))
//                .thenReturn(ps);
//        Mockito.when(con.prepareStatement("SELECT * FROM doctor WHERE id=?"))
//                .thenReturn(ps);
//        Mockito.when(con.prepareStatement("SELECT * FROM patient WHERE id=?"))
//                .thenReturn(ps);
//        Mockito.when(con.prepareStatement("SELECT * FROM doctor_appointment WHERE case_record_id=?"))
//                .thenReturn(ps);
//        Mockito.when(con.prepareStatement("SELECT * FROM case_record WHERE id=?"))
//                .thenReturn(ps);
//        Mockito.when(con.prepareStatement("SELECT * FROM login_password WHERE id=?"))
//                .thenReturn(ps);
//
//        Mockito.when(rs2.getInt("login_password_id")).thenReturn(13);
//        Mockito.when(rs.getInt("patient_id")).thenReturn(10);
//        Mockito.when(rs.getInt("case_record_id")).thenReturn(11);
//        Mockito.when(rs.getInt("doctor_id")).thenReturn(12);
//        Mockito.when(rs3.getString("login")).thenReturn("admin");
//        Mockito.when(rs.getDate("birthday")).thenReturn(Date.valueOf("2000-10-05"));
//        Mockito.when(rs.next())
//                .thenReturn(true)
//                .thenReturn(false)
//                .thenReturn(true)
//                .thenReturn(false)
//                .thenReturn(true)
//                .thenReturn(false);
//        Mockito.when(rs2.next())
//                .thenReturn(true)
//                .thenReturn(false)
//                .thenReturn(true)
//                .thenReturn(false)
//                .thenReturn(true)
//                .thenReturn(false)
//                .thenReturn(true)
//                .thenReturn(false)
//                .thenReturn(true)
//                .thenReturn(false)
//                .thenReturn(true)
//                .thenReturn(false);
//        Mockito.when(rs2.getString("name")).thenReturn("Василий");
//        Mockito.when(rs2.getString("surname")).thenReturn("Олегович");
//        Mockito.when(rs2.getString("passport")).thenReturn("АН18278472");
//        Mockito.when(rs2.getString("telephone")).thenReturn("380507205911");
//        Mockito.when(rs2.getString("department")).thenReturn("Хирург");
//        Mockito.when(rs2.getString("initial_diagnosis")).thenReturn("Перелом");
//        Mockito.when(rs2.getInt("id")).thenReturn(15);
//        Mockito.when(rs2.getString("complete")).thenReturn("true");
//        Mockito.when(rs3.getString("type")).thenReturn("Приём лекарств");
//        Mockito.when(rs3.getString("description")).thenReturn("");
//        Mockito.when(rs3.getString("name_staff_complete")).thenReturn("Вася Петров(доктор)");
//
//        Mockito.when(session.getAttribute("finalAddress")).thenReturn("users/patients.jps");
//
//        try (MockedStatic<ConnectionPool> utilMock = Mockito.mockStatic(ConnectionPool.class)) {
//            utilMock.when(ConnectionPool::getConnection)
//                    .thenReturn(con);
//
//            Command command = CommandContainer.getCommand("viewCaseRecord");
//            String address = command.execute(req, resp, con);
//            Assert.assertEquals(address, "controller?command=archivePatient");
//        }
//    }
//
//    @After
//    public void destroy() throws SQLException {
//        con.close();
//    }
//}
