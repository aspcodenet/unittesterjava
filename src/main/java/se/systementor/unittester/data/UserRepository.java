package se.systementor.unittester.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    long countByRegisteredAtBetween(java.sql.Timestamp start, java.sql.Timestamp end);
}
