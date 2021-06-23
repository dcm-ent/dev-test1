package com.dcm.jxls.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.dcm.jxls.model.Employee;

@Controller
@RequestMapping(value = "/report")
public class ExcelController {
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletContext servletContext = request.getServletContext();
		String templatePath = servletContext.getRealPath("WEB-INF/classes/report/template") + "/";
		
		List<Employee> employees = generateSampleEmployeeData();

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=" + "object_collection_output.xls");
		
	  //try(InputStream is = new FileInputStream(templatePath + "employee_template.xls")) {
		try(InputStream is = new FileInputStream(templatePath + "object_collection_template.xls")) {
	            Context context = new Context();
	            context.putVar("employees", employees);
	            JxlsHelper.getInstance().processTemplate(is, response.getOutputStream(), context);
	    }
	}

	@RequestMapping(value = "/downloadLocal", method = RequestMethod.GET)
	public void downloadLocal(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletContext servletContext = request.getServletContext();
		
		String templatePath = servletContext.getRealPath("WEB-INF/classes/report/template") + "/";
		String outPath = servletContext.getRealPath("output/excel") + "/";
		
		List<Employee> employees = generateSampleEmployeeData();
	    try(InputStream is = new FileInputStream(templatePath + "object_collection_template.xls")) {
	        try (OutputStream os = new FileOutputStream(outPath + "object_collection_output.xls")) {
	            Context context = new Context();
	            context.putVar("employees", employees);
	            JxlsHelper.getInstance().processTemplate(is, os, context);
	        }
	    }
	}

	private List<Employee> generateSampleEmployeeData() {
		List<Employee> employees = new ArrayList<>();
		Date now = new Date();
		
		employees.add(new Employee("test 1", now, 10000.0, 0.0));
		employees.add(new Employee("test 2", now, 20000.0, 1.0));
		employees.add(new Employee("test 3", now, 30000.0, 2.0));
		employees.add(new Employee("test 4", now, 40000.0, 3.0));
		employees.add(new Employee("Å×½ºÆ® 5", now, 50000.0, 4.0));
		
		return employees;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(MultipartHttpServletRequest req) {
		
		Iterator<String> fileNames = req.getFileNames();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			System.out.println(fileName);
			
			MultipartFile mf = req.getFile(fileName);
			String name = mf.getOriginalFilename();
			long size = mf.getSize();
			System.out.println(name + ": " + size);
		}
		
		return null;
	}
}
