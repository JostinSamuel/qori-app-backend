package com.mytesting.app.resource;

import com.mytesting.app.model.Agricultor;
import com.mytesting.app.model.Produccion;
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

@Path("/agricultores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Agricultores", description = "Gestión de agricultores y su producción")
public class AgricultorResource {

    @GET
    @Operation(summary = "Listar agricultores", description = "Devuelve una lista de todos los agricultores con su producción.")
    public List<Agricultor> list() {
        return Agricultor.listAll();
    }

    @POST
    @Transactional
    @Operation(summary = "Registrar agricultor", description = "Registra un nuevo agricultor junto con sus productos (opcional).")
    @APIResponse(responseCode = "201", description = "Agricultor registrado exitosamente")
    @APIResponse(responseCode = "400", description = "Datos inválidos (ej: DNI incorrecto)")
    public Response create(Agricultor agricultor) {
        if (agricultor.id != null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        // Link production to farmer to ensure FK consistency
        if (agricultor.producciones != null) {
            for (Produccion produccion : agricultor.producciones) {
                produccion.agricultor = agricultor;
            }
        }

        agricultor.persist();
        return Response.created(URI.create("/agricultores/" + agricultor.id)).entity(agricultor).build();
    }
}
