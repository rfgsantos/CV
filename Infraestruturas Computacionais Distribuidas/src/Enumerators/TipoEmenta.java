package Enumerators;

public enum TipoEmenta{
	ALMOCO, JANTAR,FECHADO;
	
	public String toStringementa(TipoEmenta tipo){
		if(tipo == TipoEmenta.JANTAR){
			return "jantar";
		}
		else if(tipo == TipoEmenta.ALMOCO){
			return "almoco";
		}
		else if(tipo == TipoEmenta.FECHADO){
			return "fechado";
		}
		return "debug";
	}
}
