package com.app.nsi;

import com.app.nsi.dao.StudentDAO;
import com.app.nsi.entity.Student;
import com.app.nsi.entity.StudentDetails;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CommandLineRunnerApplication {

    private static Scanner input = new Scanner(System.in);

    private void addStudentWithDetails(StudentDAO studentDAO) {

        Student student = new Student();

        StudentDetails details = new StudentDetails();

        System.out.println("Unesite ime studenta: ");
        student.setFirstName(input.next());

        System.out.println("Unesite prezime studenta: ");
        student.setLastName(input.next());

        System.out.println("Unesite adresu studenta: ");
        details.setAddress(input.next());

        System.out.println("Unesite email studenta: ");
        details.setEmail(input.next());

        details.setStudent(student);
        student.setStudentDetails(details);

        studentDAO.addStudent(student);
    }

    private void findAllStudents(StudentDAO studentDAO) {

        for(Student s : studentDAO.findAllStudents()){
            System.out.println("\n======\n");
            System.out.println(s);
            System.out.println(s.getStudentDetails());
        }
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
            //addStudentWithDetails(studentDAO);
            findAllStudents(studentDAO);
        };
    }
}
