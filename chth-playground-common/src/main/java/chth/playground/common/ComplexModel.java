package chth.playground.common;

import lombok.*;

/**
 * @author: 程泰恒
 * @date: 2019/5/28 14:26
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ComplexModel {

    private String version;

    private String hash;

    private String timeStamp;

    private Long index;

    private Long type;

    private PrescriptionModel body;

    private Long cost;

    private String advice;

}
