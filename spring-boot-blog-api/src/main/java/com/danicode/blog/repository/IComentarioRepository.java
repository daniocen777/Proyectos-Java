package com.danicode.blog.repository;

import com.danicode.blog.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IComentarioRepository extends JpaRepository<Comentario, Long> {
    public List<Comentario> findByPublicacionId(long publicacionId);
}
