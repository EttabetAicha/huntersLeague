package org.aicha.hunters_leagues.web.rest;

import org.aicha.hunters_leagues.domain.User;
import org.aicha.hunters_leagues.service.AuthService;
import org.aicha.hunters_leagues.web.vm.LoginVM;
import org.aicha.hunters_leagues.web.vm.RegisterVM;
import org.aicha.hunters_leagues.mapper.LoginMapper;
import org.aicha.hunters_leagues.mapper.RegisterMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final LoginMapper loginMapper;
    private final RegisterMapper registerMapper;

    public AuthController(AuthService authService, LoginMapper loginMapper, RegisterMapper registerMapper) {
        this.authService = authService;
        this.loginMapper = loginMapper;
        this.registerMapper = registerMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterVM registerVM) {
        User user = registerMapper.toEntity(registerVM);
        User registeredUser = authService.register(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginVM loginVM) {
        User user = loginMapper.toEntity(loginVM);
        boolean isAuthenticated = authService.login(user);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}