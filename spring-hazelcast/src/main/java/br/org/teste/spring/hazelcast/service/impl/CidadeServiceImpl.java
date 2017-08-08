package br.org.teste.spring.hazelcast.service.impl;

import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;

import br.org.teste.spring.hazelcast.model.Cidade;
import br.org.teste.spring.hazelcast.service.CidadeService;

@Service
public class CidadeServiceImpl implements CidadeService {

	private Logger logger = LogManager.getLogger();

	@Autowired
	private HazelcastInstance hazelcastInstance;

	@Override
	public void incluirCidade(Cidade cidade) {

		logger.debug("[CIDADE-SERVICE-IMPL][incluirCidade] Iniciando Processo de Inclusao de Cidade - Cidade ["+ cidade.getNome() +"], Populacao ["+ cidade.getPopulacao() +"]");

		IMap<String, Cidade> map = hazelcastInstance.getMap("cidades");
		map.put(cidade.getNome(), cidade);

		logger.debug("[CIDADE-SERVICE-IMPL][incluirCidade] Finalizado Processo de Inclusao de Cidade - Cidade ["+ cidade.getNome() +"], Populacao ["+ cidade.getPopulacao() +"], Map ["+ map +"]");
	}


	/** O Predicate somente irá funcionar sem esforços quando subir o client do hazelcast com o servidor,
	 * no caso deste projeto eu estou subindo o server separado do client. Por isso o JAR que contém o model do projeto
	 * deverá ser adicionado ao classpath do hazelcast. A alteração pode ser feita no arquivo start.sh.
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List<Cidade> consultarCidades() {

		IMap<String, Cidade> map = hazelcastInstance.getMap("cidades");

		logger.debug("[CIDADE-SERVICE-IMPL][consultarCidades] Iniciando Processo de Consulta de Cidades, Cache ["+ map +"]");

		EntryObject e = new PredicateBuilder().getEntryObject();
		Predicate predicate = e.get("populacao").lessThan( 200 );

		Collection<Cidade> cidades = map.values(predicate);

		logger.debug("[CIDADE-SERVICE-IMPL][consultarCidades] Finalizado Processo de Consulta de Cidades, Cidades ["+ cidades +"]");

		return (List<Cidade>) cidades;
	}
}
