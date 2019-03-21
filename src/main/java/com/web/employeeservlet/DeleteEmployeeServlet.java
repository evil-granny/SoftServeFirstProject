package com.web.employeeservlet;

import com.database.service.EmployeeService;
import com.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/delete-employee")
public class DeleteEmployeeServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(DeleteEmployeeServlet.class);
    private Long id;
    private EmployeeService service = new EmployeeService();
    private List<Employee> employees = new ArrayList<>();

    public DeleteEmployeeServlet() {
        super();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("employees", service.getAll());
        request.getRequestDispatcher("/WEB-INF/views/employee.jsp").forward(request, response);
        id = Long.valueOf(request.getParameter("id"));
        service.remove(id);
        response.sendRedirect("/com_serve_main_war_exploded/employee");

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doDelete(req, resp);
        service.remove(id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
