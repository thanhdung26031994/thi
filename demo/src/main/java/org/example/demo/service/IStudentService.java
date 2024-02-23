package org.example.demo.service;

import org.example.demo.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> getAllStudent();

    void addStudent(Student student);

    Student findById(Integer id);

    void updateStudent(Student student);

    void moveById(Integer id);

    List<Student> searchByName(String name);
}
