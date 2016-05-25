package com.theironyard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRepo extends CrudRepository <Game, Integer> {

    @Query("SELECT g FROM Game g, User u WHERE g.user_id = u.id")
    List<Game> findByUser (User user);

}
