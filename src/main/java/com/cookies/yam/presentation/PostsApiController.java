package com.cookies.yam.presentation;

import com.cookies.yam.application.security.auth.LoginUser;
import com.cookies.yam.application.PostsService;
import com.cookies.yam.application.dto.PostsDto;
import com.cookies.yam.application.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST API Controller
 */
@RequestMapping("/api/vi")
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    /* CREATE */
    @PostMapping("/posts")
    public ResponseEntity save(@RequestBody PostsDto.Request dto, @LoginUser UserDto.Response user) {
        return ResponseEntity.ok(postsService.save(dto, user.getNickname()));
    }

    /* READ */
    @GetMapping("/posts/{id}")
    public ResponseEntity read(@PathVariable Long id) {
        return ResponseEntity.ok(postsService.findById(id));
    }

    /* UPDATE */
    @PutMapping("/posts/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody PostsDto.Request dto) {
        postsService.update(id, dto);
        return ResponseEntity.ok(id);
    }

    /* DELETE */
    @DeleteMapping("/posts/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        postsService.delete(id);
        return ResponseEntity.ok(id);
    }
}
