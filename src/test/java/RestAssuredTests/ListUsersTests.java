package RestAssuredTests;

import reqresClasses.Users;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import propertyLoader.PropertyLoader;

import static io.restassured.RestAssured.given;

public class ListUsersTests {

    private static RequestSpecification spec;
    private static Users usersListWithoutFilter;
    private static Users usersListWithFilter;

    @BeforeClass
    public static void initSpec(){
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(PropertyLoader.getProperty("baseUrl"))
                .addFilter(new ResponseLoggingFilter())   // лог запроса
                .addFilter(new RequestLoggingFilter())    // лог ответа
                .build();

        usersListWithoutFilter = given()
                .spec(spec)
                .get("users")
                .then()
                .extract().body().as(Users.class);

        usersListWithFilter = given()
                .spec(spec)
                .get("users?page={id}", 2)
                .then()
                .extract().body().as(Users.class);
    }

    @Test(description = "При запросе users без фильтрации параметр page равен 1")
    public void test001() {
        Assert.assertEquals(usersListWithoutFilter.getPage(), 1);
    }



    @Test(description = "При запросе users без фильтрации параметр per_page равен 6")
    public void test002() {
        Assert.assertEquals(usersListWithoutFilter.getPer_page(), 6);
    }

    @Test(description = "При запросе users без фильтрации приходят первые 6 объектов отсортированные по id")
    public void test003() {
        Assert.assertEquals(usersListWithoutFilter.getData().size(), 6);
        Assert.assertTrue(checkSort(usersListWithoutFilter));
        Assert.assertEquals(getFirstId(usersListWithoutFilter), 1);
    }

    @Test(description = "При запросе users c фильтраций параметр page равен значению фильтра")
    public void test004() {
        Assert.assertEquals(usersListWithFilter.getPage(), 2);
    }

    @Test(description = "При запросе users с фильтрацией параметр per_page равен 6")
    public void test005() {
        Assert.assertEquals(usersListWithFilter.getPer_page(), 6);
    }

    @Test(description = "При запросе users с фильтраций приходят 6 объектов отсортированные по id")
    public void test006() {

    }

    @Test(description = "При запросе users отдается корректное значение total")
    public void test007() {
        Assert.assertEquals(usersListWithFilter.getData().size(), 6);
        Assert.assertTrue(checkSort(usersListWithFilter));
        Assert.assertEquals(getFirstId(usersListWithFilter), 7);
    }

    @Test(description = "При запросе users отдается корректное значение total_pages")
    public void test008() {
        Assert.assertEquals(usersListWithFilter.getTotal_pages(), 2);
    }

    @Test(description = "При запросе users в data передается корректное значение id")
    public void test009() {
        Assert.assertEquals(usersListWithoutFilter.getData().get(4).getId(), 5);
    }

    @Test(description = "При запросе users в data передается корректное значение email")
    public void test010() {
        Assert.assertEquals(usersListWithoutFilter.getData().get(4).getEmail(), "charles.morris@reqres.in");
    }

    @Test(description = "При запросе users в data передается корректное значение first_name")
    public void test011() {
        Assert.assertEquals(usersListWithoutFilter.getData().get(4).getFirst_name(), "Charles");

    }

    @Test(description = "При запросе users в data передается корректное значение last_name")
    public void test012() {
        Assert.assertEquals(usersListWithoutFilter.getData().get(4).getLast_name(), "Morris");

    }

    @Test(description = "При запросе users в data передается корректное значение avatar")
    public void test013() {
        Assert.assertEquals(usersListWithoutFilter.getData().get(4).getAvatar(), "https://reqres.in/img/faces/5-image.jpg");
    }

    private Boolean checkSort(Users u) {
        boolean result = false;
        for(int i = 0; i < u.getData().size(); i++) {
            if (i + 1 == u.getData().size()) {
                result = true;
                break;
            }
            if (u.getData().get(i).getId() > u.getData().get(i+1).getId()) {
                result = false;
                break;
            }
        }
        return result;
    }

    private Integer getFirstId(Users u) {
        return u.getData().get(0).getId();
    }
}
