package com.zoeeasy.cloud.platform.controller.parkinfo;

import com.alibaba.fastjson.JSONObject;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.charge.appointrule.ParkingAppointRuleService;
import com.zoeeasy.cloud.charge.appointrule.dto.request.ParkingAppointRuleDeleteRequestDto;
import com.zoeeasy.cloud.charge.appointrule.dto.request.ParkingAppointRuleListGetByParkingInfoRequestDto;
import com.zoeeasy.cloud.charge.appointrule.dto.request.ParkingAppointRulePagedListGetByParkingInfoRequestDto;
import com.zoeeasy.cloud.charge.appointrule.dto.request.ParkingAppointRuleUpdateRequestDto;
import com.zoeeasy.cloud.charge.appointrule.dto.result.ParkingAppointRuleViewResultDto;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import com.zoeeasy.cloud.integration.appoint.AppointRuleManagerIntegrationService;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.*;
import com.zoeeasy.cloud.pms.park.dto.result.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 停车场控制器
 *
 * @author walkman
 */
@Api(value = "停车场Api", tags = "停车场Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/cloud/parkinfo")
@ApiVersion(1)
@ApiSigned
@RequiresPermissions(value = {
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_INFO_CREATE,
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_INFO_EDIT,
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_INFO_DELETE,
        PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_INFO_VIEW,
}, logical = Logical.OR)
@Slf4j
public class ParkingInfoController {

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private ParkingAppointRuleService parkingAppointRuleService;

    @Autowired
    private AppointRuleManagerIntegrationService appointRuleManagerIntegrationService;

    @ApiOperation(value = "添加停车场信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/add")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_INFO_CREATE)
    @AuditLog(title = "停车场Api", value = "添加停车场信息", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addParking(@RequestBody ParkingAddRequestDto requestDto) {
        return parkingInfoService.addParking(requestDto);
    }

    @ApiOperation(value = "删除停车场", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_INFO_DELETE)
    @AuditLog(title = "停车场Api", value = "删除停车场", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteParking(@RequestBody ParkingDeleteRequestDto requestDto) {
        return parkingInfoService.deleteParking(requestDto);
    }

    @ApiOperation(value = "修改停车场信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/update")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_INFO_EDIT)
    @AuditLog(title = "停车场Api", value = "修改停车场信息", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateParking(@RequestBody ParkingUpdateRequestDto requestDto) {
        return parkingInfoService.updateParking(requestDto);
    }

    @ApiOperation(value = "获取停车场", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingResultDto.class)
    @PostMapping(value = "/get")
    @RequiresPermissions(value = {
            PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_INFO_VIEW,
            PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_INFO_EDIT}, logical = Logical.OR)
    public ObjectResultDto<ParkingResultDto> getParking(@RequestBody ParkingGetRequestDto requestDto) {
        return parkingInfoService.getParking(requestDto);
    }

    @ApiOperation(value = "根据code获取停车场", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingResultDto.class)
    @PostMapping(value = "/getByCode")
    public ObjectResultDto<ParkingResultDto> getParkingInfoByCode(@RequestBody ParkingByCodeGetRequestDto requestDto) {
        return parkingInfoService.getParkingInfoByCode(requestDto);
    }

    @ApiOperation(value = "分页获取停车场列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingListResultDto.class)
    @PostMapping(value = "/pagelist")
    public PagedResultDto<ParkingListResultDto> getParkingPagedList(@RequestBody ParkingQueryPagedRequestDto requestDto) {
        return parkingInfoService.getParkingPagedList(requestDto);
    }

    @ApiOperation(value = "获取停车场列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingListGetResultDto.class)
    @PostMapping(value = "/list")
    public ListResultDto<ParkingListGetResultDto> getParkingList(@RequestBody ParkingListGetRequestDto requestDto) {
        return parkingInfoService.getParkingList(requestDto);
    }

    /**
     * 维护停车场预定规则
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "维护停车场预定规则", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/addappointrule")
    @AuditLog(title = "停车场Api", value = "维护停车场预定规则", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto addParkingAppointRule(@RequestBody ParkingAppointRuleUpdateRequestDto requestDto) {
        return appointRuleManagerIntegrationService.maintainParkingAppointRule(requestDto);
    }

    /**
     * 删除停车场预定规则
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "删除停车场预定规则", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/deleteappointrule")
    @AuditLog(title = "停车场Api", value = "删除停车场预定规则", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    public ResultDto deleteParkingAppointRule(@RequestBody ParkingAppointRuleDeleteRequestDto requestDto) {
        return parkingAppointRuleService.deleteParkingAppointRule(requestDto);
    }

    /**
     * 查询停车场预约规则
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "查询停车场预约规则", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingAppointRuleViewResultDto.class)
    @PostMapping(value = "/appointrulelist")
    public ListResultDto<ParkingAppointRuleViewResultDto> getAppointRuleListByParkingInfo(@RequestBody ParkingAppointRuleListGetByParkingInfoRequestDto requestDto) {
        return parkingAppointRuleService.getAppointRuleListByParkingInfo(requestDto);
    }

    /**
     * 根据停车场查预约规则
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "根据停车场查收费规则信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkingAppointRuleViewResultDto.class)
    @PostMapping(value = "/appointrulelistpage")
    public PagedResultDto<ParkingAppointRuleViewResultDto> getAppointRulePagedListRuleByParkingInfo(@RequestBody ParkingAppointRulePagedListGetByParkingInfoRequestDto requestDto) {
        return parkingAppointRuleService.getAppointRulePagedListRuleByParkingInfo(requestDto);
    }

    @ApiOperation(value = "申请停车场上下线", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/apply")
    @AuditLog(title = "停车场Api", value = "申请停车场上下线", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto applyRunStatus(@RequestBody ApplyRunStatusRequestDto requestDto) {
        return parkingInfoService.applyRunStatus(requestDto);
    }

    @ApiOperation(value = "撤销上下架申请", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/repealApply")
    @AuditLog(title = "停车场Api", value = "申请停车场上下线", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto repealApply(@RequestBody RepealApplyRequestDto requestDto) {
        return parkingInfoService.repealApply(requestDto);
    }

    @ApiOperation(value = "修改可用车位", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/updateLotAvailable")
    @AuditLog(title = "停车场Api", value = "修改可用车位", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    public ResultDto updateLotAvailable(@RequestBody LotAvailableUpdateRequestDto requestDto) {
        return parkingInfoService.updateLotAvailable(requestDto);
    }

    @ApiOperation(value = "获取二维码地址", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ParkQRCodeResultDto.class)
    @PostMapping(value = "/getQRCodeUrl")
    public ObjectResultDto<ParkQRCodeResultDto> getQRCodeUrl(@RequestBody ParkQRCodeRequestDto requestDto) {
        return parkingInfoService.getQRCodeUrl(requestDto);
    }

    /**
     * 获取二维码图片
     *
     * @param accessToken
     * @param request
     * @return
     */
    public static String getMiNiQR(String accessToken, HttpServletRequest request, String parkingCode, String fullName) {
        String photoUrl = "G://photo";
        String codeUrl = photoUrl + "/" + fullName + StringUtils.getUUID() + ".png ";
        String QRCodeUrl = "weChatQRCode" + StringUtils.getUUID() + ".png ";
        try {
            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            JSONObject paramJson = new JSONObject();
            paramJson.put("scene", parkingCode);
            paramJson.put("path", "pages/index/index");
            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            OutputStream os = new FileOutputStream(new File(codeUrl));
            int len;
            byte[] arr = new byte[1024];
            while ((len = bis.read(arr)) != -1) {
                os.write(arr, 0, len);
                os.flush();
            }
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return QRCodeUrl;
    }

    /**
     * 微信小程序接收二维码
     *
     * @param request
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "微信小程序接收二维码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/getWeChatQRCode")
    public List<String> weChatQRCode(HttpServletRequest request) throws IOException {
        ParkingListGetRequestDto parkingListGetRequestDto = new ParkingListGetRequestDto();
        List<String> list = new ArrayList<>();
        ObjectResultDto<WeChatSmallAppQrcodeResultDto> token = parkingInfoService.getToken();
        if (token.getData() != null) {
            ListResultDto<ParkingListGetResultDto> parkingList = parkingInfoService.getParkingList(parkingListGetRequestDto);
            if (CollectionUtils.isNotEmpty(parkingList.getItems())) {
                int i = 0;
                for (ParkingListGetResultDto parkingListGetResultDto : parkingList.getItems()) {
                    String QRCodeUrl = getMiNiQR(token.getData().getToken(), request, parkingListGetResultDto.getCode(), parkingListGetResultDto.getFullName());
                    list.add(QRCodeUrl + i++);
                }
            }
        }
        return list;
    }
}
