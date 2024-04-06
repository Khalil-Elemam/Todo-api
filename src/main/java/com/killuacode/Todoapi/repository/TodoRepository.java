package com.killuacode.Todoapi.repository;

import com.killuacode.Todoapi.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    @Query(value = """
            select * from todo where user_id = ?1
            """, nativeQuery = true)
    List<Todo> findByUserId(Integer userId);
}
