package com.project.literalura.repository;

import com.project.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Optional<Autor> findByName(String name);
}
