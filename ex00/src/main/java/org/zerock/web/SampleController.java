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
	
	@RequestMapping("doA")				// �޼ҵ尡 �ش� uri (http://localhost:8080/web/doA) ��ο� �ش��� �� �޼ҵ� ����
	public void doA(){
		logger.info("doA called.............");
	}
	
	@RequestMapping("doB")
	public void doB(){
		logger.info("doB called.............");
	}
	
	@RequestMapping("doC")
	public String doC( @ModelAttribute("msg") String msg){
										// �޼ҵ� ���� �Ķ���Ϳ� ����  @ModelAttribute("msg")�� request �� 'msg' �̸��� �Ķ���͸� ���ڿ��� ó���� �信 ����
										// url��  �Ķ���� ���� (http://localhost:8080/web/doC?msg=message)
		logger.info("doC called.............");
		
		return "result";				// /WEB-INF/views/result.jsp ������ ����
	}
	
	@RequestMapping("/doD")
	public String doD(Model model){		// Model Ŭ������ �Ķ���ͷ� ���
										// Model : ������mvc���� �⺻ ���� (�信 ���ϴ� �����͸� �����ϴ� ������ �����̳�)
		//make sample data
		ProductVO product = new ProductVO("Sample Product", 10000);
		
		logger.info("doD");
		
		model.addAttribute(product);	// addAttributes("�̸�",��ü) : ��ü�� Ư���� �̸��� �s�� �信�� �̸����� �̿��Ͽ� ��ü ó��
										// addAtrributes(��ü) : �̸��� �������� ������ �ڵ������� ù�ڸ� �ҹ��ڷ� ó���� Ŭ�������� �̸����� ó�� 
		return "productDetail";
	}
	
	@RequestMapping("/doE")
	public String doE(RedirectAttributes rttr){
										// RedirectAttributes �� �Ķ���ͷ� ��� �� uri�� �����Ͱ� ������ ����
		logger.info("doE called but redirect to /doF..........");
		
		rttr.addFlashAttribute("msg", "This is the Message!! with redirected");
										// �����̷�Ʈ ������ addFlashAttribute()�� �ӽ� ������ ����
		return "redirect:doF";			// �ٽ� �������� '/doF' �� ȣ��
	}
	
	@RequestMapping("/doF")
	public void doF(String msg){
		logger.info("doF called.............");
	}
	
	@RequestMapping("/doJSON")
	public @ResponseBody ProductVO doJSON(){
		ProductVO vo = new ProductVO("���� ��ǰ", 30000);
		return vo;
	}
}
