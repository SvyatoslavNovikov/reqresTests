package HttpURLConnectionTests;

import HttpURLConnection.Request;
import HttpURLConnection.RequestSpec;
import org.testng.Assert;
import org.testng.annotations.Test;
import reqresClasses.Users;
import org.testng.annotations.BeforeClass;
import propertyLoader.PropertyLoader;

import java.io.IOException;

public class ListUsersTests {

    private static Users usersListWithoutFilter;
    private static Users usersListWithFilter;

    @BeforeClass
    public static void precondition() throws IOException {
        RequestSpec spec = new RequestSpec()
                                .setBaseUrl(PropertyLoader.getProperty("baseUrl"))
                                .setProperties("Content-Type", "application/json")
                                .setConnectionTimeout(10000)
                                .setReadTimeout(10000);

        usersListWithoutFilter = new Request()
                                .spec(spec)
                                .get("users")
                                .extractBodyAs(Users.class);

        usersListWithFilter = new Request()
                                .spec(spec)
                                .get("users?page=2") // ToDo Сделать добавление значения в {параметры}
                                .extractBodyAs(Users.class);
    }

    @Test(description = "При запросе users без фильтрации параметр page равен 1")
    public void test001() {
        Assert.assertEquals(usersListWithoutFilter.getPage(), 1);
    }



    @Test(description = "При запросе users без фильтрации параметр per_page равен 6")
    public void test002() {
        Assert.assertEquals(usersListWithoutFilter.getPer_page(), 6);
    }

//    @Test(description = "При запросе users без фильтрации приходят первые 6 объектов отсортированные по id")
//    public void test003() {
//        Assert.assertEquals(usersListWithoutFilter.getData().size(), 6);
//        Assert.assertTrue(checkSort(usersListWithoutFilter));
//        Assert.assertEquals(getFirstId(usersListWithoutFilter), 1);
//    }

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

//    @Test(description = "При запросе users отдается корректное значение total")
//    public void test007() {
//        Assert.assertEquals(usersListWithFilter.getData().size(), 6);
//        Assert.assertTrue(checkSort(usersListWithFilter));
//        Assert.assertEquals(getFirstId(usersListWithFilter), 7);
//    }

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
}
