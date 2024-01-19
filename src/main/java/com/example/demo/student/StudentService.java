package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentrepository;
@Autowired
    public StudentService(StudentRepository studentrepository) {
        this.studentrepository = studentrepository;
    }

    public List<Student> getStudent(){
    return studentrepository.findAll();
//        return List.of(new Student(
//                1L,
//                "Mariam",
//                "felix@gmail.com",
//                LocalDate.of(2000, Month.APRIL,5),
//                21
//        ));
    }

    public void addNewStudent(Student student) {
    Optional<Student> studentOptional = studentrepository.findStudentByEmail(student.getEmail());

    if(studentOptional.isPresent()){
        throw new IllegalStateException("email taken");
    }
    studentrepository.save(student);
    System.out.println(student);
    }

    public void deleteStudent(Long studentid) {
  boolean exists =  studentrepository.existsById(studentid);
    if(!exists){
        throw new IllegalStateException("student with id " + studentid + " does not exist");
    }
    studentrepository.deleteById(studentid);
    }
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student upstudent = studentrepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " doesnst exist"));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(upstudent.getName(), name)) {
            upstudent.setName(name);
        }
        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(upstudent.getEmail(), email)) {
            Optional<Student> studentOptional = studentrepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            upstudent.setEmail(email);
        }

    }

}
