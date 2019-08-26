package chth.playground.front.dao;

import chth.playground.common.DrugModel;

import java.util.List;

/**
 * @author: 程泰恒
 * @date: 2019/5/30 18:49
 */
public interface DrugDao {

    public void saveDrugList(String pid, List<DrugModel> list);

    public List<DrugModel> findByPrescriptionId(String pid);
}
