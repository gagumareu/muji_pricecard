package com.junghwan.springbootdeveloper.dto.upload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {

    private String uuid;
    private String fileName;
    private boolean img;
    private String s3Url;

    public String getLink(){
        if (img){
            return "s_" + uuid + "_" + fileName;
        }else {
            return uuid + "_" + fileName;
        }
    }
}