package br.com.fesvip.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_mensagem")
@Data
public class Mensagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String anexos;

    private String texto;

    private LocalDateTime data;

    @ManyToOne
    @JsonIgnore
    private Chat chat;

    @ManyToOne
    private Usuario usuario;
}
