package cen.novel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author cen    2018��5��11������9:43:49
 *
 */
public class GetTxt {
	private static Logger logger=Logger.getLogger(GetTxt.class);
	//baseURL
	public static String baseURL="http://www.xxbiquge.com/";
	
	public static void main(String[] args){
		//������С˵Ŀ��url
		Scanner input=new Scanner(System.in);
		System.out.println("������Ŀ��С˵url:");
		String url=baseURL+input.next();
		//�õ���С˵�������½ڵ�urls
		List<String> urls=new ArrayList<String>();
		try {
			Document doc=Jsoup.connect(url).get();
			   Element et=doc.getElementsByTag("dl").first();
		        Elements es= et.getElementsByTag("a");
		        for(Element e:es){
		        	String url_1=e.attr("href");
		           urls.add(baseURL+url_1);
		        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.debug("Ŀ��С˵���ӳ���,url=="+url,e);
		}
		
	
	//����С˵�½� �õ�С˵content
		try {
			FileWriter fw=null;
			BufferedWriter bw=null;
			for(int i=0;i<urls.size();i++){
			 fw = new FileWriter("E:\\test.txt", true);
			       bw = new BufferedWriter(fw);
			 Document doc=Jsoup.connect(urls.get(i)).get();
			 String title=doc.getElementsByTag("h1").text();
			 bw.write(title);
			 bw.newLine();
			 logger.debug("Ŀ��С˵title,url=="+title);
      String  content=doc.getElementById("content").text();
      //�Զ������
      Pattern pattern1 = Pattern
			       .compile("[ ????]");
 	 String[] strs=pattern1.split(content);
 	 //С˵����
			   bw.write(strs[0].substring(1));
			   bw.newLine();
			   for(int j=1;j<strs.length;j++){
		        		 bw.write(strs[j]);
			        	 bw.newLine();
			        	 bw.flush();
		        }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
