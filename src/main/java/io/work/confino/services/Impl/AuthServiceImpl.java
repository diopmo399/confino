package io.work.confino.services.Impl;

import io.work.confino.models.CompanyAccount;
import io.work.confino.models.Role;
import io.work.confino.repositories.CompanyAccountMongoRepository;
import io.work.confino.repositories.RoleMongoRepository;
import io.work.confino.security.jwt.JwtUtils;
import io.work.confino.security.request.LoginRequest;
import io.work.confino.security.request.SignupRequest;
import io.work.confino.security.response.JwtResponse;
import io.work.confino.security.response.MessageResponse;
import io.work.confino.security.service.UserDetailsImpl;
import io.work.confino.services.AuthService;
import io.work.confino.utils.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    final private CompanyAccountMongoRepository companyAccountMongoRepository;

    final private RoleMongoRepository roleMongoRepository;

    public AuthServiceImpl(CompanyAccountMongoRepository companyAccountMongoRepository,
                           RoleMongoRepository roleMongoRepository) {
        this.companyAccountMongoRepository = companyAccountMongoRepository;
        this.roleMongoRepository = roleMongoRepository;
    }

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @Override
    public ResponseEntity<?> registerUser(SignupRequest signupRequest) {
        if (companyAccountMongoRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (companyAccountMongoRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }


        // Create new user's account
        CompanyAccount companyAccount = CompanyAccount.builder()
                .username(signupRequest.getUsername())
                .email(signupRequest.getEmail())
                .password(encoder.encode(signupRequest.getPassword()))
                .firstName(signupRequest.getFirstName())
                .lastName(signupRequest.getLastName())
                .build();

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleMongoRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleMongoRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleMongoRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        companyAccount.setRoles(roles);
        companyAccountMongoRepository.save(companyAccount);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
