package com.mytesting.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
@Schema(name = "Organizacion", description = "Entidad que representa una organización social (Comedor, Olla Común, etc.)")
public class Organizacion extends BaseEntity {

    @Schema(required = true, description = "Nombre de la organización. Ej: Comedor Popular Los Andes")
    @NotBlank(message = "El nombre de la organización es obligatorio")
    public String nombre;

    @Schema(required = true, description = "Tipo de organización")
    @NotNull(message = "El tipo de organización es obligatorio")
    public TipoOrganizacion tipo;

    @Schema(required = true, description = "DNI del responsable (8 dígitos). Ej: 12345678")
    @NotBlank(message = "El DNI del responsable es obligatorio")
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener 8 dígitos")
    @Column(unique = true)
    public String responsableDni;

    @Schema(required = true, description = "Teléfono de contacto. Ej: 987654321")
    @NotBlank(message = "El teléfono es obligatorio")
    public String telefono;

    @Schema(required = true, description = "Cantidad de beneficiarios atendidos. Ej: 50")
    @Min(value = 1, message = "Debe tener al menos un beneficiario")
    public int beneficiarios;

    @Schema(required = true, description = "Días de atención. Ej: Lunes a Viernes")
    @NotBlank(message = "Los días de atención son obligatorios")
    public String diasAtencion;

    @Schema(description = "Tipos de organizaciones disponibles")
    public enum TipoOrganizacion {
        OLLA_COMUN,
        COMEDOR,
        PROGRAMA_SOCIAL
    }
}
