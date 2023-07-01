package com.cookies.yam.presentation;


import com.cookies.yam.application.PostsService;
import com.cookies.yam.application.UserService;
import com.cookies.yam.application.dto.PostsDto;
import com.cookies.yam.application.dto.UserDto;
import com.cookies.yam.domain.File;
import com.cookies.yam.domain.Posts;
import com.cookies.yam.domain.User;
import com.cookies.yam.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * REST API Controller
 */
@RequestMapping("/api/vi")
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;
    private final UserService userService;
    /* CREATE */
    @PostMapping("/posts/save")
    public ResponseEntity save(@RequestBody PostsDto.Request dto, UserDto.Response user) {
        String username = user.getUsername();

        /*
        @RequestParam("file") MultipartFile file,
        if (!file.isEmpty()) {
            String fileName = FileUploadUtil.uploadFile(file);

            File fileEntity = new File();
            fileEntity.setOriginalFileName(file.getOriginalFilename());
            fileEntity.setRenamedFileName(fileName);

            post.setFile(fileEntity);
        }*/


        Optional<User> checkUser = userService.findByUsername(username);
        User u = checkUser.orElseGet(User::new);
        Long user_id = u.getId();
        String title = dto.getTitle();
        String content = dto.getContent();
        int limit = dto.getLimit();
        int count = dto.getCount();
        Long category_id = dto.getCategory_id();
        Long address_id = dto.getAddress_id();

        Long id = postsService.save( dto, username);

        return ResponseEntity.ok(id);
    }

    /* READ */
    @PostMapping("/posts/detail")
    public ResponseEntity read(@RequestBody PostsDto.Request dto) {

        return ResponseEntity.ok(postsService.findById(dto.getId()));
    }

    /* UPDATE */
    @PutMapping("/posts/update/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody PostsDto.Request dto) {
        postsService.update(id, dto);
        return ResponseEntity.ok(id);
    }

    /* DELETE */
    @DeleteMapping("/posts/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        postsService.delete(id);
        return ResponseEntity.ok(id);
    }

    /* LIST */
    /* param : searchValue, keyword, orderValue, page*/
    @GetMapping("/posts/list")
    /* default page = 0, size = 10  */
    public String index(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
    Pageable pageable, UserDto.Response user) {
        Page<Posts> list = postsService.pageList(pageable);

        if (user != null) {
            model.addAttribute("user", user);
        }

        model.addAttribute("posts", list);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());

        return "index";
    }
}
