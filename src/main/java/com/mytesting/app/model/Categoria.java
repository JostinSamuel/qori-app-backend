package com.mytesting.app.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
@Schema(name = "Categoria", description = "Categoría de productos agrícolas (ej: Tubérculos, Frutas)")
public class Categoria extends BaseEntity {

    @Schema(required = true, description = "Nombre de la categoría. Ej: Tubérculos")
    @NotBlank(message = "El nombre es obligatorio")
    public String nombre;
}
