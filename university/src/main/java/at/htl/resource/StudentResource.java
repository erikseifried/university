package at.htl.resource;

import at.htl.boundary.LectureRepository;
import at.htl.boundary.StudentRepository;
import at.htl.model.Lecture;
import at.htl.model.Student;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Path("/api/student")
public class StudentResource {
    @Inject
    StudentRepository studentRepository;

    @Inject
    LectureRepository lectureRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudents(){
        try{
            List<Student> students = studentRepository.listAll();
            return Response.ok(students).build();

        } catch(Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createStudent(Student student){
        try{
            studentRepository.persist(student);
            return Response.status(Response.Status.CREATED).entity(student).build();

        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id") Long id){
        try{
            studentRepository.deleteById(id);
            return Response.ok().build();
        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Transactional
    @PUT
    @Path("/{studentId}/assign/{lectureId}")
    public Response assignStudentToLecture(@PathParam("studentId") Long studentId, @PathParam("lectureId") Long lectureId){
        try{
            Student student = studentRepository.findById(studentId);
            Lecture lecture = lectureRepository.findById(lectureId);

            student.setLecture(lecture);

            List<Student> students = lecture.getStudents();
            students.add(student);

            studentRepository.persist(student);
            lectureRepository.persist(lecture);
            return Response.ok().build();

        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
