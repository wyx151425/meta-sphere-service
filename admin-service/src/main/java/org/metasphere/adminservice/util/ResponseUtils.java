package org.metasphere.adminservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.metasphere.adminservice.model.vo.resp.MSResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtils {

    public static void out(HttpServletResponse response, MSResponse msResponse) {
        ObjectMapper jsonMapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            jsonMapper.writeValue(response.getWriter(), msResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
