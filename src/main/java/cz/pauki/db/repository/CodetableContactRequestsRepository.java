package cz.pauki.db.repository;

import cz.pauki.db.entity.CodetableContactRequestsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Spring repository interface for {@link CodetableContactRequestsEntity}
 */
@Repository
public interface CodetableContactRequestsRepository extends JpaRepository<CodetableContactRequestsEntity, BigInteger> {

    List<CodetableContactRequestsEntity> findAllByOrderByPriorityAsc();
}
