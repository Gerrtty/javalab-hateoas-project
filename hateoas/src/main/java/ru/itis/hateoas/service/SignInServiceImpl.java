package ru.itis.hateoas.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.dto.SignInDto;
import ru.itis.hateoas.dto.SignUpDto;
import ru.itis.hateoas.dto.TokenDto;
import ru.itis.hateoas.model.BlogUser;
import ru.itis.hateoas.repository.BlogUsersRepository;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

@Service
public class SignInServiceImpl {

    @Value("${jwt.secret}")
    private String secret;

    private final BlogUsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SignInServiceImpl(BlogUsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public TokenDto signIn(SignInDto signInData) throws AccessDeniedException {
        Optional<BlogUser> optionalUser = usersRepository.findBlogUserByLogin(signInData.getEmail());

        if(optionalUser.isPresent()) {

            BlogUser user = optionalUser.get();

            if(passwordEncoder.matches(signInData.getPassword(), user.getPassword())) {

                String token = Jwts.builder()
                        .setSubject(user.getId().toString())
                        .claim("email", user.getLogin())
                        .signWith(SignatureAlgorithm.HS256, secret)
                        .compact();

                return new TokenDto(token);

            }

            else {
                throw new AccessDeniedException("Wrong email/password");
            }

        }

        else {
            throw new AccessDeniedException("User not found");
        }
    }

    public BlogUser signUp(SignUpDto signUpDto) {

        BlogUser user = BlogUser.builder()
                .login(signUpDto.getEmail())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .build();

        user = usersRepository.save(user);

        return user;

    }

}