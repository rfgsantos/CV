package Enumerators;

public enum TipoPrato {
	ENTRADA, PEIXE, CARNE, FRUTA, DOCE;
	
	
	public String toString(TipoPrato tipo){
		
		switch(tipo){
			case CARNE:
				return "carne";
			case PEIXE:
				return "peixe";
			case ENTRADA:
				return "entrada";
			case FRUTA:
				return "fruta";
			case DOCE:
				return "doce";
		}
		return null;
	}
	
}


