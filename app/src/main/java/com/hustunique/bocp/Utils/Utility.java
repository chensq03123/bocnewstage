package com.hustunique.bocp.Utils;

import com.boc.bocop.sdk.util.BOCOPParameters;

import java.net.URLEncoder;

/**
 * Created by chensq on 14-11-9.
 */
public class Utility {
    public static String encodeUrl(BOCOPParameters parameters) {
        if (parameters == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int loc = 0; loc < parameters.size(); loc++) {
            if (first){
                first = false;
            }
            else{
                sb.append("&");
            }
            String keyParam=parameters.getKey(loc);
            String valueParam=parameters.getValue(keyParam);
            if(valueParam==null){
                //Log.i("encodeUrl", "key:"+keyParam+" 's value is null");
            }
            else{
                sb.append(URLEncoder.encode(parameters.getKey(loc)) + "="
                        + URLEncoder.encode(parameters.getValue(loc)));
            }

        }
        return sb.toString();
    }
}
