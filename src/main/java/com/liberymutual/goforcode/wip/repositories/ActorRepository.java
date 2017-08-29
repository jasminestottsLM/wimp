package com.liberymutual.goforcode.wip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liberymutual.goforcode.wip.models.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long>{

}
