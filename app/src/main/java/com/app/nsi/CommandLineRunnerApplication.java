package com.app.nsi;

import com.app.nsi.dao.AppDAO;
import com.app.nsi.entity.Faculty;
import com.app.nsi.entity.Student;
import com.app.nsi.entity.StudentDetails;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineRunnerApplication {

    private static Scanner input = new Scanner(System.in);

    private void addStudentWithDetails(AppDAO appDAO) {

        Faculty elfak = new Faculty("Elfak", "Aleksandra Medveda 4, Nis");

        Student veljko = new Student("Veljko", "Fajnisevic", elfak);

        StudentDetails veljkoDetails = new StudentDetails("veljkofajnisevic@elfak.rs", "Karadjordjeva 12, Bor", veljko);

        veljkoDetails.setStudent(veljko);

        veljko.setStudentDetails(veljkoDetails);
        veljko.setFaculty(elfak);

        elfak.setStudents(List.of(veljko));

        appDAO.addStudent(veljko);
    }

    private void findAllStudents(AppDAO appDAO) {

        for (Student s : appDAO.findAllStudents()) {
            System.out.println("\n======\n");
            System.out.println(s);
            System.out.println(s.getStudentDetails());
            System.out.println(s.getFaculty());
        }
    }

    private void findAllFaculties(AppDAO appDAO) {

        for (Faculty f : appDAO.findAllFaculties()) {
            System.out.println(f);
        }
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            //addStudentWithDetails(appDAO);
            //findAllStudents(appDAO);
            findAllFaculties(appDAO);
        };
    }
}
