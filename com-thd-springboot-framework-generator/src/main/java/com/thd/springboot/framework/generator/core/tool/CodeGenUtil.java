package com.thd.springboot.framework.generator.core.tool;

import com.alibaba.fastjson.JSONObject;
import com.thd.springboot.framework.generator.core.CodeGen;
import com.thd.springboot.framework.generator.core.dto.Table;
import com.thd.springboot.framework.generator.core.dto.TemplateData;
import com.thd.springboot.framework.generator.core.tableutil.TableUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * com.thd.springboot.framework.generator.core.tool.CodeGenUtil
 *
 * @author: wanglei62
 * @DATE: 2020/3/23 10:17
 **/
@Component
public class CodeGenUtil implements CodeGen {
	@Autowired
	private TemplateData templateData;
    @Autowired
    private TableUtil tableUtil;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 生成代码
     */
    public Table generator(String tableName) throws Exception{
        Table table = this.tableUtil.loadTable(tableName);
        return table;
    };

    public void createCode(String tableName,String templatePath,String targetPath) throws Exception{

        String charset = "utf-8";
        File templateFile = new File(templateData.getTemplateFolderPath() + templatePath);

        File templateFolder = templateFile.getParentFile();
		logger.info("模板:" + templateFile.getAbsolutePath());



        File targetFile = new File(templateData.getTargetFolderPath() + targetPath);

		logger.info("生成文件位置:" + targetFile.getAbsolutePath());
		File targetFolder = targetFile.getParentFile();
		if(!targetFolder.exists()){
			targetFolder.mkdirs();
		}
        //初使化FreeMarker配置
		Configuration configuration = new Configuration();
		//设置要解析的模板所在的目录，并加载模板文件
		configuration.setDirectoryForTemplateLoading(templateFolder);
		//设置编码格式
		configuration.setDefaultEncoding("utf-8");


        Template t = null;


		// 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
		// 否则会出现乱码

		t = configuration.getTemplate(templateFile.getName(),charset);

		if(targetFile == null){
			throw new Exception(" target path err !");
		}

		File outFile = new File(targetFile.getAbsolutePath());
		Writer out = null;
		try {
			if(charset == null){
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
			}else{
				out = new OutputStreamWriter(new FileOutputStream(outFile), charset);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		templateData.setTable(tableUtil.loadTable(tableName));
		try {

			System.err.println(JSONObject.toJSONString(templateData));
			t.process(templateData, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    };
}
