package com.irinayanushkevich.restapi.rest;

import com.irinayanushkevich.restapi.model.File;
import com.irinayanushkevich.restapi.service.FileService;
import com.irinayanushkevich.restapi.util.GsonUtil;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class FileRestControllerV1 extends HttpServlet {

    private final FileService fileService = new FileService();
    private final String pathForFiles = "src/main/webapp/files";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = fileIdMapping(request);
        if (id == 0) {
            List<File> files = fileService.getAll();
            GsonUtil.writeToJson(response, files);
        } else {
            File file = fileService.getById(id);
            GsonUtil.writeToJson(response, file);
        }
    }

    @Override
    //TODO: get the file from request and save to filepath
    //TODO: get user_id from request headers
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        DiskFileItemFactory disk = new DiskFileItemFactory();
        disk.setRepository(new java.io.File(pathForFiles));
        ServletFileUpload uploader = new ServletFileUpload(disk);

        String name = request.getParameter("file_name");
        //String filePath =
        ServletInputStream in = request.getInputStream();
        //Integer userId =
        File file = fileService.save(in, null, name);
        GsonUtil.writeToJson(response, file);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        ServletInputStream in = request.getInputStream();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("file_name");
        File file = fileService.update(in, id, name);
        GsonUtil.writeToJson(response, file);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        fileService.delete(id);
        GsonUtil.writeToJson(response,"File with id=" + id + " was deleted");
    }

    private Integer fileIdMapping(HttpServletRequest request) throws UnsupportedEncodingException {
        String urlPattern = "/api/v1/files/";
        request.setCharacterEncoding("UTF-8");
        String url = request.getRequestURL().toString();
        String id = url.substring(url.indexOf(urlPattern) + urlPattern.length());

        if (id.isBlank()) {
            return 0;
        } else {
            return Integer.parseInt(id);
        }
    }
}
