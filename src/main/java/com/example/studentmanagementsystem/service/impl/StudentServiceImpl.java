package com.example.studentmanagementsystem.service.impl;

import com.example.studentmanagementsystem.entity.Student;
import com.example.studentmanagementsystem.exception.School;
import com.example.studentmanagementsystem.repository.StudentRepository;
import com.example.studentmanagementsystem.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        try {
            if (students.isEmpty()) {
                throw new School("603", "Sorry the student list is empty");
            }

        } catch (Exception e) {
            throw new School("604", "Could not fetch all student from the service layer"+e.getMessage());
        }
        return students;
    }

    @Override
    public Student saveStudent( Student student) {

            return studentRepository.save(student);

    }

    @Override
    public Student getStudentById(Long id) {
        try{
            return studentRepository.findById(id).get();
        }catch (IllegalArgumentException e){
            throw  new School("605","id provided is not a valid id");
        }catch (NoSuchElementException e) {

            throw new School("","id not existed");
        }

    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
     studentRepository.deleteById(id);
    }
}
