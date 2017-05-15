package br.com.hubfintech.account.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.hubfintech.account.daos.ContaDao;
import br.com.hubfintech.account.daos.TransferenciaDao;
import br.com.hubfintech.account.models.Conta;
import br.com.hubfintech.account.models.StatusTransferencia;
import br.com.hubfintech.account.models.TipoConta;
import br.com.hubfintech.account.models.Transferencia;

@Controller
@RequestMapping("/transferencia")
public class TransferenciaController {

	@Autowired
	private TransferenciaDao daoTransferencia;
	
	@Autowired
	private ContaDao daoConta;
	
	@RequestMapping(value="/novaTransferencia", method=RequestMethod.GET)
	public ModelAndView form(Transferencia trans){
		List<Conta> contas = daoConta.listar();
		ModelAndView view = new ModelAndView("transferencia/cadastroTransferencia");
		view.addObject("todasContas", contas);
		return view;
	}
	
	@RequestMapping(value="addTransferencia", method=RequestMethod.POST)
	public ModelAndView adicionaTransferencia(String contaDebito, String contaCredito, 
				@Valid Transferencia transferencia, BindingResult result){
		
		transferencia.setData(Calendar.getInstance());
		transferencia.setStatus(StatusTransferencia.EFETUADO);
		transferencia.setContaDebito(daoConta.listarById(Long.parseLong(contaDebito.replace(",", ""))));
		transferencia.setContaCredito(daoConta.listarById(Long.parseLong(contaCredito.replace(",", ""))));
		
		adicionaAporteSeForMatriz(transferencia);
		
		daoTransferencia.gravarOrUpdate(transferencia);
		return new ModelAndView("redirect:transferenciaList");
	}


	@RequestMapping(value="transferenciaList", method=RequestMethod.GET)
	public ModelAndView listar(){
		List<Transferencia> translist = daoTransferencia.listar();
		ModelAndView view = new ModelAndView("transferencia/listaTrans");
		view.addObject("transferencias", translist);
		return view;
	}
	
	@RequestMapping("/estorno/{id}")
	public ModelAndView estorno(@PathVariable("id") Integer id, String aport){
		ModelAndView view = new ModelAndView("redirect:/transferencia/transferenciaList");
		
	    Transferencia trans = daoTransferencia.listarById(id);
	    
	    if(trans.getContaCredito().getTipoConta().equals(TipoConta.MATRIZ)){
	    	validaCodigoAportAndGrava(aport, view, trans);
	    }else{
	    	trans.setStatus(StatusTransferencia.ESTORNADO);
	    	daoTransferencia.gravarOrUpdate(trans);
	    }
	    System.out.println(view.getViewName());
	    return view;
	}
	
	/**Valida se o codigo aport recebido é valido
	 * 
	 * @param aport - codigo recebido por pelo Modal
	 * @param view - adicionado uma infrmação que não foi estornado
	 * @param trans - Salva ou não o Storno
	 */
	private void validaCodigoAportAndGrava(String aport, ModelAndView view,
			Transferencia trans) {
		if(aport.replace(",", "").equals(trans.getAporte())){
			trans.setStatus(StatusTransferencia.ESTORNADO);
			daoTransferencia.gravarOrUpdate(trans);
		}
		else{
			System.out.println("Passou memo");
			view.addObject("codigoInvalido", "Insira um codigo valido de Aport");
		}
	}
	
	/**
	 * 
	 * @param transferencia
	 */
	private void adicionaAporteSeForMatriz(Transferencia transferencia) {
		if(transferencia.getContaCredito().getTipoConta().equals(TipoConta.MATRIZ)){
			transferencia.setAporte(adicionaAport());
		}
		else
			transferencia.setAporte(null);
		
	}
	/**Aport Aleatório
	 * 
	 * Seleciona 3 numeros randomicos do array Letras
	 * Recupera o 4 numeros do Time
	 * Seleciona 3 numeros randomicos do array Letras 
	 * 
	 * @return
	 */
	private String adicionaAport() {
		Random ran = new Random();
		String[] letras ={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j",  
				"k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D"  
				,"E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W",  
				"X","Y","Z"};
		StringBuffer aportGerado = new StringBuffer();
		
		for ( int i = 0; i < 3; i++){    
	          int a = ran.nextInt(letras.length);
	          aportGerado.append(letras[a]);    
	        } 
		Long time = new Date().getTime();
		String timeDate = time.toString();
		aportGerado.append(timeDate.substring(4,8));
		
		for ( int i = 0; i < 3; i++){    
	          int a = ran.nextInt(letras.length);
	          aportGerado.append(letras[a]);    
	    } 
		
		return aportGerado.toString();
	}
	
}
