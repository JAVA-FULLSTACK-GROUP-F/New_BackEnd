package unimap.groupf.zerohunger;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.Predicate;
import org.apache.logging.log4j.core.config.status.StatusConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@ComponentScan
public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
    @Transactional
    void updateUsername(Long id, String newUsername);
    @Transactional
    void updatePhoneNumber(Long id, String newPhoneNumber);
    @Transactional
    void updatePassword(Long id, String newPassword);
    @Transactional
    List<User> findAll();
    @Transactional
    <S extends User> S save(S entity);
    Optional<User> findByUsername(String username);
    @Transactional
    <S extends User> List<S> saveAll(Iterable<S> entities);
    
    @Transactional
    Page<User> findAll(Pageable pageable);
    @Transactional
    long count(Predicate predicate);
    @Transactional
    List<User> findAll(Predicate predicate);
    @Transactional
    List<User> findAllByOrderByUsername();
    @Transactional
    Optional<User> findById(Long id);
    @Transactional
    public boolean existById(Long id);
    @Transactional
    public void delete(User entity);
    @Transactional
    public long count() ;
    @Transactional
    public void deleteById(Long id);
    @Transactional   
    public List<User> findAllById(Iterable<Long> ids);
    @Transactional
    public boolean existsById(Long id);
    @Transactional
    public void updateStatus(User user, StatusConfiguration newStatus);
    @Transactional
    List<User> findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(String username, String email);
    @Transactional
    long countByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(String username, String email);
    @Transactional
    public void deleteAll(Iterable<? extends User> entities);
    @Transactional
    public void deleteAll();
    @Transactional
    public void updateEmail(Long id, String email);
}