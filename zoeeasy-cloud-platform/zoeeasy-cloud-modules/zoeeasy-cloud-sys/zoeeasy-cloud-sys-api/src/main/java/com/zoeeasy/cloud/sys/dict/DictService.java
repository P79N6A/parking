package com.zoeeasy.cloud.sys.dict;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.sys.dict.dto.*;

/**
 * 字典管理服务
 *
 * @author walkman
 */
public interface DictService {

    /**
     * 获取字典类别列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<DictTypeResultDto> getDictTypeList(DictTypeListRequestDto requestDto);

    /**
     * 获取字典类别
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<DictTypeResultDto> getDictType(DictTypeGetRequestDto requestDto);

    /**
     * 检验字典类别是否可用
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<DictTypeCheckResultDto> checkDictType(DictTypeCheckRequestDto requestDto);

    /**
     * 添加字典类型
     *
     * @param requestDto
     * @return
     */
    ResultDto addDictType(DictTypeAddRequestDto requestDto);

    /**
     * 修改字典类型
     *
     * @param requestDto
     * @return
     */
    ResultDto updateDictType(DictTypeUpdateRequestDto requestDto);

    /**
     * 删除字典类型
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteDictType(DictTypeDeleteRequestDto requestDto);

    /**
     * 根据字典类型获取字典项列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<DictTypeItemListResultDto> getDictTypeItemList(DictTypeItemListRequestDto requestDto);

    /**
     * 根据字典项ID获取字典
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<DictItemResultDto> getDictItem(DictItemGetRequestDto requestDto);

    /**
     * 检验字典项是否可用
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<DictItemCheckResultDto> checkDictItem(DictItemCheckRequestDto requestDto);

    /**
     * 添加字典项
     *
     * @param requestDto
     * @return
     */
    ResultDto addDictItem(DictItemAddRequestDto requestDto);

    /**
     * 修改字典项
     *
     * @param requestDto
     * @return
     */
    ResultDto updateDictItem(DictItemUpdateRequestDto requestDto);

    /**
     * 删除字典项
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteDictItem(DictItemDeleteRequestDto requestDto);

}
