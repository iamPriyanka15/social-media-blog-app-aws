package com.priyanka.learning.socialmediablogapp.service;

import com.priyanka.learning.socialmediablogapp.dto.PostDto;
import com.priyanka.learning.socialmediablogapp.payload.PostResponse;

import java.util.List;

public interface PostService {

  PostDto  createPost(PostDto postDto);

  PostResponse getAllPosts(int pageNo, int pageSize);

  PostDto getPostById(Long id);

  PostDto updatePost(PostDto postDto, long id);



  void deletePostById(long id);
}
