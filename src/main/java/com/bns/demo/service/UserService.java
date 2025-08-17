package com.bns.demo.service;

import com.bns.demo.model.User;
import com.bns.demo.repository.UserRepository;
import com.bns.demo.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, User updatedUser) {
        User existing = getUserById(id);
        existing.setFirstName(updatedUser.getFirstName());
        existing.setLastName(updatedUser.getLastName());
        existing.setStartDate(updatedUser.getStartDate());
        existing.setEndDate(updatedUser.getEndDate());
        existing.setEffectiveDate(updatedUser.getEffectiveDate());
        existing.setRole(updatedUser.getRole());
        existing.setStatus(updatedUser.getStatus());
        existing.setEmailId(updatedUser.getEmailId());
        existing.setContactNum(updatedUser.getContactNum());
        return userRepository.save(existing);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}