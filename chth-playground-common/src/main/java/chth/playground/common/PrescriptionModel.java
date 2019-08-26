package chth.playground.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
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
public class PrescriptionModel implements Serializable {

    private static final long serialVersionUID = -8478829257915258208L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 处方ID
     */
    private String prescriptionId;

    /**
     * 组织ID
     */
    private String organizationId;

    /**
     * 组织名称
     */
    private String organizationName;

    /**
     * 药房ID
     */
    private String pharmacyId;

    /**
     * 药房名称
     */
    private String pharmacyName;

    /**
     * 患者ID
     */
    private String patientId;

    /**
     * 患者名称
     */
    private String patientName;

    /**
     * 患者性别
     */
    private String patientSex;

    /**
     * 科室名称
     */
    private String departmentName;

    /**
     * 医生姓名
     */
    private String doctorName;

    /**
     * 药师姓名
     */
    private String pharmacistName;

    /**
     * 疾病代码 (多个)
     */
    private List<String> icdCodes;

    /**
     * 备注信息
     */
    private String advice;

    /**
     * 花费
     */
    private Long cost;

    /**
     * 有效天数
     */
    private Long effectiveDays;

    /**
     * 处方状态
     */
    private Integer status;


    /**
     * 药品列表
     */
    private List<DrugModel> drugList;

    /**
     * 处方开具时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")   //pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}
