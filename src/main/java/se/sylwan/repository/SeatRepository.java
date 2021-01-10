package se.sylwan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.sylwan.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

}
