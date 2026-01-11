package com.mytesting.app.resource;

import com.mytesting.app.model.Producto;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Productos", description = "Gestión del catálogo de productos agrícolas")
public class ProductoResource {

    @GET
    @Operation(summary = "Listar productos", description = "Lista todos los productos registrados.")
    public List<Producto> list() {
        return Producto.listAll();
    }

    @POST
    @Transactional
    @Operation(summary = "Crear producto", description = "Registra un nuevo producto en el catálogo.")
    public Producto create(Producto producto) {
        producto.persist();
        return producto;
    }
}
