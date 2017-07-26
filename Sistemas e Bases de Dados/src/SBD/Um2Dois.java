package SBD;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Um2Dois {
	// Sabemos à partida que apenas são 2 threads,
	// logo atribuimos respectivamente Thread1 = 1, Thread2 = 2
	// portanto todas as variaveis respetivas a cada thread têm o seu ID como referencia.
	
	// Semaforo que permite apanhar o objecto novo introduzido ou bloquear quando não existe novo objecto 
	private Semaphore s1,s2;
	
	// utilizas arraylist porque é dinamica e mais versatil
	private ArrayList<Object> t1,t2;
	
	public Um2Dois() {
		// TODO Auto-generated constructor stub
		t1 = new ArrayList<Object>();
		t2 = new ArrayList<Object>();
		s1 = new Semaphore(0);
		s2 = new Semaphore(0);
	}
	
	public void put(Object o){
		//cada vez que adicionas um objecto numa arraylist ele fica na ultima posicao
		t1.add(o);
		t2.add(o);
		// o semaforo apenas dá permissão de acquire igual ao numero de objectos introduzidos
		s1.release();
		s2.release();
		
	}
	
	public Object get(int idThread){
		
		//variavel auxiliar que guarda o valor a ser devolvido pela função
		Object o1, o2;
		o1 = null;o2 = null;
		
		if(idThread == 1){
			try {
				// consegue apenas dar acquire quando existe elemento
				s1.acquire();
				//o objecto na posicao 0 é sempre o menos recente
				o1 = t1.get(0);
				// removes o elemento para garantir que a thread não apanha esse valor outra vez
				t1.remove(0);
				return o1;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(idThread == 2){
			try {
				// consegue apenas dar acquire quando existe elemento
				s2.acquire();
				//o objecto na posicao 0 é sempre o menos recente
				o2 = t2.get(0);
				// removes o elemento para garantir que a thread não apanha esse valor outra vez
				t2.remove(0);
				return o2;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return null;
	}

}
