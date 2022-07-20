package RestAssuredTests;

import reqresClasses.create.Create;
import reqresClasses.create.CreateReqData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import propertyLoader.PropertyLoader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static io.restassured.RestAssured.given;

public class CreateTests {

    private static RequestSpecification spec;
    private static Create responseData;
    private static CreateReqData createData;

    @BeforeClass
    public static void initSpec(){
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(PropertyLoader.getProperty("baseUrl"))
                .addFilter(new ResponseLoggingFilter())   // лог запроса
                .addFilter(new RequestLoggingFilter())    // лог ответа
                .build();
    }

    @Test(description = "По post запросу user ответ 201")
    public void test017() {
        createData = new CreateReqData()
                .setName("svyatoslav")
                .setJob("qa");

        responseData = given()
                .spec(spec)
                .body(createData)
                .post("users")
                .then()
                .statusCode(201)
                .extract().body().as(Create.class);

    }

    @Test(description = "По post запросу user возвращается значение name", dependsOnMethods = {"test017"})
    public void test018() {
        Assert.assertEquals(responseData.getName(), createData.getName());
    }

    @Test(description = "По post запросу user возвращается значение job", dependsOnMethods = {"test017"})
    public void test019() {
        Assert.assertEquals(responseData.getJob(), createData.getJob());

    }

//    @Test(description = "По post запросу user возвращается значение id")
//    public void test020() {
//    }

    @Test(description = "По post запросу user возвращается значение createdAt", dependsOnMethods = {"test017"})
    public void test021() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmX");
        Assert.assertEquals(dateFormat.format(responseData.getCreatedAt()),
                            dateFormat.format(new Date()));
    }

}
