package ifrs.service;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collections;
import java.util.List;
import ifrs.service.entity.User;
import ifrs.service.repository.UserRepository;
import jakarta.transaction.Transactional;


@Path("/autenticar")
public class AuthResource {
    @Inject
    AuthService authService;
    @Inject
    UserRepository userRepository; 

    @POST
    @Path("/registrar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(@FormParam("username") String name, @FormParam("password") String password, @FormParam("acessLv") String accessLv) {
        try {
            System.out.println("Recebendo solicitação de registro para: " + name);
            authService.register(name, password, accessLv);
            System.out.println("Usuário registrado com sucesso: " + name);
            return Response.ok().entity(Collections.singletonMap("success", true)).build();
        } catch (Exception e) {
            System.err.println("Erro ao registrar usuário: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(Collections.singletonMap("message", e.getMessage())).build();
        }
    }

    @POST
    @Path("/logar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("name") String name, @FormParam("password") String password) {
        try {
            System.out.println("Recebendo solicitação de login para: " + name);
            boolean isAuthenticated = authService.login(name, password);
            if (isAuthenticated) {
                System.out.println("Usuário autenticado com sucesso: " + name);
                return Response.ok().entity(Collections.singletonMap("success", true)).build();
            } else {
                System.out.println("Falha na autenticação do usuário: " + name);
                return Response.status(Response.Status.UNAUTHORIZED).entity(Collections.singletonMap("success", false)).build();
            }
        } catch (Exception e) {
            System.err.println("Erro ao autenticar usuário: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(Collections.singletonMap("message", e.getMessage())).build();
        }
    }
    @GET
    @Path("/listaTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        List<User> users = authService.listAll();
        return Response.ok(users).build();
    }

    //Apaga tudo para realizar o teste
    @Transactional
    @DELETE
    @Path("/deleteAll")
    public Response cleanUpUsers() {
        userRepository.deleteAll();
        return Response.ok().build();
    }

    public static class Credentials{
        public String username;
        public String password;
    }


}
