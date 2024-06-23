package com.accio.librarymanagementsystem.Controller;

import com.accio.librarymanagementsystem.Models.Student;
import com.accio.librarymanagementsystem.Request.updateRequest;
import com.accio.librarymanagementsystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @PostMapping("/addstudent")
    public String addstudnet(@RequestBody Student student){

        String result = studentService.addStudent(student);

        return result;
    }

    @GetMapping("/getStudent")
    public ResponseEntity getStudent(@RequestParam Integer studentId){

        try {
            Student student = studentService.getStudent(studentId);
             return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAllStudents")
    public List<Student> findAllStudents(){
        List<Student>studentList = studentService.findAllStudents();
        return studentList;
    }

    @PutMapping("/updateStudent")
    public ResponseEntity updateStudent(updateRequest updateRequest){

        try {
            String response = studentService.updateStudent(updateRequest);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
