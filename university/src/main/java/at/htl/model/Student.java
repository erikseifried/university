package at.htl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.enterprise.inject.build.compatible.spi.Validation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "first name may not be blank")
    private String firstName;

    @NotBlank(message = "last name may not be blank")
    private String lastName;

    private Integer age;

    private Date dateOfBirth;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "lecture_id", referencedColumnName = "id")
    Lecture lecture;

    public Student(){}

    public Student(Long id, String firstName, String lastName, Integer age, Date dateOfBirth, Lecture lecture) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.lecture = lecture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "first name may not be blank") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "first name may not be blank") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "last name may not be blank") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "last name may not be blank") String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }
}
