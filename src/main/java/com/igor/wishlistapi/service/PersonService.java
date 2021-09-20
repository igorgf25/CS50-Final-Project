package com.igor.wishlistapi.service;

import com.igor.wishlistapi.controller.dto.PersonRq;
import com.igor.wishlistapi.controller.dto.PersonRs;
import com.igor.wishlistapi.model.Person;
import com.igor.wishlistapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder encoder;

    public List<PersonRs> findAll(){
        var people = personRepository.findAll();
        return people.stream().map(PersonRs::convert).collect(Collectors.toList());
    }

    public PersonRs findById(Long id){
        Optional<Person> person = personRepository.findById(id);
        PersonRs p = new PersonRs();
        if(person.isPresent()){
            return p.convert(person.get());
        }else{
            throw new NoSuchElementException();
        }
    }

    public void insertPerson(PersonRq person)
    {
        Person p = new Person();
        p.setEmail(person.getEmail());
        p.setLogin(person.getLogin());
        p.setName(person.getName());
        p.setPassword(encoder.encode(person.getPassword()));
        personRepository.save(p);
    }

    public void updatePerson(PersonRq person, Long id){
        Optional<Person> p = personRepository.findById(id);
        Person t = new Person();
        if(p.isPresent()){
            if(p.get().getId().equals(id) && p.get().getLogin().equals(person.getLogin())){
                t.setId(id);
                t.setPassword(person.getPassword());
                t.setName(person.getName());
                t.setEmail(person.getEmail());
                t.setLogin(p.get().getLogin());
                personRepository.save(t);
            }else{
                throw new NoSuchElementException();
            }
        }else{
            throw new NoSuchElementException();
        }
    }

    public ResponseEntity<Person> validPassword(String login, String password){
        Optional<Person> person = personRepository.findByLogin(login);

        if (person.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Person p = person.get();

        boolean valid = false;
        valid = encoder.matches(password, p.getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        if (!valid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(p);
        }
    }

}
