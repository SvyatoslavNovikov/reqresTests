package RestAssured.entities.create;

import java.util.Date;

public class Create extends CreateReqData{

    private int id;
    private Date createdAt;

    public int getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

}
