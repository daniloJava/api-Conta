package br.com.hubfintech.account.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.hubfintech.account.daos.ContaDao;
import br.com.hubfintech.account.daos.PessoaFisicaDao;
import br.com.hubfintech.account.daos.PessoaJuridicaDao;
import br.com.hubfintech.account.models.Conta;
import br.com.hubfintech.account.models.PessoaFisica;
import br.com.hubfintech.account.models.PessoaJuridica;
import br.com.hubfintech.account.validation.PessoaFisicaValidation;
import br.com.hubfintech.account.validation.PessoaJuridicaValidation;
import br.com.hubfintech.account.validation.PessoaValidation;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaJuridicaDao daoJuridico;
	
	@Autowired
	private PessoaFisicaDao daoFisica;
	
	@Autowired
	private ContaDao daoConta;

	private ModelAndView viewAddPessoa = new ModelAndView("pessoa/cadastroPessoa");

	@InitBinder("pessoaFisica")
	public void initFisicaBinder(WebDataBinder binder){
		binder.setDisallowedFields("conta");
		binder.addValidators(new PessoaFisicaValidation());
	}
	
	@InitBinder("pessoaJuridica")
	public void initJuridicaBinder(WebDataBinder binder){
		binder.setDisallowedFields("conta");
		binder.addValidators(new PessoaJuridicaValidation());
	}
	
	@InitBinder("pessoa")
	public void initPessoaBinder(WebDataBinder binder){
		binder.setDisallowedFields("conta");
		binder.addValidators(new PessoaValidation());
	}
	
	@RequestMapping("/novaPessoa")
	public ModelAndView form(PessoaFisica pessoaFisica, PessoaJuridica pessoaJuridica){
		List<Conta> contas = daoConta.listar();
		
		viewAddPessoa.addObject("todasContas", contas);
		
		return viewAddPessoa;
	}
	
	@RequestMapping(value="addPessoaFisica", method=RequestMethod.POST)
	public ModelAndView adicionarPf(@RequestParam String conta, 
			@Valid PessoaFisica pessoaFisica, BindingResult resultFisica,
			PessoaJuridica pessoaJuridica, BindingResult resultJuridica,
			RedirectAttributes redirectAttributes) {
		
		ModelAndView view = new ModelAndView();
		pessoaFisica.setConta(daoConta.listarById(Long.parseLong(conta.replace(",", ""))));//Gambiarra para armazenar a conta.
		
		if(resultFisica.hasErrors()){
			viewAddPessoa.addObject("visivelFisica", "Fisica");
			return form(pessoaFisica, pessoaJuridica);
		}
		
		daoFisica.gravarOrUpdate(pessoaFisica);
		
		view.setViewName("redirect:pessoaList");
		return view;
	}
	
	@RequestMapping(value="addPessoaJuridica", method=RequestMethod.POST)
	public ModelAndView adicionarPj(@RequestParam String conta, 
			@Valid PessoaJuridica pessoaJuridica, BindingResult result,
			PessoaFisica pessoaFisica, BindingResult resultFisica,
			RedirectAttributes redirectAttributes) {
		
		pessoaJuridica.setConta(daoConta.listarById(
				Long.parseLong(conta.replace(",", ""))));//Gambiarra para armazenar a conta.
		
		if(result.hasErrors()){
			return form(pessoaFisica, pessoaJuridica);
		}
		
		daoJuridico.gravarOrUpdate(pessoaJuridica);
		ModelAndView view = new ModelAndView("redirect:pessoaList");
		return view;
	}
	
	@RequestMapping(value="pessoaList", method=RequestMethod.GET)
	public ModelAndView listar(){
		List<PessoaFisica> pfs = daoFisica.listar();
		List<PessoaJuridica> pjs = daoJuridico.listar();
		
		
		ModelAndView view = new ModelAndView("pessoa/listaPessoa");
		view.addObject("pessoasFisica", pfs);
		view.addObject("pessoasJuridica", pjs);
		
		
		return view;
	}
	
}
