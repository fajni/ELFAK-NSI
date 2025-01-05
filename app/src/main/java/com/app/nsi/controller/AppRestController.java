package com.app.nsi.controller;

import com.app.nsi.dao.AppDAO;
import com.app.nsi.entity.Faculty;
import com.app.nsi.entity.Student;
import com.app.nsi.entity.StudentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppRestController {

    private final AppDAO appDAO;

    @Autowired
    public AppRestController(AppDAO appDAO) {
        this.appDAO = appDAO;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/students")
    public List<Student> findAllStudents() {

        List<Student> students = appDAO.findAllStudents();
        List<StudentDetails> details = appDAO.findStudentDetails();

        for(Student student : students)
            System.out.println(student);

        return students;
    }

    @GetMapping("/students/{id}")
    public Student findStudent(@PathVariable("id") Integer id) {

        return appDAO.findStudentById(id);
    }

    @GetMapping("/faculties")
    public List<Faculty> findAllFaculties() {

        return appDAO.findAllFaculties();
    }

}
