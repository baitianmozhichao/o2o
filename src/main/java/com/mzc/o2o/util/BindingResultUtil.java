package com.mzc.o2o.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 搜集hibernate validator的错误描述
 *
 * @auther: mzc
 */
public class BindingResultUtil {

    public static String bindResult2Str(BindingResult bindingResult){
        List<ObjectError> objectErrorList = bindingResult.getAllErrors();
        StringBuilder sb = new StringBuilder();
        for (ObjectError error: objectErrorList){
            sb.append(error.getDefaultMessage()+",");
        }
        return sb.toString();
    }
}
