package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.entity.Comment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class CommentService {
//    public Flux<Comment> findByPostId(Integer id) {
//
//        WebClient client =getWebClient("https://jsonplaceholder.typicode.com/");
//        Flux<Comment> commentFlux=client.get()
//                .uri("posts/"+id+"/comments")
//                .retrieve()
//                .bodyToFlux(Comment.class);
//
//        return commentFlux;
//    }

    public Flux<Comment> getAllComments() {
        WebClient client=getWebClient("https://jsonplaceholder.typicode.com");
        Flux<Comment> commentFlux=client.get().uri("/comments")
                .retrieve()
                .bodyToFlux(Comment.class);

        return commentFlux;
    }



    public Flux<Comment> getAllCommentsByPostId(Integer postId) {

        WebClient client=getWebClient("https://jsonplaceholder.typicode.com");
        Flux<Comment> commentFlux=client.get()
                .uri("posts/"+postId+"/comments")
                .retrieve()
                .bodyToFlux(Comment.class);
        return commentFlux;
    }


    private WebClient getWebClient(String baseUrl){
        return WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", baseUrl))
                .build();

    }
}
