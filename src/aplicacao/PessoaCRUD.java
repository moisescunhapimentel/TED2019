package aplicacao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class PessoaCRUD {

	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;

	public void inicializarConexao() {
		entityManagerFactory = Persistence.createEntityManagerFactory("exemplo-jpa");
		entityManager = entityManagerFactory.createEntityManager();
	}

	public EntityTransaction obterTransacao() {
		return entityManager.getTransaction();
	}

	public void fecharConecoesEntity() {
		entityManager.close();
		entityManagerFactory.close();
	}

	public List<Pessoa> listarPessoas() {
			return entityManager.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
	}

	public Pessoa buscarPessoaPorId(Integer id) {
		return entityManager.find(Pessoa.class, id);
	}
	
	public void cadastrarPessoa(Pessoa pessoa) {
		obterTransacao().begin();
		entityManager.persist(pessoa);
		obterTransacao().commit();
	}

	public void deletarPessoa(Pessoa pessoa) {
		obterTransacao().begin();
		entityManager.remove(pessoa);
		obterTransacao().commit();
	}
}
