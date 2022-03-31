package com.webapp;

import com.webapp.Model.User;
import com.webapp.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(true)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repository;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("Bhuvana.a@gmail.com");
        user.setPassword("bhuvana12345");
        user.setFirstName("Bhuvana");
        user.setLastName("A");

        User savedUser = repository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<User> users = repository.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for(User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        Integer userId = 4;
        Optional<User> optionalUser = repository.findById(userId);
        User user = optionalUser.get();
        user.setPassword("Bhuvana47074");
        repository.save(user);

        User updatedUser = repository.findById(userId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("Bhuvana47074");
    }

    @Test
    public void testGet() {
        Integer userId =4;
        Optional<User> optionalUser = repository.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }
    @Test
    public void testDelete() {
        Integer userId =4;
        repository.deleteById(userId);
        Optional<User> optionalUser = repository.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
