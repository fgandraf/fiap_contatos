package com.felipegandra.contatos.repository;

import com.felipegandra.contatos.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    Optional<Contato> findByNome(String nome);

    @Query("SELECT c FROM Contato c WHERE c.email = :email")
    Optional<Contato> findByEmail(@Param("email") String email);

    List<Contato> findByDataNascimentoBetween(LocalDate dataInicio, LocalDate dataFinal);

    @Query("SELECT c FROM Contato c WHERE c.dataNascimento BETWEEN :dataInicial AND :dataFinal")
    List<Contato> listarAniversariantesDoPeriodo(@Param("dataInicial") LocalDate dataInicio, @Param("dataFinal") LocalDate dataFinal);
}
