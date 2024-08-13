package at.htl.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LectureType lectureType;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    List<Student> students;

    @OneToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "id", nullable = true)
    Professor professor;

    public Lecture(){}

    public Lecture(Long id, LectureType lectureType, List<Student> students, Professor professor) {
        this.id = id;
        this.lectureType = lectureType;
        this.students = students;
        this.professor = professor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LectureType getLectureType() {
        return lectureType;
    }

    public void setLectureType(LectureType lectureType) {
        this.lectureType = lectureType;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
