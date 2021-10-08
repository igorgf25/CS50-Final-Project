package com.igor.wishlistapi.controller;

import com.igor.wishlistapi.controller.dto.PersonRq;
import com.igor.wishlistapi.controller.dto.PersonRs;
import com.igor.wishlistapi.model.Person;
import com.igor.wishlistapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins="http://localhost:4200")
public class personController {

    @Autowired
    private PersonService personService;

    @GetMapping("/")
    public List<PersonRs> findAll(){
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public PersonRs findById(@PathVariable("id") Long id){
        return personService.findById(id);
    }

    @PostMapping("/")
    public void insertPerson(@RequestBody PersonRq person)
    {
        personService.insertPerson(person);
    }

    @PutMapping("/")
    public void updatePerson(@RequestBody PersonRq person,@RequestParam Long id){
        personService.updatePerson(person, id);
    }

    @PostMapping("/validPassword")
    public ResponseEntity<Person> validPassword(@RequestParam String login, @RequestParam String password){
        return personService.validPassword(login, password);
    }
}

