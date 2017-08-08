package br.org.teste.spring.hazelcast.service;

import java.util.List;

import br.org.teste.spring.hazelcast.model.Cidade;

public interface CidadeService {

	public void incluirCidade(Cidade cidade) throws Exception;

	public List<Cidade> consultarCidades() throws Exception;

}