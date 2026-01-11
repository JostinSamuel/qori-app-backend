package com.mytesting.app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
@Schema(name = "Agricultor", description = "Entidad que representa a un agricultor proveedor")
public class Agricultor extends BaseEntity {

    @Schema(required = true, description = "DNI del agricultor (8 dígitos). Ej: 10203040")
    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener 8 dígitos")
    @Column(unique = true)
    public String dni;

    @Schema(required = true, description = "Nombres del agricultor. Ej: Juan")
    @NotBlank(message = "Los nombres son obligatorios")
    public String nombres;

    @Schema(required = true, description = "Apellidos del agricultor. Ej: Perez")
    @NotBlank(message = "Los apellidos son obligatorios")
    public String apellidos;

    @Schema(required = true, description = "Teléfono de contacto. Ej: 999888777")
    @NotBlank(message = "El teléfono es obligatorio")
    public String telefono;

    @Schema(required = true, description = "Departamento de ubicación. Ej: Cusco")
    @NotBlank(message = "El departamento es obligatorio")
    public String departamento;

    @Schema(required = true, description = "Provincia de ubicación. Ej: Cusco")
    @NotBlank(message = "La provincia es obligatoria")
    public String provincia;

    @Schema(required = true, description = "Distrito de ubicación. Ej: San Jerónimo")
    @NotBlank(message = "El distrito es obligatorio")
    public String distrito;

    @Schema(description = "Lista de productos que cultiva el agricultor")
    @OneToMany(mappedBy = "agricultor", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Produccion> producciones = new ArrayList<>();
}
