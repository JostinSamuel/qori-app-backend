package com.mytesting.app.resource;

import com.mytesting.app.model.Organizacion;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/organizaciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Organizaciones", description = "Gestión de organizaciones sociales (Comedores, Ollas Comunes)")
public class OrganizacionResource {

    @GET
    @Operation(summary = "Listar organizaciones", description = "Devuelve una lista de todas las organizaciones registradas.")
    public List<Organizacion> list() {
        return Organizacion.listAll();
    }

    @POST
    @Transactional
    @Operation(summary = "Crear organización", description = "Registra una nueva organización.")
    @APIResponse(responseCode = "201", description = "Organización creada exitosamente")
    @APIResponse(responseCode = "400", description = "Datos inválidos o organización ya existente")
    public Response create(Organizacion organizacion) {
        if (organizacion.id != null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        organizacion.persist();
        return Response.created(URI.create("/organizaciones/" + organizacion.id)).entity(organizacion).build();
    }
}
