package com.priyanka.learning.socialmediablogapp.service.impl;

import com.priyanka.learning.socialmediablogapp.dto.CommentDto;
import com.priyanka.learning.socialmediablogapp.entity.Comment;
import com.priyanka.learning.socialmediablogapp.entity.Post;
import com.priyanka.learning.socialmediablogapp.exception.ResourceNotFoundException;
import com.priyanka.learning.socialmediablogapp.repository.CommentRepository;
import com.priyanka.learning.socialmediablogapp.repository.PostRepository;
import com.priyanka.learning.socialmediablogapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        //Map Comment DTO to Comment Entity
        Comment comment = mapDTOEntity(commentDto);
        //Fetch Post from Post Repository using PostId from Request URI
        Post post= postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",String.valueOf(postId)));
        //Set Comment to Post
        comment.setPost(post);
        //save Comment to DB
        Comment savedCommentEntity = commentRepository.save(comment);
        //Map Comment Entity to Comment DTO
        CommentDto saveCommentDto = mapEntityToDto(savedCommentEntity);

        return saveCommentDto;
    }

    private CommentDto mapEntityToDto(Comment savedCommentEntity) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(savedCommentEntity.getId());
        commentDto.setName(savedCommentEntity.getName());
        commentDto.setBody(savedCommentEntity.getBody());
        commentDto.setEmail(savedCommentEntity.getEmail());
        return commentDto;
    }

    private Comment mapDTOEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;

    }


}
