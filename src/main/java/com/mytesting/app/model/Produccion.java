package com.mytesting.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
@Schema(name = "Produccion", description = "Relación de productos cultivados por un agricultor")
public class Produccion extends BaseEntity {

    @Schema(hidden = true, description = "Referencia al agricultor (se asigna automáticamente)")
    @NotNull(message = "El agricultor es obligatorio")
    @ManyToOne(optional = false)
    public Agricultor agricultor;

    @Schema(required = true, description = "Producto cultivado")
    @NotNull(message = "El producto es obligatorio")
    @ManyToOne(optional = false)
    public Producto producto;

    @Schema(required = true, description = "Cantidad producida semanalmente (kg/unidades). Ej: 100")
    @Min(value = 1, message = "La cantidad semanal debe ser mayor a 0")
    public int cantidadSemanal;
}
