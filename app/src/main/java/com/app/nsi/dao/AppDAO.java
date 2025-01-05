package com.app.nsi.dao;

import com.app.nsi.entity.Faculty;
import com.app.nsi.entity.Student;
import com.app.nsi.entity.StudentDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAO {

    private final EntityManager entityManager;

    @Autowired
    public AppDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void addStudent(Student student) {
        entityManager.persist(student);
    }

    @Transactional
    public void addStudentWithDetails(Student student, StudentDetails studentDetails) {

        studentDetails.setStudent(student);
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


    public List<Faculty> findAllFaculties() {

        TypedQuery<Faculty> query = entityManager.createQuery("SELECT f FROM Faculty f", Faculty.class);

        return query.getResultList();
    }
}
