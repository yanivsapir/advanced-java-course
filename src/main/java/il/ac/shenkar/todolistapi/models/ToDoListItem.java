
package il.ac.shenkar.todolistapi.models;


import javax.persistence.*;

@Entity
@Table(name="items")
public class ToDoListItem {

    private int id;
    private int userId;
    private String task;
    private String status;

    public ToDoListItem() {
    }

    public ToDoListItem(int userId, String task, String status) {
        this.userId = userId;
        this.task = task;
        this.status = status;
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
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "task")
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ToDoListItem that = (ToDoListItem) o;

        if (userId != that.userId)
            return false;
        if (!task.equals(that.task))
            return false;
        return status.equals(that.status);
    }

    @Override public int hashCode() {
        int result = userId;
        result = 31 * result + task.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }

    @Override public String toString() {
        return "ToDoListItem{" + "id=" + id + ", userId=" + userId + ", task='" + task + '\'' + ", status='" + status + '\'' + '}';
    }
}
