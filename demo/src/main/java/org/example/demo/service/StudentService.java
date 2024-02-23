package org.example.demo.service;

import org.example.demo.config.DBConnection;
import org.example.demo.model.ClassRoom;
import org.example.demo.model.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements IStudentService{
    @Override
    public List<Student> getAllStudent() {
        List<Student> studentList = new ArrayList<>();
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement("select student.*, classroom.name as nameClass\n" +
                    "from student\n" +
                    "join classroom on classroom.id = student.c_id;");

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                LocalDate dateOfBirth = rs.getDate("date_birth").toLocalDate();
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                Integer idClass = rs.getInt("c_id");
                String nameClass = rs.getString("nameClass");
                ClassRoom classRoom = new ClassRoom(idClass, nameClass);
                studentList.add(new Student(id, name, email, dateOfBirth, address, phone,classRoom));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return studentList;
    }

    @Override
    public void addStudent(Student student) {
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement("insert into student values (?,?,?,?,?,?,?);");
            statement.setInt(1, student.getId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getEmail());
            statement.setDate(4, Date.valueOf(student.getDateOfBirth()));
            statement.setString(5, student.getAddress());
            statement.setString(6, student.getPhone());
            statement.setInt(7, student.getClassRoom().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Student findById(Integer id) {
        Student student = null;
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement("select name, email, date_birth, address, phone, c_id from student where id = ?;");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                String email = rs.getString("email");
                LocalDate dateOfBirth = rs.getDate("date_birth").toLocalDate();
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                Integer idClass = rs.getInt("c_id");

                ClassRoom classRoom = new ClassRoom(idClass);
                student = new Student(name, email, dateOfBirth, address, phone, classRoom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return student;
    }

    @Override
    public void updateStudent(Student student) {
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement("update student set name = ?, email = ?, date_birth = ?, address = ?, phone = ?, c_id = ? where id = ?;");
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setDate(3, Date.valueOf(student.getDateOfBirth()));
            statement.setString(4, student.getAddress());
            statement.setString(5, student.getPhone());
            statement.setInt(6, student.getClassRoom().getId());
            statement.setInt(7, student.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void moveById(Integer id) {
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement("delete from student where id = ?;");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Student> searchByName(String name) {
        List<Student> studentList = new ArrayList<>();
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement("select student.*, classroom.name as nameClass\n" +
                    "from student\n" +
                    "join classroom on classroom.id = student.c_id\n" +
                    "having name  like ?;");
            statement.setString(1, "%" + name + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Integer id = rs.getInt("id");
                String name1 = rs.getString("name");
                String email = rs.getString("email");
                LocalDate dateOfBirth = rs.getDate("date_birth").toLocalDate();
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                Integer idClass = rs.getInt("c_id");
                String nameClass = rs.getString("nameClass");
                ClassRoom classRoom = new ClassRoom(idClass, nameClass);
                studentList.add(new Student(id, name1, email, dateOfBirth, address, phone,classRoom));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }
}
