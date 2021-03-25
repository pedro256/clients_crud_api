package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

}
