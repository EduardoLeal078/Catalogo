package ifrs;
import ifrs.entity.Content;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
public class ContResourceTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.defaultParser = Parser.JSON;
    }

    @BeforeEach
    public void cleanDatabase() {
        // Limpa todos os conteúdos antes de cada teste
        given()
            .when()
            .delete("/cont")
            .then()
            .statusCode(204);

        // Adiciona um conteúdo inicial
        Content content = new Content();
        content.setTitle("Test Title");
        content.setDescription("Test Description");
        content.setContType("text");
        content.setContData("Test data");

        given()
            .contentType(ContentType.JSON)
            .body(content)
            .when()
            .post("/cont")
            .then()
            .statusCode(201);
    }

    @Test
    public void testAddContent() {
        Content content = new Content();
        content.setTitle("New Test Title");
        content.setDescription("New Test Description");
        content.setContType("text");
        content.setContData("New test data");

        given()
            .contentType(ContentType.JSON)
            .body(content)
            .when()
            .post("/cont")
            .then()
            .statusCode(201);
    }

    @Test
    public void testListAllContent() {
        given()
            .when()
            .get("/cont")
            .then()
            .statusCode(200)
            .body("$", hasSize(1));
    }

    @Test
    public void testRemoveContent() {
        // Adiciona um conteúdo para ser removido
        Content content = new Content();
        content.setTitle("Another Test Title");
        content.setDescription("Another Test Description");
        content.setContType("text");
        content.setContData("Another test data");

        int id = given()
            .contentType(ContentType.JSON)
            .body(content)
            .when()
            .post("/cont")
            .then()
            .statusCode(201)
            .extract()
            .path("id");

        // Remove o conteúdo
        given()
            .when()
            .delete("/cont/" + id)
            .then()
            .statusCode(204);

        // Verifica se o conteúdo foi removido
        given()
            .when()
            .get("/cont")
            .then()
            .statusCode(200)
            .body("$", hasSize(1));  // Alterar para refletir a quantidade de conteúdos esperados
    }
}
