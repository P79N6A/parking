package com.zhuyitech.parking.pms.dto.result.car;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.result.FullAuditedEntityDto;
import com.scapegoat.infrastructure.jackson.annotation.ImageUrl;
import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 车型视图模型
 *
 * @author walkman
 */
@ApiModel(value = "CarBrandResultDto", description = "车型视图模型")
@Data
@EqualsAndHashCode(callSuper = false)
public class CarBrandResultDto extends FullAuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 全称
     */
    @ApiModelProperty("全称")
    private String fullName;

    /**
     * 首字母
     */
    @ApiModelProperty("首字母")
    private String initial;

    /**
     * 上级ID
     */
    @ApiModelProperty("上级ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    /**
     * LOGO
     */
    @ApiModelProperty("LOGO")
    @ImageUrl(value = Const.IMAGE_URL_PREFIX)
    private String logo;

    /**
     * 深度 1品牌 2子公司 3车型 4具体车型
     */
    @ApiModelProperty("深度")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer depth;

    /**
     * 销售状态
     */
    @ApiModelProperty("销售状态")
    private String saleState;

    /**
     * 价格
     */
    @ApiModelProperty("价格")
    private String price;

    /**
     * 年款
     */
    @ApiModelProperty("年款")
    private String yearType;

    /**
     * 生产状态
     */
    @ApiModelProperty("生产状态")
    private String productionState;

    /**
     * 车辆等级
     */
    @ApiModelProperty("车辆等级")
    private String sizeType;

    /**
     *
     basic	string	基本信息
     price	string	厂家指导价
     saleprice	string	商家报价
     warrantypolicy	string	保修政策
     vechiletax	string	车船税减免
     displacement	string	排量(L)
     gearbox	string	变速箱
     comfuelconsumption	string	综合工况油耗(L/100km)
     userfuelconsumption	string	网友油耗(L/100km)
     officialaccelerationtime100	string	官方0-100公里加速时间(s)
     testaccelerationtime100	string	实测0-100公里加速时间(s)
     maxspeed	string	最高车速(km/h)
     seatnum	string	乘员人数（区间）(个)
     body	string	车体
     color	string	车身颜色
     len	string	车长(mm)
     width	string	车宽(mm)
     height	string	车高(mm)
     wheelbase	string	轴距(mm)
     fronttrack	string	前轮距(mm)
     reartrack	string	后轮距(mm)
     weight	string	整备质量(kg)
     fullweight	string	满载质量(kg)
     mingroundclearance	string	最小离地间隙(mm)
     approachangle	string	接近角(°)
     departureangle	string	离去角(°)
     luggagevolume	string	行李厢容积(L)
     luggagemode	string	行李厢盖开合方式
     luggageopenmode	string	行李厢打开方式
     inductionluggage	string	感应行李厢
     doornum	string	车门数(个)
     tooftype	string	车顶型式
     hoodtype	string	车篷型式
     roofluggagerack	string	车顶行李箱架
     sportpackage	string	运动外观套件
     engine	string	发动机
     position	string	发动机位置
     model	string	发动机型号
     displacement	string	排量(L)
     displacementml	string	排量(mL)
     intakeform	string	进气形式
     cylinderarrangetype	string	气缸排列型式
     cylindernum	string	气缸数(个)
     valvetrain	string	每缸气门数(个)
     valvestructure	string	气门结构
     compressionratio	string	压缩比
     bore	string	缸径(mm)
     stroke	string	行程(mm)
     maxhorsepower	string	最大马力(Ps)
     maxpower	string	最大功率(kW)
     maxpowerspeed	string	最大功率转速(rpm)
     maxtorque	string	最大扭矩(Nm)
     maxtorquespeed	string	最大扭矩转速(rpm)
     fueltype	string	燃料类型
     fuelgrade	string	燃油标号
     fuelmethod	string	供油方式
     fueltankcapacity	string	燃油箱容积(L)
     cylinderheadmaterial	string	缸盖材料
     cylinderbodymaterial	string	缸体材料
     environmentalstandards	string	环保标准
     startstopsystem	string	启停系统
     gearbox	string	变速箱
     gearbox	string	变速箱
     shiftpaddles	string	换挡拨片
     chassisbrake	string	底盘制动
     bodystructure	string	车体结构
     powersteering	string	转向助力
     frontbraketype	string	前制动类型
     rearbraketype	string	后制动类型
     parkingbraketype	string	驻车制动类型
     drivemode	string	驱动方式
     airsuspension	string	空气悬挂
     adjustablesuspension	string	可调悬挂
     frontsuspensiontype	string	前悬挂类型
     rearsuspensiontype	string	后悬挂类型
     centerdifferentiallock	string	中央差速器锁
     safe	string	安全配置
     airbagdrivingposition	string	驾驶位安全气囊
     airbagfrontpassenger	string	副驾驶位安全气囊
     airbagfrontside	string	前排侧安全气囊
     airbagfronthead	string	前排头部气囊(气帘)
     airbagknee	string	膝部气囊
     airbagrearside	string	后排侧安全气囊
     airbagrearhead	string	后排头部气囊(气帘)
     safetybeltprompt	string	安全带未系提示
     safetybeltlimiting	string	安全带限力功能
     safetybeltpretightening	string	安全带预收紧功能
     frontsafetybeltadjustment	string	前安全带调节
     rearsafetybelt	string	后排安全带
     tirepressuremonitoring	string	胎压监测装置
     zeropressurecontinued	string	零压续行(零胎压继续行驶)
     centrallocking	string	中控门锁
     childlock	string	儿童锁
     remotekey	string	遥控钥匙
     keylessentry	string	无钥匙进入系统
     keylessstart	string	无钥匙启动系统
     engineantitheft	string	发动机电子防盗
     wheel	string	车轮
     fronttiresize	string	前轮胎规格
     reartiresize	string	后轮胎规格
     sparetiretype	string	备胎类型
     hubmaterial	string	轮毂材料
     drivingauxiliary	string	行车辅助
     abs	string	刹车防抱死(ABS)
     ebd	string	电子制动力分配系统(EBD)
     brakeassist	string	刹车辅助(EBA/BAS/BA/EVA等)
     tractioncontrol	string	牵引力控制(ASR/TCS/TRC/ATC等)
     esp	string	动态稳定控制系统（ESP）
     eps	string	随速助力转向调节(EPS)
     automaticparking	string	自动驻车
     hillstartassist	string	上坡辅助
     hilldescent	string	陡坡缓降
     frontparkingradar	string	泊车雷达(车前)
     reversingradar	string	倒车雷达(车后)
     reverseimage	string	倒车影像
     panoramiccamera	string	全景摄像头
     cruisecontrol	string	定速巡航
     adaptivecruise	string	自适应巡航
     gps	string	GPS导航系统
     automaticparkingintoplace	string	自动泊车入位
     ldws	string	车道偏离预警系统
     activebraking	string	主动刹车/主动安全系统
     integralactivesteering	string	整体主动转向系统
     nightvisionsystem	string	夜视系统
     blindspotdetection	string	盲点检测
     doormirror	string	门窗/后视镜
     openstyle	string	开门方式
     electricwindow	string	电动车窗
     uvinterceptingglass	string	防紫外线/隔热玻璃
     privacyglass	string	隐私玻璃
     antipinchwindow	string	电动窗防夹功能
     skylightopeningmode	string	天窗开合方式
     skylightstype	string	天窗型式
     rearwindowsunshade	string	后窗遮阳帘
     rearsidesunshade	string	后排侧遮阳帘
     rearwiper	string	后雨刷器
     sensingwiper	string	感应雨刷
     electricpulldoor	string	电动吸合门
     rearmirrorwithturnlamp	string	后视镜带侧转向灯
     externalmirrormemory	string	外后视镜记忆功能
     externalmirrorheating	string	外后视镜加热功能
     externalmirrorfolding	string	外后视镜电动折叠功能
     externalmirroradjustment	string	外后视镜电动调节
     rearviewmirrorantiglare	string	内后视镜防眩目功能
     sunvisormirror	string	遮阳板化妆镜
     light	string	灯光
     headlighttype	string	前大灯类型
     optionalheadlighttype	string	选配前大灯类型
     headlightautomaticopen	string	前大灯自动开闭
     headlightautomaticclean	string	前大灯自动清洗功能
     headlightdelayoff	string	前大灯延时关闭
     headlightdynamicsteering	string	前大灯随动转向
     headlightilluminationadjustment	string	前大灯照射范围调整
     headlightdimming	string	会车前灯防眩目功能
     frontfoglight	string	前雾灯
     readinglight	string	阅读灯
     interiorairlight	string	车内氛围灯
     daytimerunninglight	string	日间行车灯
     ledtaillight	string	LED尾灯
     lightsteeringassist	string	转向辅助灯
     internalconfig	string	内部配置
     steeringwheelbeforeadjustment	string	方向盘前后调节
     steeringwheelupadjustment	string	方向盘上下调节
     steeringwheeladjustmentmode	string	方向盘调节方式
     steeringwheelmemory	string	方向盘记忆设置
     steeringwheelmaterial	string	方向盘表面材料
     steeringwheelmultifunction	string	多功能方向盘
     steeringwheelheating	string	方向盘加热
     computerscreen	string	行车电脑显示屏
     huddisplay	string	HUD抬头数字显示
     interiorcolor	string	内饰颜色
     rearcupholder	string	后排杯架
     supplyvoltage	string	车内电源电压
     seat	string	座椅
     sportseat	string	运动座椅
     seatmaterial	string	座椅材料
     seatheightadjustment	string	座椅高低调节
     driverseatadjustmentmode	string	驾驶座座椅调节方式
     auxiliaryseatadjustmentmode	string	副驾驶座椅调节方式
     driverseatlumbarsupportadjustment	string	驾驶座腰部支撑调节
     driverseatshouldersupportadjustment	string	驾驶座肩部支撑调节
     frontseatheadrestadjustment	string	前座椅头枕调节
     rearseatadjustmentmode	string	后排座椅调节方式
     rearseatreclineproportion	string	后排座位放倒比例
     rearseatangleadjustment	string	后排座椅角度调节
     frontseatcenterarmrest	string	前座中央扶手
     rearseatcenterarmrest	string	后座中央扶手
     seatventilation	string	座椅通风
     seatheating	string	座椅加热
     seatmassage	string	座椅按摩功能
     electricseatmemory	string	电动座椅记忆
     childseatfixdevice	string	儿童安全座椅固定装置
     thirdrowseat	string	第三排座椅
     entcom	string	娱乐通讯
     locationservice	string	定位互动服务
     bluetooth	string	蓝牙系统
     externalaudiointerface	string	外接音源接口
     builtinharddisk	string	内置硬盘
     cartv	string	车载电视
     speakernum	string	扬声器数量
     audiobrand	string	音响品牌
     dvd	string	DVD
     cd	string	CD
     consolelcdscreen	string	中控台液晶屏
     rearlcdscreen	string	后排液晶屏
     aircondrefrigerator	string	空调/冰箱
     airconditioningcontrolmode	string	空调控制方式
     tempzonecontrol	string	温度分区控制
     rearairconditioning	string	后排独立空调
     reardischargeoutlet	string	后排出风口
     airconditioning	string	空气调节/花粉过滤
     airpurifyingdevice	string	车内空气净化装置
     carrefrigerator	string	车载冰箱
     actualtest	string	实际测试
     accelerationtime100	string	加速时间（0—100km/h）(s)
     brakingdistance	string	制动距离（100—0km/h）(m)
     */
}
