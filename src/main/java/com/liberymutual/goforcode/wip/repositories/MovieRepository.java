package com.liberymutual.goforcode.wip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liberymutual.goforcode.wip.models.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

}
