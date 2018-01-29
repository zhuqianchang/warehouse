package indi.zqc.warehouse.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import indi.zqc.warehouse.dao.MaterialDao;
import indi.zqc.warehouse.dao.StockDao;
import indi.zqc.warehouse.enums.ECodeType;
import indi.zqc.warehouse.exception.BusinessException;
import indi.zqc.warehouse.model.Material;
import indi.zqc.warehouse.model.Stock;
import indi.zqc.warehouse.model.condition.MaterialCondition;
import indi.zqc.warehouse.service.MaterialService;
import indi.zqc.warehouse.util.ECodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Title : MaterialServiceImpl.java
 * Package : indi.zqc.warehouse.service.impl
 * Description : 物料服务实现
 * Create on : 2018/1/28 13:42
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialDao materialDao;

    @Autowired
    private StockDao stockDao;

    @Override
    public int insertMaterial(Material material) {
        return materialDao.insertMaterial(material);
    }

    @Override
    public int deleteMaterial(String materialCode) {
        List<Stock> stocks = stockDao.selectMaterialStock(materialCode);
        if (stocks != null && stocks.size() > 0) {
            throw new BusinessException("物料存在仓库中，不能删除");
        }
        return materialDao.deleteMaterial(materialCode);
    }

    @Override
    public int updateMaterial(Material material) {
        //TODO 若有外键关联不能修改类型
        return materialDao.updateMaterial(material);
    }

    @Override
    public Material selectMaterial(String materialCode) {
        return materialDao.selectMaterial(materialCode);
    }

    @Override
    public Page<Material> selectMaterialPage(MaterialCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getNumPerPage());
        Page<Material> materials = materialDao.selectMaterialPage(condition);
        for (Material material : materials) {
            if (StringUtils.isNotBlank(material.getMaterialType())) {
                material.setMaterialTypeText(ECodeUtils.selectECodeText(ECodeType.MATERIAL.getKey(), material.getMaterialType()));
            }
        }
        return materials;
    }
}
