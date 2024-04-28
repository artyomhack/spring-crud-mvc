package com.artyom.crud.dao;

import com.artyom.crud.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private final EntityManager manager;

    public UserDAOImpl(EntityManager manager) {
        this.manager = manager;
        createTableIfNotExist();
    }

    @Override
    public void save(User user) {
        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();
    }

    @Override
    public Optional<User> updateById(Long id, User user) {
        return Optional.empty();
    }

    @Override
    public Optional<List<User>> fetchAll() {
        manager.getTransaction().begin();
        List<User> users = manager.createQuery("from User", User.class).getResultList();
        manager.getTransaction().commit();
        return Optional.of(users);
    }

    @Override
    public Optional<User> fetchById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    private void createTableIfNotExist() {
        manager.getTransaction().begin();
        manager.createNativeQuery("CREATE SCHEMA IF NOT EXISTS public").executeUpdate();
        manager.getTransaction().commit();
    }
}
