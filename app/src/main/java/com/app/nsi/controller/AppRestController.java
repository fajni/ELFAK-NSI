package com.app.nsi.controller;

import com.app.nsi.dao.AppDAO;
import com.app.nsi.entity.Faculty;
import com.app.nsi.entity.Student;
import com.app.nsi.entity.StudentDetails;
import com.app.nsi.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@RestController
public class AppRestController {

    private final AppDAO appDAO;

    @Autowired
    public AppRestController(AppDAO appDAO) {
        this.appDAO = appDAO;
    }

    @GetMapping("/")
    public HashMap<String, String> hello() {
        return new HashMap<>(){{
            put("/students", "Show all students");
            put("/students/{id}", "Show student");
            put("/faculties", "Show all faculties");
            put("/subjects", "Show all subjects");
        }};
    }

    @GetMapping("/students")
    public List<Student> findAllStudents() {

        return appDAO.findAllStudents();
    }

    @GetMapping("/students/{id}")
    public Student findStudent(@PathVariable("id") Integer id) {

        return appDAO.findStudentById(id);
    }

    @GetMapping("/faculties")
    public List<Faculty> findAllFaculties() {

        return appDAO.findAllFaculties();
    }

    @GetMapping("/subjects")
    public List<Subject> findAllSubjects() {

        return appDAO.findAllSubjects();
    }

}
