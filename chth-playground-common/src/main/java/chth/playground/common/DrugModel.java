package chth.playground.common;

/**
 * @author: 程泰恒
 * @date: 2019/5/23 11:42
 */

import lombok.*;

import java.io.Serializable;

/**
 * 药品
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrugModel implements Serializable {


    /**
     * 主键ID
     */
    private String id;

    /**
     * 处方ID
     */
    private String pId;

    /**
     * 编码
     */
    private String drugCode;

    /**
     * 名称
     */
    private String drugName;

    /**
     * 数量
     */
    private Integer drugCount;

    /**
     * 单价
     */
    private Long drugPrice;

    /**
     * 包装形式
     */
    private String drugSpec;

    /**
     * 剂型
     */
    private String formCode;

    /**
     * 总计服药数
     */
    private String totalDose;

    /**
     * 用药方式
     */
    private String routeCode;

    /**
     * 用药频率
     */
    private String rate;

    /**
     * 用药频率单位
     */
    private String rateUnit;

    /**
     * 用药量
     */
    private String dose;

    /**
     * 用药量单位
     */
    private String doseUnit;

}

