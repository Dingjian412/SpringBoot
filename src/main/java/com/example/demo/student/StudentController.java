package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //http://localhost:8080/api/v1/student/getallstudents
    @RequestMapping(value = "/getallstudents", method = RequestMethod.GET)
    public List<Student> getallstudents()
    {
        return studentService.getStudents();
    }

    //http://localhost:8080/api/v1/student/getstudent/1
    @RequestMapping(value = "/getstudent/{studentId}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable("studentId") Long id)
    {
        return studentService.getStudentById(id);
    }

//    //http://localhost:8080/api/v1/student/getstudent?studentId=1
//    @RequestMapping(value = "/getstudent", method = RequestMethod.GET)
//    public Student getStudent(@RequestParam("studentId") Long id)
//    {
//        return studentService.getStudentById(id);
//    }

    //http://localhost:8080/api/v1/student/registerNewStudent
    @PostMapping(value = "/registerNewStudent")
    public void registerNewStudent(@RequestBody Student student)
    {
        studentService.addNewStudent(student);
    }

    @PostMapping(value = "/updateStudent")
    public void updateStudent(@RequestBody Student student)
    {
        studentService.updateStudent(student);
    }

//    @PostMapping(value = "/deleteStudent2")
//    public void deleteStudent2(@RequestParam Long id)
//    {
//        studentService.deleteStudent(id);
//    }


    //http://localhost:8080/api/v1/student/deleteStudent/2
    @DeleteMapping(path = "/deleteStudent/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id)
    {
        studentService.deleteStudent(id);
    }
}
