package com.accio.librarymanagementsystem.Repositories;

import com.accio.librarymanagementsystem.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
