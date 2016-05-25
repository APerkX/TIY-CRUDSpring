package com.theironyard;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface GameRepo extends CrudRepository <Game, Integer> {

    List<Game> findByUser (User user);

}
