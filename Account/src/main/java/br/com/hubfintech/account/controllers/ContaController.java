package br.com.hubfintech.account.controllers;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.hubfintech.account.daos.ContaDao;
import br.com.hubfintech.account.models.Conta;
import br.com.hubfintech.account.validation.ContaValidation;

@Controller
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
	private ContaDao contaDao;

	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new ContaValidation());
		
	}
	
	@RequestMapping("/novaConta")
	public ModelAndView form(Conta conta) {
		ModelAndView view = new ModelAndView("conta/cadastroConta");
		List<Conta> contas = contaDao.listar();
		view.addObject("todasContas", contas);
		
		return view;
	}
	
	@RequestMapping(value="addConta", method=RequestMethod.POST)
	public ModelAndView gravar(@Valid  Conta conta, BindingResult result,
			RedirectAttributes redirectAttributes) {
		
		conta.setDataCriacao(Calendar.getInstance());
		if(result.hasErrors()){
			return form(conta);
		}
		
		contaDao.gravarOrUpdate(conta);
		
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
		return new ModelAndView("redirect:contaList");
	}
		
	@RequestMapping(value="contaList", method=RequestMethod.GET)
	public ModelAndView listar(){
		List<Conta> contas = contaDao.listar();
		ModelAndView view = new ModelAndView("conta/listaConta");
		view.addObject("contas", contas);
		
		return view;
	}

}
