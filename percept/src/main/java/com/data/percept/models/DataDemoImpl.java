package com.data.percept.models;

import java.util.List;

public class DataDemoImpl {
    	private String respostaXml;
	
	private boolean sucessoRequisicao;
	
	public DataDemoImpl(String resposta) {
		this.respostaXml = resposta;
		if (resposta == null) {
			sucessoRequisicao = false;
		} else {
			sucessoRequisicao = true;
		}
	}

	public List<String> getIds(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	public String get(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean sucesso(){
		return sucessoRequisicao;
	}
	
	public String getRespostaXml(){
		return this.respostaXml;
	}
}
