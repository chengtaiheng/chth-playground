package chth.playground.common;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends AbstractUser implements Serializable {

    private static final long serialVersionUID = -6950004503601652555L;

    private String userName;

    private String password;

    private Boolean admin;

    public User(String id, String userName, String password, Boolean admin) {
        super(id);
        this.userName = userName;
        this.password = password;
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                " id='" + id + '\'' +
                ", admin=" + admin +
                ", password='" + password + '\'' +
                ",userName='" + userName + '\'' +
                '}';
    }
}