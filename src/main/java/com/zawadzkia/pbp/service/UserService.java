package com.zawadzkia.pbp.service;

import com.zawadzkia.pbp.exceptions.UserOperationException;
import com.zawadzkia.pbp.mappers.UserMapper;
import com.zawadzkia.pbp.model.Authority;
import com.zawadzkia.pbp.model.User;
import com.zawadzkia.pbp.model.enums.AuthorityType;
import com.zawadzkia.pbp.model.mt.UserMT;
import com.zawadzkia.pbp.model.repository.AuthorityRepository;
import com.zawadzkia.pbp.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    public void registerUser(UserMT userMT) throws Exception {
        if (StringUtils.isBlank(userMT.getUsername())) {
            throw new UserOperationException("User creation error");
        }
        if (StringUtils.isBlank(userMT.getPassword())) {
            throw new UserOperationException("User creation error");
        }
        if (StringUtils.isBlank(userMT.getEmail())) {
            throw new UserOperationException("User creation error");
        }
        User user = userMapper.converUserMT(userMT);
        user.setEnabled(true);
        Optional<Authority> authorityOptional = authorityRepository.findByName(AuthorityType.ROLE_USER);
        Authority authority = authorityOptional.orElseThrow(() -> new UserOperationException("User creation error"));
        HashSet<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        user.setAuthorities(authorities);
        user.setPassword(passwordEncoder.encode(userMT.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setModifiedAt(LocalDateTime.now());
        userRepository.save(user);
    }
}
