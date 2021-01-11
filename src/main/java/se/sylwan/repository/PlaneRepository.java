package se.sylwan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.sylwan.model.Plane;

public interface PlaneRepository extends JpaRepository<Plane, String> {

}
