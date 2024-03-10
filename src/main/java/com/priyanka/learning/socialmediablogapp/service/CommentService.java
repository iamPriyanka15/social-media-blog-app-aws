package com.priyanka.learning.socialmediablogapp.service;

import com.priyanka.learning.socialmediablogapp.dto.CommentDto;

import java.util.List;

public interface CommentService {

   CommentDto createComment(long postId, CommentDto commentDto);

   List<CommentDto> getAllCommentsByPostId(long postId);

   CommentDto getCommentByPostIdAndCommentId(long postId, long id);

   CommentDto updateCommentByPostIdAndCommentId(long postId, long id, CommentDto commentDto);

}
