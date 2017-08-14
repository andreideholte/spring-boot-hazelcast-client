package br.org.teste.spring.hazelcast.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.org.teste.spring.hazelcast.model.Cidade;
import br.org.teste.spring.hazelcast.service.CidadeService;

@RestController
public class CidadeController {

	private Logger logger = LogManager.getLogger();

	@Autowired
	private CidadeService cidadeService;

	@RequestMapping(value="/teste-spring-hazelcast/cidades", method = {RequestMethod.POST})
	public ResponseEntity<String> incluirCidades(@RequestBody Cidade cidade){

		logger.info("[CIDADE-CONTROLLER][incluirCidades] Iniciando Processo de Inclusao de Cidade - Cidade ["+ cidade.getNome() +"], Populacao ["+ cidade.getPopulacao() +"]");

		try{
			this.cidadeService.incluirCidade(cidade);

			logger.info("[CIDADE-CONTROLLER][incluirCidades] Finalizado Processo de Inclusao de Cidade - Cidade ["+ cidade.getNome() +"], Populacao ["+ cidade.getPopulacao() +"]");
			return new ResponseEntity<>(HttpStatus.CREATED);

		}catch(Exception e){
			logger.info("[CIDADE-CONTROLLER][incluirCidades] Falha Ocorrida Durante o Processo de Inclusao de Cidade - Cidade ["+ cidade.getNome() +"], Populacao ["+ cidade.getPopulacao() +"], Excecao ["+ e.getMessage() +"]");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value="/teste-spring-hazelcast/cidades", method = {RequestMethod.GET})
	public ResponseEntity<List<Cidade>> consultarCidades(){

		logger.info("[CIDADE-CONTROLLER][consultarCidades] Iniciando Processo de Consulta de Cidade");

		try{
			List<Cidade> cidades = this.cidadeService.consultarCidades();

			if(cidades != null && !cidades.isEmpty()){
				logger.info("[CIDADE-CONTROLLER][consultarCidades] Finalizado Processo de Consulta de Cidade - Cidades ["+ cidades.size() +"]");
				return new ResponseEntity<List<Cidade>>(cidades, HttpStatus.OK);

			}else{
				logger.info("[CIDADE-CONTROLLER][consultarCidades] Nenhum Resultado Encontrado Para a Consulta de Cidade - Cidade ["+ cidades.size() +"]");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		}catch(Exception e){
			logger.error("[CIDADE-CONTROLLER][consultarCidades] Falha Ocorrida Durante o Processo de Consulta de Cidade, Excecao ["+ e.getMessage() +"]");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
