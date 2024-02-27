package com.boardproject.projectboard.service;

import com.boardproject.projectboard.domain.UserAccount;
import com.boardproject.projectboard.dto.UserAccountDto;
import com.boardproject.projectboard.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public Optional<UserAccountDto> searchUser(String username) {
        return userAccountRepository.findById(username)
                .map(UserAccountDto::from);
    }

    public UserAccountDto saveUser(String userId, String Password, String email, String nickname, String memo){
        return  UserAccountDto.from(
//                가입자가 생성시키니 마지막 생성자를 userid로 처리
                userAccountRepository.save(UserAccount.of(userId, Password, email, nickname, memo, userId)));
    }

}
