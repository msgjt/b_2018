package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.msg.edu.jbugs.userManagement.business.control.UserService;
import ro.msg.edu.jbugs.userManagement.business.converter.UserConverter;
import ro.msg.edu.jbugs.userManagement.persistence.dao.BugDao;
import ro.msg.edu.jbugs.userManagement.persistence.dao.UserDao;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.BugStatusType;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.SeverityType;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.UserStatus;

@WebServlet(urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    @EJB
    private UserService userService;

    @EJB
    private UserDao userDao;

    @EJB
    private BugDao bugDao;

    @EJB
    private UserConverter userConverter;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

//        UserDto user1 = UserDto.builder()
//                .email("aaaa@msggroup.com")
//                .firstName("Alex")
//                .lastName("Luca")
//                .password("password")
//                .mobileNumber("1234567890")
//                .build();
//
//        UserDto userDto = new UserDto();
//        String message = "";
//        List<UserDto> users = userService.getAllUsers();
//        try {
//            userDto = userService.createUser(user1);
////            userService.setUserStatus(441L, UserStatus.DISABLED);
////            userService.login("lucaal", "password");
//
//            message = "success";
//        } catch (BusinessException e) {
//            e.printStackTrace();
//            message = "failed!";
//        }

        Optional<User> optionalUser = userDao.getUserByUsernameWithRolesAndPermissions("macarc");
        User user = new User();
        user.setFirstName("qwerty");
        user.setLastName("asdfg");
        user.setId(1l);
        user.setPassword("fd");
        user.setStatus(UserStatus.ACTIVE);
        userDao.createUser(user);
//        Optional<User> userOptional = userDao.getUserByUsername("admina");
//        User user = userOptional.get();
//        userDao.addUser(user);
//        Bug bug = new Bug();
//        bug.setTitle("qwerty");
//        bug.setVersion("1");
//        bug.setStatusType(BugStatusType.IN_PROGRESS);
//        bug.setSeverityType(SeverityType.HIGH);
//        bug.setDescription("d");
//        bug.setDueDate(Date.valueOf(LocalDate.now()));
//        bug.setCreator(user);
//        bug.setAssignee(user);
//        bug.setId(1l);
//        bugDao.addBug(bug);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Test EJB Bean New</title>");
            out.println("</head>");
            out.println("<body>");
//            out.println(message + "<br>");
//            out.println(userDto.toString());
//            users.forEach(u->out.print(u+"<br>"));
            out.print(optionalUser.get());
            optionalUser.get().getRoles().forEach(out::println);
            optionalUser.get().getRoles().forEach(r->r.getPermissions().forEach(out::println));
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on
    // the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
