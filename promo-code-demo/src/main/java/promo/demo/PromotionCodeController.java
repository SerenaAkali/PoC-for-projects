package promo.demo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/code")
public class PromotionCodeController {
	
	@GetMapping("/findById/{id}")
	public String getPromotionCodeDetails(@PathVariable("id") String codeId, Model m) {
		RestTemplate rest = new RestTemplate();
		PromotionCode p = rest.getForObject("http://localhost:8086/promocode?codeid=" + codeId, PromotionCode.class);
		System.out.println("retrieved code details by id");
		m.addAttribute("code", p);
		return "details";
	}
	
	@PostMapping("/update")
	public String updatePromotionCode(@RequestBody PromotionCode code) {
		RestTemplate rest = new RestTemplate();
		PromotionCode tmp = null;
		if(code.getCodeId() != null) {
			tmp = rest.getForObject("http://localhost:8086/promocode?codeid=" + code.getCodeId(), PromotionCode.class);
			System.out.println("retrieved code details if id existed");
		} else {
			tmp = new PromotionCode();
			tmp.setCodeNumber(8);
			tmp.setDueDate(LocalDateTime.now().plusDays(10));
		}
		rest.put("http://localhost:8086/promocode?codeid=" + tmp.getCodeId(), tmp);
		return "details";
	}
	
	@DeleteMapping("delete/{id}")
	public String deletePromotionCode(@PathVariable("id") String codeId, Model m) {
		RestTemplate rest = new RestTemplate();
		rest.delete("http://localhost:8086/promocode?codeid=" + codeId, PromotionCode.class);
		System.out.println("delete code by id");
		return "index";
	}
}
