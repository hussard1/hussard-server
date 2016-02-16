package com.hussard.web.base.bbs.validator;

import com.hussard.web.base.bbs.domain.Content;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by user on 2015-07-07.
 */
@Component
public class FileSizeValidator  implements Validator{

    private static long contentCapacity = 10000000;

    @Override
    public boolean supports(Class<?> clazz) {
        return Content.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors){

        Content content = (Content)target;
        if(!fileSizeCheck(content.getFileUpload())){
            errors.rejectValue("fileUpload", "bbs.validation.fileerror");
        }
    }

    public boolean fileSizeCheck(MultipartFile fileUpload[]){

        long fileSize = 0;
        boolean result = true;

        for(int i=0; i<fileUpload.length; i++) {
            fileSize = fileUpload[i].getSize();
            if (fileSize > contentCapacity) {
                result = false;
            }
        }
        return result;
    }
}
