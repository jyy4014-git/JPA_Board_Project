package com.boardproject.projectboard.repository;


import com.boardproject.projectboard.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}
