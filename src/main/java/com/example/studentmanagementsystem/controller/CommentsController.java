package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.entity.Comment;
import com.example.studentmanagementsystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;


@RestController
public class CommentsController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value="/{postId}",method=RequestMethod.GET)
    public ResponseEntity<Flux<Comment>> getAllCommentsByPostId(@PathVariable Integer postId){
        Flux<Comment> comment=commentService.getAllCommentsByPostId(postId);
        HttpStatus status= Objects.nonNull(comment)?HttpStatus.OK:HttpStatus.NOT_FOUND;

        return  new ResponseEntity<>(comment,status);

    }

    @RequestMapping(value="/allComments",method=RequestMethod.GET)
    public ResponseEntity<Flux<Comment>> getAllComments(){
        Flux<Comment> comment=commentService.getAllComments();
        HttpStatus status= Objects.nonNull(comment)?HttpStatus.OK:HttpStatus.NOT_FOUND;
        return  new ResponseEntity<>(comment,status);
    }

}
