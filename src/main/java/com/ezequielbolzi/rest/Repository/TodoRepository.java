package com.ezequielbolzi.rest.Repository;

import com.ezequielbolzi.rest.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Task, Long> {
}
