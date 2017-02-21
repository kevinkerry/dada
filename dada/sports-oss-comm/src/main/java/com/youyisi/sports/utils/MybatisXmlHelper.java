package com.youyisi.sports.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

public class MybatisXmlHelper {

	private String packageOutPath = "com.youyisi.sports.model.user";// 指定实体生成所在包的路径
	private String authorName = "shuye";// 作者名字
	private String tablename = "T_SPORT_USER";// 表名
	private String entityname = "User";// 实体名
	private String[] colnames; // 列名数组
	private String[] colTypes; // 列名类型数组
	private String idName;
	private int[] colSizes; // 列名大小数组
	private boolean f_util = false; // 是否需要导入包java.util.*
	private boolean f_sql = false; // 是否需要导入包java.sql.*

	// 数据库连接dev_setting dev_crawler
	private static final String URL = "jdbc:mysql://10.1.80.200:3306/sports";
	private static final String NAME = "root";
	private static final String PASS = "root";
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	/*
	 * 构造函数
	 */
	public MybatisXmlHelper() {
		// 创建连接
		Connection con = null;
		// 查要生成实体类的表
		String sql = "select * from " + tablename;
		PreparedStatement pStemt = null;
		try {
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			con = DriverManager.getConnection(URL, NAME, PASS);
			pStemt = con.prepareStatement(sql);
			ResultSetMetaData rsmd = pStemt.getMetaData();
			int size = rsmd.getColumnCount(); // 统计列
			colnames = new String[size];
			colTypes = new String[size];
			colSizes = new int[size];
			idName = rsmd.getColumnName(1);
			for (int i = 0; i < size; i++) {
				colnames[i] = rsmd.getColumnName(i + 1);
				colTypes[i] = rsmd.getColumnTypeName(i + 1);

				if (colTypes[i].equalsIgnoreCase("datetime")) {
					f_util = true;
				}
				if (colTypes[i].equalsIgnoreCase("image")
						|| colTypes[i].equalsIgnoreCase("text")) {
					f_sql = true;
				}
				colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
			}

			String content = buildDomain();
			String mybatisXml = buildXml();
			try {
				File directory = new File("");
				writeXml(mybatisXml,directory);
				writeDomain(content, directory);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



	private void writeXml(String mybatisXml, File directory) throws Exception {
		String outputPath = directory.getAbsolutePath()
				+ "/src/main/java/"
				+ this.packageOutPath.replace(".", "/") + "/"
				+ entityname + ".xml";
		createFile(directory.getAbsolutePath()
				+ "/src/main/java/"
				+ this.packageOutPath.replace(".", "/") + "/", outputPath);
		FileWriter fw = new FileWriter(outputPath);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(mybatisXml);
		pw.flush();
		pw.close();
		
	}
	
	private String getLastPackageName(){
		String[] names = packageOutPath.split("\\.");
		return names[names.length-1];
	}



	private void writeDomain(String content, File directory) throws IOException {
		String outputPath = directory.getAbsolutePath()
				+ "/src/main/java/"
				+ this.packageOutPath.replace(".", "/") + "/"
				+ entityname + ".java";
		createFile(directory.getAbsolutePath()
				+ "/src/main/java/" + this.packageOutPath.replace(".", "/")
				+ "/", outputPath);
		FileWriter fw = new FileWriter(outputPath);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(content);
		pw.flush();
		pw.close();
	}

	

	private void createFile(String path, String outputPath)
			throws IOException {
		File storagePath = new File(path);
		if (!storagePath.exists()) {
			storagePath.mkdirs();
		}
		File file = new File(outputPath);
		if (!file.exists()) {
			file.createNewFile();
		}
	}

	/**
	 * 功能：生成实体类主体代码
	 * 
	 * @param colnames
	 * @param colTypes
	 * @param colSizes
	 * @return
	 */
	private String buildDomain() {
		StringBuffer sb = new StringBuffer();
		sb.append("package " + this.packageOutPath + ";\r\n");
		sb.append("\r\n");
		// 判断是否导入工具包
		if (f_util) {
			sb.append("import java.util.Date;\r\n");
		}
		if (f_sql) {
			sb.append("import java.sql.*;\r\n");
		}

		// 注释部分
		sb.append("   /**\r\n");
		sb.append("    * " + entityname + " 实体类\r\n");
		sb.append("    * " + new Date() + " " + this.authorName + "\r\n");
		sb.append("    */ \r\n");
		// 实体部分
		sb.append("\r\n\r\npublic class " + entityname + " {\r\n");
		processAllAttrs(sb);// 属性
		processAllMethod(sb);// get set方法
		sb.append("}\r\n");

		// System.out.println(sb.toString());
		return sb.toString();
	}
	
	private String buildXml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n");
		sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\r\n");
		sb.append("<mapper namespace=\""+ this.packageOutPath + "."+entityname+"\" >\r\n");
		processResultMap(sb);
		processCommonSelect(sb);
		processGetById(sb);
		processQueryPage(sb);
		processDelete(sb);
		processSave(sb);
		processUpdate(sb);
		
		
		return sb.toString();
	}

	private void processUpdate(StringBuffer sb) {
		sb.append("<update id=\"update\" parameterType=\""+entityname+"\" >\r\n");
		sb.append("\tupdate sports."+tablename+" set ");
		for (int i = 0; i < colnames.length; i++) {
			if(!colnames[i].equalsIgnoreCase("id")){
				if(i==colnames.length-1){
					sb.append(colnames[i]+"="+"#{"+firstLowerCase(getProperty(colnames[i]))+"}");
				}else{
					sb.append(colnames[i]+"="+"#{"+firstLowerCase(getProperty(colnames[i]))+"},");
				}
			}
		}
		
		sb.append("\n\twhere "+idName+" = #{"+firstLowerCase(getProperty(idName))+"}\r\n");
		sb.append("</update>\r\n");
		sb.append("</mapper>");
	}



	private void processSave(StringBuffer sb) {
		sb.append("<insert id=\"save\" parameterType=\""+entityname+"\" >\r\n");
		sb.append("\tinsert into sports."+tablename+" (");
		for (int i = 0; i < colnames.length; i++) {
			if(i==colnames.length-1){
				sb.append(colnames[i]);
			}else{
				sb.append(colnames[i]+",");
			}
			
		}
		sb.append("\n)\r\n values (");
		for (int i = 0; i < colnames.length; i++) {
			if(i==colnames.length-1){
				sb.append("#{"+firstLowerCase(getProperty(colnames[i]))+"}");
			}else{
				sb.append("#{"+firstLowerCase(getProperty(colnames[i]))+"},");
			}
			
		}
		sb.append("\n)\r\n");
		sb.append("\t\t<selectKey keyProperty=\""+firstLowerCase(getProperty(idName))+"\" resultType=\"Long\" statementType=\"PREPARED\">\r\n");
		sb.append("\t\t\tselect LAST_INSERT_ID() as value\r\n");
		sb.append("\t\t</selectKey>\r\n");
		sb.append("</insert>\r\n");
			
	  
 
	}



	private void processDelete(StringBuffer sb) {
		sb.append("<delete id=\"delete\" parameterType=\""+entityname+"\" >\r\n");
		sb.append("\t delete from sports."+tablename+"\r\n");
		sb.append("\twhere  "+idName+" = #{"+firstLowerCase(getProperty(idName))+"}\r\n");
		sb.append("</delete>\r\n");		
	}

	 private void processQueryPage(StringBuffer sb) {
		    sb.append("<select id=\"queryPage\" resultMap=\"" + firstLowerCase(entityname) + "Result\">\r\n");
			sb.append("\t <include refid=\"commonSelect\"/>\r\n");
			sb.append("\torder by "+getAlias()+"."+idName+" asc\r\n");
			sb.append("</select>\r\n");
	}


	
	private void processGetById(StringBuffer sb) {
		
		sb.append("<select id=\"getById\" resultMap=\"" + firstLowerCase(entityname) + "Result\" parameterType=\"java.lang.Integer\" >\r\n");
		sb.append("\t<include refid=\"commonSelect\"/>\r\n");
		sb.append("\twhere "+getAlias()+". "+idName+" = #{"+firstLowerCase(getProperty(idName))+"}\r\n");
		sb.append("</select>\r\n");
		    
		 
	}

	
	private String getAlias(){
		String alias = tablename.charAt(0)+"";
		return alias.toLowerCase();
	}


	private void processCommonSelect(StringBuffer sb) {
		sb.append("<sql id=\"commonSelect\" >\r\n");
		sb.append("\t\tselect");
		for (int i = 0; i < colnames.length; i++) {
			if(i==colnames.length-1){
				sb.append(" "+getAlias()+"."+colnames[i]+"\r\n");
			}else{
				sb.append(" "+getAlias()+"."+colnames[i]+",");
			}
				
		}
		
		sb.append("\t\tfrom sports."+tablename+" "+getAlias()+"\r\n");
		sb.append("</sql>\r\n");
		
	}



	private void processResultMap(StringBuffer sb) {
		sb.append("\t<resultMap id=\"" + firstLowerCase(entityname) + "Result\" type=\""
				+ entityname + "\" >\r\n");
		for (int i = 0; i < colnames.length; i++) {
			if (colnames[i].equalsIgnoreCase("id")) {
				sb.append("\t<id column=\"" + colnames[i] + "\" property=\""
						+ firstLowerCase(getProperty(colnames[i])) + "\" />\r\n");
			} else {
				sb.append("\t<result column=\"" + colnames[i] + "\" property=\""
						+ firstLowerCase(getProperty(colnames[i])) + "\" />\r\n");
			}
		}
		sb.append("\t</resultMap>\r\n");

	}



	/**
	 * 功能：生成所有属性
	 * 
	 * @param sb
	 */
	private void processAllAttrs(StringBuffer sb) {

		for (int i = 0; i < colnames.length; i++) {
			if(!colnames[i].equalsIgnoreCase("id")){
				sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " "
						+ firstLowerCase(getProperty(colnames[i])) + ";\r\n");
			}
			
		}

	}

	/**
	 * 功能：生成所有方法
	 * 
	 * @param sb
	 */
	private void processAllMethod(StringBuffer sb) {

		for (int i = 0; i < colnames.length; i++) {
			sb.append("\tpublic void set" + firstUpperCase(getProperty(colnames[i])) + "("
					+ sqlType2JavaType(colTypes[i]) + " "
					+ firstLowerCase((colnames[i])) + "){\r\n");
			sb.append("\tthis." + firstLowerCase(getProperty(colnames[i])) + "="
					+ firstLowerCase(getProperty(colnames[i])) + ";\r\n");
			sb.append("\t}\r\n");
			sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get"
					+ firstUpperCase(getProperty(colnames[i])) + "(){\r\n");
			sb.append("\t\treturn " + firstLowerCase(getProperty(colnames[i])) + ";\r\n");
			sb.append("\t}\r\n");
		}

	}

	/**
	 * 功能：将输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	private static String firstUpperCase(String str) {

		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}

		return new String(ch);
	}
	
	
	
	
	/**
	 * 功能：将输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	private String anyLowerCase(String str) {

		char[] ch = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		for(int index=0;index<ch.length;index++){
			if (ch[index] >= 'A' && ch[index] <= 'Z') {
				if(index==0){
					sb.append((ch[index] + 32));
				}else{
					sb.append("_"+(ch[index] + 32));
				}
				
			}else{
				sb.append(ch[index]);
			}
		}
		

		return sb.toString();
	}
	
	private static String alllowerCase(String str) {

		char[] ch = str.toCharArray();
		for(int index=0;index<ch.length;index++){
			if (ch[index] >= 'A' && ch[index] <= 'Z') {
				ch[index] = (char) (ch[index] + 32);
			}else{
				ch[index] = ch[index];
			}
		}
		

		return new String(ch);
	}
	
	
	

	/**
	 * 功能：将输入字符串的首字母改成小写
	 * 
	 * @param str
	 * @return
	 */
	private String firstLowerCase(String str) {

		char[] ch = str.toCharArray();
		if (ch[0] >= 'A' && ch[0] <= 'Z') {
			ch[0] = (char) (ch[0] + 32);
		}

		return new String(ch);
	}
	
	/**
	 * 功能：将输入字符串的首字母改成小写
	 * 
	 * @param str
	 * @return
	 */
	private static  String getProperty(String str) {

		String[] _s = str.split("_");
		StringBuffer sb = new StringBuffer();
		for(int index=0;index<_s.length;index++){
			if(index==0){
				sb.append(alllowerCase(_s[index]));
			}else{
				sb.append(firstUpperCase(alllowerCase(_s[index])));
			}
		}
		return sb.toString();
	}

	/**
	 * 功能：获得列的数据类型
	 * 
	 * @param sqlType
	 * @return
	 */
	private String sqlType2JavaType(String sqlType) {

		if (sqlType.equalsIgnoreCase("bit")) {
			return "Boolean";
		} else if (sqlType.equalsIgnoreCase("tinyint")) {
			return "Byte";
		} else if (sqlType.equalsIgnoreCase("smallint")) {
			return "Short";
		} else if (sqlType.equalsIgnoreCase("int")) {
			return "Integer";
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "Long";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "Float";
		} else if (sqlType.equalsIgnoreCase("decimal")
				|| sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("real")
				|| sqlType.equalsIgnoreCase("money")
				|| sqlType.equalsIgnoreCase("smallmoney")) {
			return "Double";
		} else if (sqlType.equalsIgnoreCase("varchar")
				|| sqlType.equalsIgnoreCase("char")
				|| sqlType.equalsIgnoreCase("nvarchar")
				|| sqlType.equalsIgnoreCase("nchar")
				|| sqlType.equalsIgnoreCase("text")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("datetime")) {
			return "Date";
		} else if (sqlType.equalsIgnoreCase("image")) {
			return "Blod";
		}

		return null;
	}

	/**
	 * 出口 TODO
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
//System.out.println(getProperty("USER_ID"));
		new MybatisXmlHelper();

	}

}