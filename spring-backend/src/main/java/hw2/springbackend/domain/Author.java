package hw2.springbackend.domain;

import com.mongodb.lang.NonNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {
  @Id
  private String id;
  private String firstName;
  private String lastName;
  private List<Book> books;
}
