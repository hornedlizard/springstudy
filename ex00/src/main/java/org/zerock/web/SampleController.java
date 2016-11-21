package org.zerock.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.ProductVO;

@Controller
public class SampleController {

	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@RequestMapping("doA")				// 메소드가 해당 uri (http://localhost:8080/web/doA) 경로에 해당할 때 메소드 실행
	public void doA(){
		logger.info("doA called.............");
	}
	
	@RequestMapping("doB")
	public void doB(){
		logger.info("doB called.............");
	}
	
	@RequestMapping("doC")
	public String doC( @ModelAttribute("msg") String msg){
										// 메소드 내의 파라미터에 사용된  @ModelAttribute("msg")는 request 시 'msg' 이름의 파라미터를 문자열로 처리해 뷰에 전달
										// url로  파라미터 전달 (http://localhost:8080/web/doC?msg=message)
		logger.info("doC called.............");
		
		return "result";				// /WEB-INF/views/result.jsp 파일을 실행
	}
	
	@RequestMapping("/doD")
	public String doD(Model model){		// Model 클래스를 파라미터로 사용
										// Model : 스프링mvc에서 기본 제공 (뷰에 원하는 데이터를 전달하는 일종의 컨테이너)
		//make sample data
		ProductVO product = new ProductVO("Sample Product", 10000);
		
		logger.info("doD");
		
		model.addAttribute(product);	// addAttributes("이름",객체) : 객체에 특별한 이름을 븉여 뷰에서 이름값을 이용하여 객체 처리
										// addAtrributes(객체) : 이름을 지정하지 않으면 자동적으로 첫자를 소문자로 처리한 클래스명을 이름으로 처리 
		return "productDetail";
	}
	
	@RequestMapping("/doE")
	public String doE(RedirectAttributes rttr){
										// RedirectAttributes 를 파라미터로 사용 시 uri에 데이터가 보이지 않음
		logger.info("doE called but redirect to /doF..........");
		
		rttr.addFlashAttribute("msg", "This is the Message!! with redirected");
										// 리다이렉트 시점에 addFlashAttribute()로 임시 데이터 전달
		return "redirect:doF";			// 다시 브라우저에 '/doF' 를 호출
	}
	
	@RequestMapping("/doF")
	public void doF(String msg){
		logger.info("doF called.............");
	}
	
	@RequestMapping("/doJSON")
	public @ResponseBody ProductVO doJSON(){
		ProductVO vo = new ProductVO("샘플 상품", 30000);
		return vo;
	}
}
