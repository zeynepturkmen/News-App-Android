package com.sabanciuniv.repo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sabanciuniv.model.Comments;
import com.sabanciuniv.model.News;
import com.sabanciuniv.model.NewsCategory;

@Repository
public interface CatRepo extends MongoRepository<NewsCategory, String>{

	Optional<NewsCategory> findById(int parseInt);


}
