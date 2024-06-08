package com.accio.librarymanagementsystem.Service;

import com.accio.librarymanagementsystem.Models.Student;
import com.accio.librarymanagementsystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String addStudent(Student student) {

        studentRepository.save(student);
        return "Student has been saved to the DB";
    }

    public Student getStudent(Integer studentId) throws Exception
    {
        Optional<Student> OptinalStudnet = studentRepository.findById(studentId);

        if(OptinalStudnet.isEmpty())
        {
            throw new Exception("Entered Studnet Id does not exist");
        }
        //getting the student Id
        Student Id = OptinalStudnet.get();
        return Id;
    }
}
