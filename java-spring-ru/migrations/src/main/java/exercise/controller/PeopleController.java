package exercise.controller;

import exercise.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    JdbcTemplate jdbc;

    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getPeople() {
        String query = "SELECT * FROM person";
        return jdbc.query(query,
                (rs, rowNum) -> new Person(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person getPerson(@PathVariable int id) {
        String query = "SELECT * FROM person WHERE id = ?";
        return jdbc.queryForObject(query,
                (rs, rowNum) -> new Person(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")),
                id);
    }
    // END
}
