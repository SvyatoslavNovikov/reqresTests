package RestAssured.entities;

import java.util.List;

public class Users {

    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<UserData> data;

    public Users setPage(int page) {
        this.page = page;
        return this;
    }

    public Users per_page(int per_page) {
        this.per_page = per_page;
        return this;
    }

    public Users total(int total) {
        this.total = total;
        return this;
    }

    public Users total_pages(int total_pages) {
        this.total_pages = total_pages;
        return this;
    }

    public int getPage() {
        return page;
    }

    public int getPer_page() {
        return per_page;
    }

    public int getTotal() {
        return total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<UserData> getData() {
        return data;
    }

}
