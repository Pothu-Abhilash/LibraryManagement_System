package com.accio.librarymanagementsystem.Repositories;

import com.accio.librarymanagementsystem.Models.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CardRepository extends JpaRepository<LibraryCard, Integer> {
}
