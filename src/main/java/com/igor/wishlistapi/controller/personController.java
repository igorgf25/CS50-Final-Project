package com.igor.wishlistapi.controller;

import com.igor.wishlistapi.controller.dto.PersonRq;
import com.igor.wishlistapi.controller.dto.PersonRs;
import com.igor.wishlistapi.model.Person;
import com.igor.wishlistapi.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class personController {

    private final PersonRepository personRepository;
    private final PasswordEncoder encoder;

    public personController(PersonRepository personRepository, PasswordEncoder encoder) {
        this.personRepository = personRepository;
        this.encoder = encoder;
    }

    @GetMapping("/")
    public List<PersonRs> findAll(){
        var people = personRepository.findAll();
        return people.stream().map(PersonRs::convert).collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public PersonRs findById(@PathVariable("id") Long id){
        var person = personRepository.getOne(id);
        return PersonRs.convert(person);
    }

    @PostMapping("/insert")
    public void insertPerson(@RequestBody PersonRq person)
    {
        Person p = new Person();
        p.setEmail(person.getEmail());
        p.setLogin(person.getLogin());
        p.setName(person.getName());
        p.setPassword(encoder.encode(person.getPassword()));
        personRepository.save(p);
    }


    @PostMapping("/update")
    public void updatePerson(@RequestBody PersonRq person,@RequestParam Long id){
        Person p = personRepository.getOne(id);
        p.setName(person.getName());
        p.setPassword(encoder.encode(person.getPassword()));
        p.setEmail(person.getEmail());
        personRepository.save(p);
    }

    @PostMapping("/validPassword")
    public ResponseEntity<Person> validPassword(@RequestParam String login,
                                              @RequestParam String password){


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

