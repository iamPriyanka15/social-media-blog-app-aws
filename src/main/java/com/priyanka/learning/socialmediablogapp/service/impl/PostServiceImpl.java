package com.priyanka.learning.socialmediablogapp.service.impl;

import com.priyanka.learning.socialmediablogapp.dto.PostDto;
import com.priyanka.learning.socialmediablogapp.entity.Post;
import com.priyanka.learning.socialmediablogapp.exception.ResourceNotFoundException;
import com.priyanka.learning.socialmediablogapp.repository.PostRepository;
import com.priyanka.learning.socialmediablogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Override
    public PostDto createPost(PostDto postDto) {
        //Map PostDTO to Post Entity
        Post post = mapDtoToEntity(postDto);

        //save to DB
        Post savedPost = postRepository.save(post);

        //Map Post Entity to PostDTO
       PostDto savedPostDto = mapEntityToDto(savedPost);
        return savedPostDto;
    }




    @Override
    public List<PostDto> getAllPosts() {
       List<Post> allPosts = postRepository.findAll();
        //Map Post Entity to PostDTO
       List<PostDto> postDtoList = allPosts.stream().map(post -> mapEntityToDto(post)).collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",String.valueOf(id)));
        PostDto postDto = mapEntityToDto(post);
        return postDto;
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
       Post existingPost = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",String.valueOf(id)));
        existingPost.setDescription(postDto.getDescription());
        existingPost.setContent(postDto.getContent());
        existingPost.setTitle(postDto.getTitle());

        Post updatedPost = postRepository.save(existingPost);
        return mapEntityToDto(updatedPost);
    }


    @Override
    public void deletePostById(long id) {
        Post existingPost = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",String.valueOf(id)));
        postRepository.delete(existingPost);
    }

    private Post mapDtoToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        return post;
    }

    private PostDto mapEntityToDto(Post post) {

        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        return postDto;
    }

}