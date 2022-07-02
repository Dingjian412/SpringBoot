package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository=studentRepository;
    }

    public List<Student> getStudents()
    {
        return studentRepository.findAll();
    }


    public void addNewStudent(Student student)
    {
        Optional<Student> studentByEmail=studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("email taken.");
        }else{
            studentRepository.save(student);
        }
    }

    @Transactional
    public void updateStudent(Student student)
    {
        Optional<Student> studentById=studentRepository.findById(student.getId());
        if(studentById.isPresent()){
             if(student.getName()!=null && student.getName().length()>0 && !Objects.equals(student.getName(),studentById.get().getName()))
             {
                 studentById.get().setName(student.getName());
             }else{
                 throw new IllegalStateException("data wrong -- name.");
             }

            if(student.getEmail()!=null && student.getEmail().length()>0 && !Objects.equals(student.getEmail(),studentById.get().getEmail()))
            {
                studentById.get().setEmail(student.getEmail());
            }else{
                throw new IllegalStateException("data wrong -- email.");
            }

            if(student.getDob()!=null && !Objects.equals(student.getDob(),studentById.get().getDob()))
            {
                studentById.get().setDob(student.getDob());
            }else{
                throw new IllegalStateException("data wrong -- dob.");
            }
        }else{
            throw new IllegalStateException("cannot find this student.");
        }
    }

    public void deleteStudent(Long studentId)
    {
        if(studentRepository.existsById(studentId)){
            studentRepository.deleteById(studentId);
        }else{
            throw new IllegalStateException("cannot find this student.");
        }
    }

    public Student getStudentById(Long studentId) {
        Optional<Student> student=studentRepository.findById(studentId);
        if(student.isPresent()){
            return student.get();
        }else{
            throw new IllegalStateException("cannot find this student.");
        }
    }
}
