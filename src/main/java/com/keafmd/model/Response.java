package com.keafmd.model;

/**
 * Keafmd
 *
 * @ClassName: Response
 * @Description: 存放API返回的数据
 * @author: 牛哄哄的柯南
 * @date: 2021-08-05 9:26
 */
public class Response {
    private String result;//API返回码
    private String content;//API返回数据
    public Response(){}

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
