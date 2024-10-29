    package com.proyecto.api.controller.authentication;

    import com.proyecto.api.dto.authentication.LoginDto;
    import com.proyecto.api.dto.authentication.TokenDto;
    import com.proyecto.api.security.encrypt.PasswordEncryptService;
    import com.proyecto.api.security.jwt.OperationJwt;
    import com.proyecto.api.service.user.UserService;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.Calendar;

    @RestController
    @RequestMapping("/api/v1/auth")
    public class AuthorizationController {

        private final OperationJwt  operationJwt;
        private final UserService userService;
        private final PasswordEncryptService passwordEncryptService;

        public AuthorizationController(OperationJwt operationJwt, UserService userService, PasswordEncryptService passwordEncryptService) {
            this.operationJwt = operationJwt;
            this.userService = userService;
            this.passwordEncryptService = passwordEncryptService;
        }

        @PostMapping
        public ResponseEntity<TokenDto> generationJwt(@RequestBody LoginDto loginDto){
            UserRepositoryDto userFound = userService.findByEmail(loginDto.getEmail());
            System.out.println("userFound: " + userFound);
            if (userFound != null){
                TokenDto tokenDto = new TokenDto();
                Calendar durationToken = Calendar.getInstance();
                durationToken.add(Calendar.MINUTE, 5);
                String jwt = "JWT No generado";

                System.out.println("Password clara: " + loginDto.getPassword());
                System.out.println("Password cifrada: " + userFound.getPassword());
                System.out.println("$2a$12$Z0y1t40E0BbyBcsjVE4FmuTaG2vn1Mq0aHSO8h8cGno03Gqop0cAa".equals(userFound.getPassword()));
                System.out.println("Validacion directa " + passwordEncryptService.isPasswordMatch(loginDto.getPassword(), "$2a$12$Z0y1t40E0BbyBcsjVE4FmuTaG2vn1Mq0aHSO8h8cGno03Gqop0cAa"));
                Boolean validacion1 = passwordEncryptService.isPasswordMatch(loginDto.getPassword(), userFound.getPassword());
                System.out.println("Validacion parametrizada " + validacion1);
                BCryptPasswordEncoder descrifrador = new BCryptPasswordEncoder();
                System.out.println("Validacion parametrizada " + descrifrador.matches(loginDto.getPassword(), userFound.getPassword()));
                if (passwordEncryptService.isPasswordMatch(loginDto.getPassword(), userFound.getPassword())){
                    jwt = operationJwt.generateToken(userFound, durationToken);
                }else {
                    return new ResponseEntity("The password " + loginDto.getPassword() + " is not correct", HttpStatus.NOT_FOUND);
                }

                tokenDto.setJwt(jwt);
                tokenDto.setExpirationDateJwt(durationToken.getTime());
                return new ResponseEntity<>(tokenDto, HttpStatus.OK);
            }else {
                return new ResponseEntity("The email " + loginDto.getEmail() + " not found", HttpStatus.NOT_FOUND);
            }
        }

    }
