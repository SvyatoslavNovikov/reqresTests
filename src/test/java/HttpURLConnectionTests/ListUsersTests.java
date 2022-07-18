package HttpURLConnectionTests;

import HttpURLConnection.Request;
import RestAssured.entities.Users;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import propertyLoader.PropertyLoader;

import java.io.IOException;

public class ListUsersTests {

    private static Users usersListWithoutFilter;
    private static Users usersListWithFilter;

    @BeforeClass
    public static void precondition() throws IOException {
        Request reqWithoutFilters = new Request()
                .given(PropertyLoader.getProperty("baseUrl").concat("users"))
                .get()
//                .extractBody().as(usersListWithFilter)
                ;
    }

//    @Test(description = "При запросе users без фильтрации параметр page равен 1")
    public void test001() {

    }

//    @Test(description = "При запросе users без фильтрации параметр per_page равен 6")
    public void test002() {

    }

//    @Test(description = "При запросе users без фильтрации приходят первые 6 объектов отсортированные по id")
    public void test003() {

    }

//    @Test(description = "При запросе users c фильтраций параметр page равен значению фильтра")
    public void test004() {

    }

//    @Test(description = "При запросе users с фильтрацией параметр per_page равен 6")
    public void test005() {

    }

//    @Test(description = "При запросе users с фильтраций приходят 6 объектов отсортированные по id")
    public void test006() {

    }

//    @Test(description = "При запросе users отдается корректное значение total")
    public void test007() {

    }

//    @Test(description = "При запросе users отдается корректное значение total_pages")
    public void test008() {

    }

//    @Test(description = "При запросе users в data передается корректное значение id")
    public void test009() {

    }

//    @Test(description = "При запросе users в data передается корректное значение email")
    public void test010() {

    }

//    @Test(description = "При запросе users в data передается корректное значение first_name")
    public void test011() {

    }

//    @Test(description = "При запросе users в data передается корректное значение last_name")
    public void test012() {

    }

//    @Test(description = "При запросе users в data передается корректное значение avatar")
    public void test013() {

    }
}
