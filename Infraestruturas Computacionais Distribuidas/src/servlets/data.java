
package servlets;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;

/**
 * @author Porfírio Filipe
 *
 */
public class data {
	
public static String bissexto(int ano) {
	if(Year.of(ano).isLeap())
		 return "bissexto";
	return "não bissexto";
} 


public static LocalDate amanha () {
	return diaSeguinte(LocalDate.now());
}

public static LocalDate ontem () {
	return diaAnterior(LocalDate.now());
}

public static LocalDate diaSeguinte(LocalDate data) {
	return data.plusDays(1);
}

public static LocalDate diaAnterior(LocalDate data) 
{ 
  return data.minusDays(1);
}

public static long diferencaEmDias(LocalDate antes, LocalDate depois) {
  return antes.until(depois, ChronoUnit.DAYS);
}

public static long idade(LocalDate nascimento, LocalDate hoje) {
    return nascimento.until(hoje, ChronoUnit.YEARS);
}

public static String diaSemana(LocalDate data) {
	// The values are numbered following the ISO-8601 standard, from 1 (Monday) to 7 (Sunday)
	switch (data.getDayOfWeek().getValue()) {
	case 1: return "segunda-feira";
	case 2: return "terça-feira";
	case 3: return "quarta-feira";
	case 4: return "quinta-feira";
	case 5: return "sexta-feira";
	case 6: return "sábado";
	case 7: return "domingo";
	}
	return "?";
}

private static String right (String value, int length) {
	return value.substring(value.length() - length);
}

public static String show(LocalDate dt) {
	return right("0" + dt.getDayOfMonth(),2) + "/" + right("0"+(dt.getMonthValue()),2) + "/" + dt.getYear();
}


public static String saber(LocalDate data) {
	return "Tem " +idade(data, LocalDate.now())+" anos de idade, já viveu "+diferencaEmDias(data, LocalDate.now())+" dias, nasceu num(a) "+
	diaSemana(data)+" de um ano "+bissexto(data.getYear())+". \nDia anterior & seguinte: "+show(diaAnterior(data))+" & "+show(diaSeguinte(data))+".";
}

public static String saber(String data) {
	return saber(LocalDate.parse(data));
}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LocalDate today = LocalDate.now();
	    LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
	    LocalDate yesterday = tomorrow.minusDays(2);

	    System.out.println("today: "+today);
	    System.out.println("tomorrow: "+tomorrow);
	    System.out.println("yesterday: "+yesterday);
	    System.out.println(saber(LocalDate.parse("1965-08-09")));

	}

}
