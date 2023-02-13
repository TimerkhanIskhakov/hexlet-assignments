package exercise.controller;
import com.querydsl.core.types.dsl.BooleanExpression;
import exercise.model.User;
import exercise.model.QUser;
import exercise.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Зависимости для самостоятельной работы
// import org.springframework.data.querydsl.binding.QuerydslPredicate;
// import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    // BEGIN
    @Operation(summary = "Get users by firstname and lastname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return all users if parameters not specified"),
            @ApiResponse(responseCode = "200", description = "Return all users, who contains specified parameters")
    })
    @GetMapping(path = "")
    public List<User> getUsers(@RequestParam(required = false) String firstName,
                               @RequestParam(required = false) String lastName) {

        BooleanExpression expression1 = null;
        if (!Objects.isNull(firstName) && !firstName.isEmpty()) {
            expression1 = QUser.user.firstName.containsIgnoreCase(firstName);
        }
        BooleanExpression expression2 = null;
        if (!Objects.isNull(lastName) && !lastName.isEmpty()) {
            expression2 = QUser.user.lastName.containsIgnoreCase(lastName);
        }

        BooleanExpression expression3 = expression1 == null ? expression2 : expression1.and(expression2);

        return expression3 == null ? userRepository.findAll() : (List<User>) userRepository.findAll(expression3);
    }
    // END
}

