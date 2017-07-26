
using UnityEngine;

public class enemyHealth: MonoBehaviour {
	public float health = 50.0f;
	// Use this for initialization
	ParticleSystem ps;
	private int scoreValue;
	private gameManager manager;
	UnityEngine.AI.NavMeshAgent nav;
	private Animator animator;
	private AudioSource[] audio;

	public void Awake(){
		ps = GetComponentInChildren<ParticleSystem>();
		setEnemyHealth ();
		manager = GameObject.Find ("gameManager").GetComponent <gameManager>();
		nav = GetComponent<UnityEngine.AI.NavMeshAgent> ();
		animator = GetComponent<Animator> ();
		audio = GetComponents<AudioSource> ();
	}

	public void takeDamage(float damage, Vector3 hitpoint){

		ps.Stop();
		ps.transform.position = hitpoint;
		health -= damage;
		ps.Play ();

		if(health <=0f){
			Die ();
		}
	}

	public void Die(){
		manager.updateScore(scoreValue);
		//de forma a não dar scores errados, e erros, visto que uma vez morto, não  é necesário ser
		//shootable
		this.gameObject.layer = 0;
		//disable AI - deixa de perseguir o player
		nav.isStopped = true;
		//animação
		animator.SetTrigger("isDead");
		//som
		audio[2].Play();
		//destroi objecto com delay 2 segundos - tempo da animação -
		Destroy(this.gameObject,2f);


	}

	private void setEnemyHealth(){
		switch(gameObject.name){
		case "zombieSlow":
			scoreValue = 10;
			break;
		case "zombieAxe":
			scoreValue = 20;
			break;
		case "zombieFast":
			scoreValue = 30;
			break;
		default:
			scoreValue = 40;
			break;
		}
	}

}
