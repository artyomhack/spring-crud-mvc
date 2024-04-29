package com.artyom.crud.dao;

import com.artyom.crud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
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
            throw new IllegalArgumentException("User is not in persistenceContext.");
        }
    }

    @Override
    @Transactional
    public Optional<List<User>> fetchAll() {
        return Optional.of(manager.createQuery("from User", User.class).getResultList());
    }

    @Override
    public Optional<User> fetchById(Long id) {
        return Optional.ofNullable(manager.find(User.class, id));
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
