
package il.ac.shenkar.todolistapi.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    

    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String userId;
    private String password;
    private List<ToDoListItem> toDoListItemList;

    public User() {
    }

    public User(String firstName, String middleName, String lastName, String email, String userId, String password, List<ToDoListItem> toDoListItemList) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.userId = userId;
        this.password = password;
        this.toDoListItemList = toDoListItemList;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

     public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(fetch = FetchType.EAGER, targetEntity = ToDoListItem.class, cascade = CascadeType.ALL, mappedBy= "userId")
    @Cascade(value=org.hibernate.annotations.CascadeType.ALL)
    public List<ToDoListItem> getToDoListItemList() {
        return toDoListItemList;
    }

    public void setToDoListItemList(List<ToDoListItem> toDoListItemList) {
        this.toDoListItemList = toDoListItemList;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        User user = (User) o;

        if (id != user.id)
            return false;
        if (!firstName.equals(user.firstName))
            return false;
        if (!middleName.equals(user.middleName))
            return false;
        if (!lastName.equals(user.lastName))
            return false;
        if (!email.equals(user.email))
            return false;
        if (!userId.equals(user.userId))
            return false;
        if (!password.equals(user.password))
            return false;
        return toDoListItemList.equals(user.toDoListItemList);
    }

    @Override public int hashCode() {
        int result = id;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + middleName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + toDoListItemList.hashCode();
        return result;
    }

    @Override public String toString() {
        return "User{" + "id=" + id + ", firstName='" + firstName + '\'' + ", middleName='" + middleName + '\'' + ", lastName='" + lastName + '\''
                + ", email='" + email + '\'' + ", userId='" + userId + '\'' + ", password='" + password + '\'' + ", toDoListItemList=" + toDoListItemList + '}';
    }
}
