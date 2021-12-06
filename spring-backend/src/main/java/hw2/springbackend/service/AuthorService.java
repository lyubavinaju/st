package hw2.springbackend.service;

import hw2.springbackend.domain.Author;
import hw2.springbackend.domain.Book;
import hw2.springbackend.repository.AuthorRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

  private final AuthorRepository repository;

  @Autowired
  public AuthorService(AuthorRepository repository) {
    this.repository = repository;
  }

  public List<Author> all() {
    return repository.findAll();
  }

  public Author getById(String id) {
    return repository.findById(id).orElseThrow(NoSuchElementException::new);
  }

  public Author add(Author author) {
    if (author.getBooks() == null) {
      author.setBooks(new ArrayList<>());
    }
    return repository.save(author);
  }

  public void deleteById(String id) {
    repository.deleteById(id);
  }

  public Author addBookByAuthorId(String authorId, Book book) {
    Author author = repository.findById(authorId).orElseThrow(NoSuchElementException::new);
    author.getBooks().add(book);
    return repository.save(author);
  }
}
