package org.zorba_1015;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class SchoolMain {
    public static void main(String [] args) {
            Scanner scanner = new Scanner(System.in);

            // Insert Subjects
            String[] subjects = {"Physics", "Chemistry", "Mathematics", "Computer"};
            for (String subjectName : subjects) {
                Subject subject = new Subject();
                subject.setSubjectName(subjectName);
                saveEntity(subject);
            }

            // Insert Student
            System.out.println("Enter student name:");
            String studentName = scanner.nextLine();
            System.out.println("Enter student roll number:");
            String rollNumber = scanner.nextLine();
            System.out.println("Choose subject:");
            List<Subject> subjectList = getEntities(Subject.class);
            for (int i = 0; i < subjectList.size(); i++) {
                System.out.println((i + 1) + ". " + subjectList.get(i).getSubjectName());
            }
            int subjectChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            Student student = new Student();
            student.setStudentName(studentName);
            student.setRollNumber(rollNumber);
            student.setSubject(subjectList.get(subjectChoice - 1));
            saveEntity(student);

            // Insert Teacher
            System.out.println("Enter teacher name:");
            String teacherName = scanner.nextLine();
            System.out.println("Enter teacher qualification:");
            String teacherQualification = scanner.nextLine();
            System.out.println("Enter experience of teaching:");
            int experience = scanner.nextInt();
            System.out.println("Choose subject to teach:");
            for (int i = 0; i < subjectList.size(); i++) {
                System.out.println((i + 1) + ". " + subjectList.get(i).getSubjectName());
            }
            subjectChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            Teacher teacher = new Teacher();
            teacher.setTeacherName(teacherName);
            teacher.setTeacherQualification(teacherQualification);
            teacher.setExperienceOfTeaching(experience);
            teacher.setSubject(subjectList.get(subjectChoice - 1));
            saveEntity(teacher);

            // Select Records
            System.out.println("Students:");
            List<Student> students = getEntities(Student.class);
            for (Student stud : students) {
                System.out.println(stud.getStudentName() + " - " + stud.getSubject().getSubjectName());
            }

            System.out.println("Teachers:");
            List<Teacher> teachers = getEntities(Teacher.class);
            for (Teacher teach : teachers) {
                System.out.println(teach.getTeacherName() + " - " + teach.getSubject().getSubjectName());
            }

            // Update Records
            updateSubjectName();

            // Delete Records
            deleteStudentsBySubject("Physics");
            scanner.close();
        }

        private static void saveEntity(Object entity) {
            Session session = HibernateUtility.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            session.close();
        }

        private static <T> List<T> getEntities(Class<T> entityType) {
            Session session = HibernateUtility.getSessionFactory().openSession();
            List<T> entities = session.createQuery("from " + entityType.getSimpleName(), entityType).list();
            session.close();
            return entities;
        }

        private static void updateSubjectName() {
            Session session = HibernateUtility.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            List<Subject> subjects = session.createQuery("from Subject where subjectName = :oldName", Subject.class)
                    .setParameter("oldName", "Mathematics").list();
            for (Subject subject : subjects) {
                subject.setSubjectName("Astronomy");
                session.update(subject);
            }
            transaction.commit();
            session.close();
        }

        private static void deleteStudentsBySubject(String subjectName ){
            Session session = HibernateUtility.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            List<Student> students = session.createQuery("select s from Student s join s.subject subj where subj.subjectName = :subjectName", Student.class)
                    .setParameter("subjectName", subjectName).list();
            for (Student student : students) {
                session.delete(student);
            }
            transaction.commit();
            session.close();
        }
    }