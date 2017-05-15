package br.com.hubfintech.account.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.hubfintech.account.models.Conta;

@Repository
@Transactional
public class ContaDao {

	@PersistenceContext
	private EntityManager manager;
	
	public void gravarOrUpdate(Conta conta){
		if (conta.getId() == null)
			manager.persist(conta);
		else
			manager.merge(conta);
	}
	
	public List<Conta> listar(){
		return manager.createQuery("select c from Conta c", Conta.class).getResultList();
	}

	public Conta listarById(Long contaId) {
		return manager.find(Conta.class, contaId);
	}
	
	
}
