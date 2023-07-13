package com.ezequielbolzi.rest.Controller;

import com.ezequielbolzi.rest.Model.Task;
import com.ezequielbolzi.rest.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;

    @GetMapping(value = "/tasks")
    public List<Task> getTasks(){

        return todoRepository.findAll();
    }

    @PostMapping(value = "/savetask")
       public String saveTask(@RequestBody Task task){
        todoRepository.save(task);
        return "Saved Task";
    }

    @PutMapping(value = "/update/{id}")
        public String updateTask(@PathVariable long id, @RequestBody Task task){
            Task updateTask = todoRepository.findById(id).get();
            updateTask.setTitle(task.getTitle());
            updateTask.setDescription(task.getDescription());
            todoRepository.save(updateTask);
            return "Update task";
    }
    @DeleteMapping(value = "delete/{id}")
        public String deleteTask(@PathVariable long id){
        Task deletedTask = todoRepository.findById(id).get();
        todoRepository.delete(deletedTask);
        return "Delete task";
    }
}
