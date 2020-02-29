package com.github.MaxLebedev2000.db.users;

import java.sql.SQLException;

public interface UsersCustomRepository {
    boolean isUserExists(String login) throws SQLException;
    boolean isPasswordCorrect(String login, String password) throws SQLException;
}
