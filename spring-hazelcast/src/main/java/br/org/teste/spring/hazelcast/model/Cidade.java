package br.org.teste.spring.hazelcast.model;

import java.io.IOException;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

public class Cidade implements DataSerializable {

	private String nome;
	private Integer populacao;

	public Cidade() {
	}

	public Cidade(String nome, Integer populacao) {
		this.nome = nome;
		this.populacao = populacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPopulacao() {
		return populacao;
	}

	public void setPopulacao(Integer populacao) {
		this.populacao = populacao;
	}

	@Override
	public String toString() {
		return "Cidade [nome=" + nome + ", populacao="+ populacao +"]";
	}

	@Override
	public void readData(ObjectDataInput in) throws IOException {
		this.nome = in.readUTF();
		this.populacao = (Integer) in.readInt();
	}

	@Override
	public void writeData(ObjectDataOutput out) throws IOException {
		out.writeUTF(nome);
		out.writeInt(populacao);
	}
}