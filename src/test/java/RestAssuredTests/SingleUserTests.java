package RestAssuredTests;

import RestAssured.entities.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import propertyLoader.PropertyLoader;

import static io.restassured.RestAssured.given;

public class SingleUserTests {

    private static RequestSpecification spec;
    private static User user;

    @BeforeClass
    public static void initSpec() {
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(PropertyLoader.getProperty("baseUrl"))
//                .addFilter(new ResponseLoggingFilter())   // лог запроса
//                .addFilter(new RequestLoggingFilter())    // лог ответа
                .build();

        user = given()
                .spec(spec)
                .get("users/{id}", 9)
                .then()
                .extract().body().as(User.class);
    }

    @Test(description = "По запросу user с id отдается корректное значение data")
    public void test014_1() {
        Assert.assertEquals(user.getData().getId(), 9);
    }

    @Test(description = "По запросу user с id отдается корректное значение data")
    public void test014_2() {
        Assert.assertEquals(user.getData().getFirst_name(), "Tobias");
    }

    @Test(description = "По запросу user с id отдается корректное значение data")
    public void test014_3() {
        Assert.assertEquals(user.getData().getLast_name(), "Funke");
    }

    @Test(description = "По запросу user с id отдается корректное значение data")
    public void test014_4() {
        Assert.assertEquals(user.getData().getEmail(), "tobias.funke@reqres.in");
    }

    @Test(description = "По запросу user с id отдается корректное значение data")
    public void test014_5() {
        Assert.assertEquals(user.getData().getAvatar(), "https://reqres.in/img/faces/9-image.jpg");
    }

    @Test(description = "По запросу user с id отдается корректное значение support")
    public void test015_1() {
        Assert.assertEquals(user.getSupport().getUrl(), "https://reqres.in/#support-heading");
    }

    @Test(description = "По запросу user с id отдается корректное значение support")
    public void test015_2() {
        Assert.assertEquals(user.getSupport().getText(), "To keep ReqRes free, contributions towards server costs are appreciated!");
    }

    @Test(description = "По запросу user с некорректным id ответ 404")
    public void test016() {
        given()
                .spec(spec)
                .get("users/{id}", 23)
                .then()
                .statusCode(404);
    }

}
