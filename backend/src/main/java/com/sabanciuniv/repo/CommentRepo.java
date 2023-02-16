package com.sabanciuniv.repo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sabanciuniv.model.Comments;
import com.sabanciuniv.model.News;

@Repository
public interface CommentRepo extends MongoRepository<Comments, String>{

	List<Comments> findByNid(String newsId);


}
