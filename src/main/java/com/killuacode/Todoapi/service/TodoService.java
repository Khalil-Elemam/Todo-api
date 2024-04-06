package com.killuacode.Todoapi.service;


import com.killuacode.Todoapi.entity.Todo;
import com.killuacode.Todoapi.entity.User;
import com.killuacode.Todoapi.exception.TodoNotFoundException;
import com.killuacode.Todoapi.repository.TodoRepository;
import com.killuacode.Todoapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<Todo> retrieveAllTodos(Integer userId) {
        return todoRepository.findByUserId(userId);
    }

    public Todo retrieveTodo(Integer userId, Integer todoId) {
        return todoRepository
                .findByUserId(userId)
                .stream()
                .filter(todo -> todo.getId().equals(todoId))
                .findFirst()
                .orElseThrow(() -> new TodoNotFoundException("Todo Not Found"));
    }

    public ResponseEntity<Todo> createTodo(Todo todo, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow();
        todo.setUser(user);
        todoRepository.save(todo);
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    public ResponseEntity<Void> deleteTodo(Integer userId, Integer todoId) {
        todoRepository.deleteById(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<Todo> updateTodo(Todo updatedTodo, Integer userId, Integer todoId) {
        User user = userRepository.findById(userId).orElseThrow();
        Optional<Todo> todoOptional = todoRepository.findById(todoId);
        if(todoOptional.isEmpty())
            throw new TodoNotFoundException("Todo Not Found");
        Todo todo = todoOptional.get();
        todo.setTargetDate(updatedTodo.getTargetDate());
        todo.setDone(updatedTodo.isDone());
        todo.setContent(updatedTodo.getContent());
        todoRepository.save(todo);
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }
}
