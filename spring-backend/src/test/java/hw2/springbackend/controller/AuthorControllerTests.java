package hw2.springbackend.controller;

import hw2.springbackend.domain.Author;
import hw2.springbackend.domain.Book;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.MapPropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(initializers = AuthorControllerTests.Initializer.class)
public class AuthorControllerTests {
  public static final int PORT = 27017;
  public static final String HTTP_LOCALHOST_8080 = "http://localhost:8080";

  public static class Initializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
      MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0").withExposedPorts(PORT);
      mongoDBContainer.start();

      Map<String, Object> properties = new HashMap<>();
      properties.put("spring.data.mongodb.host", mongoDBContainer.getContainerIpAddress());
      properties.put("spring.data.mongodb.port", mongoDBContainer.getMappedPort(PORT));
      applicationContext.getEnvironment().getPropertySources()
                        .addLast(new MapPropertySource("TestConfigProperties", properties));
    }
  }

  @Autowired
  private RestTemplateBuilder restTemplateBuilder;

  @Test
  public void testAddAuthor() {
    RestTemplate restTemplate = restTemplateBuilder.build();
    Author author = new Author(null, "Name", "LastName", null);

    Author actualAuthor = restTemplate.postForEntity(HTTP_LOCALHOST_8080 + "/author", author, Author.class).getBody();

    Assertions.assertNotNull(actualAuthor);
    Assertions.assertEquals(actualAuthor.getFirstName(), author.getFirstName());
    Assertions.assertEquals(actualAuthor.getLastName(), author.getLastName());
    Assertions.assertEquals(actualAuthor.getBooks(), List.of());
  }

  @Test
  public void testAllAuthors() {
    RestTemplate restTemplate = restTemplateBuilder.build();
    Author author1 = new Author(null, "Name1", "LastName1", null);
    Author author2 = new Author(null, "Leo", "Tolstoy", List.of(new Book("War and Peace", 1867)));

    restTemplate.postForEntity(HTTP_LOCALHOST_8080 + "/author", author1, Author.class).getBody();
    restTemplate.postForEntity(HTTP_LOCALHOST_8080 + "/author", author2, Author.class).getBody();
    ParameterizedTypeReference<List<Author>> typeReference = new ParameterizedTypeReference<>() {
    };
    List<Author> authors =
        restTemplate.exchange(HTTP_LOCALHOST_8080 + "/author", HttpMethod.GET, null, typeReference).getBody();

    Assertions.assertNotNull(authors);
    Assertions.assertEquals(authors.size(), 2);
  }
  //TODO test of book insertion
}
