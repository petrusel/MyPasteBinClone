package com.petrusel.paste.repository;

import com.petrusel.paste.model.Paste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasteRepository extends JpaRepository<Paste, Long> {
}
