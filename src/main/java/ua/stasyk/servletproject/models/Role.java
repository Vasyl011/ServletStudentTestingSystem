package ua.stasyk.servletproject.models;

public class Role {

    private Integer id;
    private String role;


    public Role(){

    }

    public Role (String role){
        this.role=role;
    }

    public Role(Integer id, String role) {
        this.id = id;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
