package cz.pauki.db.repository;

import cz.pauki.db.entity.FormContactUsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Spring repository interface for {@link FormContactUsEntity}
 */
@Repository
public interface FormContactUsRepository extends JpaRepository<FormContactUsEntity, BigInteger> {
    List<FormContactUsEntity> findAllByOrderByCreatedAtDesc();
}
