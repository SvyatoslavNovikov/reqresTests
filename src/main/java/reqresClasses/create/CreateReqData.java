package reqresClasses.create;

public class CreateReqData {

    private String name;
    private String job;

    public CreateReqData setName(String name) {
        this.name = name;
        return this;
    }

    public CreateReqData setJob(String job) {
        this.job = job;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

}
