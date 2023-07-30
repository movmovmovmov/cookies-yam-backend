package com.cookies.yam.presentation;


import com.cookies.yam.application.FilesService;
import com.cookies.yam.application.PostsService;
import com.cookies.yam.application.UserService;
import com.cookies.yam.application.dto.PostsDto;
import com.cookies.yam.domain.Files;
import com.cookies.yam.domain.Posts;
import com.cookies.yam.domain.User;
import com.cookies.yam.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * REST API Controller
 */
@RequestMapping("/api/vi")
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;
    private final FilesService filesService;
    private final UserService userService;


    /* CREATE */
    @PostMapping("/posts/save")
    public ResponseEntity save(@RequestBody PostsDto.Request dto, @RequestBody MultipartFile file) throws IOException {
        String username = dto.getUsername();

        Optional<User> checkUser = userService.findByUsername(username);
        User u = checkUser.orElseGet(User::new);

        String title = dto.getTitle();
        String content = dto.getContent();
        int limit = dto.getLimit();
        int count = dto.getCount();
        Long category_id = dto.getCategory_id();
        Long address_id = dto.getAddress_id();

        Long posts_id = postsService.save( title, content, limit, count, category_id, address_id, username);


        if (!file.isEmpty()) {
            String fileName = FileUploadUtil.uploadFile(file, posts_id);

            Files fileEntity = new Files();
            fileEntity.setOriginalFileName(file.getOriginalFilename());
            fileEntity.setRenamedFileName(fileName);

            filesService.save(file.getOriginalFilename(),fileName,posts_id);
        }
        return ResponseEntity.ok(posts_id);
    }

    /*
    // READ
    @PostMapping("/posts/detail")
    public ResponseEntity read(@RequestBody PostsDto.Request dto) {
        Long posts_id = dto.getPosts_id();
        dto.setId(posts_id);

        return ResponseEntity.ok(postsService.findById(dto.getId()));
    }
    */

    /*
    // UPDATE
    @PutMapping("/posts/update/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody PostsDto.Request dto) {
        postsService.update(id, dto);
        return ResponseEntity.ok(id);
    }
    */
    /*
    // DELETE
    @DeleteMapping("/posts/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        postsService.delete(id);
        return ResponseEntity.ok(id);
    }
    */


    // LIST
    // param : searchValue, keyword, orderValue, page
    // default page = 0, size = 10
    @GetMapping("/posts/list")
    public Page<Posts> list(@RequestParam(defaultValue = "") String keyword,
                               @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "createdAt") String sortBy) {

        Sort sort = Sort.by(sortBy).descending();
        if(sortBy.equals("count")){
            sort = Sort.by(sortBy).ascending();
        }
        Pageable pageable = PageRequest.of(page, 20, sort);
        return postsService.search(keyword, pageable);
    }

    // READ
    @GetMapping("/posts/detail")
    public ResponseEntity read(@RequestParam(defaultValue="0") long id){
        System.out.println("테스트1: ");
        Optional<Posts> post = postsService.Read(id);

        System.out.println("test haza: "+post);


        return ResponseEntity.ok(post);
    }
}
