package com.jncompany;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jncompany.ClienParse.CompareCntDesc;

public class PpomParse {

	static String baseUrl = "http://www.ppomppu.co.kr/zboard/";
	static String parseUrl = "http://www.ppomppu.co.kr/zboard/zboard.php?id=freeboard&page=";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PpomParse ps = new PpomParse();
		
		List<ItemVo> bbsList = new ArrayList<ItemVo>() ;
		
		try {
			
			
			//2페이지 까지 추출..
			for(int i=1; i<=2; i++){
				Document doc = Jsoup.connect(parseUrl+i).get(); 
				Elements contents = doc.select("tr.list1");
				
				//Contents Parse
				List<ItemVo> bbsList1 = ps.getParseElement(contents);
				bbsList.addAll(bbsList1);	
			}
			
			//리스트 소트(cnt 역순)
			Collections.sort(bbsList,new CompareCntDesc());
			int cRow = 1;
			for (ItemVo bbs : bbsList) {
				
				//10건만 출력
				if(cRow >= 10 ){
					break;
				}
				
				Document detailDoc = Jsoup.connect(bbs.getLink()).get();
				// 웹에서 내용을 가져온다.
				Elements detailContents = detailDoc.select("tr td.board-contents");
				for (Element detailEl : detailContents) {

					String imgUrl = detailEl.getElementsByClass("attachedImage").select("img").eq(0).attr("src").toString();
					String imgStr = detailEl.getElementsByClass("attachedImage").size() > 0 ? "<첨부이미지> " : "";
					String cont = detailEl.getElementById("writeContents").text().length() > 50
								? detailEl.getElementById("writeContents").text().substring(0, 50) + "..."
								: detailEl.getElementById("writeContents").text();

					bbs.setImgsrc("");//imgUrl
					bbs.setContent("");//imgStr + cont

				}
				
				System.out.println(bbs.toString());
				cRow++;
			}


		} catch (IOException e) { // Jsoup의 connect 부분에서 IOException 오류가 날 수
									// 있으므로 사용한다.
			e.printStackTrace();
		}


	}
	
	//Contents Parse
	private List<ItemVo> getParseElement(Elements contents){
		
		List<ItemVo> bbsList = new ArrayList<ItemVo>() ;
		
		try {
			
			for (Element el : contents) {

				ItemVo bbs = new ItemVo();

				String subject = el.getElementsByTag("td").eq(2).text();
				String detailUrl = baseUrl + el.getElementsByTag("td").eq(2).select("a").attr("href").toString();

				bbs.setSubject(subject);;
				bbs.setLink(detailUrl);

				bbs.setTime(el.getElementsByTag("td").eq(3).text());
				bbs.setCnt(Integer.parseInt(el.getElementsByTag("td").eq(5).text()));
				bbsList.add(bbs);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bbsList;
	}
	
	 /**
     * cnt 으로 내림차순(Desc) 정렬
     * @author Administrator
     *
     */
    static class CompareCntDesc implements Comparator<ItemVo>{
 
        @Override
        public int compare(ItemVo o1, ItemVo o2) {
        	 return o1.getCnt() > o2.getCnt() ? -1 : o1.getCnt() < o2.getCnt() ? 1:0;
        }        
    }

}
