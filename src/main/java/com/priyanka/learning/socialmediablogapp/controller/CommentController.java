package com.priyanka.learning.socialmediablogapp.controller;

import com.priyanka.learning.socialmediablogapp.dto.CommentDto;
import com.priyanka.learning.socialmediablogapp.dto.PatchDto;
import com.priyanka.learning.socialmediablogapp.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") long postId, @RequestBody @Valid CommentDto commentDto){

        CommentDto savedCommentDto = commentService.createComment(postId, commentDto);

        return new ResponseEntity<>(savedCommentDto, HttpStatus.CREATED);
    }

    //Get all Comments - /api/v1/posts/{postId}/comments
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> fetchAllCommentsByPostId(@PathVariable("postId") Long postId){
        List<CommentDto> commentDtoList = commentService.getAllCommentsByPostId(postId);
        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);

    }

    //Get Comment By CommentId and PostId - /api/v1/posts/{postId}/comments/{id}
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> fetchCommentsByPostIdAndCommentId(@PathVariable("postId") Long postId,@PathVariable("id") Long id){
        CommentDto commentDto = commentService.getCommentByPostIdAndCommentId(postId,id);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);

    }

    //PUT Update Comment By PostId and CommentId - /api/v1/posts/{postId}/comments/{id}

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateCommentsByPostIdAndCommentId(@PathVariable("postId") Long postId,
                                                                         @PathVariable("id") Long id,
                                                                         @RequestBody @Valid CommentDto commentDto){
        CommentDto updatedCommentDto = commentService.updateCommentByPostIdAndCommentId(postId,id, commentDto);
        return new ResponseEntity<>(updatedCommentDto, HttpStatus.OK);

    }

    //Delete Comment By CommentId and PostId - /api/v1/posts/{postId}/comments/{id}


    //Patch Comment By CommentId and PostId

    @PatchMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> partiallyupdateCommentsByPostIdAndCommentId(@PathVariable("postId") Long postId,
                                                                                  @PathVariable("id") Long id,
                                                                                  @RequestBody PatchDto patchDto){
        CommentDto updatedCommentDto = null;
        if(patchDto.getOperation().equalsIgnoreCase("update")){
            updatedCommentDto = commentService.updateCommentPartiallyByPostIdAndCommentId(postId,id, patchDto);
        }else if(patchDto.getOperation().equalsIgnoreCase("delete")){
            // updatedCommentDto = commentService.deleteParticularField(postId,id, patchDto);
        }

        return new ResponseEntity<>(updatedCommentDto, HttpStatus.OK);

    }




}
