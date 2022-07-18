package RestAssuredTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import propertyLoader.PropertyLoader;

import static io.restassured.RestAssured.given;

public class DeleteTest {

    private static RequestSpecification spec;


    @BeforeClass
    public static void initSpec(){
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(PropertyLoader.getProperty("baseUrl"))
//                .addFilter(new ResponseLoggingFilter())   // лог запроса
//                .addFilter(new RequestLoggingFilter())    // лог ответа
                .build();
    }

    @Test(description = "По delete запросу users ответ 204")
    public void test030() {
        given()
                .spec(spec)
                .delete("users/{id}", 2)
                .then()
                .statusCode(204);
    }
}
