package Enumerators;

public enum ModoPrato {
	FDSFERIADO, DIASEMANA;
	
	
	public String toStringModo(ModoPrato modo){
		
		if(modo == ModoPrato.DIASEMANA){
			return "diasemana";
		}
		else if(modo == ModoPrato.FDSFERIADO){
			return "fdsferiado";
		}
		
		return "cenas";
	}
}
