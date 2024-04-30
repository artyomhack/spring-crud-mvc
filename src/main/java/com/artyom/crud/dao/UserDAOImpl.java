package com.artyom.crud.dao;

import com.artyom.crud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public void save(User user) {
        manager.persist(user);
    }

    @Override
    @Transactional
    public void updateById(Long id, User user) {
        User userFromBD = manager.find(User.class, id);
        if (manager.contains(userFromBD)) {
            userFromBD.setName(user.getName());
            userFromBD.setLastname(user.getLastname());
            userFromBD.setAge(user.getAge());
        } else {
            throw new NoSuchElementException("User is not in persistenceContext.");
        }
    }

    @Override
    @Transactional
    public Optional<List<User>> fetchAll() {
        List<User> list = manager.createQuery("from User", User.class).getResultList();
        return list.isEmpty() ? Optional.empty() : Optional.of(list);
    }

    @Override
    @Transactional
    public Optional<User> fetchById(Long id) {
        TypedQuery<User>  query = manager.createQuery("from User u WHERE u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getResultList().isEmpty() ? Optional.empty() : Optional.of(query.getResultList().get(0));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (fetchById(id).isPresent()) {
            manager.remove(fetchById(id).get());
        }
    }
}
