package com.example.website2.repos;

import com.example.website2.domain.UserData;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserData, Integer> {
}
