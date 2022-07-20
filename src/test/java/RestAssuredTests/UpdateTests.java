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

public class UpdateTests {

    private static RequestSpecification spec;
    private static Create putResponseData;
    private static CreateReqData putCreateData;
    private static Create patchResponseData;
    private static CreateReqData patchCreateData;
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmX");


    @BeforeClass
    public static void initSpec(){
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(PropertyLoader.getProperty("baseUrl"))
                .addFilter(new ResponseLoggingFilter())   // лог запроса
                .addFilter(new RequestLoggingFilter())    // лог ответа
                .build();
    }

    @Test(description = "По update запросу user ответ 200")
    public void test022() {
        putCreateData = new CreateReqData()
                .setName("svyatoslav")
                .setJob("qa");

        putResponseData = given()
                .spec(spec)
                .body(putCreateData)
                .put("users/{id}", 2)
                .then()
                .statusCode(200)
                .extract().body().as(Create.class);
    }

    @Test(description = "По update запросу user возвращается значение name", dependsOnMethods = "test022")
    public void test023() {
        Assert.assertEquals(putResponseData.getName(), putCreateData.getName());
    }

    @Test(description = "По update запросу user возвращается значение job", dependsOnMethods = "test022")
    public void test024() {
        Assert.assertEquals(putResponseData.getJob(), putCreateData.getJob());
    }

    @Test(description = "По update запросу user возвращается значение updateAt", dependsOnMethods = "test022")
    public void test025() {
        Assert.assertEquals(dateFormat.format(putResponseData.getCreatedAt()),
                dateFormat.format(new Date()));
    }

    @Test(description = "По patch запросу user ответ 200")
    public void test026() {
        patchCreateData = new CreateReqData()
                .setName("leonid")
                .setJob("superchild");

        patchResponseData = given()
                .spec(spec)
                .body(patchCreateData)
                .patch("users/{id}", 2)
                .then()
                .statusCode(200)
                .extract().body().as(Create.class);
    }

    @Test(description = "По patch запросу user возвращается значение name",dependsOnMethods = "test026")
    public void test027() {
        Assert.assertEquals(patchResponseData.getName(), patchCreateData.getName());
    }

    @Test(description = "По patch запросу user возвращается значение job", dependsOnMethods = "test026")
    public void test028() {
        Assert.assertEquals(patchResponseData.getJob(), patchCreateData.getJob());
    }

    @Test(description = "По patch запросу user возвращается значение updateAt")
    public void test029() {
        Assert.assertEquals(dateFormat.format(patchResponseData.getCreatedAt()),
                dateFormat.format(new Date()));
    }

}
