package chth.playground.common;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AbstractUser implements Serializable {

    private static final long serialVersionUID = -7708843845642679334L;

    String id;

}