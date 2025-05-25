package com.app.nsi.dao;

import com.app.nsi.entity.Faculty;
import com.app.nsi.entity.Student;
import com.app.nsi.entity.StudentDetails;
import com.app.nsi.entity.Subject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Repository
public class AppDAO {

    private final EntityManager entityManager;

    @Autowired
    public AppDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    // STUDENT:
    @Transactional
    public void addStudent(Student student) {

        entityManager.persist(student);
    }

    @Transactional
    public void addStudentWithDetails(Student student, StudentDetails studentDetails) {

        student.setStudentDetails(studentDetails);

        entityManager.persist(student);
    }

    public List<Student> findAllStudents() {

        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s", Student.class);

        return query.getResultList();
    }

    public List<StudentDetails> findStudentDetails() {

        TypedQuery<StudentDetails> query = entityManager.createQuery("SELECT d FROM StudentDetails d", StudentDetails.class);

        return query.getResultList();
    }

    public Student findStudentById(Integer id) {

        return entityManager.find(Student.class, id);
    }


    // FACULTY:
    public List<Faculty> findAllFaculties() {

        TypedQuery<Faculty> query = entityManager.createQuery("SELECT f FROM Faculty f", Faculty.class);

        return query.getResultList();
    }

    @Transactional
    public void addFaculty(Faculty faculty) {

        entityManager.persist(faculty);
    }

    @Transactional
    public void addFacultyWithStudents(Faculty faculty, List<Student> students) {

        faculty.setStudents(students);
        entityManager.persist(faculty);
    }


    // SUBJECT:
    public List<Subject> findAllSubjects() {

        TypedQuery<Subject> query = entityManager.createQuery("SELECT s FROM Subject s", Subject.class);

        return query.getResultList();
    }

    @Transactional
    public void addSubject(Subject subject) {

        entityManager.persist(subject);
    }

    @Transactional
    public void addSubjectWithStudents(Subject subject, List<Student> students) {

        subject.setStudents(students);
        entityManager.persist(subject);
    }
}
