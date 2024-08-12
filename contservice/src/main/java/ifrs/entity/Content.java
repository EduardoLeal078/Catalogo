package ifrs.entity;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;
    private String title;
    private String description;
    private String contType;
    private String contData;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getContType() {
        return contType;
    }
    public void setContType(String contType) {
        this.contType = contType;
    }
    public String getContData() {
        return contData;
    }
    public void setContData(String contData) {
        this.contData = contData;
    }
}
