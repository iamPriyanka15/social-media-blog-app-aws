package com.priyanka.learning.socialmediablogapp.service.impl;

import com.priyanka.learning.socialmediablogapp.dto.CommentDto;
import com.priyanka.learning.socialmediablogapp.dto.PatchDto;
import com.priyanka.learning.socialmediablogapp.entity.Comment;
import com.priyanka.learning.socialmediablogapp.entity.Post;
import com.priyanka.learning.socialmediablogapp.exception.ResourceNotFoundException;
import com.priyanka.learning.socialmediablogapp.repository.CommentRepository;
import com.priyanka.learning.socialmediablogapp.repository.PostRepository;
import com.priyanka.learning.socialmediablogapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<CommentDto> getAllCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        //Map Entity to Dto
        List<CommentDto> commentDtoList = comments.stream().map(comment -> mapEntityToDto(comment)).collect(Collectors.toList());
        return commentDtoList;
    }

    @Override
    public CommentDto getCommentByPostIdAndCommentId(long postId, long id) {
        //Fetch Post
        Post postEntity = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",String.valueOf(postId)));
        //Fetch Comment by CommentId
        Comment commentEntity = commentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("comment","id",String.valueOf(id)));
        //validate comment belongs to that particular Post
        if(!commentEntity.getPost().getId().equals(postEntity.getId())){
            throw new RuntimeException("Bad request Comment Not Found");
        }
        //Map Comment Entity to DTO
        CommentDto commentDto = mapEntityToDto(commentEntity);
        return commentDto;
    }

    @Override
    public CommentDto updateCommentByPostIdAndCommentId(long postId, long id, CommentDto commentDto) {

        //Fetch Post
        Post postEntity = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",String.valueOf(postId)));
        //Fetch Comment by CommentId
        Comment commentEntity = commentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("comment","id",String.valueOf(id)));
        //validate comment belongs to that particular Post
        if(!commentEntity.getPost().getId().equals(postEntity.getId())){
            throw new RuntimeException("Bad request Comment Not Found");
        }

        // Update old comment Details with new Comment Dto
        commentEntity.setName(commentDto.getName());
        commentEntity.setEmail(commentDto.getEmail());
        commentEntity.setBody(commentDto.getBody());

        //save Updated Comment Entity
        Comment updatedCommentEntity = commentRepository.save(commentEntity);

        //Map Comment Entity to DTO
        CommentDto updatedcommentDto = mapEntityToDto(commentEntity);

        return updatedcommentDto;
    }

    @Override
    public CommentDto updateCommentPartiallyByPostIdAndCommentId(Long postId, Long id, PatchDto patchDto) {

        //Fetch Post
        Post postEntity = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",String.valueOf(postId)));
        //Fetch Comment by CommentId
        Comment commentEntity = commentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("comment","id",String.valueOf(id)));
        //validate comment belongs to that particular Post
        if(!commentEntity.getPost().getId().equals(postEntity.getId())){
            throw new RuntimeException("Bad request Comment Not Found");
        }


        partiallyUpdateCommentEntity(patchDto, commentEntity);

        //save Updated Comment Entity
        Comment updatedCommentEntity = commentRepository.save(commentEntity);

        //Map Comment Entity to DTO
        CommentDto updatedcommentDto = mapEntityToDto(commentEntity);

        return updatedcommentDto;
        
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

    private void partiallyUpdateCommentEntity(PatchDto patchDto, Comment commentEntity) {
        switch (patchDto.getKey()){
            case "Email" :
                commentEntity.setEmail(patchDto.getValue());
                break;
            case "Body" :
                commentEntity.setBody(patchDto.getValue());
                break;
            case "Name" :
                commentEntity.setName(patchDto.getValue());
                break;
        }
    }


}
