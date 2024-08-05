package com.example.demo.repo;

import com.example.demo.model.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommuneRepo extends JpaRepository<Commune,Integer> {
}
