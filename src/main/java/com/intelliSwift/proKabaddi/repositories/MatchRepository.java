package com.intelliSwift.proKabaddi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intelliSwift.proKabaddi.entity.Match;


@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{

}
