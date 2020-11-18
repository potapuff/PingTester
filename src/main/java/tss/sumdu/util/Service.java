package tss.sumdu.util;

import tss.sumdu.UrlTesterApp;

import java.net.MalformedURLException;
import java.net.URL;

public class Service {
    private String url;
    private Integer code;
    private final String message;

    public Service(String url, Integer code, String message){
        setUrl(url);
        setCode(code);
        this.message = message;
    }

    private void setUrl(String url){
        try {
            url = Helpers.normalizeURL(url);
            new URL(UrlTesterApp.URL + url);
        }  catch (MalformedURLException e) {
            throw new ValidationError(e);
        }
        this.url = url;
    }


    private void setCode(Integer code){
       if (code < 100 || code > 999){
           throw new ValidationError("Response code must be between 100 and 999, Your:"+code);
       }
       this.code = code;
    }

    public String getUrl(){
        return url;
    }
    public Integer getCode(){
        return code;
    }
    public String getMessage(){
        return message;
    }
}
