package indi.zqc.warehouse.controller;

import indi.zqc.warehouse.model.DWZResult;
import indi.zqc.warehouse.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Title : LicenseController.java
 * Package : indi.zqc.warehouse.controller
 * Description : License
 * Create on : 2018/2/5 10:52
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Controller
@RequestMapping("/manage/license")
public class LicenseController extends BaseController {

    @Autowired
    private LicenseService licenseService;

    @RequestMapping("/index")
    public String license(Model model) {
        model.addAttribute("license", licenseService.selectLicense());
        return "license/license";
    }

    @RequestMapping("/save")
    @ResponseBody
    public DWZResult saveLicense(String limitDate, String key) {
        licenseService.saveLicense(limitDate, key);
        return ajaxDone();
    }
}
