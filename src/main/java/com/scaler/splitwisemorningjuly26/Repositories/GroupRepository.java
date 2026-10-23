package com.scaler.splitwisemorningjuly26.Repositories;

import com.scaler.splitwisemorningjuly26.Models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
}
