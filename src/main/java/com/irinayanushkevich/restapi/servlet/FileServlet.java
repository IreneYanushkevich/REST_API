package com.irinayanushkevich.restapi.servlet;

import com.irinayanushkevich.restapi.model.File;
import com.irinayanushkevich.restapi.repository.FileRepository;
import com.irinayanushkevich.restapi.repository.hib_rep_impl.HibFileRepositoryImpl;
import com.irinayanushkevich.restapi.util.GsonUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FileServlet extends HttpServlet {

    private final FileRepository fileRep = new HibFileRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GsonUtil gsonUtil = new GsonUtil(response);
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        if (id == 0) {
            gsonUtil.writeToJson(fileRep.getAll());
        } else {
            gsonUtil.writeToJson(fileRep.getById(id));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GsonUtil gsonUtil = new GsonUtil(response);
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("file_name");
        String filePath = request.getParameter("file_path");
        gsonUtil.writeToJson(fileRep.create(new File(id, name, filePath)));
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GsonUtil gsonUtil = new GsonUtil(response);
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("file_name");
        String filePath = request.getParameter("file_path");
        gsonUtil.writeToJson(fileRep.update(new File(id, name, filePath)));
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GsonUtil gsonUtil = new GsonUtil(response);
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        fileRep.delete(id);
        gsonUtil.writeToJson("File with id=" + id + " was deleted");
    }
}
