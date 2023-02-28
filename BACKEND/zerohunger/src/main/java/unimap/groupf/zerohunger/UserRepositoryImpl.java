package unimap.groupf.zerohunger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.h2.mvstore.Page;
import org.ibankapp.base.persistence.domain.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserRepositoryImpl extends JpaRepository<User, Long> implements UserRepository {

    private final EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
public <S extends User> S save(S entity) {
    return entityManager.merge(entity);
}

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        List<S> savedEntities = new ArrayList<>();
        for (S entity : entities) {
            savedEntities.add(save(entity));
        }
        return savedEntities;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }
    
    @Override
    public boolean existById(Long id) {
        return findById(id).isPresent();
    }
    
    @Override
    public List<User> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        cq.select(cq.from(User.class));
        return entityManager.createQuery(cq).getResultList();
    }
    
    @Override
    public List<User> findAllById(Iterable<Long> ids) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root).where(root.get("id").in(ids));
        return entityManager.createQuery(cq).getResultList();
    }
    
    @Override
    public long count() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(User.class)));
        return entityManager.createQuery(cq).getSingleResult();
    }
    
    @Override
    public void deleteById(Long id) {
        findById(id).ifPresent(entityManager::remove);
    }
    
    @Override
    public void delete(User entity) {
        entityManager.remove(entity);
    }
    
    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        for (User entity : entities) {
            delete(entity);
        }
    }
    
    @Override
    public void deleteAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<User> cd = cb.createCriteriaDelete(User.class);
        cd.from(User.class);
        entityManager.createQuery(cd).executeUpdate();
    }
    
    @Override
    public Page<User> findAll(Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.orderBy(getSortOrders(pageable.getSort(), root, cb));
        TypedQuery<User> query = entityManager.createQuery(cq);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        return new PageImpl<>(query.getResultList(), pageable, count());
    }
    
    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<S> cq = cb.createQuery(example.getProbeType());
        cq.where(getExamplePredicate(cb, cq, example));
        return entityManager.createQuery(cq).setMaxResults(1).getResultList().stream().findFirst();
    }
    
    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<S> cq = cb.createQuery(example.getProbeType());
        cq.where(getExamplePredicate(cb, cq, example));
        return entityManager.createQuery(cq).getResultList();
    }
    
    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<S> cq = cb.createQuery(example.getProbeType());
    Root<S> root = cq.from(example.getProbeType());
    cq.where(getExamplePredicate(cb, cq, example));
    cq.orderBy(getSortOrders(sort, root, cb));
    return entityManager.createQuery(cq).getResultList();
    }
    
    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<S> cq = cb.createQuery(example.getProbeType());
    Root<S> root = cq.from(example.getProbeType());
    cq.where(getExamplePredicate(cb, cq, example));
    cq.orderBy(getSortOrders(pageable.getSort(), root, cb));
    TypedQuery<S> query = entityManager.createQuery(cq);
    query.setFirstResult((int) pageable.getOffset());
    query.setMaxResults(pageable.getPageSize());
    return new PageImpl<>(query.getResultList(), pageable, count());
    }
    
    @Override
    public <S extends User> long count(Example<S> example) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> cq = cb.createQuery(Long.class);
    Root<S> root = cq.from(example.getProbeType());
    cq.select(cb.count(root)).where(getExamplePredicate(cb, cq, example));
    return entityManager.createQuery(cq).getSingleResult();
    }
    
    @Override
    public <S extends User> boolean exists(Example<S> example) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<S> cq = cb.createQuery(example.getProbeType());
    cq.where(getExamplePredicate(cb, cq, example));
    return entityManager.createQuery(cq).setMaxResults(1).getResultList().stream().findFirst().isPresent();
    }
    
    @Override
    public Optional<User> findByUsername(String username) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<User> cq = cb.createQuery(User.class);
    Root<User> root = cq.from(User.class);
    cq.where(cb.equal(root.get("username"), username));
    return entityManager.createQuery(cq).setMaxResults(1).getResultList().stream().findFirst();
    }
    
    @Override
    public Optional<User> findByEmail(String email) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<User> cq = cb.createQuery(User.class);
    Root<User> root = cq.from(User.class);
    cq.where(cb.equal(root.get("email"), email));
    return entityManager.createQuery(cq).setMaxResults(1).getResultList().stream().findFirst();
    }
    
    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<User> cq = cb.createQuery(User.class);
    Root<User> root = cq.from(User.class);
    cq.where(cb.equal(root.get("phoneNumber"), phoneNumber));
    return entityManager.createQuery(cq).setMaxResults(1).getResultList().stream().findFirst();
    }
    
    @Override
    public void updateUsername(Long id, String username) {
    findById(id).ifPresent(user -> {
    user.setUsername(username);
    entityManager.merge(user);
    });
    }
    
    @Override
    public void updateEmail(Long id, String email) {
    findById(id).ifPresent(user -> {
    user.setEmail(email);
    entityManager.merge(user);
    });
    }
    
    @Override
    public void updatePhoneNumber(Long id, String phoneNumber) {
    findById(id).ifPresent(user -> {
    user.setPhoneNumber(phoneNumber);
    entityManager.merge(user);
    });
    }
    
    @Override
    public void updatePassword(Long id, String password) {
    findById(id).ifPresent(user -> {
    user.setPassword(password);
    entityManager.merge(user);
    });
    }
}
    
