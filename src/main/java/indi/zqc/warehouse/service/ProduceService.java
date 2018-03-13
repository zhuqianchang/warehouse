package indi.zqc.warehouse.service;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.Produce;
import indi.zqc.warehouse.model.condition.ProduceCondition;

import javax.servlet.http.HttpServletResponse;

/**
 * Title : ProduceService.java
 * Package : indi.zqc.warehouse.service
 * Description : 生产服务
 * Create on : 2018/3/12 20:41
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface ProduceService {

    int insertProduce(Produce produce);

    int deleteProduce(String produceCode);

    int updateProduce(Produce produce);

    Produce selectProduce(String produceCode);

    Page<Produce> selectProducePage(ProduceCondition condition);

    void exportProduce(ProduceCondition condition, HttpServletResponse response);
}
