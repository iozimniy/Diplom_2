package site.nomoreparties.stellarburgers.user;


public class ChangeData {
    private String name;
    private String email;

    public ChangeData() {
    }

    public ChangeData(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static ChangeData changeEmail(User user) {
        return new ChangeData(user.getName(), DataGenerator.generateRandomEmail());
    }

    public static ChangeData changeName(User user) {
        return new ChangeData(DataGenerator.generateRandomName(), user.getEmail());
    }

    public static ChangeData changeEmailAndName() {
        return new ChangeData(DataGenerator.generateRandomName(), DataGenerator.generateRandomEmail());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
