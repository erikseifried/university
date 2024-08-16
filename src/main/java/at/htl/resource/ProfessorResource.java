package at.htl.resource;

import at.htl.boundary.LectureRepository;
import at.htl.boundary.ProfessorRepository;
import at.htl.model.Lecture;
import at.htl.model.Professor;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.util.List;

@Path("/api/professor")
public class ProfessorResource {

    @Inject
    ProfessorRepository professorRepository;

    @Inject
    LectureRepository lectureRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProfessors(){
        try{
            List<Professor> professors = professorRepository.listAll();
            return Response.ok(professors).build();

        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProfessor(Professor professor){
        try{
            professorRepository.persist(professor);
            return Response.status(Response.Status.CREATED).build();

        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public Response deleteProfessor(@PathParam("id") Long id){
        try{
            professorRepository.deleteById(id);
            return Response.ok().build();

        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Transactional
    @PUT
    @Path("/{professorId}/assign/{lectureId}")
    public Response assignProfessorToLecture(@PathParam("professorId") Long professorId, @PathParam("lectureId") Long lectureId){
        try{
            Professor professor = professorRepository.findById(professorId);
            Lecture lecture = lectureRepository.findById(lectureId);

            professor.setLecture(lecture);
            lecture.setProfessor(professor);

            professorRepository.persist(professor);
            lectureRepository.persist(lecture);
            return Response.ok().build();

        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
