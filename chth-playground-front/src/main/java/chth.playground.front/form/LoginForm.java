package chth.playground.front.form;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author: 程泰恒
 * @date: 2019/5/7 17:02
 */

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm implements Serializable {

    @NotBlank
    @Size(min = 6, max = 10)
    private String userName;

    @NotBlank
    @Size(min = 8)
    private String password;

}
