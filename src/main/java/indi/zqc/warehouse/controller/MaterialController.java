package indi.zqc.warehouse.controller;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.enums.ECodeType;
import indi.zqc.warehouse.model.DWZResult;
import indi.zqc.warehouse.model.Material;
import indi.zqc.warehouse.model.condition.MaterialCondition;
import indi.zqc.warehouse.service.MaterialService;
import indi.zqc.warehouse.util.ECodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Title : MaterialController.java
 * Package : indi.zqc.warehouse.controller
 * Description : 物料
 * Create on : 2018/1/28 14:54
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Controller
@RequestMapping(value = "/material/*")
public class MaterialController extends BaseController {

    @Autowired
    private MaterialService materialService;

    @RequestMapping("/list")
    public String materialList(Model model, MaterialCondition condition, String navTabId) {
        Page<Material> page = materialService.selectMaterialPage(condition);
        condition.setData(page);
        condition.setTotalCount(page.getTotal());
        model.addAttribute(PAGE, condition);
        model.addAttribute("materialTypes", ECodeUtils.selectECodeList(ECodeType.MATERIAL.getKey()));
        model.addAttribute(NAVTABID, navTabId);
        return "material/material_list";
    }

    @RequestMapping("/add")
    public String addMaterial(Model model, String navTabId) {
        model.addAttribute("materialTypes", ECodeUtils.selectECodeList(ECodeType.MATERIAL.getKey()));
        model.addAttribute(NAVTABID, navTabId);
        return "material/material_add";
    }

    @RequestMapping("/edit")
    public String editMaterial(Model model, String materialCode, String navTabId) {
        model.addAttribute("material", materialService.selectMaterial(materialCode));
        model.addAttribute("materialTypes", ECodeUtils.selectECodeList(ECodeType.MATERIAL.getKey()));
        model.addAttribute(NAVTABID, navTabId);
        return "material/material_add";
    }

    @RequestMapping("/view")
    public String viewMaterial(Model model, String materialCode, String navTabId) {
        model.addAttribute("material", materialService.selectMaterial(materialCode));
        model.addAttribute(NAVTABID, navTabId);
        return "material/material_view";
    }

    @RequestMapping("/save")
    @ResponseBody
    public DWZResult saveMaterial(Material material, String navTabId, HttpServletRequest request) {
        Material oldMaterial = materialService.selectMaterial(material.getMaterialCode());
        if (oldMaterial == null) {
            setCreateInfo(material);
            materialService.insertMaterial(material);
        } else {
            setModifyInfo(material);
            materialService.updateMaterial(material);
        }
        String forwardUrl = getForwardUrl(request) + "/material/list?navTabId=" + navTabId;
        return dialogAjaxDone(navTabId, forwardUrl);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public DWZResult deleteMaterial(String materialCode) {
        materialService.deleteMaterial(materialCode);
        return ajaxDone();
    }

    @RequestMapping("/verify")
    @ResponseBody
    public boolean verifyMaterialCode(String materialCode) {
        return materialService.selectMaterial(materialCode) == null;
    }

    @RequestMapping("/lookup")
    public String materialLookup(Model model, MaterialCondition condition) {
        Page<Material> page = materialService.selectMaterialPage(condition);
        condition.setData(page);
        condition.setTotalCount(page.getTotal());
        model.addAttribute(PAGE, condition);
        model.addAttribute("materialTypes", ECodeUtils.selectECodeList(ECodeType.MATERIAL.getKey()));
        return "material/material_lookup";
    }

}
