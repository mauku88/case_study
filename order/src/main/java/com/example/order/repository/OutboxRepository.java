package com.example.order.repository;


import com.example.order.entity.Outbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxRepository extends JpaRepository<Outbox, String> {
}
