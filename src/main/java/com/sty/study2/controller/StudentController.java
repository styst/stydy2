package com.sty.study2.controller;

import com.sty.study2.model.Student;
import com.sty.study2.service.StudentService;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        PageRequest page = PageRequest.of(0, 4);
        Page<Student> students1 = studentService.test3(page);
        List<Student> students = students1.getContent();
        String result = "";
        for(Student s: students) {
            result = result+s.getName()+"  "+s.getAge()+"  "+s.getId()+"  "+s.getSex()+"  ";
        }
        return result;
    }
    @GetMapping("http")
    public String getHttpInfo() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://localhost:8080/student/list");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpget);


        } catch (IOException io) {

        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {

            }
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
            }

        }
        return response.getEntity().toString();
    }
}

