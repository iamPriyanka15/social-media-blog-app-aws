package com.priyanka.learning.socialmediablogapp.controller;

import com.priyanka.learning.socialmediablogapp.dto.PostDto;
import com.priyanka.learning.socialmediablogapp.payload.PostResponse;
import com.priyanka.learning.socialmediablogapp.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody @Valid PostDto postDto){
       PostDto savedPostDto = postService.createPost(postDto);
       return new ResponseEntity(savedPostDto, HttpStatus.CREATED);

    }
    //pagination and sorting
    @GetMapping
    public PostResponse getAllPosts(
         @RequestParam(value = "pageNo", defaultValue = "0", required = false)   int pageNo,
         @RequestParam(value = "pageSize", defaultValue = "4", required = false) int pageSize,
         @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
         @RequestParam(value = "sortDir", defaultValue = "id", required = false) String sortDir
    ){

        return postService.getAllPosts(pageNo,pageSize, sortBy,sortDir);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id){
        return ResponseEntity.ok(postService.getPostById(id));

    }

    //Update

    @PutMapping("/{id}")
    //@PatchMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody @Valid PostDto postDto, @PathVariable long id){
       PostDto updatedPostResponse = postService.updatePost(postDto, id);
       return ResponseEntity.ok(updatedPostResponse);
    }


    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id){
        postService.deletePostById(id);
        return ResponseEntity.ok("Deleted Successfully Post Resource ::"+id);
    }



}
