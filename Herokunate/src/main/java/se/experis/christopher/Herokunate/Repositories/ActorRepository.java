package se.experis.christopher.Herokunate.Repositories;

import se.experis.christopher.Herokunate.Models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
    
}
