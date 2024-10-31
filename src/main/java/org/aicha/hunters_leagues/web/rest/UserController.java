package org.aicha.hunters_leagues.web.rest;

import org.aicha.hunters_leagues.domain.User;
import org.aicha.hunters_leagues.service.UserService;
import org.aicha.hunters_leagues.web.exception.ResourceNotFoundException;
import org.aicha.hunters_leagues.web.vm.UserVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserVM> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        UserVM userVM = convertToVM(createdUser);
        return ResponseEntity.ok(userVM);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserVM> getUserById(@PathVariable UUID id) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        UserVM userVM = convertToVM(user);
        return ResponseEntity.ok(userVM);
    }

    @GetMapping
    public ResponseEntity<List<UserVM>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        List<UserVM> userVMs = users.stream().map(this::convertToVM).collect(Collectors.toList());
        return ResponseEntity.ok(userVMs);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<UserVM>> getUsersWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "username") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Page<User> users = userService.getUsersWithPaginationAndSorting(page, size, sortBy, sortDir);
        List<UserVM> userVMs = users.getContent().stream().map(this::convertToVM).collect(Collectors.toList());
        Page<UserVM> userVMPage = new PageImpl<>(userVMs, users.getPageable(), users.getTotalElements());
        return ResponseEntity.ok(userVMPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserVM> updateUser(@PathVariable UUID id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        UserVM userVM = convertToVM(updatedUser);
        return ResponseEntity.ok(userVM);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    private UserVM convertToVM(User user) {
        UserVM userVM = new UserVM();
        userVM.setId(user.getId());
        userVM.setUsername(user.getUsername());
        userVM.setRole(user.getRole());
        userVM.setFirstName(user.getFirstName());
        userVM.setLastName(user.getLastName());
        userVM.setCin(user.getCin());
        userVM.setEmail(user.getEmail());
        userVM.setNationality(user.getNationality());
        userVM.setJoinDate(user.getJoinDate());
        userVM.setLicenseExpirationDate(user.getLicenseExpirationDate());
        return userVM;
    }
}