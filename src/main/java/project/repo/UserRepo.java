package project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {


}
