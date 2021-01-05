package se.sylwan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.sylwan.model.Travel;

public interface TravelRepositiory extends JpaRepository<Travel, Long> {
	

}
