package com.priyanka.learning.socialmediablogapp.service;

import com.priyanka.learning.socialmediablogapp.dto.PostDto;

import java.util.List;

public interface PostService {

  PostDto  createPost(PostDto postDto);

  List<PostDto> getAllPosts();

  PostDto getPostById(Long id);

  PostDto updatePost(PostDto postDto, long id);



  void deletePostById(long id);
}
