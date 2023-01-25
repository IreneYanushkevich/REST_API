package com.irinayanushkevich.restapi.rest;

import com.irinayanushkevich.restapi.model.File;
import com.irinayanushkevich.restapi.service.FileService;
import com.irinayanushkevich.restapi.util.GsonUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class FileRestControllerV1 extends HttpServlet {

    private final FileService fileService = new FileService();

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        java.io.File fileIO = getFile(request);
        Integer userId = Integer.parseInt(request.getHeader("user_id"));
        File fileRest = fileService.save(fileIO, userId);
        GsonUtil.writeToJson(response, fileRest);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer fileId = fileIdMapping(request);
        java.io.File fileIO = getFile(request);
        File file = fileService.update(fileIO, fileId);
        GsonUtil.writeToJson(response, file);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = fileIdMapping(request);
        fileService.delete(id);
        GsonUtil.writeToJson(response, "File with id=" + id + " was deleted");
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

    private java.io.File getFile(HttpServletRequest request) throws UnsupportedEncodingException {
        String pathForFiles = "src/main/resources/files/";
        int memMaxSize = 100 * 1024;
        int fileMaxSize = 100 * 1024;

        request.setCharacterEncoding("UTF-8");
        java.io.File fileIO = null;
        String fileName;
        DiskFileItemFactory disk = new DiskFileItemFactory();

        disk.setRepository(new java.io.File(pathForFiles));
        disk.setSizeThreshold(memMaxSize);
        ServletFileUpload upload = new ServletFileUpload(disk);
        upload.setSizeMax(fileMaxSize);

        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()) {
                    fileName = fileItem.getName();
                    if (fileName.lastIndexOf("\\") >= 0) {
                        fileIO = new java.io.File(pathForFiles + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        fileIO = new java.io.File(pathForFiles + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fileItem.write(fileIO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileIO;
    }
}
