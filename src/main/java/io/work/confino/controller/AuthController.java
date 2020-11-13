package io.work.yourschools.controller;

import io.work.yourschools.entity.*;
import io.work.yourschools.exceptions.BadRequestException;
import io.work.yourschools.repositories.EtudiantRepository;
import io.work.yourschools.repositories.RepresentantRepository;
import io.work.yourschools.repositories.RoleRepository;
import io.work.yourschools.repositories.UtilisateurRepository;
import io.work.yourschools.security.jwt.JwtUtils;
import io.work.yourschools.security.request.LoginRequest;
import io.work.yourschools.security.request.SignupRequest;
import io.work.yourschools.security.response.JwtResponse;
import io.work.yourschools.security.response.MessageResponse;
import io.work.yourschools.security.service.UserDetailsImpl;
import io.work.yourschools.utils.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UtilisateurRepository userRepository;
    @Autowired
    RepresentantRepository representantRepository;
    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

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

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }


        // Create new user's account


        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: ttttt   Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "representant":
                        Role modRole = roleRepository.findByName(ERole.ROLE_REPRESENTANT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        if (ERole.ROLE_USER == signUpRequest.getType()){
            Etudiant user = Etudiant
                    .builder()
                    .username(signUpRequest.getUsername())
                    .email(signUpRequest.getEmail())
                    .password(encoder.encode(signUpRequest.getPassword()))
                    .nom(signUpRequest.getNom())
                    .prenom(signUpRequest.getPrenom())
                    .niveauEtude(signUpRequest.getNiveauEtude())
                    .serie(signUpRequest.getSerie())
                    .build();
            user.setRoles(roles);
            userRepository.save(user);
        }
        if (ERole.ROLE_REPRESENTANT == signUpRequest.getType()){
            Representant user = Representant
                    .builder()
                    .username(signUpRequest.getUsername())
                    .email(signUpRequest.getEmail())
                    .nom(signUpRequest.getNom())
                    .prenom(signUpRequest.getPrenom())
                    .password(encoder.encode(signUpRequest.getPassword()))
                    .fonction(signUpRequest.getFonction())
                    .etablissement(signUpRequest.getEtablissement())
                    .build();
            user.setRoles(roles);
            Etablissement etablissement = user.getEtablissement();
            user.setEtablissement(null);
            Representant representant = representantRepository.findByEtablissement(etablissement);
            if (representant != null)
                throw new BadRequestException("Cet etablissement à un representant");
            user = representantRepository.save(user);
            user.setEtablissement(etablissement);
            representantRepository.saveAndFlush(user);
        }

        if (ERole.ROLE_ADMIN == signUpRequest.getType()){
            Etudiant user = Etudiant
                    .builder()
                    .username(signUpRequest.getUsername())
                    .email(signUpRequest.getEmail())
                    .nom(signUpRequest.getNom())
                    .prenom(signUpRequest.getPrenom())
                    .password(encoder.encode(signUpRequest.getPassword()))
                    .build();
            user.setRoles(roles);
            userRepository.save(user);
        }



        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
