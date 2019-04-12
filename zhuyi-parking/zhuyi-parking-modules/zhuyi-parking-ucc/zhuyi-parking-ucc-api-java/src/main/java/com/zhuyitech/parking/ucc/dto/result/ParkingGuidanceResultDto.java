package com.zhuyitech.parking.ucc.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ParkingGuidanceResultDto
 * @Description TODO
 * @Author qhxu
 * @Date 2019/3/19 18:03
 * @Version1.0
 **/
@Data
public class ParkingGuidanceResultDto extends BaseDto {
    /**
     * {
     *  "code":"编码",
     *  "message":"结果"，
     * “afterURL”:”停车图片URL”，
     * “planeURL”:”车库平面图URL”,
     * "path":[
     *   {"x":"y"},
     *   {"x":"y"},
     *   {"x":"y"},
     *   {"x":"y"}
     *   ]
     * }
     */

    private Integer code;

    private String message;

    /**
     * 长度
     */
    private Long len;

    /**
     * path
     */
    private List path;

    /**
     * 停车图片URL
     */
    private String backURL;

    /**
     * 车库平面图URL
     */
    private String planeURL;
}
