package indi.zqc.warehouse.dao;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.Produce;
import indi.zqc.warehouse.model.condition.ProduceCondition;
import org.apache.ibatis.annotations.Param;

/**
 * Title : ProduceDao.java
 * Package : indi.zqc.warehouse.dao
 * Description : 生产DAO
 * Create on : 2018/3/12 20:40
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface ProduceDao {

    int insertProduce(Produce produce);

    int deleteProduce(@Param("produceCode") String produceCode);

    int updateProduce(Produce produce);

    Produce selectProduce(@Param("produceCode") String produceCode);

    Page<Produce> selectProducePage(ProduceCondition condition);
}
