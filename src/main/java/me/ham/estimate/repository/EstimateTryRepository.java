package me.ham.estimate.repository;


import me.ham.estimate.entity.EstimateTry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstimateTryRepository extends JpaRepository<EstimateTry, Integer> {
}
