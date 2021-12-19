package hw2.springbackend.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import hw2.springbackend.domain.Author;
import hw2.springbackend.domain.Book;
import hw2.springbackend.service.AuthorService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AuthorController.class)
public class AuthorControllerComponentTests {
  private final ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AuthorService service;

  @Test
  public void testAllAuthors() throws Exception {
    Author author1 = new Author(null, "Name1", "LastName1", null);
    Author author2 = new Author(null, "Leo", "Tolstoy", List.of(new Book("War and Peace", 1867)));

    given(service.all()).willReturn(List.of(author1, author2));
    MvcResult mvcResult = mockMvc.perform(get("/author"))
                                 .andExpect(status().isOk())
                                 .andReturn();

    TypeReference<List<Author>> typeReference = new TypeReference<>() {
    };

    String contentAsString = mvcResult.getResponse().getContentAsString();

    List<Author> actualResult = mapper.readValue(contentAsString, typeReference);

    Assertions.assertNotNull(actualResult);
    Assertions.assertEquals(actualResult.size(), 2);
  }

  @Test
  public void testGetAuthor() throws Exception {
    String id = "id1";
    Author author = new Author(id, "Leo", "Tolstoy", List.of(new Book("War and Peace", 1867)));

    given(service.getById(id)).willReturn(author);
    MvcResult mvcResult = mockMvc.perform(get("/author/" + id))
                                 .andExpect(status().isOk())
                                 .andReturn();

    TypeReference<Author> typeReference = new TypeReference<>() {
    };

    String contentAsString = mvcResult.getResponse().getContentAsString();

    Author actualResult = mapper.readValue(contentAsString, typeReference);

    Assertions.assertNotNull(actualResult);
    Assertions.assertEquals(actualResult.getId(), author.getId());
    Assertions.assertEquals(actualResult.getFirstName(), author.getFirstName());
    Assertions.assertEquals(actualResult.getLastName(), author.getLastName());
    Assertions.assertEquals(actualResult.getBooks().size(), 1);
  }

  @Test
  public void testDeleteExisting() throws Exception {
    String id = "id1";
    Author author = new Author(id, "Leo", "Tolstoy", List.of(new Book("War and Peace", 1867)));

    given(service.getById(id)).willReturn(author);
    MvcResult mvcResult = mockMvc.perform(get("/author/" + id))
                                 .andExpect(status().isOk())
                                 .andReturn();

    TypeReference<Author> typeReference = new TypeReference<>() {
    };

    String contentAsString = mvcResult.getResponse().getContentAsString();

    Author actualResult = mapper.readValue(contentAsString, typeReference);

    Assertions.assertNotNull(actualResult);
    Assertions.assertEquals(actualResult.getId(), author.getId());
    Assertions.assertEquals(actualResult.getFirstName(), author.getFirstName());
    Assertions.assertEquals(actualResult.getLastName(), author.getLastName());
    Assertions.assertEquals(actualResult.getBooks().size(), 1);
  }

  //TODO tests of other request mappings
}
