package indi.zqc.warehouse.service;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.Material;
import indi.zqc.warehouse.model.condition.MaterialCondition;

/**
 * Title : MaterialService.java
 * Package : indi.zqc.warehouse.service
 * Description : 物料服务
 * Create on : 2018/1/28 13:42
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface MaterialService {

    int insertMaterial(Material material);

    int deleteMaterial(String materialCode);

    int updateMaterial(Material material);

    Material selectMaterial(String materialCode);

    Page<Material> selectMaterialPage(MaterialCondition condition);
}
