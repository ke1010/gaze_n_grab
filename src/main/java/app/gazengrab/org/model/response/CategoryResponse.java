package app.gazengrab.org.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    @Id
    private String id;
    private String name;
    private String iconUrl;
}
