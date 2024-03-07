package com.priyanka.learning.socialmediablogapp.repository;

import com.priyanka.learning.socialmediablogapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
