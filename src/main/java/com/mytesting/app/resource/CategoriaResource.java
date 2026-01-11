package com.mytesting.app.resource;

import com.mytesting.app.model.Categoria;
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

@Path("/categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Categorías", description = "Gestión de categorías de productos")
public class CategoriaResource {

    @GET
    @Operation(summary = "Listar categorías", description = "Lista todas las categorías de productos disponibles.")
    public List<Categoria> list() {
        return Categoria.listAll();
    }

    @POST
    @Transactional
    @Operation(summary = "Crear categoría", description = "Registra una nueva categoría de productos.")
    public Categoria create(Categoria categoria) {
        categoria.persist();
        return categoria;
    }
}
