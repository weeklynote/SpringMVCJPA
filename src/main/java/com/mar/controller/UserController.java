package com.mar.controller;

import com.mar.dao.ResumeDao;
import com.mar.model.ResponseDTO;
import com.mar.model.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mar.utils.StringUtils.isEmpty;

/**
 * @Author: 刘劲
 * @Date: 2020/4/21 18:04
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ResumeDao resumeDao;
    public static List<String> tokens = new ArrayList<>(16);

    @ResponseBody
    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public ResponseDTO login(String name, String password){
        ResponseDTO responseDTO = new ResponseDTO();
        if (isEmpty(name)){
           responseDTO.setCode(999);
           responseDTO.setMessage("账号不能为空");
            return responseDTO;
        }
        if (isEmpty(password)){
            responseDTO.setCode(999);
            responseDTO.setMessage("密码不能为空");
            return responseDTO;
        }
        if ("admin".equals(name) && "admin".equals(password)){
            responseDTO.setCode(0);
            String token = UUID.randomUUID().toString();
            tokens.add(token);
            responseDTO.setMessage(token);
            return responseDTO;
        }else {
            responseDTO.setCode(999);
            responseDTO.setMessage("账号或密码错误");
            return responseDTO;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public ResponseDTO list(){
        ResponseDTO responseDTO = new ResponseDTO();
        final List<Resume> all = resumeDao.findAll();
        responseDTO.setCode(0);
        responseDTO.setData(all);
        return responseDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseDTO save(@RequestBody Resume resume){
        Resume save = resumeDao.save(resume);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(0);
        responseDTO.setData(save);
        return responseDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public ResponseDTO delete(Integer id){
        resumeDao.deleteById(id);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(0);
        return responseDTO;
    }
}
