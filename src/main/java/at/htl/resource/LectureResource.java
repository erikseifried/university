package at.htl.resource;

import at.htl.boundary.LectureRepository;
import at.htl.model.Lecture;
import at.htl.model.LectureType;
import io.quarkus.logging.Log;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hibernate.boot.jaxb.internal.stax.BufferedXMLEventReader;

import java.awt.*;
import java.util.List;

@Path("/api/lecture")
public class LectureResource {

    @Inject
    LectureRepository lectureRepository;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Lecture getLectureDetails(@PathParam("id") Long id){
        return lectureRepository.findById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLectures(){
        try{
            List<Lecture> lectures = lectureRepository.listAll();
            return Response.ok(lectures).build();

        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createLecture(Lecture lecture){
        try{
            lectureRepository.persist(lecture);
            return Response.status(Response.Status.CREATED).build();

        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public Response deleteLecture(@PathParam("id")Long id){
        try{
            lectureRepository.deleteById(id);
            return Response.ok().build();

        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
