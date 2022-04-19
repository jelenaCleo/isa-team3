package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.mail.MessagingException;
import java.util.List;

public interface UserService {

    public List<UserDto> getAll();

    public UserDto get(Long id);

    public void delete(Long id);

    public boolean isEmailRegistered(String email);

    public UserDetails loadUserByUsername(String email);

    public User createClient (RegisterDto registerRequest) throws MessagingException;

    public User createAdmin(RegisterDto registerRequest);

    String enableUser(String email);
}
