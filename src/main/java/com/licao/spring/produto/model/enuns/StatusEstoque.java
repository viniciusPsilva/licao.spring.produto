package com.licao.spring.produto.model.enuns;

public enum StatusEstoque {
	
	OK("ok"),
	NAMEDIA("na media"),
	BAIXO("baixo");
	
	private String status;

	private StatusEstoque(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
}
