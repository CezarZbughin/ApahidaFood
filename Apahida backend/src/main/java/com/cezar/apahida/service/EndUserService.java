package com.cezar.apahida.service;

import com.cezar.apahida.model.EndUser;
import com.cezar.apahida.repository.EndUserRepository;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import java.util.List;
import java.util.Optional;

@Service
public class EndUserService {
    @Autowired
    private EndUserRepository endUserRepository;

    public List<EndUser> getAll(){
        return endUserRepository.findAll();
    }

    public EndUser getByUsernameAndPassword(String username, String password) throws CredentialException {
        Optional<EndUser> user = endUserRepository.findByUsernameAndPassword(username, password);
        if(user.isEmpty()){
            throw new CredentialException();
        }
        return user.get();
    }

    public EndUser getById(long id) throws Exception{
        var ok = endUserRepository.findByUsername("cezar");
        Optional<EndUser> user = endUserRepository.findById(id);
        if(user.isEmpty()){
            throw new Exception("not found user by id");
        }
        return user.get();
    }

    public EndUser getByUsername(String username) throws UsernameNotFoundException {
        Optional<EndUser> user = endUserRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException(username);
        }
        return user.get();
    }



}
