package hw2.springbackend.controller;

import hw2.springbackend.domain.Author;
import hw2.springbackend.domain.Book;
import hw2.springbackend.service.AuthorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

  private final AuthorService service;

  @Autowired
  public AuthorController(AuthorService service) {
    this.service = service;
  }

  @GetMapping("/author")
  public List<Author> all() {
    return service.all();
  }

  @GetMapping("/author/{id}")
  public Author one(@PathVariable String id) {
    return service.getById(id);
  }

  @PostMapping("/author")
  public Author add(@RequestBody Author author) {
    return service.add(author);
  }

  @DeleteMapping("/author/{id}")
  public void delete(@PathVariable String id) {
    service.deleteById(id);
  }

  @PostMapping("/author/{id}/book")
  public Author addBook(@PathVariable String id, @RequestBody Book book) {
    return service.addBookByAuthorId(id, book);
  }
}
