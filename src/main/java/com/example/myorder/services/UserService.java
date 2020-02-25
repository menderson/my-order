package com.example.myorder.services;

import com.example.myorder.api.dtos.CreateUserDto;
import com.example.myorder.api.dtos.UserResponseDto;
import com.example.myorder.api.mappers.UserMapper;
import com.example.myorder.entities.User;
import com.example.myorder.exceptions.AlreadyExistsException;
import com.example.myorder.exceptions.NotFoundException;
import com.example.myorder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDto create(CreateUserDto createUserDto) {
        validateUserEmail(createUserDto.getEmail());

        User user = userRepository.save(createUser(createUserDto));

       return UserMapper.toResponseDto(user);
    }

    public UserResponseDto findUserById(Integer id) {
        User user = findById(id);

        return UserMapper.toResponseDto(user);
    }

    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()){
            throw new NotFoundException("Não existe usuário para o id: " + id);
        }

        return user.get();
    }

    public List<UserResponseDto> listAll() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseList = new ArrayList<>();

        for (User user : users) {
            UserResponseDto userResponse = new UserResponseDto();
            userResponse.setName(user.getName());
            userResponse.setAddress(user.getAddress());
            userResponse.setPhone(user.getPhone());
            userResponse.setEmail(user.getEmail());

            userResponseList.add(userResponse);
        }

        return userResponseList;
    }

    //JAVA 8
//    public List<UserResponseDto> listAll() {
//        List<User> users = userRepository.findAll();
//
//        users.stream().map(user -> new UserResponseDto()
//                .setName(user.getName())
//                .setEmail(user.getEmail())
//                .setPhone(user.getPhone())
//                .setAddress(user.getAddress()))
//                .collect(Collectors.toList());
//    }

    //JAVA 8 e ModelMapper
//    public List<UserResponseDto> listAll() {
//        List<User> users = userRepository.findAll();
//
//        users.stream().map(UserMapper::toResponseDto)
//                .collect(Collectors.toList());
//    }

    private User createUser(CreateUserDto createUserDto) {
        return new User()
                .setName(createUserDto.getName())
                .setEmail(createUserDto.getEmail())
                .setAddress(createUserDto.getAddress())
                .setPassword(createUserDto.getPassword())
                .setPhone(createUserDto.getPhone());
    }

    private void validateUserEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            throw new AlreadyExistsException("já existe um usuário cadastrado com este email");
        }
    }


}
