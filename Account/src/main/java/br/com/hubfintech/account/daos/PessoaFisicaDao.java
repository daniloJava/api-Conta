package br.com.hubfintech.account.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.hubfintech.account.models.PessoaFisica;

@Repository
@Transactional
public class PessoaFisicaDao {

	@PersistenceContext
	private EntityManager manager;
	
	public void gravarOrUpdate(PessoaFisica fisica){
		if (fisica.getId() == null)
			manager.persist(fisica);
		else
			manager.merge(fisica);
	}
	
	public List<PessoaFisica> listar(){
		return manager.createQuery("select pf from PessoaFisica pf", PessoaFisica.class).getResultList();
	}
	
	public PessoaFisica listarById(Long pessoaId) {
		return manager.find(PessoaFisica.class, pessoaId);
	}
	
}
