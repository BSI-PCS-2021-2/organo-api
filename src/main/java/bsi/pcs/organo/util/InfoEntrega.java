package bsi.pcs.organo.util;

public enum InfoEntrega {
	ENTREGA("Entrega"), RETIRADA("Retirada"), ENTREGA_E_RETIRADA("Entrega_e_retirada");

	private final String infoEntrega;
	
	private InfoEntrega(String infoEntrega) {
		this.infoEntrega = infoEntrega;
	}
	
	public String getInfoEntrega() {
		return infoEntrega;
	}
}
