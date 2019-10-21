package com.sty.study2.controller;

import com.sty.study2.model.Student;
import com.sty.study2.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    private StudentService studentService;
    @GetMapping("index")
    public String index() {
        return "success";
    }
    @GetMapping("list")
    public String getStudentList() {
        List<Student> students = studentService.list();
        String result = "";
        for(Student s: students) {
            result = result+s.getName()+"  "+s.getAge()+"  "+s.getId()+"  "+s.getSex()+"  ";
        }
        return result;

    }
    @GetMapping("get")
    public String getStudentOne() {
        return studentService.test2();
    }
}
