package ua.stasyk.servletproject.models;


public class User {

    private Integer id;
    private String username;
    private String password;
    private Role role;
    private boolean blocked;

    public User(){

    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(Integer id,String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(Integer id,String username, String password,Boolean blocked){
        this.id = id;
        this.username = username;
        this.password = password;
        this.blocked=blocked;
    }

    public User(Integer id,String username, Boolean blocked){
        this.id = id;
        this.username = username;
        this.blocked=blocked;
    }

    public User(Integer id,String username, String password, Role role){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
