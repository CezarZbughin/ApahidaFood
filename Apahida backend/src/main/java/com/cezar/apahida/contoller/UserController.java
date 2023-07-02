package com.cezar.apahida.contoller;

import com.cezar.apahida.model.EndUser;
import com.cezar.apahida.service.EndUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private EndUserService endUserService;

    @GetMapping("/get")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(endUserService.getAll());
    }

    @GetMapping("/self")
    public ResponseEntity<?> self(Principal principal){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "http://localhost:8090");
        responseHeaders.set("Access-Control-Allow-Headers","*");
        responseHeaders.set("Access-Control-Allow-Methods","*");
        responseHeaders.set("Access-Control-Allow-Credentials","true");
        responseHeaders.set("Access-Control-Expose-Headers","*");
        //return ResponseEntity.ok().headers(responseHeaders).body(endUserService.getByUsername(principal.getName()));
        return ResponseEntity.ok(endUserService.getByUsername(principal.getName()));
    }



}
