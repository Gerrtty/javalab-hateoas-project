package ru.itis.hateoas.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
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

        System.out.println(optionalUser.get().getLogin());
        System.out.println(optionalUser.get().getPassword());

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

    public BlogUser signUp(EntityModel<BlogUser> model) {

        if (model.getContent() != null) {

            BlogUser user = model.getContent();

            user = usersRepository.save(BlogUser.builder()
                    .login(user.getLogin())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .firstName(user.getFirstName())
                    .secondName(user.getSecondName())
                    .build());

            return user;
        }

        else {
            throw new IllegalArgumentException("null");
        }

    }

    public BlogUser signUp(SignUpDto user) {


        BlogUser u = usersRepository.save(BlogUser.builder()
                .login(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
//                .firstName(user.getFirstName())
//                .secondName(user.getSecondName())
                .build());

        return u;

    }

}