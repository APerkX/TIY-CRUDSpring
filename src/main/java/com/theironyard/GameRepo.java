package com.theironyard;

import org.springframework.data.repository.CrudRepository;

public interface GameRepo extends CrudRepository <Game, Integer> {

    Game findFirstByName(String name);

}
