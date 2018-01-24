package indi.zqc.warehouse.controller;

import indi.zqc.warehouse.enums.DWZCallbackType;
import indi.zqc.warehouse.enums.DWZStatusCode;
import indi.zqc.warehouse.model.Common;
import indi.zqc.warehouse.model.DWZResult;
import indi.zqc.warehouse.util.SecurityContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Title : BaseController.java
 * Package : indi.zqc.warehouse.controller
 * Description : 基础控制器
 * Create on : 2018/1/22 19:40
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class BaseController {

    /**
     * 操作模式（增、改、查）
     */
    protected static final String PATTERN = "pattern";

    protected static final String PATTERN_ADD = "add";

    protected static final String PATTERN_EDIT = "edit";

    protected static final String PATTERN_VIEW = "view";

    protected static final String NAVTABID = "navTabId";

    protected static final String PAGE = "page";

    /**
     * 创建信息
     *
     * @param common
     */
    public void setCreateInfo(Common common) {
        common.setCreateDateTime(new Date());
        common.setCreateUser(SecurityContextUtils.getCurrentUserCode());
        this.setModifyInfo(common);
    }

    /**
     * 修改信息
     *
     * @param common
     */
    public void setModifyInfo(Common common) {
        common.setModifyDateTime(new Date());
        common.setModifyUser(SecurityContextUtils.getCurrentUserCode());
    }

    public String getForwardUrl(HttpServletRequest request) {
        String requestUrl = request.getServletPath();
        String[] url = requestUrl.split("/");
        String forwardUrl = "";
        if (url.length > 1) {
            forwardUrl = request.getContextPath() + "/" + url[1];
        }
        return forwardUrl;
    }

    public DWZResult navTabAjaxDone(String navTabId) {
        return getDWZJsonObject(DWZStatusCode.OK, "操作成功", navTabId, null, null);
    }

    public DWZResult dialogAjaxDone(String navTabId, String forwardUrl) {
        return getDWZJsonObject(DWZStatusCode.OK, "操作成功", navTabId, DWZCallbackType.CLOSECURRENT, forwardUrl);
    }

    private DWZResult getDWZJsonObject(DWZStatusCode statusCode, String message, String navTabId,
                                      DWZCallbackType callbackType, String forwardUrl) {
        if (callbackType == null) {
            callbackType = DWZCallbackType.EMPTY;
        }
        DWZResult dwzResult = new DWZResult();
        dwzResult.setStatusCode(statusCode.getKey());
        dwzResult.setMessage(message);
        dwzResult.setNavTabId(navTabId);
        dwzResult.setCallbackType(callbackType.getValue());
        dwzResult.setForwardUrl(forwardUrl);
        return dwzResult;
    }
}
