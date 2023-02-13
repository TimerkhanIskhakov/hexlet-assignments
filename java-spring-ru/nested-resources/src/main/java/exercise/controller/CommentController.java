package exercise.controller;

import exercise.ResourceNotFoundException;
import exercise.model.Comment;
import exercise.model.Post;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // BEGIN
    @Operation(summary = "Get all comments for post")
    @ApiResponse(responseCode = "200", description = "List of all comments fo post")
    @GetMapping("/{postId}/comments")
    public Iterable<Comment> getComments(@PathVariable long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @Operation(summary = "Get comment by post id and comment id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment found"),
            @ApiResponse(responseCode = "404", description = "Comment for post not found")
    })
    @GetMapping("/{postId}/comments/{commentId}")
    public Comment getComment(@PathVariable long postId, @PathVariable long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
    }

    @Operation(summary = "Create comment for post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment created"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @PostMapping("/{postId}/comments")
    public void createComment(@PathVariable long postId, @RequestBody Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Operation(summary = "Update comment for post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment updated"),
            @ApiResponse(responseCode = "404", description = "Post or comment not found")
    })
    @PatchMapping("/{postId}/comments/{commentId}")
    public void updateComment(@PathVariable long postId, @PathVariable long commentId, @RequestBody Comment comment) {
        if (commentRepository.existsByIdAndPostId(commentId, postId)) {
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
            comment.setPost(post);
            comment.setId(commentId);
            commentRepository.save(comment);
        } else {
            throw new ResourceNotFoundException("Comment not found");
        }
    }


    @Operation(summary = "Delete comment for post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment deleted"),
            @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    @DeleteMapping("/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable long postId, @PathVariable long commentId) {
        if (commentRepository.existsByIdAndPostId(commentId, postId)) {
            commentRepository.deleteById(commentId);
        } else {
            throw new ResourceNotFoundException("Comment not found");
        }
    }
    // END
}
