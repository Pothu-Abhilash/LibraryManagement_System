package com.accio.librarymanagementsystem.Repositories;

import com.accio.librarymanagementsystem.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,String> {
}
