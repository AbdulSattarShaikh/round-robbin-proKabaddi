package com.intelliSwift.proKabaddi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intelliSwift.proKabaddi.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}
