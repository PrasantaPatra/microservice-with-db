package com.postgresdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.certpath.OCSPResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/getAllStudent")
    public List<Student> getallStudent() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(e -> students.add(e));
        return students;
    }

    @GetMapping("/getById/{id}")
    public Student getStudent(@PathVariable String id) {
        return studentRepository.findById(UUID.fromString(id)).get();
    }

    @PostMapping("/addStudent")
    public String saveStudent(@RequestBody String data) throws JsonProcessingException {
        studentRepository.save(new Student(new ObjectMapper().readTree(data)));
        return "Successfully Saved";
    }

    @PutMapping("/updateStudent/{id}")
    public String updateStudent(@RequestBody String data, @PathVariable String id) throws JsonProcessingException {
        Student student = studentRepository.findById(UUID.fromString(id)).get();
        student.setDetails(new ObjectMapper().readTree(data));
        studentRepository.save(student);
        return "Successfully Update";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        studentRepository.deleteById(UUID.fromString(id));
        return "Successfully Deleted";
    }
}
