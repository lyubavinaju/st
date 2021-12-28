
package entity;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Todolist {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("todolist")
    @Expose
    private List<Todo> todolist = new ArrayList<Todo>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Todo> getTodolist() {
        return todolist;
    }

    public void setTodolist(List<Todo> todolist) {
        this.todolist = todolist;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Todolist.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("todolist");
        sb.append('=');
        sb.append(((this.todolist == null)?"<null>":this.todolist));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.todolist == null)? 0 :this.todolist.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Todolist) == false) {
            return false;
        }
        Todolist rhs = ((Todolist) other);
        return (((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name)))&&((this.todolist == rhs.todolist)||((this.todolist!= null)&&this.todolist.equals(rhs.todolist))));
    }

}
