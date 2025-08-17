package com.bns.demo.controller;

import com.bns.demo.model.User;
import com.bns.demo.model.UserDto;
import com.bns.demo.service.UserService;
import com.bns.demo.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public Map<String, Object> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        Page<User> userPage = userService.getAllUsers(PageRequest.of(page, size));
        Map<String, Object> response = new HashMap<>();
        response.put("users", userPage.getContent().stream().map(userMapper::toDto).collect(Collectors.toList()));
        response.put("totalCount", userPage.getTotalElements());
        return response;
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return userMapper.toDto(user);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        User user = userMapper.fromDto(userDto);
        User created = userService.createUser(user);
        return userMapper.toDto(created);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = userMapper.fromDto(userDto);
        User updated = userService.updateUser(id, user);
        return userMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}