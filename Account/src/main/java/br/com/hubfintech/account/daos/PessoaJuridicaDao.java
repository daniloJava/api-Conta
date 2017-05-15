package br.com.hubfintech.account.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.hubfintech.account.models.PessoaJuridica;

@Repository
@Transactional
public class PessoaJuridicaDao {

	@PersistenceContext
	private EntityManager manager;
	
	public void gravarOrUpdate(PessoaJuridica juridica){
		if (juridica.getId() == null)
			manager.persist(juridica);
		else
			manager.merge(juridica);
	}
	
	public List<PessoaJuridica> listar(){
		return manager.createQuery("select pj from PessoaJuridica pj", PessoaJuridica.class).getResultList();
	}
	
	public PessoaJuridica listarById(Long pessoaId) {
		return manager.find(PessoaJuridica.class, pessoaId);
	}
}
