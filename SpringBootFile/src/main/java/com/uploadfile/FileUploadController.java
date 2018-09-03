package com.uploadfile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * 文件上传
 */
@Controller
public class FileUploadController {

	/*
	 * 获取file.html页面
	 */
	@RequestMapping("file")
	public String file(){
		return "/file";
	}

	/**
	 * 实现文件上传 使用MultipartFile
	 * */
	@RequestMapping("fileUpload")
	@ResponseBody
	public String fileUpload(@RequestParam("fileName") MultipartFile file){
		if(file.isEmpty()){
			return "false";
		}
		//得到上传文件名
		String fileName = file.getOriginalFilename();
		int size = (int) file.getSize();
		System.out.println(fileName + "-->" + size);

		//定义上传路径
		String path = "/Users/dx/ff" ;
		File dest = new File(path + "/" + fileName);
		//判断文件父目录是否存在
		if(!dest.getParentFile().exists()){
			//创建路径
			dest.getParentFile().mkdir();
		}
		try {
			//保存文件
			file.transferTo(dest);
			return "true";
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
	}

/*
	 * 获取multifile.html页面
	 */
	@RequestMapping("multifile")
	public String multifile(){
		return "/multifile";
	}

	/**
	 * 实现多文件上传
	 * */

	//@ResponseBody为把该方法的返回值转化为JSON格式
	@GetMapping("multifileUpload")
	@ResponseBody
	public String multifileUpload(HttpServletRequest request){

		List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");

		if(files.isEmpty()){
			return "false";
		}

		String path = "F:/test" ;

		for(MultipartFile file:files){
			String fileName = file.getOriginalFilename();
			int size = (int) file.getSize();
			System.out.println(fileName + "-->" + size);

			if(file.isEmpty()){
				return "false";
			}else{
				File dest = new File(path + "/" + fileName);
				if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
					dest.getParentFile().mkdir();
				}
				try {
					file.transferTo(dest);
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "false";
				}
			}
		}
		return "true";
	}
}