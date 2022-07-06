package com.danicode.blog.repository;

import com.danicode.blog.entity.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPublicacionRepository extends JpaRepository<Publicacion, Long> {
}
