package br.com.hubfintech.account.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.hubfintech.account.models.Transferencia;

@Repository
@Transactional
public class TransferenciaDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void gravarOrUpdate(Transferencia trans){
		if (trans.getId() == null)
			manager.persist(trans);
		else
			manager.merge(trans);
	}
	
	public List<Transferencia> listar(){
		return manager.createQuery("select t from Transferencia t", Transferencia.class).getResultList();
	}
	
	public Transferencia listarById(Integer trasnfId) {
		return manager.find(Transferencia.class, trasnfId);
	}
	
}
