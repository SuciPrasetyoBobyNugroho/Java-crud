package com.example.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.AppUserDto;
import com.example.dto.ResponseData;
import com.example.models.entities.AppUser;
import com.example.services.AppUserService;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<AppUser>> register(@RequestBody AppUserDto userDto) {

        ResponseData<AppUser> response = new ResponseData<>();
        AppUser appUser = modelMapper.map(userDto, AppUser.class);
        response.setPayload(appUserService.registerAppUser(appUser));
        response.setStatus(true);
        response.getMessages().add("AppUser Saved!!!");
        return ResponseEntity.ok(response);
    }
}
