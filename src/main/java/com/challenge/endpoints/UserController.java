package com.challenge.endpoints;

import java.util.List;

import com.challenge.entity.User;
import com.challenge.service.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(this.userService.findById(userId).get());
    }

    @GetMapping
    public ResponseEntity<List<User>> findByAccelerationNameOrCompanyId
            (@RequestParam(required = false) String accelerationName, @RequestParam(required = false) Long companyId) {
        if (accelerationName != null)
            return ResponseEntity.ok(this.userService.findByAccelerationName(accelerationName));
        if (companyId != null)
            return ResponseEntity.ok(this.userService.findByCompanyId(companyId));
        return null;
    }
}