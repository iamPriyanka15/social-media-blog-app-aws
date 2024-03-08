package com.priyanka.learning.socialmediablogapp.controller;

import com.priyanka.learning.socialmediablogapp.dto.CommentDto;
import com.priyanka.learning.socialmediablogapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") long postId, @RequestBody CommentDto commentDto){

        CommentDto savedCommentDto = commentService.createComment(postId, commentDto);

        return new ResponseEntity<>(savedCommentDto, HttpStatus.CREATED);
    }
}
