package DOM;

import java.util.Calendar;


public class Tempo {
	
	private int mes,dia,ano,hora,minuto,segundo;
	private int mesCorrente,diaCorrente,anoCorrente,horaCorrente,minutoCorrente,segundoCorrente;
	
	public Tempo(){
		this.mes = 0;
		this.ano = 0;
		this.dia = 0;
		this.hora = 0;
		this.minuto = 0;
		this.segundo = 0;
		this.mesCorrente = 0;
		this.diaCorrente = 0;
		this.anoCorrente = 0;
		this.horaCorrente = 0;
		this.minutoCorrente = 0;
		this.segundoCorrente = 0;
		data();

	}

	
	public void data(){
		Calendar cal = Calendar.getInstance();
		//devolve 1 porque e o primeiro dia da semana
//		System.out.println(Calendar.SUNDAY);
		diaCorrente = cal.get(Calendar.DAY_OF_WEEK);
		mesCorrente = cal.get(Calendar.MONTH)+1;
		anoCorrente = cal.get(Calendar.YEAR);

		horaCorrente = cal.get(Calendar.HOUR_OF_DAY);
		minutoCorrente = cal.get(Calendar.MINUTE);
		segundoCorrente = cal.get(Calendar.SECOND);
	}
	
	public void modificaData(int dia, int mes, int ano){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, ano);
		cal.set(Calendar.MONTH, mes-1);
		cal.set(Calendar.DAY_OF_MONTH, dia);
		this.mes = cal.get(Calendar.MONTH);
		this.dia = cal.get(Calendar.DAY_OF_WEEK);
		this.ano = cal.get(Calendar.YEAR);
	}
	
	public void modificaHora(int horas, int minutos, int segundos){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, horas);
		cal.set(Calendar.MINUTE, minutos);
		if(segundos !=0){
			cal.set(Calendar.SECOND, segundos);
		}
		this.hora = cal.get(Calendar.HOUR_OF_DAY);
		this.minuto = cal.get(Calendar.MINUTE);
		this.segundo = cal.get(Calendar.SECOND);
	}
	
	public int getMes(){
		return this.mes;
	}
	
	public int getAno(){
		return this.ano;
	}
	
	public int getDia(){
		return this.dia;
	}
	
	public int getHora(){
		return this.hora;
	}
	
	public int getMinuto(){
		return this.minuto;
	}
	
	public int getSegundo(){
		return this.segundo;
	}
	
	public int getDiaCorrente(){
		return this.diaCorrente;
	}
	
	public int getMesCorrente(){
		return this.mesCorrente;
	}
	
	public int getAnoCorrente(){
		return this.anoCorrente;
	}
	
	public int getHoraCorrente(){
		return this.horaCorrente;
	}
	
	public int getMinutoCorrente(){
		return this.minutoCorrente;
	}
	
	public int getSegundoCorrente(){
		return this.segundoCorrente;
	}
	
	
	public static void main(String [] args){
		Tempo cenas = new Tempo();
		cenas.data();
//		cenas.modificaData();
		System.out.println("dia: " + cenas.getDia() + ", mes: " + cenas.getMes() + ", ano : " + cenas.getAno());
		System.out.println("dia: " + cenas.getDiaCorrente() + ", mes: " + cenas.getMesCorrente() + ", ano : " + cenas.getAnoCorrente());
	
	}
}
