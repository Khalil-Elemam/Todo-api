package com.killuacode.Todoapi.service;


import com.killuacode.Todoapi.dto.UserDTO;
import com.killuacode.Todoapi.dto.UserDTOMapper;
import com.killuacode.Todoapi.entity.User;
import com.killuacode.Todoapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    public List<UserDTO> retrieveAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userDTOMapper)
                .toList();
    }


    public ResponseEntity<Void> deleteUser(Integer userId) {
        userRepository.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
