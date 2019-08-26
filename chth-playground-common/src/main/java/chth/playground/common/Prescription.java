package chth.playground.common;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: 程泰恒
 * @date: 2019/5/23 11:39
 */

@Document("complexModel")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class
Prescription implements Serializable {

    private static final long serialVersionUID = -8478829257915258208L;

    /**
     * 文档ID
     */
    @Field("body._id")
    private String id;

    /**
     * 处方ID
     */
    @Field("body.prescriptionId")
    private String prescriptionId;

    /**
     * 组织ID
     */
    @Field("body.organizationId")
    private String organizationId;

    /**
     * 组织名称
     */
    @Field("body.organizationName")
    private String organizationName;

    /**
     * 药房ID
     */
    @Field("body.pharmacyId")
    private String pharmacyId;

    /**
     * 药房名称
     */
    @Field("body.pharmacyName")
    private String pharmacyName;

    /**
     * 患者ID
     */
    @Field("body.patientId")
    private String patientId;

    /**
     * 患者名称
     */
    @Field("body.patientName")
    private String patientName;

    /**
     * 患者性别
     */
    @Field("body.patientSex")
    private String patientSex;

    /**
     * 科室名称
     */
    @Field("body.departmentName")
    private String departmentName;

    /**
     * 医生姓名
     */
    @Field("body.doctorName")
    private String doctorName;

    /**
     * 药师姓名
     */
    @Field("body.pharmacistName")
    private String pharmacistName;

    /**
     * 疾病代码 (多个)
     */
    @Field("body.icdCodes")
    private List<String> icdCodes;

    /**
     * 备注信息
     */
    @Field("body.advice")
    private String advice;

    /**
     * 花费
     */
    @Field("body.cost")
    private Long cost;

    /**
     * 有效天数
     */
    @Field("body.effectiveDays")
    private Long effectiveDays;

    /**
     * 处方状态
     */
    @Field("body.status")
    private Integer status;


    /**
     * 药品列表
     */
    @Field("body.drugList")
    private List<DrugModel> drugList;
    /**
     * 处方开具时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Field("body.createTime")
    private Date createTime;

}
