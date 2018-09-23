package me.zhouzhuo810.magpietvdemo.entity;

public class TestListEntity {
    private String name;
    private String sex;
    private String email;
    private String phone;

    public TestListEntity(String name, String sex, String email, String phone) {
        this.name = name;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
