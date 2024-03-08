package com.priyanka.learning.socialmediablogapp.service;

import com.priyanka.learning.socialmediablogapp.dto.CommentDto;

public interface CommentService {

   CommentDto createComment(long postId, CommentDto commentDto);


}
