package org.example.demo.controller;

import org.example.demo.model.ClassRoom;
import org.example.demo.model.Student;
import org.example.demo.service.ClassRoomService;
import org.example.demo.service.IClassRoomService;
import org.example.demo.service.IStudentService;
import org.example.demo.service.StudentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "StudentServlet", urlPatterns = "/student")
public class StudentController extends HttpServlet {
    private static final IStudentService studentService = new StudentService();
    private static final IClassRoomService classRoomService = new ClassRoomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreate(req,resp);
                break;
            case "edit":
                showEdit(req,resp);
                break;
            case "delete":
                deleteStudent(req,resp);
                break;
            default:
                listStudent(req,resp);
                break;
        }
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) {
        Integer id = Integer.valueOf(req.getParameter("id"));
        studentService.moveById(id);
        try {
            resp.sendRedirect("/student");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        Student student = studentService.findById(id);
        req.setAttribute("student", student);
        List<ClassRoom> classRooms = classRoomService.getAllClass();
        req.setAttribute("classes", classRooms);
        RequestDispatcher dispatcher = req.getRequestDispatcher("edit.jsp");
        dispatcher.forward(req,resp);
    }

    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("create.jsp");
        List<ClassRoom> classRooms = classRoomService.getAllClass();
        req.setAttribute("classes", classRooms);
        dispatcher.forward(req,resp);
    }

    private void listStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("list.jsp");
        List<Student> studentList = studentService.getAllStudent();
        req.setAttribute("student", studentList);
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                createStudent(req,resp);
                break;
            case "edit":
                editStudent(req,resp);
                break;
            case "search":
                searchByName(req,resp);
                break;
        }
    }

    private void searchByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        List<Student> studentList = studentService.searchByName(name);
        req.setAttribute("student", studentList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("search.jsp");
        dispatcher.forward(req,resp);
    }

    private void editStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        LocalDate dateOfBirth = LocalDate.ofEpochDay(req.getDateHeader("dateOfBirth"));
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        Integer idClass = Integer.valueOf(req.getParameter("idClass"));
        String nameClass = req.getParameter("nameClass");
        ClassRoom classRoom = new ClassRoom(idClass, nameClass);
        Student student = new Student(name, email, dateOfBirth, address, phone, classRoom);
        studentService.updateStudent(student);
        resp.sendRedirect("/student");
    }

    private void createStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
         Integer id = Integer.valueOf(req.getParameter("id"));
         String name = req.getParameter("name");
         String email = req.getParameter("email");
         LocalDate dateOfBirth = LocalDate.ofEpochDay(req.getDateHeader("dateOfBirth"));
         String address = req.getParameter("address");
         String phone = req.getParameter("phone");
         Integer idClass = Integer.valueOf(req.getParameter("idClass"));
         String nameClass = req.getParameter("nameClass");
         ClassRoom classRoom = new ClassRoom(idClass, nameClass);
         Student student = new Student(id, name, email, dateOfBirth, address, phone, classRoom);
         studentService.addStudent(student);
         resp.sendRedirect("/student");
    }
}
