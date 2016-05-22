package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Scott on 5/22/16.
 */
public interface UserRepo extends CrudRepository<User, Integer> {
}
