package com.mytesting.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
@Schema(name = "Producto", description = "Producto agrícola específico")
public class Producto extends BaseEntity {

    @Schema(required = true, description = "Nombre del producto. Ej: Papa Huayro")
    @NotBlank(message = "El nombre es obligatorio")
    public String nombre;

    @Schema(required = true, description = "Categoría a la que pertenece")
    @NotNull(message = "La categoría es obligatoria")
    @ManyToOne
    public Categoria categoria;
}
