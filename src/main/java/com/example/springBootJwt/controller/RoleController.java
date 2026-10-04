package com.example.springBootJwt.controller;


import com.example.springBootJwt.entity.Role;
import com.example.springBootJwt.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/roles")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<String> addRole(@RequestBody Role role) {

        roleService.addRole(role);
        return ResponseEntity.ok("Done");

    }
}
