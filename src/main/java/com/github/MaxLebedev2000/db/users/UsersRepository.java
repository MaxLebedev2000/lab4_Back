package com.github.MaxLebedev2000.db.users;

import org.springframework.data.repository.Repository;

public interface UsersRepository extends Repository<User, Long>, UsersCustomRepository{
    void save(User user);
    User getByLogin(String login);
    User getByToken(String token);
}
