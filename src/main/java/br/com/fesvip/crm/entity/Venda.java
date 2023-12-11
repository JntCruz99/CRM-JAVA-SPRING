package br.com.fesvip.crm.entity;

import br.com.fesvip.crm.entity.enums.Cursos;
import br.com.fesvip.crm.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_venda")
public class Venda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    @NotNull
    private Cliente cliente;

    @NotNull
    private Status status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    @NotNull
    private Usuario usuario;

    @NotNull
    private LocalDateTime data;

    @NotNull
    private Cursos curso;

    private double valor;

    private String obs;
}
