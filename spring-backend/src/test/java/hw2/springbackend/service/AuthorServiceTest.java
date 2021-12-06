package hw2.springbackend.service;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

import hw2.springbackend.domain.Author;
import hw2.springbackend.domain.Book;
import hw2.springbackend.repository.AuthorRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

  @Mock
  private AuthorRepository repository;

  private AuthorService service;

  @BeforeEach
  void setUp() {
    service = new AuthorService(repository);
  }

  @Test
  public void testGetByIdExisting() {
    String id = "testId";
    Author author = new Author(id, "TestName", "TestLastName", List.of());
    when(repository.findById(id)).thenReturn(Optional.of(author));
    Author actualAuthor = service.getById(id);
    Assertions.assertNotNull(actualAuthor);
    Assertions.assertEquals(actualAuthor.getId(), id);
  }

  @Test
  public void testGetByIdNotExisting() {
    String id = "NotExistingId";
    when(repository.findById(id)).thenReturn(Optional.empty());
    Assertions.assertThrows(NoSuchElementException.class, () -> service.getById(id));
  }

  @Test
  public void addBookByAuthorIdNotExisting() {
    String id = "NotExistingId";
    when(repository.findById(id)).thenReturn(Optional.empty());
    Assertions.assertThrows(
        NoSuchElementException.class,
        () -> service.addBookByAuthorId(id, new Book("name", 2000)));
  }

  @Test
  public void addBookByAuthorId() {
    String id = "TestId";
    Book book = new Book("TestBook", 2000);
    Author author = new Author(id, "TestName", "TestLastName", new ArrayList<>());
    Author author2 = new Author(id, "TestName", "TestLastName", List.of(book));

    when(repository.findById(id)).thenReturn(Optional.of(author));

    when(repository.save(argThat(auth -> auth.getId().equals(id)))).thenReturn(author2);

    Author actualAuthor = service.addBookByAuthorId(id, book);
    Assertions.assertEquals(actualAuthor.getBooks().size(), 1);
    Assertions.assertEquals(actualAuthor.getBooks().get(0), author2.getBooks().get(0));
  }
}
