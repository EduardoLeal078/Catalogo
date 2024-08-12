package ifrs.resource;

import ifrs.service.ContService;
import ifrs.entity.Content;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import jakarta.ws.rs.core.Response;
import java.util.Optional;

@Path("/conteudo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContResource {

    @Inject
    ContService contService;

    @GET
    @Path("/listar")
    public List<Content> listAllCont() {
        return contService.listAllCont();
    }

    @POST
    @Path("/adicionar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCont(@FormParam("title") String title, @FormParam("description") String description,
            @FormParam("contType") String contType, @FormParam("contData") String contData) {
        Content content = new Content();
        content.setTitle(title);
        content.setDescription(description);
        content.setContType(contType);
        content.setContData(contData);
        contService.addCont(content);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    public Response getCont(@PathParam("id") Long id) {
        Optional<Content> content = contService.getCont(id);
        if (content.isPresent()) {
            return Response.ok(content.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/atualizar/{id}")
    public Response updateCont(@PathParam("id") Long id, Content updatedCont) {
        Optional<Content> updatedContent = contService.updateCont(id, updatedCont);
        if (updatedContent.isPresent()) {
            return Response.ok(updatedContent.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("deletar/{id}")
    public Response removeCont(@PathParam("id") Long id) {
        boolean removed = contService.removeCont(id);
        if (removed) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/deletar/todos")
    public Response removeAllCont() {
        contService.removeAllCont();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}