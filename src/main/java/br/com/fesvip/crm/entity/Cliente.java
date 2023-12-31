package br.com.fesvip.crm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "tb_cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    private String numeroTelefone1;

    private String numeroTelefone2;

    private String plataforma;


    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Venda> venda;

    @NotNull
    private LocalDateTime data;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Chat chat;
}
