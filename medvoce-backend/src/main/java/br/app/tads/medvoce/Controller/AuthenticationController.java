package br.app.tads.medvoce.Controller;

import br.app.tads.medvoce.Config.responseBuilder.ResponseBuilder;
import br.app.tads.medvoce.Config.security.AuthorizationService;
import br.app.tads.medvoce.Config.security.TokenService;
import br.app.tads.medvoce.Model.enums.Groups;
import br.app.tads.medvoce.Model.interfaces.IUser;
import br.app.tads.medvoce.Model.records.Auth;
import br.app.tads.medvoce.Model.records.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private ResponseBuilder responseBuilder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        try{
            var usernamePassword = new UsernamePasswordAuthenticationToken(
                    login.email(),
                    login.password()
            );

            var auth = this.authenticationManager.authenticate(usernamePassword);
            String authority = auth.getAuthorities().toArray()[0].toString();

            Groups group = null;
            Auth userAuthorized = null;

            switch (authority){
                case "ROLE_ADMIN":
                    group = Groups.ADMIN;
                    break;
                case "ROLE_DOCTOR":
                    group = Groups.DOCTOR;
                    break;
                case "ROLE_PATIENT":
                    group = Groups.PATIENT;
                    break;
                default:
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            var token = tokenService.generateToken((IUser) auth.getPrincipal());

            Long id = ((IUser) auth.getPrincipal()).getId();
            String name = ((IUser) auth.getPrincipal()).getName();

            userAuthorized = new Auth(token, name, group, id);

            return ResponseEntity.ok(userAuthorized);

        } catch (Exception e){
            return responseBuilder.build(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
