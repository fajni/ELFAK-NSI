package com.app.nsi;

import com.app.nsi.dao.AppDAO;
import com.app.nsi.entity.Faculty;
import com.app.nsi.entity.Student;
import com.app.nsi.entity.StudentDetails;
import com.app.nsi.entity.Subject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineRunnerApplication {

    private static Scanner input = new Scanner(System.in);

    private void add(AppDAO appDAO) {

        Faculty elfak = new Faculty("Elfak", "Aleksandra Medveda 4, Nis");
        Faculty fon = new Faculty("FON", "Jove Ilica 154, Beograd");

        Student veljko1 = new Student("Veljko", "Fajnisevic", elfak);
        Student veljko2 = new Student("Veljko", "Veljkovic", elfak);
        Student nikola = new Student("Nikola", "Nikolic", fon);

        Subject nsi = new Subject("Napredno softversko inzenjerstvo", List.of(veljko1, veljko2));
        Subject ps = new Subject("Projektovanje softvera", List.of(nikola));

        StudentDetails veljkoDetails = new StudentDetails("veljkofajnisevic@elfak.rs", "Karadjordjeva 12, Bor", veljko1);

        elfak.setStudents(List.of(veljko1, veljko2));
        fon.setStudents(List.of(nikola));

        veljko1.setSubjects(List.of(nsi));
        veljko2.setSubjects(List.of(nsi));
        nikola.setSubjects(List.of(ps));

        appDAO.addFaculty(elfak);
        appDAO.addFaculty(fon);

        appDAO.addSubject(nsi);
        appDAO.addSubject(ps);

        appDAO.addStudentWithDetails(veljko1, veljkoDetails);
        appDAO.addStudent(veljko2);
        appDAO.addStudent(nikola);
    }

    private void findAllStudents(AppDAO appDAO) {

        System.out.println("\nALL STUDENTS: ");

        for (Student s : appDAO.findAllStudents()) {
            System.out.println("Student: " + s);
            System.out.println("Student details: " + s.getStudentDetails());
            System.out.println("Student faculty: " + s.getFaculty());
        }
    }


    private void findAllFaculties(AppDAO appDAO) {

        System.out.println("\nAll faculties: ");

        for (Faculty f : appDAO.findAllFaculties()) {
            System.out.println(f);
        }
    }

    private void findAllSubjects(AppDAO appDAO) {

        System.out.println("\nAll subjects: ");

        for (Subject s : appDAO.findAllSubjects())
            System.out.println(s);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {

        return runner -> {

            add(appDAO);

            findAllStudents(appDAO);
            findAllFaculties(appDAO);
            findAllSubjects(appDAO);
        };
    }

}
