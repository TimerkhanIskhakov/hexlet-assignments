package exercise.controller;

import exercise.dto.PersonDto;
import exercise.model.Person;
import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/people")
public class PeopleController {

    // Автоматически заполняем значение поля
    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person getPerson(@PathVariable long id) {
        return this.personRepository.findById(id);
    }

    @GetMapping(path = "")
    public Iterable<Person> getPeople() {
        return this.personRepository.findAll();
    }

    // BEGIN
    @PostMapping(path = "")
    public void createPerson(@RequestBody PersonDto personDto) {

        Person newPerson = new Person();
        newPerson.setFirstName(personDto.getFirstName());
        newPerson.setLastName(personDto.getLastName());
        this.personRepository.save(newPerson);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePerson(@PathVariable long id) {
        this.personRepository.deleteById(id);
    }

    @PatchMapping(path = "/{id}")
    public void updatePerson(@PathVariable long id, @RequestBody PersonDto personDto) {

        Person person = new Person();
        person.setId(id);
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());

        this.personRepository.save(person);
    }
    // END
}
