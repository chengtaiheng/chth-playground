package chth.playground.front.repository;

import chth.playground.common.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author: 程泰恒
 * @date: 2019/5/29 9:42
 */

public interface PrescriptionRepository extends MongoRepository<Prescription, String> {

    public List<Prescription> findByCreateTimeBetweenAndStatus(Date start, Date end, Integer status);

    public List<Prescription> findByStatus(Integer status);

    public List<Prescription> findByCreateTimeBetween(Date start, Date end);

    public Optional<Prescription> findById(String pid);

}
